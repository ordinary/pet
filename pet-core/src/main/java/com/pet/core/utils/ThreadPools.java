package com.pet.core.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ThreadPools {
	
	private final static Log logger= LogFactory.getLog(ThreadPools.class);
	
	private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors()*2+10;

	private static ExecutorService service = Executors
			.newFixedThreadPool(POOL_SIZE);

	private static ScheduledExecutorService scheduledExecutorService = Executors
			.newScheduledThreadPool(POOL_SIZE);
	
	public static void execute(Runnable runnable) {
		try {
			service.execute(runnable);
		} catch (Exception e) {
			logger.error(runnable.getClass(), e);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
    public static Future submit(Runnable runnable) {
		try {
			return service.submit(runnable);
		} catch (Exception e) {
			logger.error(runnable.getClass(), e);
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public static Future submit(Callable callable) {
		try {
			return service.submit(callable);
		} catch (Exception e) {
			logger.error(callable.getClass(), e);
		}
		return null;
	}
	

	public static void scheduleAtFixedRate(Runnable runnable,
			long initialDelay, long period, TimeUnit unit) {
		try {
			scheduledExecutorService.scheduleAtFixedRate(runnable,
					initialDelay, period, unit);
		} catch (Exception e) {
			logger.error(runnable.getClass(), e);
		}
	}

	public static void schedule(Runnable runnable, long delay, long period,
			TimeUnit unit) {
		try {
			scheduledExecutorService.schedule(runnable, delay, unit);
		} catch (Exception e) {
			logger.error(runnable.getClass(), e);
		}
	}
	
	

}
