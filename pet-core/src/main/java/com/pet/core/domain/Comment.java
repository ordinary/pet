package com.pet.core.domain;

import java.util.Date;

public class Comment {


	private long id;
	/** 商品id */
	private long commodityId;

	private int annoy;

	/** 买家姓名 */
	private String buyerName;

	/** 信用等级 */
	private int credit;

	/** 评论日期 */
	private Date date;

	/** 商品类型 */
	private String deal;

	private long rateId;

	/** 评论内容 */
	private String text;

	/** 类型 */
	private int type;

	public long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(long commodityId) {
		this.commodityId = commodityId;
	}

	public int getAnnoy() {
		return annoy;
	}

	public void setAnnoy(int annoy) {
		this.annoy = annoy;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}

	public long getRateId() {
		return rateId;
	}

	public void setRateId(long rateId) {
		this.rateId = rateId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
