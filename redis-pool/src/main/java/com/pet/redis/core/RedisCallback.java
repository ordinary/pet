package com.pet.redis.core;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
	
	public  T callback(Jedis jedis);

}
