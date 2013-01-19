package com.pet.core.domain;

public class HotCommodity {

	private long id;

	/** 类别id */
	private int type;
	/**
	 * 顺序
	 */
	private int order;

	/**
	 * 推广点击url
	 */
	private String clickUrl;

	/**
	 * 卖家昵称
	 */
	private String nick;

	/**
	 * 淘宝客商品数字id
	 */
	private Long numIid;

	/**
	 * 图片url
	 */
	private String picUrl;

	/**
	 * 商品价格
	 */
	private String price;

	/**
	 * 商品title 宝贝名称
	 */
	private String title;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
