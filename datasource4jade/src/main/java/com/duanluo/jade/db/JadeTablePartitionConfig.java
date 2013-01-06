package com.duanluo.jade.db;

/**
 * 散列表配置（每一个表对应一个配置）
 * @author lixingliang
 *
 */
public class JadeTablePartitionConfig {
	private String database;
	private String tableName;
	private String byColumn;
	private int partitions;
	private String targetPattern;
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getByColumn() {
		return byColumn;
	}
	public void setByColumn(String byColumn) {
		this.byColumn = byColumn;
	}
	public int getPartitions() {
		return partitions;
	}
	public void setPartitions(int partitions) {
		this.partitions = partitions;
	}
	public String getTargetPattern() {
		return targetPattern;
	}
	public void setTargetPattern(String targetPattern) {
		this.targetPattern = targetPattern;
	}
}