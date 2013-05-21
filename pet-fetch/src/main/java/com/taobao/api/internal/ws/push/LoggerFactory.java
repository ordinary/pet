package com.taobao.api.internal.ws.push;

public interface LoggerFactory {
	public Logger create(String type);
	
	public Logger create(Class<?> type);
	
	public Logger create(Object object);
}
