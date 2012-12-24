package com.pet.search.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SearchClient {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContent_search_client.xml");
		applicationContext.start();
		
	}

}
