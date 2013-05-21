package com.taobao.api.internal.ws;

import java.nio.ByteBuffer;

import com.taobao.api.internal.ws.push.client.MessageContext;
import com.taobao.api.internal.ws.push.messages.MessageType;

public class FrontendMessageContext {
	private long messageId;
	private MessageContext innerContext;

	public FrontendMessageContext(long messageId, MessageContext innerContext) {
		this.messageId = messageId;
		this.innerContext = innerContext;
	}

	public void confirm() {
		// FIXME:confirm buffer management
		byte[] buffer = new byte[8];
		ByteBuffer.wrap(buffer).putLong(this.messageId);
		this.innerContext.reply(MessageType.PUBCONFIRM, 0, buffer, 0, buffer.length);
	}
}
