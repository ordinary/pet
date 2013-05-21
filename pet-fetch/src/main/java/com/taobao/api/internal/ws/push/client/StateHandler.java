package com.taobao.api.internal.ws.push.client;

public interface StateHandler {
	public void onError(Exception exception);
	public void onClose(int statusCode, String reasonText);
}
