package com.taobao.api.internal.ws;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.Constants;
import com.taobao.api.internal.ws.push.client.Client;
import com.taobao.api.internal.ws.push.client.ClientException;
import com.taobao.api.internal.ws.push.client.MessageContext;
import com.taobao.api.internal.ws.push.client.MessageHandler;
import com.taobao.api.internal.ws.push.client.StateHandler;

// act as a subscriber for special usage
public class Frontend {

	private static final Log log = LogFactory.getLog(Frontend.class);

	private CustomClient client;
	private FrontendMessageHandler handler;
		
	/**
	 * 目前必须制定groupId，防止以后默认groupId修改出现问题
	 * @param appKey
	 * @param groupId 数据推送请传入2
	 */
	public Frontend(String appKey, Integer groupId) {
		this(appKey, -1L, groupId);
	}
	/**
	 * 目前必须制定groupId，防止以后默认groupId修改出现问题
	 * @param appKey
	 * @param groupId 数据推送请传入2
	 */
	public Frontend(String appKey, Long userId, Integer groupId) {
		System.setProperty("websocket.upstreamQueueSize", "200000");
		this.client = new CustomClient(String.format("%s%s%s", appKey, userId, groupId));
		this.client.appKey = appKey;
		this.client.userId = userId;
		this.client.groupId = groupId;
		this.setHandler();
	}

	public void setSecret(String secret) {
		this.client.secret = secret;
	}

	public void setMessageHandler(FrontendMessageHandler handler) {
		this.handler = handler;
	}
	
	public void setStateHandler(StateHandler stateHandler) {
		this.client.setStateHandler(stateHandler);
	}
	
	public void setExecThreadCount(int threadCount) {
		this.client.setExecThreadCount(threadCount);
	}

	public void connect(String uri) throws ClientException {
		this.client.connect(uri, Constants.TMC_PROTOCOL_MQTT);
	}

	public void close(){
		this.client.close();
	}

	private void setHandler() {
		this.client.setMessageHandler(new MessageHandler() {
			@Override
			public void onMessage(int messageType,
					int bodyFormat,
					byte[] messageBody,
					int offset,
					int length,
					MessageContext context) {
				ByteBuffer buffer = ByteBuffer.wrap(messageBody, offset, length);
				// HACK:messageId will be first from backend
				long messageId = buffer.getLong();
				offset += 8;
				length -= 8;
				handler.onMessage(new Message(messageBody,offset,length),
						new FrontendMessageContext(messageId, context));
				
			}
		});
	}

	class CustomClient extends Client {
		private String secret;
		private String appKey;
		private long userId = -1;
		private int groupId = 1;

		public CustomClient(String clientFlag) {
			super(clientFlag);
		}

		@Override
		public Client connect(String uri, String messageProtocol, Map<String, String> headers) throws ClientException {
			headers = new HashMap<String, String>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			headers.put("timestamp", formatter.format(new Date()));
			headers.put("app_key", this.appKey);
			headers.put("user_id", this.userId + "");
			headers.put("group_id", this.groupId + "");
			headers.put("sign", this.signature(headers, this.secret));
			return super.connect(uri, messageProtocol, headers);
		}

		private String signature(Map<String, String> params, String secret) {
			String[] names = params.keySet().toArray(new String[0]);
			Arrays.sort(names);
			StringBuilder sb = new StringBuilder();
			sb.append(secret);
			for (int i = 0; i < names.length; i++) {
				String name = names[i];
				sb.append(name);
				sb.append(params.get(name));
			}
			sb.append(secret);
			return toMD5(sb.toString().getBytes(Charset.forName("utf-8"))).toUpperCase();
		}

		private String toMD5(byte[] data) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(data);
				byte[] byteDigest = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < byteDigest.length; offset++) {
					i = byteDigest[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				return buf.toString();
			} catch (NoSuchAlgorithmException e) {
				log.error("toMD5 error", e);
				return null;
			}

		}
	}
}
