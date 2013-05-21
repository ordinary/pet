package com.taobao.api.request;

import java.util.Date;
import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.FileItem;
import com.taobao.api.TaobaoUploadRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TravelItemAddResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.travel.item.add request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class TravelItemAddRequest implements TaobaoUploadRequest<TravelItemAddResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 商品上传后的状态。可选值:onsale(出售中),instock(仓库中);默认值:onsale。
	 */
	private String approveStatus;

	/** 
	* 商品的积分返点比例。如:5,表示:返点比例0.5%. 注意：返点比例必须是>0的整数.B商家在发布非虚拟商品时，这个字段必须设置，返点必须是 5的倍数，即0.5%的倍数。注意该字段值最高值不超过500，即50%。
	 */
	private Long auctionPoint;

	/** 
	* 商品所属类目ID。发布旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)。
	 */
	private Long cid;

	/** 
	* 宝贝所在地城市。如杭州 。可以通过http://dl.open.taobao.com/sdk/商品城市列表.rar查询 ，该字段为必填字段
	 */
	private String city;

	/** 
	* 商品描述，不超过50000个字符。
	 */
	private String desc;

	/** 
	* 最晚成团提前天数，最小0天，最大为180天。
	 */
	private Long duration;

	/** 
	* 费用包含，不超过1500个字符。
	 */
	private String feeInclude;

	/** 
	* 费用不包，不超过1500个字符。
	 */
	private String feeNotInclude;

	/** 
	* 支持会员打折。可选值:true,false;默认值:false(不打折)。
	 */
	private Boolean hasDiscount;

	/** 
	* 是否有发票。可选值:true,false (商城卖家此字段必须为true);默认值:false(无发票)。
	 */
	private Boolean hasInvoice;

	/** 
	* 橱窗推荐。可选值:true,false;默认值:false(不推荐)，B商家不用设置该字段，均为true。
	 */
	private Boolean hasShowcase;

	/** 
	* 商品主图。类型:JPG,GIF;最大长度:500k，支持的文件类型：gif,jpg,jpeg,png。
	 */
	private FileItem image;

	/** 
	* 是否为铁定出游商品的参数
设置为true，那么该商品为铁定出游商品；
设置为false，那么该商品为非铁定出游商品。
默认为false
	 */
	private Boolean isTdcy;

	/** 
	* 定时上架时间。(时间格式：yyyy-MM-dd HH:mm:ss)。
	 */
	private Date listTime;

	/** 
	* 商品库存。如果发布旅游度假线路宝贝，该字段可以忽略。
	 */
	private Long num;

	/** 
	* 预定须知，不超过1500个字符。
	 */
	private String orderInfo;

	/** 
	* 商家的外部编码，最大为512字节。
	 */
	private String outerId;

	/** 
	* 商品主图需要关联的图片空间的相对url。这个url所对应的图片必须要属于当前用户。pic_path和image只需要传入一个,如果两个都传，默认选择pic_path。
	 */
	private String picPath;

	/** 
	* 玩法描述，已经废弃，不用填写。
	 */
	private String playDesc;

	/** 
	* 线路玩法id，已经废弃，不用填写。
	 */
	private Long playId;

	/** 
	* 商品一口价,以“分”为单位。如果发布旅游度假线路的宝贝，该字段可以忽略。
	 */
	private Long price;

	/** 
	* Json串，价格日历信息（针对旅游度假线路的销售属性），定义了2012年6月8号成人价，儿童价，单房差均为10元，库存为100的日历价格。price_calendar属性中一个{}中表示1天的价格日历信息，可以最多输入90天的价格日历，最小和最大日期不能跨度超过90天。其中，"man_num"：成人名额； "man_price"：成人价格，以分为单位；"child_num"：儿童名额；"child_price"：儿童价格，以分为单位；"diff_price"：单人房差，以分为单位。
	 */
	private String priceCalendar;

	/** 
	* 商品属性列表。格式为：pid:vid;pid:vid。例如发布度假线路商品，那么这里就需要填写：出发地属性id:城市id;目的地市属性id:目的地市id;……等等。
	 */
	private String props;

	/** 
	* 宝贝所在地省份。如浙江，具体可以下载http://dl.open.taobao.com/sdk/商品城市列表.rar 取到，该字段为必填字段
	 */
	private String prov;

	/** 
	* 退改规定，不超过1500个字符。
	 */
	private String refundRegulation;

	/** 
	* 商品秒杀三个值：可选类型web_only(只能通过web网络秒杀)，wap_only(只能通过wap网络秒杀)，web_and_wap(既能通过web秒杀也能通过wap秒杀)。
	 */
	private String secondKill;

	/** 
	* 关联商品与店铺类目，结构:",cid1,cid2,...,"，如果店铺类目存在二级类目，必须传入子类目cids。
	 */
	private String sellerCids;

	/** 
	* sku销售属性串对应的价格，精确到分，每一个属性串都会对应一个价格，单位为分。sku_prices的数组大小应该和sku_properties的数组大小一致。如果发布线路的商品，该字段可以忽略。
	 */
	private String skuPrices;

	/** 
	* sku销售属性串，调用taobao.travel.itemprops.get接口获取商品销售属性code，然后拼接成pid:vid;pid:vid格式。如果发布线路的商品，该字段可以忽略。
	 */
	private String skuProperties;

	/** 
	* sku销售属性串对应的库存，每一个属性串都会对应一个库存，显然sku_quantities的数组大小应该和sku_properties的数组大小一致。如果发布线路的商品，该字段可以忽略。
	 */
	private String skuQuantities;

	/** 
	* 商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改，集市卖家默认拍下减库存;商城卖家默认付款减库存。
	 */
	private Long subStock;

	/** 
	* 商品标题。不能超过60个字节或者30个汉字
	 */
	private String title;

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getApproveStatus() {
		return this.approveStatus;
	}

	public void setAuctionPoint(Long auctionPoint) {
		this.auctionPoint = auctionPoint;
	}
	public Long getAuctionPoint() {
		return this.auctionPoint;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getCid() {
		return this.cid;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return this.city;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Long getDuration() {
		return this.duration;
	}

	public void setFeeInclude(String feeInclude) {
		this.feeInclude = feeInclude;
	}
	public String getFeeInclude() {
		return this.feeInclude;
	}

	public void setFeeNotInclude(String feeNotInclude) {
		this.feeNotInclude = feeNotInclude;
	}
	public String getFeeNotInclude() {
		return this.feeNotInclude;
	}

	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}
	public Boolean getHasDiscount() {
		return this.hasDiscount;
	}

	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}
	public Boolean getHasInvoice() {
		return this.hasInvoice;
	}

	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}
	public Boolean getHasShowcase() {
		return this.hasShowcase;
	}

	public void setImage(FileItem image) {
		this.image = image;
	}
	public FileItem getImage() {
		return this.image;
	}

	public void setIsTdcy(Boolean isTdcy) {
		this.isTdcy = isTdcy;
	}
	public Boolean getIsTdcy() {
		return this.isTdcy;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}
	public Date getListTime() {
		return this.listTime;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	public Long getNum() {
		return this.num;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public String getOrderInfo() {
		return this.orderInfo;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public String getOuterId() {
		return this.outerId;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPicPath() {
		return this.picPath;
	}

	public void setPlayDesc(String playDesc) {
		this.playDesc = playDesc;
	}
	public String getPlayDesc() {
		return this.playDesc;
	}

	public void setPlayId(Long playId) {
		this.playId = playId;
	}
	public Long getPlayId() {
		return this.playId;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPrice() {
		return this.price;
	}

	public void setPriceCalendar(String priceCalendar) {
		this.priceCalendar = priceCalendar;
	}
	public String getPriceCalendar() {
		return this.priceCalendar;
	}

	public void setProps(String props) {
		this.props = props;
	}
	public String getProps() {
		return this.props;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getProv() {
		return this.prov;
	}

	public void setRefundRegulation(String refundRegulation) {
		this.refundRegulation = refundRegulation;
	}
	public String getRefundRegulation() {
		return this.refundRegulation;
	}

	public void setSecondKill(String secondKill) {
		this.secondKill = secondKill;
	}
	public String getSecondKill() {
		return this.secondKill;
	}

	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}
	public String getSellerCids() {
		return this.sellerCids;
	}

	public void setSkuPrices(String skuPrices) {
		this.skuPrices = skuPrices;
	}
	public String getSkuPrices() {
		return this.skuPrices;
	}

	public void setSkuProperties(String skuProperties) {
		this.skuProperties = skuProperties;
	}
	public String getSkuProperties() {
		return this.skuProperties;
	}

	public void setSkuQuantities(String skuQuantities) {
		this.skuQuantities = skuQuantities;
	}
	public String getSkuQuantities() {
		return this.skuQuantities;
	}

	public void setSubStock(Long subStock) {
		this.subStock = subStock;
	}
	public Long getSubStock() {
		return this.subStock;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.travel.item.add";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("approve_status", this.approveStatus);
		txtParams.put("auction_point", this.auctionPoint);
		txtParams.put("cid", this.cid);
		txtParams.put("city", this.city);
		txtParams.put("desc", this.desc);
		txtParams.put("duration", this.duration);
		txtParams.put("fee_include", this.feeInclude);
		txtParams.put("fee_not_include", this.feeNotInclude);
		txtParams.put("has_discount", this.hasDiscount);
		txtParams.put("has_invoice", this.hasInvoice);
		txtParams.put("has_showcase", this.hasShowcase);
		txtParams.put("is_tdcy", this.isTdcy);
		txtParams.put("list_time", this.listTime);
		txtParams.put("num", this.num);
		txtParams.put("order_info", this.orderInfo);
		txtParams.put("outer_id", this.outerId);
		txtParams.put("pic_path", this.picPath);
		txtParams.put("play_desc", this.playDesc);
		txtParams.put("play_id", this.playId);
		txtParams.put("price", this.price);
		txtParams.put("price_calendar", this.priceCalendar);
		txtParams.put("props", this.props);
		txtParams.put("prov", this.prov);
		txtParams.put("refund_regulation", this.refundRegulation);
		txtParams.put("second_kill", this.secondKill);
		txtParams.put("seller_cids", this.sellerCids);
		txtParams.put("sku_prices", this.skuPrices);
		txtParams.put("sku_properties", this.skuProperties);
		txtParams.put("sku_quantities", this.skuQuantities);
		txtParams.put("sub_stock", this.subStock);
		txtParams.put("title", this.title);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new TaobaoHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Map<String, FileItem> getFileParams() {
		Map<String, FileItem> params = new HashMap<String, FileItem>();
		params.put("image", this.image);
		return params;
	}

	public Class<TravelItemAddResponse> getResponseClass() {
		return TravelItemAddResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkMaxValue(auctionPoint,500L,"auctionPoint");
		RequestCheckUtils.checkMinValue(auctionPoint,0L,"auctionPoint");
		RequestCheckUtils.checkNotEmpty(cid,"cid");
		RequestCheckUtils.checkNotEmpty(desc,"desc");
		RequestCheckUtils.checkMaxLength(desc,50000,"desc");
		RequestCheckUtils.checkNotEmpty(duration,"duration");
		RequestCheckUtils.checkMaxValue(duration,180L,"duration");
		RequestCheckUtils.checkMinValue(duration,0L,"duration");
		RequestCheckUtils.checkNotEmpty(feeInclude,"feeInclude");
		RequestCheckUtils.checkMaxLength(feeInclude,1500,"feeInclude");
		RequestCheckUtils.checkNotEmpty(feeNotInclude,"feeNotInclude");
		RequestCheckUtils.checkMaxLength(feeNotInclude,1500,"feeNotInclude");
		RequestCheckUtils.checkNotEmpty(orderInfo,"orderInfo");
		RequestCheckUtils.checkMaxLength(orderInfo,1500,"orderInfo");
		RequestCheckUtils.checkMaxLength(outerId,512,"outerId");
		RequestCheckUtils.checkMaxLength(playDesc,1500,"playDesc");
		RequestCheckUtils.checkNotEmpty(priceCalendar,"priceCalendar");
		RequestCheckUtils.checkNotEmpty(props,"props");
		RequestCheckUtils.checkNotEmpty(refundRegulation,"refundRegulation");
		RequestCheckUtils.checkMaxLength(refundRegulation,1500,"refundRegulation");
		RequestCheckUtils.checkMaxListSize(sellerCids,20,"sellerCids");
		RequestCheckUtils.checkMaxLength(sellerCids,256,"sellerCids");
		RequestCheckUtils.checkMaxListSize(skuPrices,380,"skuPrices");
		RequestCheckUtils.checkMaxListSize(skuProperties,380,"skuProperties");
		RequestCheckUtils.checkMaxListSize(skuQuantities,380,"skuQuantities");
		RequestCheckUtils.checkMaxValue(subStock,2L,"subStock");
		RequestCheckUtils.checkMinValue(subStock,0L,"subStock");
		RequestCheckUtils.checkNotEmpty(title,"title");
		RequestCheckUtils.checkMaxLength(title,60,"title");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
