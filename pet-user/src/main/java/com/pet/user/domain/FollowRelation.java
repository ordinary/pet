package com.pet.user.domain;

import java.util.Date;

public class FollowRelation {

	private int followerId;

	private int follewedId;

	private Date createDate;

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public int getFollewedId() {
		return follewedId;
	}

	public void setFollewedId(int follewedId) {
		this.follewedId = follewedId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
