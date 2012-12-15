package com.pet.redis.exception;

public class RootNodeUnavailableException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String rootNode;
	
	public RootNodeUnavailableException(String rootNode,String message){
		super(message);
		this.rootNode = rootNode;
	}
	public RootNodeUnavailableException(String rootNode){
		this.rootNode = rootNode;
	}
	
	public String getRootNode() {
		return rootNode;
	}
	
}
