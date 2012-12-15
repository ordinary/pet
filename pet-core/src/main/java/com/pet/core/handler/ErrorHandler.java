package com.pet.core.handler;
import net.paoding.rose.web.ControllerErrorHandler;
import net.paoding.rose.web.Invocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ErrorHandler implements ControllerErrorHandler {
	@Override
	public Object onError(Invocation inv, Throwable ex) throws Throwable {
	        Log logger = LogFactory.getLog(inv.getControllerClass());
	        logger.error("", ex);
	        return "/views/500.jsp";
	}
}