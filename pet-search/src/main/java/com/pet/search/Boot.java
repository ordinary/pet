package com.pet.search;

import java.io.File;
import java.util.List;

import net.paoding.rose.scanning.LoadScope;
import net.paoding.rose.scanning.context.core.RoseResources;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class Boot {
	public static void main(String[] args) throws Exception {
		List<Resource> resources=RoseResources.findContextResources(new LoadScope("", ""));
		String []  configLocations=new String[resources.size()];
		for (int i = 0; i < resources.size(); i++) {
			String url=resources.get(i).toString();
			configLocations[i]=url.substring(url.lastIndexOf(File.separator)+1,url.length()-1);
		}
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		applicationContext.start();

	}
}
