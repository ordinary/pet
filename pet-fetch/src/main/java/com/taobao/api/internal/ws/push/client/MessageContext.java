package com.taobao.api.internal.ws.push.client;

import com.taobao.api.internal.ws.push.Logger;
import com.taobao.api.internal.ws.push.LoggerFactory;

public class MessageContext {

	private Logger logger;
	private String messageFrom;
	private Client client;

	public MessageContext(LoggerFactory loggerFactory, Client client, String messageFrom) {
		this.logger = loggerFactory.create(this);
		this.messageFrom = messageFrom;
		this.client = client;
	}

	public void reply(int messageType, int bodyFormat, byte[] messageBody, int offset, int length) {
		try {
			this.client.sendMessage(this.messageFrom, messageType, bodyFormat, messageBody, offset, length);
		} catch (ClientException e) {
			this.logger.error("error while doing reply", e);
		}
	}
}
