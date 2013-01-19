package com.pet.user.enums;

public enum Platform {
	
	NONE(0,"未知平台"),

	SINA(1, "新浪"),

	QZONE(2, "QQ空间"),
	
	WEBCHAT(3, "微信"),

	;
	Platform(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Platform getPlatformById(int id){
		for (Platform platform : Platform.values()) {
			if (platform.getId()==id) {
				return platform;
			}
		}
		return NONE;
	}

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
