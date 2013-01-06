package com.duanluo.jade.db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JadeDataSource  implements DataSource {
    private String bizName = null;
    
    private List<DataSource> datasources = new ArrayList<DataSource>();

    private Random random = new Random();

    public void addDatasource(DataSource master) {
        this.datasources.add(master);
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }

    protected DataSource getDataSource() throws SQLException {
        return randomGet(datasources);
    }

    protected DataSource randomGet(List<DataSource> dataSources) {
        if (dataSources.size() == 0) {
            return null;
        }
        int index = random.nextInt(dataSources.size()); // 0.. size
        return dataSources.get(index);
    }

    //---------------------------------------

    

	public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizName() {
        return bizName;
    }


	public PrintWriter getLogWriter() throws SQLException {
		return getDataSource().getLogWriter();
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		getDataSource().setLogWriter(out);
	}


	public void setLoginTimeout(int seconds) throws SQLException {
		getDataSource().setLoginTimeout(seconds);
	}

	public int getLoginTimeout() throws SQLException {
		return getDataSource().getLoginTimeout();
	}

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }
    
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return false;
    }

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}