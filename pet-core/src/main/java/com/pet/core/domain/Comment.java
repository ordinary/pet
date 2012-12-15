package com.pet.core.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="comment")
//@CompoundIndexes({ @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}") })
public class Comment {

	@Id
	private ObjectId id;

	/** 商品id */
	@Field("commodity_id")
	private long commodityId;

	// @Indexed
	@Field("annoy")
	private int annoy;

	/** 买家姓名 */
	@Field("buyer_name")
	private String buyerName;

	/** 信用等级 */
	@Field("credit")
	private int credit;

	/** 评论日期 */
	@Field("date")
	private Date date;

	/** 商品类型 */
	@Field("deal")
	private String deal;

	@Field("rate_id")
	private long rateId;

	/** 评论内容 */
	@Field("text")
	private String text;

	/** 类型 */
	@Field("type")
	private int type;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

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

}
