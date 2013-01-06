package com.duanluo.jade.db;

import java.util.Map;

/**
 * 数据源配置信息
 * @author lixingliang
 *
 */
public class JadeDataSourceConfig {
	
	private String type;
	private String database;
	private String host;
	private String port;
	private String user;
	private String password;
	private String wrflag;
	private String initialSize;
	private String maxActive;
	private String maxIdle;
	protected String maxWait;
	
	private Map<String, JadeTablePartitionConfig> tablePartitionMap;//N个表散列配置
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWrflag() {
		return wrflag;
	}
	public void setWrflag(String wrflag) {
		this.wrflag = wrflag;
	}
	public String getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(String initialSize) {
		this.initialSize = initialSize;
	}
	public String getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}
	public String getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}
	public String getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}
	public Map<String, JadeTablePartitionConfig> getTablePartitionMap() {
		return tablePartitionMap;
	}
	public void setTablePartitionMap(
			Map<String, JadeTablePartitionConfig> tablePartitionMap) {
		this.tablePartitionMap = tablePartitionMap;
	}
}
