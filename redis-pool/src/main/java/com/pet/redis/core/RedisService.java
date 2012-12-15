package com.pet.redis.core;

import com.pet.redis.component.RedisPoolFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class RedisService {

	public <T> T execute(RedisCallback<T> call,String key,int db) {
		T back = null;
		ShardedJedisPool pool = null;
		ShardedJedis shardedJedis = null;
		try {
			pool = RedisPoolFactory.getRedisPoolContext().getShardJedisPool();
			shardedJedis = pool.getResource();
			Jedis jedis = shardedJedis.getShard(key);
			jedis.select(db);
			back = call.callback(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(shardedJedis);
		}
		return back;
	}

}
