package com.pet.user.domain;

import java.util.Date;

public class UserThirdPartInfo {
	private long id;
	private int userId;
	private String refreshToken;
	private String accessToken;
	private String tokenSecret;
	private String guid;
	private int type;
	private int status;
	private Date updateTime;
	private int isVerified;
	private String thirdPartName;
	private long expiredIn;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	public String getThirdPartName() {
		return thirdPartName;
	}
	public void setThirdPartName(String thirdPartName) {
		this.thirdPartName = thirdPartName;
	}
	public long getExpiredIn() {
		return expiredIn;
	}
	public void setExpiredIn(long expiredIn) {
		this.expiredIn = expiredIn;
	}
	
	
	
}
