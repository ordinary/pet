package com.pet.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="commodity")
public class Commodity {
	
	private long id;
	/**类别id*/
	private long cid;
	/**
	 * 推广点击url
	 */
	private String clickUrl;

	/**
	 * 淘宝客佣金
	 */
	private String commission;

	/**
	 * 累计成交量.注：返回的数据是30天内累计推广量
	 */
	private String commissionNum;

	/**
	 * 淘宝客佣金比率，比如：1234.00代表12.34%
	 */
	private String commissionRate;

	/**
	 * 累计总支出佣金量
	 */
	private String commissionVolume;

	/**
	 * 折扣活动结束时间
	 */
	private String couponEndTime;

	/**
	 * 折扣价格
	 */
	private String couponPrice;

	/**
	 * 折扣比率
	 */
	private String couponRate;

	/**
	 * 折扣活动开始时间
	 */
	private String couponStartTime;

	/**
	 * 商品所在地
	 */
	private String itemLocation;

	/**
	 * 淘宝客关键词搜索URL
	 */
	private String keywordClickUrl;

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
	 * 卖家信用等级
	 */
	private Long sellerCreditScore;

	/**
	 * 卖家id
	 */
	private Long sellerId;

	/**
	 * 商品所在店铺的推广点击url
	 */
	private String shopClickUrl;

	/**
	 * 店铺类型:B(商城),C(集市)
	 */
	private String shopType;

	/**
	 * 淘宝客类目推广URL
	 */
	private String taobaokeCatClickUrl;

	/**
	 * 商品title 宝贝名称
	 */
	private String title;

	/**
	 * 30天内交易量
	 */
	private Long volume;

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCommissionNum() {
		return commissionNum;
	}

	public void setCommissionNum(String commissionNum) {
		this.commissionNum = commissionNum;
	}

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getCommissionVolume() {
		return commissionVolume;
	}

	public void setCommissionVolume(String commissionVolume) {
		this.commissionVolume = commissionVolume;
	}

	public String getCouponEndTime() {
		return couponEndTime;
	}

	public void setCouponEndTime(String couponEndTime) {
		this.couponEndTime = couponEndTime;
	}

	public String getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(String couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(String couponRate) {
		this.couponRate = couponRate;
	}

	public String getCouponStartTime() {
		return couponStartTime;
	}

	public void setCouponStartTime(String couponStartTime) {
		this.couponStartTime = couponStartTime;
	}

	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public String getKeywordClickUrl() {
		return keywordClickUrl;
	}

	public void setKeywordClickUrl(String keywordClickUrl) {
		this.keywordClickUrl = keywordClickUrl;
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

	public Long getSellerCreditScore() {
		return sellerCreditScore;
	}

	public void setSellerCreditScore(Long sellerCreditScore) {
		this.sellerCreditScore = sellerCreditScore;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getShopClickUrl() {
		return shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getTaobaokeCatClickUrl() {
		return taobaokeCatClickUrl;
	}

	public void setTaobaokeCatClickUrl(String taobaokeCatClickUrl) {
		this.taobaokeCatClickUrl = taobaokeCatClickUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
