package com.taobao.api.domain;

import java.util.Date;
import java.util.List;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;

/**
 * 旅游商品结构。
 *
 * @author auto create
 * @since 1.0, null
 */
public class TravelItem extends TaobaoObject {

	private static final long serialVersionUID = 7361916432166538325L;

	/**
	 * 商品发布后的状态。onsale出售中，instock库中。
	 */
	@ApiField("approve_status")
	private String approveStatus;

	/**
	 * 商城返点比例，为5的倍数，最低0.5%。
	 */
	@ApiField("auction_point")
	private Long auctionPoint;

	/**
	 * 商品所属类目ID。发布旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)。
	 */
	@ApiField("cid")
	private Long cid;

	/**
	 * 商品发布时间（格式：yyyy-MM-dd HH:mm:ss）。
	 */
	@ApiField("created")
	private Date created;

	/**
	 * 下架时间（格式：yyyy-MM-dd HH:mm:ss）。
	 */
	@ApiField("delist_time")
	private Date delistTime;

	/**
	 * 商品描述，字数要大于5个字符，小于50000个字符。
	 */
	@ApiField("desc")
	private String desc;

	/**
	 * 商品详情url
	 */
	@ApiField("detail_url")
	private String detailUrl;

	/**
	 * 最晚成团提前天数，最小0天，最大为180天。
	 */
	@ApiField("duration")
	private Long duration;

	/**
	 * 费用包含，不超过1500个字符。
	 */
	@ApiField("fee_include")
	private String feeInclude;

	/**
	 * 费用不包，不超过1500个字符。
	 */
	@ApiField("fee_not_include")
	private String feeNotInclude;

	/**
	 * 运费承担方式，seller（卖家承担），buyer（买家承担）。
	 */
	@ApiField("freight_payer")
	private String freightPayer;

	/**
	 * 会员打折（是：true，否：false）。
	 */
	@ApiField("has_discount")
	private Boolean hasDiscount;

	/**
	 * 是否有发票（是：true，否：false）。
	 */
	@ApiField("has_invoice")
	private Boolean hasInvoice;

	/**
	 * 是否有橱窗（是：true，否：false）。
	 */
	@ApiField("has_showcase")
	private Boolean hasShowcase;

	/**
	 * 非分销商品：0，代销：1，经销：2。
	 */
	@ApiField("is_fenxiao")
	private Long isFenxiao;

	/**
	 * 是否为铁定出游商品的参数设置为true，那么该商品为铁定出游商品；设置为false，那么该商品为非铁定出游商品。默认为false
	 */
	@ApiField("is_tdcy")
	private Boolean isTdcy;

	/**
	 * 是否定时上架商品。
	 */
	@ApiField("is_timing")
	private Boolean isTiming;

	/**
	 * 商品数字ID
	 */
	@ApiField("item_id")
	private Long itemId;

	/**
	 * 商品图片列表(包括主图)。最多5个
	 */
	@ApiListField("item_imgs")
	@ApiField("travel_item_img")
	private List<TravelItemImg> itemImgs;

	/**
	 * 上架时间（格式：yyyy-MM-dd HH:mm:ss）。
	 */
	@ApiField("list_time")
	private Date listTime;

	/**
	 * 宝贝所在地，格式为（省份:城市）。
	 */
	@ApiField("location")
	private String location;

	/**
	 * 允许最多橱窗数。
	 */
	@ApiField("max_showcase")
	private Long maxShowcase;

	/**
	 * 商品修改时间（格式：yyyy-MM-dd HH:mm:ss）。
	 */
	@ApiField("modified")
	private Date modified;

	/**
	 * 卖家昵称。
	 */
	@ApiField("nick")
	private String nick;

	/**
	 * 商品数量。
	 */
	@ApiField("num")
	private Long num;

	/**
	 * 预定须知，不超过1500个字符。
	 */
	@ApiField("order_info")
	private String orderInfo;

	/**
	 * 商家编码。
	 */
	@ApiField("outer_id")
	private String outerId;

	/**
	 * 商品主图片地址。
	 */
	@ApiField("pic_url")
	private String picUrl;

	/**
	 * 商品价格，格式：500；单位：分；精确到：分。表示：5.00元
	 */
	@ApiField("price")
	private Long price;

	/**
	 * Json串，价格日历信息（针对旅游度假线路的销售属性），定义了2012年6月8号成人价，儿童价，单房差均为10元，库存为100的日历价格。price_calendar属性中一个{}中表示1天的价格日历信息，可以最多输入90天的价格日历，最小和最大日期不能跨度超过90天。其中，"man_num"：成人名额；
"man_price"：成人价格，以分为单位；"child_num"：儿童名额；"child_price"：儿童价格，以分为单位；"diff_price"：单人房差，以分为单位。
	 */
	@ApiField("price_calendar")
	private String priceCalendar;

	/**
	 * 商品属性列表。格式为：pid:vid;pid:vid。例如发布度假线路商品，那么这里就需要填写：出发地属性id:城市id;目的地市属性id:目的地市id;……等等。（注意：不会包含SKU属性）。
	 */
	@ApiField("props")
	private String props;

	/**
	 * 商品属性名称。标识着props内容里面的pid和vid所对应的名称。格式为：pid1:vid1:pid_name1:vid_name1;pid2:vid2:pid_name2:vid_name2……(注：属性名称中的冒号":"被转换为："#cln#"; 分号";"被转换为："#scln#" )。
	 */
	@ApiField("props_name")
	private String propsName;

	/**
	 * 退改规定，不超过1500个字符。
	 */
	@ApiField("refund_regulation")
	private String refundRegulation;

	/**
	 * 秒杀商品类型。打上秒杀标记的商品，用户只能下架并不能再上架，其他任何编辑或删除操作都不能进行。如果用户想取消秒杀标记，需要联系小二进行操作。如果秒杀结束需要自由编辑请联系活动负责人（小二）去掉秒杀标记。可选类型 web_only(只能通过web网络秒杀) wap_only(只能通过wap网络秒杀) web_and_wap(既能通过web秒杀也能通过wap秒杀)。
	 */
	@ApiField("second_kill")
	private String secondKill;

	/**
	 * 商品所属的卖家店铺分类的Id，一件商品可以由卖家放在店铺内部的多个商品类目下，多个以“,”分割。
	 */
	@ApiField("seller_cids")
	private String sellerCids;

	/**
	 * 标准商品Sku列表。
	 */
	@ApiListField("skus")
	@ApiField("travel_item_sku")
	private List<TravelItemSku> skus;

	/**
	 * 商品开始出售时间（格式：yyyy-MM-dd HH:mm:ss）。
	 */
	@ApiField("start")
	private Date start;

	/**
	 * 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改，集市卖家默认拍下减库存;商城卖家默认付款减库存。
	 */
	@ApiField("sub_stock")
	private Long subStock;

	/**
	 * 商品标题。
	 */
	@ApiField("title")
	private String title;

	/**
	 * 商品关联玩法结构。
	 */
	@ApiField("travel_play")
	private TravelPlay travelPlay;

	/**
	 * 商品类型(fixed:一口价;auction:拍卖)注：取消团购。
	 */
	@ApiField("type")
	private String type;

	/**
	 * 已被使用的橱窗数。
	 */
	@ApiField("used_showcase")
	private Long usedShowcase;

	/**
	 * 商品是否违规，违规：true，不违规：false。
	 */
	@ApiField("violation")
	private Boolean violation;

	/**
	 * 不带html标签的desc文本信息，该字段只在taobao.item.get接口中返回。
	 */
	@ApiField("wap_desc")
	private String wapDesc;

	/**
	 * 适合wap应用的商品详情url。
	 */
	@ApiField("wap_detail_url")
	private String wapDetailUrl;

	public String getApproveStatus() {
		return this.approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Long getAuctionPoint() {
		return this.auctionPoint;
	}
	public void setAuctionPoint(Long auctionPoint) {
		this.auctionPoint = auctionPoint;
	}

	public Long getCid() {
		return this.cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDelistTime() {
		return this.delistTime;
	}
	public void setDelistTime(Date delistTime) {
		this.delistTime = delistTime;
	}

	public String getDesc() {
		return this.desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetailUrl() {
		return this.detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public Long getDuration() {
		return this.duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getFeeInclude() {
		return this.feeInclude;
	}
	public void setFeeInclude(String feeInclude) {
		this.feeInclude = feeInclude;
	}

	public String getFeeNotInclude() {
		return this.feeNotInclude;
	}
	public void setFeeNotInclude(String feeNotInclude) {
		this.feeNotInclude = feeNotInclude;
	}

	public String getFreightPayer() {
		return this.freightPayer;
	}
	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public Boolean getHasDiscount() {
		return this.hasDiscount;
	}
	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public Boolean getHasInvoice() {
		return this.hasInvoice;
	}
	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public Boolean getHasShowcase() {
		return this.hasShowcase;
	}
	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}

	public Long getIsFenxiao() {
		return this.isFenxiao;
	}
	public void setIsFenxiao(Long isFenxiao) {
		this.isFenxiao = isFenxiao;
	}

	public Boolean getIsTdcy() {
		return this.isTdcy;
	}
	public void setIsTdcy(Boolean isTdcy) {
		this.isTdcy = isTdcy;
	}

	public Boolean getIsTiming() {
		return this.isTiming;
	}
	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
	}

	public Long getItemId() {
		return this.itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public List<TravelItemImg> getItemImgs() {
		return this.itemImgs;
	}
	public void setItemImgs(List<TravelItemImg> itemImgs) {
		this.itemImgs = itemImgs;
	}

	public Date getListTime() {
		return this.listTime;
	}
	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public Long getMaxShowcase() {
		return this.maxShowcase;
	}
	public void setMaxShowcase(Long maxShowcase) {
		this.maxShowcase = maxShowcase;
	}

	public Date getModified() {
		return this.modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getNick() {
		return this.nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getNum() {
		return this.num;
	}
	public void setNum(Long num) {
		this.num = num;
	}

	public String getOrderInfo() {
		return this.orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getOuterId() {
		return this.outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getPicUrl() {
		return this.picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Long getPrice() {
		return this.price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}

	public String getPriceCalendar() {
		return this.priceCalendar;
	}
	public void setPriceCalendar(String priceCalendar) {
		this.priceCalendar = priceCalendar;
	}

	public String getProps() {
		return this.props;
	}
	public void setProps(String props) {
		this.props = props;
	}

	public String getPropsName() {
		return this.propsName;
	}
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public String getRefundRegulation() {
		return this.refundRegulation;
	}
	public void setRefundRegulation(String refundRegulation) {
		this.refundRegulation = refundRegulation;
	}

	public String getSecondKill() {
		return this.secondKill;
	}
	public void setSecondKill(String secondKill) {
		this.secondKill = secondKill;
	}

	public String getSellerCids() {
		return this.sellerCids;
	}
	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}

	public List<TravelItemSku> getSkus() {
		return this.skus;
	}
	public void setSkus(List<TravelItemSku> skus) {
		this.skus = skus;
	}

	public Date getStart() {
		return this.start;
	}
	public void setStart(Date start) {
		this.start = start;
	}

	public Long getSubStock() {
		return this.subStock;
	}
	public void setSubStock(Long subStock) {
		this.subStock = subStock;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public TravelPlay getTravelPlay() {
		return this.travelPlay;
	}
	public void setTravelPlay(TravelPlay travelPlay) {
		this.travelPlay = travelPlay;
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getUsedShowcase() {
		return this.usedShowcase;
	}
	public void setUsedShowcase(Long usedShowcase) {
		this.usedShowcase = usedShowcase;
	}

	public Boolean getViolation() {
		return this.violation;
	}
	public void setViolation(Boolean violation) {
		this.violation = violation;
	}

	public String getWapDesc() {
		return this.wapDesc;
	}
	public void setWapDesc(String wapDesc) {
		this.wapDesc = wapDesc;
	}

	public String getWapDetailUrl() {
		return this.wapDetailUrl;
	}
	public void setWapDetailUrl(String wapDetailUrl) {
		this.wapDetailUrl = wapDetailUrl;
	}

}
