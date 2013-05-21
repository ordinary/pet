package com.taobao.api.internal.ws.push;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class DefaultLogger implements Logger {
	private Log log;

	@SuppressWarnings("rawtypes")
	public DefaultLogger(Class clazz) {
		this.log=LogFactory.getLog(clazz);
	}

	public DefaultLogger(String name) {
		this.log=LogFactory.getLog(name);
	}
	
	public boolean isDebugEnable() {
		return true;
	}

	
	public boolean isInfoEnable() {
		return true;
	}

	
	public boolean isWarnEnable() {
		return true;
	}

	
	public boolean isErrorEnable() {
		return true;
	}

	
	public boolean isFatalEnable() {
		return true;
	}

	
	public void debug(String message) {		
		log.debug(message);
	}

	
	public void debug(Throwable exception) {
		log.debug(exception);
	}
	
	public void debug(String message, Throwable exception) {
		log.debug(message,exception);
	}


	public void debug(String format, Object... args) {
		log.debug(String.format("[DEBUG] [%s] - %s", 0, String.format(format, args)));	
	}

	
	public void info(String message) {
		log.info(message);		
	}
	
	public void info(Throwable exception) {
		log.info(exception);		
	}

	
	public void info(String message, Throwable exception) {
		log.info(message,exception);		
	}

	
	public void info(String format, Object... args) {
		log.info(String.format("[INFO] [%s] - %s", 0, String.format(format, args)));	
	}

	
	public void warn(String message) {
		log.warn(message);		
	}

	
	public void warn(Throwable exception) {
		log.warn(exception);		
	}


	public void warn(String message, Throwable exception) {
		log.warn(message,exception);		
	}

	
	public void warn(String format, Object... args) {
		log.warn(String.format("[WARN] [%s] - %s", 0, String.format(format, args)));	
	}
	
	public void error(String message) {
		log.error(message);		
	}

	
	public void error(Throwable exception) {
		log.error(exception);		
	}
	
	public void error(String message, Throwable exception) {
		log.error(message,exception);	
	}
	
	public void error(String format, Object... args) {
		log.warn(String.format("[ERROR] [%s] - %s", 0, String.format(format, args)));	
	}

	
	public void fatal(String message) {
		log.fatal(message);	
	}
	
	public void fatal(Throwable exception) {
		log.fatal(exception);	
	}
	
	public void fatal(String message, Throwable exception) {
		log.fatal(message,exception);	
	}

	
	public void fatal(String format, Object... args) {
		log.fatal(String.format("[FATAL] [%s] - %s", 0, String.format(format, args)));
	}
}
