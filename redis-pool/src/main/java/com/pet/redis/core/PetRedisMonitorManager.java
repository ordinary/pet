package com.pet.redis.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Client;

public class PetRedisMonitorManager {

	private static Map<String, PetRedisMonitor> monitorMap = new ConcurrentHashMap<String, PetRedisMonitor>();

	public static void addMonitor(final String server) {
		if (monitorMap.get(server) == null) {
			final String[] serverVars = server.split(":");
			if (serverVars.length == 2) {
				try {
					Thread thread=new Thread(new Runnable() {
						public void run() {
							Client client = new Client(serverVars[0],
									Integer.parseInt(serverVars[1]));
							PetRedisMonitor monitor = new PetRedisMonitor();
							monitor.proceed(client);
							monitorMap.put(server, monitor);
						}
					});
					thread.setDaemon(true);
					thread.start();
				} catch (Exception e) {

				}

			}

		}
	}

}
