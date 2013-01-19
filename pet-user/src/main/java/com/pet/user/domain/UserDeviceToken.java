package com.pet.user.domain;

import java.util.Date;

public class UserDeviceToken {
	
	private long userId;
	
	private String deviceToken;
	
	private int deviceType;
	
	private int deviceVersion;
	
	private Date createTime;
	
	private Date modifyTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public int getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(int deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
