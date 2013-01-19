package com.pet.user.enums;

public enum DeviceType {

	NONE(0),

	IPAD(1),

	IPHONE(2),

	ANDROID(3),

	WINDOW(4),

	PC(5), ;

	private int id;

	public static DeviceType getDeviceTypeById(int id) {
		for (DeviceType deviceType : DeviceType.values()) {
			if (deviceType.getId() == id) {
				return deviceType;
			}
		}
		return NONE;
	}

	DeviceType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
