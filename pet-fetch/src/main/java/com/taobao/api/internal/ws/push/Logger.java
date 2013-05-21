package com.taobao.api.internal.ws.push;

public interface Logger {
	public boolean isDebugEnable();

	public boolean isInfoEnable();

	public boolean isWarnEnable();

	public boolean isErrorEnable();

	public boolean isFatalEnable();

	public void debug(String message);

	public void debug(Throwable exception);

	public void debug(String message, Throwable exception);

	public void debug(String format, Object... args);

	public void info(String message);

	public void info(Throwable exception);

	public void info(String message, Throwable exception);

	public void info(String format, Object... args);

	public void warn(String message);

	public void warn(Throwable exception);

	public void warn(String message, Throwable exception);

	public void warn(String format, Object... args);

	public void error(String message);

	public void error(Throwable exception);

	public void error(String message, Throwable exception);

	public void error(String format, Object... args);

	public void fatal(String message);

	public void fatal(Throwable exception);

	public void fatal(String message, Throwable exception);

	public void fatal(String format, Object... args);
}
