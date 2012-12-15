package com.pet.core.holder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHolder {

	private ApplicationContext context;

	private final Object lock = new Object();

	private static ApplicationContextHolder applicationContextHolder = new ApplicationContextHolder();

	private ApplicationContextHolder() {
		context = new ClassPathXmlApplicationContext();
	}

	public ApplicationContextHolder getInstance() {
		synchronized (lock) {
			if (applicationContextHolder == null) {
				applicationContextHolder = new ApplicationContextHolder();
			}
		}
		return applicationContextHolder;
	}

	public <T> T getBean(Class<T> beanType) {
		return context.getBean(beanType);
	}

}
