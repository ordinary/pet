package com.pet.redis.component;

public class RedisPoolFactory {

	private static RedisPoolContext redisPoolContext;

	private static Object lock = new Object();

	public static RedisPoolContext getRedisPoolContext() {
		synchronized (lock) {
			if (redisPoolContext == null) {
				redisPoolContext = new RedisPoolContext();
			}
		}
		return redisPoolContext;

	}

}
