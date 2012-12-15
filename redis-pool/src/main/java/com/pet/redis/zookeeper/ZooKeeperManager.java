package com.pet.redis.zookeeper;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.pet.redis.exception.ConfigNodeNotCompletionException;
import com.pet.redis.exception.RootNodeUnavailableException;
import com.pet.redis.util.PropertiesUtils;
import com.pet.redis.util.ZooKeeperHelper;

public class ZooKeeperManager {
	private static ZooKeeperManager manager = new ZooKeeperManager();
	public final static String default_zookeeper_path = "default_redis";
	private ZooKeeper zooKeeper;
	private Watcher doNothingWather;
	private String childNode;

	private ZooKeeperManager() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ZooKeeperManager getInstance() {
		return manager;
	}

	// TODO need rebind
	public Set<String> bind(ZooKeeperClusterListener clusterListener)
			throws RootNodeUnavailableException,
			ConfigNodeNotCompletionException {
		Set<String> nodeDatas = new HashSet<String>();
		try {
			Stat stat = zooKeeper.exists(ZooKeeperHelper.generatePath(default_zookeeper_path),
					doNothingWather);
			if (stat == null) {
				throw new RootNodeUnavailableException(default_zookeeper_path,
						"root node is not exist");
			}
			stat = zooKeeper.exists(
					ZooKeeperHelper.generatePath(default_zookeeper_path, childNode),
					doNothingWather);
			if (stat == null) {
				throw new RootNodeUnavailableException(default_zookeeper_path,
						"members node is not exist");
			}
			String membersPath = ZooKeeperHelper.generatePath(default_zookeeper_path, childNode);
			List<String> nodes = zooKeeper.getChildren(membersPath,
					new ClusterWatcher(default_zookeeper_path, ClusterWatcher.Layer.MEMBERS,
							clusterListener));
			if (nodes == null || nodes.size() == 0) {
				throw new RootNodeUnavailableException(default_zookeeper_path,
						"third node is unavaiable");
			}

			String errorNode = "";
			ClusterWatcher clusterWatcher = new ClusterWatcher(default_zookeeper_path,
					ClusterWatcher.Layer.NODE, clusterListener);
			for (String node : nodes) {
				String path = ZooKeeperHelper.generatePath(default_zookeeper_path, childNode,
						node);
				byte[] data = zooKeeper.getData(path, clusterWatcher, null);
				if (data == null) {
					if (StringUtils.isBlank(errorNode)) {
						errorNode += ",";
					}
					errorNode += node;
				} else {
					nodeDatas.add(new String(data));
				}
			}
			if (StringUtils.isNotBlank(errorNode)) {
				throw new ConfigNodeNotCompletionException(errorNode, default_zookeeper_path,
						"node is not complated");
			}

		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeDatas;
	}

	public void createRedisNode(String node,  String value){
		try {
			String rootPath = ZooKeeperHelper.generatePath(default_zookeeper_path);
			Stat stat = zooKeeper.exists(rootPath, doNothingWather);
			if (stat == null) {
				createNode(rootPath, "");
			}
			String childPath = ZooKeeperHelper.generatePath(default_zookeeper_path, childNode);
			stat = zooKeeper.exists(childPath, doNothingWather);
			if (stat == null) {
				createNode(childPath, "");
			}
			String nodeName = ZooKeeperHelper.generatePath(default_zookeeper_path, childNode,node);
			stat = zooKeeper.exists(nodeName, doNothingWather);
			if (stat == null) {
				createNode(childPath,nodeName);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRedisNode(String node){
		try {
			String nodeName = ZooKeeperHelper.generatePath(default_zookeeper_path, childNode,node);
			zooKeeper.delete(nodeName, -1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) throws KeeperException,
			InterruptedException, RootNodeUnavailableException {
		// ZooKeeperManager.getInstance().getValue();
	}

	private void init() throws IOException {
		doNothingWather = new Watcher() {
			public void process(WatchedEvent event) {

			}
		};
		zooKeeper = new ZooKeeper(PropertiesUtils.getZookeeperServerUrl(), 60000, doNothingWather);
		childNode = "members";
	}

	public void createNode(String node, String value) {
		try {
			zooKeeper.create(node, value.getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
