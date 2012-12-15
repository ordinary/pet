package com.pet.redis.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

import com.pet.redis.constants.ShardJedisPoolPattern;
import com.pet.redis.zookeeper.ZooKeeperClusterListener;
import com.pet.redis.zookeeper.ZooKeeperManager;

public class RedisPoolLoader {
	
	private static final int DEFAULT_TIMEOUT=10000;

	static class PoolLoaderConfig {
		String redisPassword;
		ZooKeeperClusterListener clusterListener;

		PoolLoaderConfig(String redisPassword,
				ZooKeeperClusterListener zooKeeperClusterListener) {
			this.redisPassword = redisPassword;
			this.clusterListener = zooKeeperClusterListener;
		}
	}

	class InnerProtocol {
		String host;
		int port;

		InnerProtocol(String value) {
			if (!value.contains(":")) {
				throw new IllegalArgumentException(
						"protocol information imperfect");
			}
			String[] values = value.split(":");
			host = values[0];
			port = Integer.parseInt(values[1]);
		}

		public String toString() {
			return host + "," + port;
		}
	}

	private ZooKeeperClusterListener clusterListener;
	private String redisPassword;

	public RedisPoolLoader(PoolLoaderConfig config) {
		this.redisPassword = config.redisPassword;
		this.clusterListener = config.clusterListener;
	}

	public Pool<?> createPool() {

		Pool<?> pool = null;
		try {
			Set<String> nodeValues = ZooKeeperManager.getInstance().bind(clusterListener);
			pool = createShardPool(nodeValues);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return pool;
	}



	
	private Pool<?> createShardPool(Set<String> nodeValues) {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		for (String value : nodeValues) {
			InnerProtocol innerProtocol = new InnerProtocol(value);
			JedisShardInfo shard = new JedisShardInfo(innerProtocol.host,
					innerProtocol.port);
			shard.setTimeout(DEFAULT_TIMEOUT);
			shard.setPassword(redisPassword);
			shards.add(shard);
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.testOnBorrow = true;
		poolConfig.testWhileIdle = true;
		poolConfig.maxIdle=50;
		poolConfig.maxActive=100;
		poolConfig.whenExhaustedAction =GenericObjectPool.WHEN_EXHAUSTED_GROW; // 超过50则自动增长
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards,
				Hashing.MURMUR_HASH,
				ShardJedisPoolPattern.NUMBER_ENDED_KEY_TAG_PATTERN);
		return pool;
	}

	
}
