package com.pet.core.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="commodity")
public class Commodity {
	
	@Id
	private ObjectId id;
	

	public void setId(ObjectId id) {
		this.id = id;
	}

	/**类别id*/
	
	@Field("cid")
	private long cid;
	/**
	 * 推广点击url
	 */
	@Field("click_url")
	private String clickUrl;

	/**
	 * 淘宝客佣金
	 */
	@Field("commission")
	private String commission;

	/**
	 * 累计成交量.注：返回的数据是30天内累计推广量
	 */
	@Field("commission_num")
	private String commissionNum;

	/**
	 * 淘宝客佣金比率，比如：1234.00代表12.34%
	 */
	@Field("commission_rate")
	private String commissionRate;

	/**
	 * 累计总支出佣金量
	 */
	@Field("commission_volume")
	private String commissionVolume;

	/**
	 * 折扣活动结束时间
	 */
	@Field("coupon_end_time")
	private String couponEndTime;

	/**
	 * 折扣价格
	 */
	@Field("coupon_price")
	private String couponPrice;

	/**
	 * 折扣比率
	 */
	@Field("coupon_rate")
	private String couponRate;

	/**
	 * 折扣活动开始时间
	 */
	@Field("coupon_start_time")
	private String couponStartTime;

	/**
	 * 商品所在地
	 */
	@Field("item_location")
	private String itemLocation;

	/**
	 * 淘宝客关键词搜索URL
	 */
	@Field("keyword_click_url")
	private String keywordClickUrl;

	/**
	 * 卖家昵称
	 */
	@Field("nick")
	private String nick;

	/**
	 * 淘宝客商品数字id
	 */
	@Field("numIid")
	@Indexed(unique=true)
	private Long numIid;

	/**
	 * 图片url
	 */
	@Field("pic_url")
	private String picUrl;

	/**
	 * 商品价格
	 */
	@Field("price")
	private String price;

	/**
	 * 卖家信用等级
	 */
	@Field("seller_credit_score")
	private Long sellerCreditScore;

	/**
	 * 卖家id
	 */
	@Field("seller_id")
	private Long sellerId;

	/**
	 * 商品所在店铺的推广点击url
	 */
	@Field("shop_click_url")
	private String shopClickUrl;

	/**
	 * 店铺类型:B(商城),C(集市)
	 */
	@Field("shop_type")
	private String shopType;

	/**
	 * 淘宝客类目推广URL
	 */
	@Field("taobaoke_cat_click_url")
	private String taobaokeCatClickUrl;

	/**
	 * 商品title 宝贝名称
	 */
	@Field("title")
	private String title;

	/**
	 * 30天内交易量
	 */
	@Field("volume")
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
}
