package com.duanluo.jade.domain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.duanluo.jade.db.JadeDataSourceConfig;
import com.duanluo.jade.db.JadeTablePartitionConfig;

/**
 * 各项配置信息载入器
 * 
 * @author lixingliang
 * 
 */
public class Configurations {

	private static Map<String, ConfigItem> configMap;
//	private static Map<String, List<JadeTablePartitionConfig>> hashTableMap;
	private static Map<String, Map<String, JadeTablePartitionConfig>> hashTableMap;
	private static Configurations instance = null;
	private static String clientMapPath;
	public static long lastModified = 0;
	// 连接配置服务器的超时
	public static final int CONNECT_TIMEOUT = 10000;

	// 读取配置服务器的超时
	public static final int READ_TIMEOUT = 10000;

	private Configurations() {
	}

	public static Configurations getInstance() {

		try {
			if (instance == null) {
				instance = new Configurations();
			}
			if (configMap == null) {
//				Properties properties = new Properties();
//				properties.load(Thread.currentThread().getContextClassLoader()
//						.getResourceAsStream("configuration.propertise"));
//				clientMapPath = (String) properties.get("jadeconfigUrl");
//				instance.loadURL(new URL(clientMapPath));
				instance.loadURL(Thread.currentThread().getContextClassLoader().getResource("configuration.xml"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	public static void main(String[] args) {
		
		Configurations.getInstance();
	}

	public void loadFile(File file) {

		try {
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(file);
			parse(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadURL(URL url) {

		try {
			// 打开网络连接
			URLConnection connect = url.openConnection();
			connect.setConnectTimeout(CONNECT_TIMEOUT);
			// 设置读取超时
			connect.setReadTimeout(READ_TIMEOUT);
			SAXReader saxReader = new SAXReader();
			// 打开文件流
			Document doc = saxReader.read(new InputSource(connect
					.getInputStream()));
			parse(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置连接超时
		catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parse(Document doc) {
		Element root = doc.getRootElement();
		List<?> hashList = root.elements("table-partitions");

		if (hashList != null && hashList.size() > 0) {
			hashList = ((Element) hashList.get(0)).elements("table");
			hashTableMap = new HashMap<String, Map<String, JadeTablePartitionConfig>>();
			for (Object obj : hashList) {
				Element hashElm = (Element) obj;
				String database = hashElm.attributeValue("database");
				System.out.println("Hash table belonged to database: "
						+ database);
				JadeTablePartitionConfig tableconfig = null;
				if (hashTableMap.containsKey(database)) {
				    tableconfig = hashElmToObject(hashElm);
					hashTableMap.get(database).put(tableconfig.getTableName(), tableconfig);
				} else {
					Map<String, JadeTablePartitionConfig> tableConfigMap = new HashMap<String, JadeTablePartitionConfig>();
					tableconfig = hashElmToObject(hashElm);
					tableConfigMap.put(tableconfig.getTableName(), tableconfig);
					
					hashTableMap.put(database, tableConfigMap);
				}
			}
		}

		List<?> itemList = root.elements("item");
		configMap = new HashMap<String, ConfigItem>();
		for (Object obj : itemList) {
			Element elm = (Element) obj;
			String id = elm.attributeValue("id");
			String type = elm.attributeValue("type");
			ConfigItem cfg = new ConfigItem();
			cfg.setId(id);
			cfg.setType(type);
			List<?> paramList = elm.elements("parameter");
			List<?> serverList = elm.elements("server");
			if (paramList != null && paramList.size() > 0) {
				System.out.println("Config ID:" + id);
				for (Object p : paramList) {
					Element paramElm = (Element) p;
					String name = paramElm.attributeValue("name");
					String value = paramElm.attributeValue("value");
					boolean succ = cfg.addParameter(name, value);
					if (!succ) { // 参数名有重复
						System.err.println(String.format(
								"Error。 %s has duplicate parament:%s", id,
								value));
					} else {
						System.out.println(name);
					}
				}
			} else if (serverList != null && serverList.size() > 0) {
				System.out.println("DB ID:" + id);
				for (Object s : serverList) {
					Element serverElm = (Element) s;
					String wrflag = serverElm.attributeValue("wrflag");
					if ("w".equals(wrflag)) {
						cfg.setMasterDBConfig(dbElmToObject(serverElm));
					}
					if ("r".equals(wrflag)) {
						cfg.setSlaverDBConfig(dbElmToObject(serverElm));
					}
				}
			}

			if (configMap.containsKey(id)) {
				System.err.println("Error。 duplicate Configuration id ," + id);
			} else {
				configMap.put(id, cfg);
			}
		}

	}

	private JadeDataSourceConfig dbElmToObject(Element serverElm) {
		JadeDataSourceConfig dbConfig = new JadeDataSourceConfig();
		String database = serverElm.attributeValue("database");
		dbConfig.setType(serverElm.attributeValue("type"));
		dbConfig.setDatabase(database);
		dbConfig.setHost(serverElm.attributeValue("host"));
		dbConfig.setPort(serverElm.attributeValue("port"));
		dbConfig.setUser(serverElm.attributeValue("user"));
		dbConfig.setPassword(serverElm.attributeValue("password"));
		dbConfig.setWrflag(serverElm.attributeValue("wrflag"));
		dbConfig.setInitialSize(serverElm.attributeValue("initialSize"));
		dbConfig.setMaxActive(serverElm.attributeValue("maxActive"));
		dbConfig.setMaxIdle(serverElm.attributeValue("maxIdle"));
		dbConfig.setMaxWait(serverElm.attributeValue("maxWait"));
		dbConfig.setTablePartitionMap(getTableHashById(database));
		return dbConfig;
	}

	private JadeTablePartitionConfig hashElmToObject(Element hashElm) {
		JadeTablePartitionConfig hashConfig = new JadeTablePartitionConfig();
		hashConfig.setDatabase(hashElm.attributeValue("database"));
		hashConfig.setTableName(hashElm.attributeValue("tableName"));
		hashConfig.setByColumn(hashElm.attributeValue("by-column"));
		try {
			hashConfig.setPartitions(Integer.parseInt(hashElm
					.attributeValue("partitions")));
		} catch (Exception e) {
			System.err.print("configuration.xml has error:"
					+ hashElm.attributeValue("partitions")
					+ " is not a number!");
			e.printStackTrace();
		}
		hashConfig.setTargetPattern(hashElm.attributeValue("target-pattern"));
		return hashConfig;
	}

	public ConfigItem getConfigById(String id) {
		if (configMap != null) {
			return configMap.get(id);
		} else {
			return new ConfigItem();
		}
	}

	private Map<String, JadeTablePartitionConfig> getTableHashById(String dbName) {
		if (dbName != null && hashTableMap != null
				&& hashTableMap.containsKey(dbName)) {
			return hashTableMap.get(dbName);
		}
		return new HashMap<String, JadeTablePartitionConfig>();
	}

	public boolean isNotModified() {
		File configFile = new File(clientMapPath);
		return lastModified == configFile.lastModified();
	}

	public static String getClientMapPath() {
		return clientMapPath;
	}
}
