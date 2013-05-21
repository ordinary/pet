package com.taobao.api.internal.ws.push.client;

import java.nio.ByteBuffer;

import com.taobao.api.internal.ws.jp.a840.websocket.WebSocket;
import com.taobao.api.internal.ws.jp.a840.websocket.exception.WebSocketException;
import com.taobao.api.internal.ws.jp.a840.websocket.frame.Frame;
import com.taobao.api.internal.ws.jp.a840.websocket.frame.rfc6455.BinaryFrame;
import com.taobao.api.internal.ws.jp.a840.websocket.frame.rfc6455.TextFrame;
import com.taobao.api.internal.ws.jp.a840.websocket.handler.WebSocketHandler;
import com.taobao.api.internal.ws.push.Logger;
import com.taobao.api.internal.ws.push.LoggerFactory;
import com.taobao.api.internal.ws.push.messages.MessageIO;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageIO;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageType;
import com.taobao.api.internal.ws.push.mqtt.publish.MqttPublishMessage;


public class WebSocketClientHandler implements WebSocketHandler {
	private LoggerFactory loggerFactory;
	private Logger logger;
	private Client client;
	private String protocol;

	public WebSocketClientHandler(LoggerFactory loggerFactory, Client client, String protocol) {
		this.loggerFactory = loggerFactory;
		this.logger = this.loggerFactory.create(this);
		this.client = client;
		this.protocol = protocol;
	}

	public void onOpen(WebSocket socket) {
		this.logger.info("websocket open");
	}

	public void onError(WebSocket socket, WebSocketException e) {
		this.client.stopPing();
		this.client.setFailure(e);
		this.logger.error("websocket error", e);

		if (this.client.getStateHandler() != null)
			this.client.getStateHandler().onError(e);
	}

	public void onClose(WebSocket socket) {
		this.client.stopPing();
		socket.close();
		this.logger.warn("websocket closed");
	}

	
	public void onCloseFrame(WebSocket socket, int statusCode, String reasonText) {
		this.client.setFailure(new ClientException(
				String.format("websocket closed by server: %s|%s", statusCode, reasonText)));
		this.logger.warn("receive close frame: %s|%s", statusCode, reasonText);

		if (this.client.getStateHandler() != null)
			this.client.getStateHandler().onClose(statusCode, reasonText);
	}

	public void onMessage(WebSocket socket, Frame frame) {
		this.client.delayNextPing();

		if (this.client.getMessageHandler() == null)
			return;

		if (frame instanceof BinaryFrame) {
			ByteBuffer buffer = frame.getContents();
			String messageFrom;
			int messageType = 0;
			int messageBodyFormat = 0;
			int remainingLength = 0;

			if ("mqtt".equalsIgnoreCase(protocol)) {
				int mqttMessageType = MqttMessageIO
						.parseMessageType(buffer.get(0));
				if (mqttMessageType == MqttMessageType.ConnectAck) {
					return;
				} else if (mqttMessageType != MqttMessageType.Publish) {
					this.logger.warn("Not Implement MqttMessageType:" + mqttMessageType);
					return;
				}
				MqttPublishMessage message = new MqttPublishMessage();
				MqttMessageIO.parseClientReceiving(message, buffer);
				messageType = message.messageType;
				messageFrom = message.from;
				messageBodyFormat = message.bodyFormat;
				remainingLength = message.remainingLength;
			} else {
				messageType = MessageIO.readMessageType(buffer);
				messageFrom = MessageIO.readClientId(buffer);
				messageBodyFormat = MessageIO.readBodyFormat(buffer);
				remainingLength = MessageIO.readRemainingLength(buffer);
			}

			this.client.getExecutorService().execute(new OnMessage(
					this.client, 
					messageType, 
					messageBodyFormat, 
					buffer.array(), 
					buffer.arrayOffset() + buffer.position(),
					remainingLength,
					new MessageContext(
							this.loggerFactory, 
							this.client, 
							messageFrom)));
		} else if (frame instanceof TextFrame) {
			this.logger.info("text message: %s", frame);
		}
	}

	public class OnMessage implements Runnable {
		private Client client;
		private int messageType;
		private int messageBodyFormat;
		private byte[] buffer;
		private int offset;
		private int length;
		private MessageContext context;

		public OnMessage(Client client,
				int messageType,
				int messageBodyFormat,
				byte[] buffer,
				int offset,
				int length,
				MessageContext context) {
			this.client = client;
			this.messageType = messageType;
			this.messageBodyFormat = messageBodyFormat;
			this.buffer = buffer;
			this.offset = offset;
			this.length = length;
			this.context = context;
		}

		
		public void run() {
			this.client.getMessageHandler().onMessage(
					messageType,
					messageBodyFormat,
					buffer,
					offset,
					length,
					context);
		}

	}
}
