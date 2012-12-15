package com.pet.redis.zookeeper;


public interface ZooKeeperClusterListener {
	public void handleClusterNodesChanged(String path);
}
