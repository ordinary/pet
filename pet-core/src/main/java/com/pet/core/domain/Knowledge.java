package com.pet.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "knowledge")
public class Knowledge {
  
	private long id;
	
	private String content;
	
	private String url;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
