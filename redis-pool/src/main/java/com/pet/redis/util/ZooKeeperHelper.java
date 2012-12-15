package com.pet.redis.util;

import com.pet.redis.constants.Constants;

public class ZooKeeperHelper {
	public static String generatePath(String... paths) {
		StringBuffer buffer = new StringBuffer();
		for (String path : paths) {
			buffer.append(Constants.seperator).append(path);
		}
		return buffer.toString();
	}
}
