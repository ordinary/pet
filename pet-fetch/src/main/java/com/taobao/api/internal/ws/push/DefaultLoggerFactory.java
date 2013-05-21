package com.taobao.api.internal.ws.push;

public class DefaultLoggerFactory implements LoggerFactory {


	public DefaultLoggerFactory() {
		this(false, true, true, true, true);
	}

	public DefaultLoggerFactory(boolean isDebugEnable,
			boolean isInfoEnable,
			boolean isWarnEnable,
			boolean isErrorEnable,
			boolean isFatalEnable) {
	}

	
	public Logger create(String type) {
		return new DefaultLogger(type);
	}

	
	public Logger create(Class<?> type) {
		return new DefaultLogger(type);
	}

	
	public Logger create(Object object) {
		return new DefaultLogger(object.getClass());		
	}

}
