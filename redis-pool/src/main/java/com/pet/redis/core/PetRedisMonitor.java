package com.pet.redis.core;

import java.util.Date;

import redis.clients.jedis.JedisMonitor;

import com.pet.redis.util.MD5Util;
import com.pet.redis.zookeeper.ZooKeeperManager;

public class PetRedisMonitor extends JedisMonitor {

	private int time;

	private Date startDate;

	private boolean isConnect = true;

	private static final int sleepTime = 3000;

	@Override
	public void onCommand(String command) {
		if (command == null) {
			if (isConnect) {
				if (startDate == null) {
					startDate = new Date();
				}
				Date endDate = new Date();
				long intervalTime = endDate.getTime() - startDate.getTime();
				if (intervalTime > 5000 && time > 10) {
					ZooKeeperManager.getInstance().deleteRedisNode(
							MD5Util.getMD5((client.getHost() + ":" + client
									.getPort()).getBytes()));
					isConnect = false;
				}
				time++;
			}
		} else {
			time = 0;
			startDate = null;
			if (!isConnect) {
				ZooKeeperManager.getInstance().createNode(
						MD5Util.getMD5((client.getHost() + ":" + client
								.getPort()).getBytes()),
						client.getHost() + ":" + client.getPort());
				isConnect = true;
			}

		}
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
