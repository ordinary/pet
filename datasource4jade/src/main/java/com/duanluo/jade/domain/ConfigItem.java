package com.duanluo.jade.domain;

import java.util.Map;
import java.util.TreeMap;

import com.duanluo.jade.db.JadeDataSourceConfig;


/**
 * Instance配置项对象
 * @author lixingliang
 *
 */
public class ConfigItem {
	
	
	private String id; //PK
	private String type;
	private Map<String,String> parameterMap;
	private JadeDataSourceConfig masterDBConfig;
	private JadeDataSourceConfig slaverDBConfig;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
	
	public boolean addParameter(String name,String value){
		if(parameterMap == null){
			parameterMap = new TreeMap<String,String>();
		}
		boolean noContain = !parameterMap.containsKey(name);
		if(noContain){
			parameterMap.put(name, value);
		}
		return noContain;
	}

	public JadeDataSourceConfig getMasterDBConfig() {
		return masterDBConfig;
	}
	public void setMasterDBConfig(JadeDataSourceConfig masterDBConfig) {
		this.masterDBConfig = masterDBConfig;
	}
	public JadeDataSourceConfig getSlaverDBConfig() {
		return slaverDBConfig;
	}
	public void setSlaverDBConfig(JadeDataSourceConfig slaverDBConfig) {
		this.slaverDBConfig = slaverDBConfig;
	}

}
