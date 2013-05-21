package com.taobao.api.internal.ws;

public interface FrontendMessageHandler {
	public void onMessage(Message message,	FrontendMessageContext context);
}
