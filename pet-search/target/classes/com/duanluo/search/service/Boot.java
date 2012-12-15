package com.duanluo.search.service;

import com.duanluo.network.server.BootStrap;
import com.duanluo.search.search.SearcherFactory;

public class Boot {
	public static void main(String[] args) throws Exception {
		String path = "";
		if (args.length > 0) {
			path = args[0];
		} else {
			path = Thread.currentThread().getContextClassLoader()
					.getResource("conf.properties").getPath();
		}
		BootStrap.registServer(path);
		// 启动建索引服务
		SearcherFactory.startIndexService(path);

	}
}
