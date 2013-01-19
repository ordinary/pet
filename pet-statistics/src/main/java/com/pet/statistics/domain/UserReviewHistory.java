package com.pet.statistics.domain;

import java.util.Date;

public class UserReviewHistory {
	
	private int userId;
	
	private long numId;
	
	private long  cid;
	
	private Date createTime;
	
	private int doAction;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getNumId() {
		return numId;
	}

	public void setNumId(long numId) {
		this.numId = numId;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDoAction() {
		return doAction;
	}

	public void setDoAction(int doAction) {
		this.doAction = doAction;
	}
	

}
