package com.taobao.api.internal.ws.push.client;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.taobao.api.Constants;
import com.taobao.api.internal.util.NamedThreadFactory;
import com.taobao.api.internal.ws.jp.a840.websocket.WebSocket;
import com.taobao.api.internal.ws.jp.a840.websocket.WebSockets;
import com.taobao.api.internal.ws.jp.a840.websocket.exception.WebSocketException;
import com.taobao.api.internal.ws.jp.a840.websocket.frame.rfc6455.FrameRfc6455;
import com.taobao.api.internal.ws.jp.a840.websocket.frame.rfc6455.PingFrame;
import com.taobao.api.internal.ws.jp.a840.websocket.impl.WebSocketImpl;
import com.taobao.api.internal.ws.push.DefaultLoggerFactory;
import com.taobao.api.internal.ws.push.Logger;
import com.taobao.api.internal.ws.push.LoggerFactory;
import com.taobao.api.internal.ws.push.messages.MessageIO;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageIO;
import com.taobao.api.internal.ws.push.mqtt.publish.MqttPublishMessage;

public class Client {

	private int maxMessageSize = 1024;
	// heartbeat ping idle
	private int maxIdle = 60000;
	private int connectTimeoutSecond = 5;

	private LoggerFactory loggerFactory;
	private Logger logger;

	private String uri;
	private String protocol;
	private String self;
	private Map<String, String> headers;
	private WebSocket socket;
	private MessageHandler messageHandler;
	private StateHandler stateHandler;
	private ExecutorService executorService;
	private int execThreadCount = 20;

	private ConcurrentLinkedQueue<byte[]> bufferQueue;

	private boolean pingFlag;
	private long pingLast;
	private Thread pingWorker;

	private Exception failure;
	private int reconnectInterval = 10000;
	private int reconnectCount;
	private Timer reconnecTimer;
	private TimerTask reconnecTimerTask;

	public Client(String clientFlag) {
		this(new DefaultLoggerFactory(), clientFlag);
	}

	public Client(LoggerFactory loggerFactory, String clientFlag) {
		this.loggerFactory = loggerFactory;
		this.logger = this.loggerFactory.create(this);

		this.self = clientFlag;
		this.bufferQueue = new ConcurrentLinkedQueue<byte[]>();

		resetExecutorService();
		// necessary?
		this.doReconnect();
	}

	private void resetExecutorService() {
		ExecutorService old = executorService;
		this.executorService = new ThreadPoolExecutor(execThreadCount, (execThreadCount + 50), 60L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3000), new NamedThreadFactory("top-ws-client-execute"));
		if (old != null) {
			old.shutdown();
		}
	}

	public int getExecThreadCount() {
		return execThreadCount;
	}

	public void setExecThreadCount(int execThreadCount) {
		this.execThreadCount = execThreadCount;
	}

	protected MessageHandler getMessageHandler() {
		return this.messageHandler;
	}

	protected StateHandler getStateHandler() {
		return this.stateHandler;
	}

	protected ExecutorService getExecutorService() {
		return this.executorService;
	}

	protected void setFailure(Exception failure) {
		this.failure = failure;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setConnectTimeout(int connectTimeoutSecond) {
		this.connectTimeoutSecond = connectTimeoutSecond;
	}

	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	public void setMessageHandler(MessageHandler handler) {
		this.messageHandler = handler;
	}

	public void setStateHandler(StateHandler stateHandler) {
		this.stateHandler = stateHandler;
	}

	// public void setThreadPool(ExecutorService executorService) {
	// this.executorService = executorService;
	// }

	public void enableReconnect(int reconnectInterval) {
		this.reconnectInterval = reconnectInterval;
		this.doReconnect();
	}

	public void close() {
		this.stopPing();
		this.stopReconnect();
		this.socket.close();
	}

	public Client connect(String uri) throws ClientException {
		return this.connect(uri, "", null);
	}

	public Client connect(String uri, Map<String, String> headers) throws ClientException {
		return this.connect(uri, "", headers);
	}

	public Client connect(String uri, String messageProtocol) throws ClientException {
		return this.connect(uri, messageProtocol, null);
	}

	public Client connect(String uri, String messageProtocol, Map<String, String> headers) throws ClientException {
		this.stopPing();
		this.failure = null;
		this.uri = uri;
		this.protocol = messageProtocol;
		this.headers = headers;

		WebSocket startSocket = null;
		try {
			startSocket = WebSockets.create(uri, new WebSocketClientHandler(this.loggerFactory, this, this.protocol), this.protocol);
			((WebSocketImpl) startSocket).setOrigin(this.self);
			if (this.headers != null) {
				for (String h : this.headers.keySet()) {
					((WebSocketImpl) startSocket).getRequestHeader().addHeader(h, this.headers.get(h));
				}
			}
			startSocket.setBlockingMode(false);
			startSocket.setConnectionTimeout(this.connectTimeoutSecond);
			// startSocket.connect(); is sync
			// https://github.com/wsky/top-push-client/issues/20
			startSocket.connect();
		} catch (Exception e) {
			throw new ClientException("error while connecting", e);
		}

		if (this.failure != null)
			throw new ClientException("connect fail", this.failure);

		this.socket = startSocket;
		this.reconnectCount++;

		this.doPing();
		this.logger.info("connected to server %s", uri);

		return this;
	}

	public void sendMessage(String to, int messageType, int messageBodyFormat, byte[] messageBody, int offset, int length) throws ClientException {
		byte[] back = this.getBuffer();
		ByteBuffer buffer = ByteBuffer.wrap(back);

		if (Constants.TMC_PROTOCOL_MQTT.equalsIgnoreCase(this.protocol)) {
			MqttPublishMessage msg = new MqttPublishMessage();
			msg.messageType = messageType;
			msg.to = to;
			msg.remainingLength = length;
			MqttMessageIO.parseClientSending(msg, buffer);
			buffer.put(messageBody, offset, length);
		} else {
			MessageIO.writeMessageType(buffer, messageType);
			MessageIO.writeClientId(buffer, to);
			MessageIO.writeBodyFormat(buffer, messageBodyFormat);
			MessageIO.writeRemainingLength(buffer, length);
			buffer.put(messageBody, offset, length);
		}
		// empty field will cause websocket server loop, and FIN/OpCode flag
		// error
		buffer.flip();

		try {
			FrameRfc6455 frame = (FrameRfc6455) socket.createFrame(buffer);
			// client to server always mask
			// https://github.com/wsky/top-push-client/issues/3
			frame.mask();
			this.socket.send(frame);
		} catch (WebSocketException e) {
			throw new ClientException("error while sending", e);
		} finally {
			this.returnBuffer(back);
		}

		this.delayNextPing();
	}

	protected void stopPing() {
		this.pingFlag = false;
	}

	protected void delayNextPing() {
		this.pingLast = System.currentTimeMillis();
	}

	protected synchronized void doPing() {
		this.pingFlag = true;
		this.pingLast = System.currentTimeMillis();
		if (this.pingWorker != null &&
				this.pingWorker.isAlive() &&
				!this.pingWorker.isInterrupted())
			return;
		this.pingWorker = new Thread(new Runnable() {
			public void run() {
				while (true) {
					long split = System.currentTimeMillis() - pingLast;
					if (pingFlag) {
						if (split >= maxIdle)
							ping();
					}
					try {
						Thread.sleep(split >= maxIdle ? maxIdle : maxIdle - split);
					} catch (InterruptedException e) {
						logger.error(e);
					}
				}

			}
		});
		this.pingWorker.start();
	}

	protected void ping() {
		if (this.socket == null || !this.socket.isConnected())
			return;
		try {
			PingFrame pingFrame = new PingFrame();
			pingFrame.mask();
			this.socket.send(pingFrame);
			if (this.logger.isDebugEnable())
				this.logger.debug("ping#" + this.reconnectCount);
		} catch (WebSocketException e) {
			this.logger.error(e.toString());
		}
	}

	private void stopReconnect() {
		if (this.reconnecTimer != null)
			this.reconnecTimer.cancel();
	}

	private void doReconnect() {
		this.stopPing();
		this.reconnecTimerTask = new TimerTask() {
			@Override
			public void run() {
				if (socket != null && !socket.isConnected()) {
					try {
						connect(uri, protocol, headers);
					} catch (ClientException e) {
						logger.error("error while reconnecting", e);
					}
				}
			}
		};
		this.reconnecTimer = new Timer(true);
		this.reconnecTimer.schedule(
				this.reconnecTimerTask, new Date(), this.reconnectInterval);
	}

	// easy buffer pool
	private byte[] getBuffer() {
		byte[] buffer = this.bufferQueue.poll();
		if (buffer == null)
			buffer = new byte[maxMessageSize];
		return buffer;
	}

	private void returnBuffer(byte[] buffer) {
		this.bufferQueue.add(buffer);
	}
}
