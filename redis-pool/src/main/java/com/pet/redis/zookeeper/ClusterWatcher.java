package com.pet.redis.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class ClusterWatcher implements Watcher {
	static enum Layer {
		ROOT(1), MEMBERS(2), NODE(3);
		int layer;

		Layer(int layer) {
			this.layer = layer;
		}

	}

	ZooKeeperClusterListener clusterListener;
	private Layer layer;
	private String membersPath;

	public ClusterWatcher(String membersPath, Layer layer, ZooKeeperClusterListener clusterListener) {
		this.clusterListener = clusterListener;
		this.layer = layer;
		this.membersPath = membersPath;
	}


	public void process(WatchedEvent event) {
		
		EventType type = event.getType();

		if (layer.equals(Layer.MEMBERS)) {
			if (type.equals(EventType.NodeChildrenChanged)) {
				clusterListener.handleClusterNodesChanged(membersPath);
			}
		} else if (layer.equals(Layer.NODE)) {
			if (type.equals(EventType.NodeDataChanged)) {
				clusterListener.handleClusterNodesChanged(membersPath);
			}
		}
	}
}
