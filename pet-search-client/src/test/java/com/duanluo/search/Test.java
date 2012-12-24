package com.duanluo.search;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Test {
     public static void main(String[] args) throws Exception {
    	 Properties properties = new Properties();
			String path = Thread.currentThread().getContextClassLoader().getResource("conf.properties").getPath();
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			properties.load(in);
			System.out.println(properties.get("brokerUrl"));
	}
}
