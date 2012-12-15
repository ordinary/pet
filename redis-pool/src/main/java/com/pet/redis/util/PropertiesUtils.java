package com.pet.redis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.pet.redis.zookeeper.ZooKeeperManager;

public class PropertiesUtils {
	private static Properties properties=new Properties();
	static{
		try {
			InputStream in = ZooKeeperManager.class.getClassLoader()
					.getResourceAsStream("redis.zookeeper.properties");
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String  getZookeeperServerUrl() {
		return properties.getProperty("zookeeper.server.url");
	}
	
	public static String  getRedisServerUrl() {
		return properties.getProperty("redis.server.url");
	}

}
