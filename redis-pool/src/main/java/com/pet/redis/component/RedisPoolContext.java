package com.pet.redis.component;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

import com.pet.redis.component.RedisPoolLoader.PoolLoaderConfig;
import com.pet.redis.constants.Constants;
import com.pet.redis.core.PetRedisMonitorManager;
import com.pet.redis.util.MD5Util;
import com.pet.redis.util.PropertiesUtils;
import com.pet.redis.zookeeper.ZooKeeperClusterListener;
import com.pet.redis.zookeeper.ZooKeeperManager;

public class RedisPoolContext {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final int try_times = 3;
	private RedisPoolLoader redisPoolLoader;
	private final int sleepTime = 1000;

	private Pool<?> pool;

	static {
		String redisServerUrls = PropertiesUtils.getRedisServerUrl();
		for (String redisServerUrl : redisServerUrls.split(";")) {
			ZooKeeperManager.getInstance().createRedisNode(
					MD5Util.getMD5(redisServerUrl.trim().getBytes()),
					redisServerUrl);
			PetRedisMonitorManager.addMonitor(redisServerUrl.trim());
		}
	}

	public RedisPoolContext() {
		PoolLoaderConfig config = new PoolLoaderConfig(Constants.redis_passwd,
				new ZooKeeperClusterListener() {
					public void handleClusterNodesChanged(String path) {
						reloadPool();
					}
				});
		redisPoolLoader = new RedisPoolLoader(config);
		for (int i = 0; i < try_times; i++) {
			if (loadPool()) {
				break;
			}
		}

	}

	private boolean loadPool() {
		boolean flag = false;
		lock.writeLock().lock();
		try {
			pool = redisPoolLoader.createPool();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return flag;
	}

	public void reloadPool() {
		while (true) {
			if (loadPool()) {
				break;
			} else {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ShardedJedisPool getShardJedisPool() {
		return (ShardedJedisPool) pool;
	}

}
