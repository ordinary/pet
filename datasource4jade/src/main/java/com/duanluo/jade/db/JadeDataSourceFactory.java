package com.duanluo.jade.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQLType;
import net.paoding.rose.jade.dataaccess.DataSourceFactory;
import net.paoding.rose.jade.dataaccess.DataSourceHolder;
import net.paoding.rose.jade.statement.StatementMetaData;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.duanluo.jade.domain.ConfigItem;
import com.duanluo.jade.domain.Configurations;

public class JadeDataSourceFactory implements DataSourceFactory {

	private static int instanceCount = 0;// for test

	private Log logger = LogFactory.getLog(getClass());

	private static ConcurrentMap<String, JadeDataSource> cachedDsMap = new ConcurrentHashMap<String, JadeDataSource>();

	public JadeDataSourceFactory() {
		instanceCount++;
		if (instanceCount > 3) {
			System.err
					.println("ERROR ERROR ERROR: too many NewDataSourceFactory, count:"
							+ instanceCount);
		}
	}

	private void createJadeDatasource(String catalog) {
		JadeDataSource masterDataSource = new JadeDataSource();
		JadeDataSource slaveDataSource = null;

		masterDataSource.setBizName(catalog);

		BasicDataSource masterBasicDatasource = initBasicDatasource(catalog,
				true);
		masterDataSource.addDatasource(masterBasicDatasource);

		BasicDataSource slaveBasicDatasource = initBasicDatasource(catalog,
				false);
		if (slaveBasicDatasource == null) {
			slaveBasicDatasource = masterBasicDatasource;
		}
		if (slaveBasicDatasource == masterBasicDatasource) {
			slaveDataSource = masterDataSource;
		} else {
			slaveDataSource = new JadeDataSource();
			slaveDataSource.setBizName(catalog);
			slaveDataSource.addDatasource(slaveBasicDatasource);
		}

		cachedDsMap.putIfAbsent(catalog + "_R", slaveDataSource);
		cachedDsMap.putIfAbsent(catalog + "_W", masterDataSource);
		return;
	}

	private BasicDataSource initBasicDatasource(String bizName, boolean write) {
		JadeDataSourceConfig dsConfig = getDsConfig(bizName, write);
		boolean notfound = "notfound".equals(dsConfig.getDatabase());
		if (notfound) {
			System.err
					.println(" ~~~~~~~~~~~~~~~Config ERROR: Not Found DB Configuration of "
							+ bizName + ", Please tell Administrator");
		}
		int initialSize = Integer.parseInt(dsConfig.getInitialSize());
		int maxActive = Integer.parseInt(dsConfig.getMaxActive());
		int maxWait = Integer.parseInt(dsConfig.getMaxWait());// 建议10000毫秒
		int maxIdle = maxActive / 10 + 1;
		int minIdle = maxActive / 25 + 1;

		// timeBetweenEvictionRunsMillis毫秒秒检查一次连接池中空闲的连接,
		// 把空闲时间超过minEvictableIdleTimeMillis毫秒的连接断开,直到连接池中的连接数到minIdle为止
		long timeBetweenEvictionRunsMillis = 1000 * 30;
		long minEvictableIdleTimeMillis = 1000 * 60 * 5;

		// 创建数据源
		BasicDataSource ds = new BasicDataSource();
		if ("mysql".equalsIgnoreCase(dsConfig.getType())) {
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl(String
					.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8&connectTimeout=100",
							dsConfig.getHost(), dsConfig.getPort(),
							dsConfig.getDatabase()));
		} else if ("postgresql".equalsIgnoreCase(dsConfig.getType())) {
			ds.setDriverClassName("org.postgresql.Driver");
			ds.setUrl(String.format(
					"jdbc:postgresql://%s:%s/%s?connectTimeout=100",
					dsConfig.getHost(), dsConfig.getPort(),
					dsConfig.getDatabase()));
		} else {
			throw new NullPointerException(
					"server type must be either postgresql or mysql");
		}
		ds.setUsername(dsConfig.getUser());
		ds.setPassword(dsConfig.getPassword());

		ds.setInitialSize(initialSize);
		ds.setMaxActive(maxActive);
		ds.setMinIdle(minIdle);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWait(maxWait);

		ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		ds.setNumTestsPerEvictionRun(maxActive); // 每次检查链接的数量，设置和maxActive一样大，这样每次可以有效检查所有的链接
		ds.setValidationQuery("SELECT 1");
		if (!notfound) {
			Connection c = null;
			try {
				c = ds.getConnection();
			} catch (SQLException e) {
				logger.error(bizName + " Config Error", e);
			} finally {
				if (c != null) {
					try {
						c.close();
					} catch (SQLException e) {
					}
				}
			}
		}
		return ds;
	}

	private JadeDataSourceConfig getDsConfig(String bizName, boolean write) {
		JadeDataSourceConfig dsConfig = null;

		System.out.println("getDsConfig : " + bizName);
		ConfigItem dbConfig = Configurations.getInstance().getConfigById(
				bizName);
		if (dbConfig == null) {
			logger.error(bizName + " DatasourceConfig not found ");
		} else {
			dsConfig = write ? dbConfig.getMasterDBConfig() : dbConfig
					.getSlaverDBConfig();
		}

		return dsConfig;
	}

	@Override
	public DataSourceHolder getHolder(StatementMetaData metaData,
			Map<String, Object> runtime) {
		String catalog = metaData.getDAOMetaData().getDAOClass()
				.getAnnotation(DAO.class).catalog();
		String bizName = metaData.getSQLType() == SQLType.READ ? catalog + "_R"
				: catalog + "_W";
		System.out.println("=========================" + bizName);

		JadeDataSource dataSource = null; // cachedDsMap.get(bizName);
		int count = 0;
		while ((dataSource = cachedDsMap.get(bizName)) == null && count++ < 5) {
			createJadeDatasource(catalog);
			System.out.println("try...to get " + bizName + "||" + count + "||"
					+ cachedDsMap.size());
		}
		return new DataSourceHolder(dataSource);
	}
}
