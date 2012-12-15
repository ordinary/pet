package com.pet.redis.exception;

public class ConfigNodeNotCompletionException extends Exception {

	private static final long serialVersionUID = 1L;

	private String node;
	private String rootNode;

	public ConfigNodeNotCompletionException(String node, String rootNode, String message) {
		super(message);
		this.node = node;
		this.rootNode = rootNode;
	}

	public ConfigNodeNotCompletionException(String node, String rootNode) {
		this.node = node;
		this.rootNode = rootNode;
	}

	public String getRootNode() {
		return rootNode;
	}

	public String getNode() {
		return node;
	}

}