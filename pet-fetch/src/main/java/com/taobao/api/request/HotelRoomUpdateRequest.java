package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.FileItem;
import com.taobao.api.TaobaoUploadRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.HotelRoomUpdateResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.hotel.room.update request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class HotelRoomUpdateRequest implements TaobaoUploadRequest<HotelRoomUpdateResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 面积。可选值：A,B,C,D。分别代表：
A：15平米以下，B：16－30平米，C：31－50平米，D：50平米以上
	 */
	private String area;

	/** 
	* 宽带服务。A,B,C,D。分别代表：
A：无宽带，B：免费宽带，C：收费宽带，D：部分收费宽带
	 */
	private String bbn;

	/** 
	* 床型。可选值：A,B,C,D,E,F,G,H,I。分别代表：A：单人床，B：大床，C：双床，D：双床/大床，E：子母床，F：上下床，G：圆形床，H：多床，I：其他床型
	 */
	private String bedType;

	/** 
	* 早餐。A,B,C,D,E。分别代表：
A：无早，B：单早，C：双早，D：三早，E：多早
	 */
	private String breakfast;

	/** 
	* 订金。0～99999900的正整数。在payment_type为订金时必须输入，存储的单位是分。不能带角分。
	 */
	private Long deposit;

	/** 
	* 商品描述。不能超过25000个汉字（50000个字符）。
	 */
	private String desc;

	/** 
	* 手续费。0～99999900的正整数。在payment_type为手续费或手续费/间夜时必须输入，存储的单位是分。不能带角分。
	 */
	private Long fee;

	/** 
	* 酒店房间商品gid。必须为数字。
	 */
	private Long gid;

	/** 
	* 购买须知。不能超过4000个汉字（8000个字符）。
	 */
	private String guide;

	/** 
	* 酒店商品是否提供发票
	 */
	private Boolean hasReceipt;

	/** 
	* 为到店支付卖家特殊使用，代表多种支付类型的房态。room_quotas可选，如果有值，也会处理。
	 */
	private String multiRoomQuotas;

	/** 
	* 支付类型。可选值：A,B,C,D,E。分别代表：
A：全额支付，B：手续费，C：订金，D：手续费/间夜，E：前台面付
	 */
	private String paymentType;

	/** 
	* 酒店商品图片。类型:JPG,GIF;最大长度:500K。支持的文件类型：gif,jpg,jpeg,png。更新的时候只能更新一张图片，此图片覆盖原有所有图片。如果不传则使用原有所有图片。
如需再发图片，需要调用商品图片上传接口，1个商品最多5张图片。
	 */
	private FileItem pic;

	/** 
	* 商品主图需要关联的图片空间的相对url。这个url所对应的图片必须要属于当前用户。pic_path和image只需要传入一个,如果两个都传，默认选择pic_path
	 */
	private String picPath;

	/** 
	* 价格类型。可选值：A,B。分别代表：A：参考预订价，B实时预订价 。未选该参数默认为参考预订价。选择实时预订价的情况下，支付类型必须选择为A(全额支付)
	 */
	private String priceType;

	/** 
	* 发票说明，不能超过100个汉字,200个字符。
	 */
	private String receiptInfo;

	/** 
	* 发票类型为其他时的发票描述,不能超过30个汉字，60个字符。
	 */
	private String receiptOtherTypeDesc;

	/** 
	* 发票类型。A,B。分别代表： A:酒店住宿发票,B:其他
	 */
	private String receiptType;

	/** 
	* 1. 全额支付类型必填 2. t代表类别(1表示任意退;2表示不能退;3表示阶梯退)，p代表退款规则（数组）， d代表天数，r代表扣除手续费比率。示例代表的意思就是"阶梯退:提前3天内退订，收取订单总额10%的违约金;提前2天内退订，收取订单总额20%的违约金，提前1天内退订，收取订单总额30%的违约金"。 3. 任意退、不能退不能指定退款规则;阶梯退不能没有退款规则;阶梯退规则最多10条,且每条规则天数、费率不能相同;阶梯退遵循天数越短,违约金越大的业务规则;天数需为整数,且90>天数>=0;费率需为整数且100<=费率<=0;阶梯退规则只有一条时,费率不能设置为100%;阶梯退规则只有一条时,不能设定0天收取0%;
	 */
	private String refundPolicyInfo;

	/** 
	* 房态信息。可以传今天开始90天内的房态信息。日期必须连续。JSON格式传递。
date：代表房态日期，格式为YYYY-MM-DD，
price：代表当天房价，0～99999999，存储的单位是分,
num：代表当天可售间数，0～999。
如：
[{"date":2011-01-28,"price":10000, "num":10},{"date":2011-01-29,"price":12000,"num":10}]
	 */
	private String roomQuotas;

	/** 
	* 设施服务。JSON格式。
value值true有此服务，false没有。
bar：吧台，catv：有线电视，ddd：国内长途电话，idd：国际长途电话，toilet：独立卫生间，pubtoliet：公共卫生间。
如：
{"bar":false,"catv":false,"ddd":false,"idd":false,"pubtoilet":false,"toilet":false}
	 */
	private String service;

	/** 
	* 商品的site_param
	 */
	private String siteParam;

	/** 
	* 床宽。可选值：A,B,C,D,E,F,G,H。分别代表：A：1米及以下，B：1.1米，C：1.2米，D：1.35米，E：1.5米，F：1.8米，G：2米，H：2.2米及以上
	 */
	private String size;

	/** 
	* 状态。可选值1，2，3。1：上架。2：下架。3：删除。传入相应状态代表去执行相应的操作。
	 */
	private Long status;

	/** 
	* 楼层。长度不超过8
	 */
	private String storey;

	/** 
	* 酒店商品名称。不能超过60字节
	 */
	private String title;

	public void setArea(String area) {
		this.area = area;
	}
	public String getArea() {
		return this.area;
	}

	public void setBbn(String bbn) {
		this.bbn = bbn;
	}
	public String getBbn() {
		return this.bbn;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getBedType() {
		return this.bedType;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getBreakfast() {
		return this.breakfast;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Long getDeposit() {
		return this.deposit;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getFee() {
		return this.fee;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Long getGid() {
		return this.gid;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getGuide() {
		return this.guide;
	}

	public void setHasReceipt(Boolean hasReceipt) {
		this.hasReceipt = hasReceipt;
	}
	public Boolean getHasReceipt() {
		return this.hasReceipt;
	}

	public void setMultiRoomQuotas(String multiRoomQuotas) {
		this.multiRoomQuotas = multiRoomQuotas;
	}
	public String getMultiRoomQuotas() {
		return this.multiRoomQuotas;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPic(FileItem pic) {
		this.pic = pic;
	}
	public FileItem getPic() {
		return this.pic;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPicPath() {
		return this.picPath;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getPriceType() {
		return this.priceType;
	}

	public void setReceiptInfo(String receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	public String getReceiptInfo() {
		return this.receiptInfo;
	}

	public void setReceiptOtherTypeDesc(String receiptOtherTypeDesc) {
		this.receiptOtherTypeDesc = receiptOtherTypeDesc;
	}
	public String getReceiptOtherTypeDesc() {
		return this.receiptOtherTypeDesc;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getReceiptType() {
		return this.receiptType;
	}

	public void setRefundPolicyInfo(String refundPolicyInfo) {
		this.refundPolicyInfo = refundPolicyInfo;
	}
	public String getRefundPolicyInfo() {
		return this.refundPolicyInfo;
	}

	public void setRoomQuotas(String roomQuotas) {
		this.roomQuotas = roomQuotas;
	}
	public String getRoomQuotas() {
		return this.roomQuotas;
	}

	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return this.service;
	}

	public void setSiteParam(String siteParam) {
		this.siteParam = siteParam;
	}
	public String getSiteParam() {
		return this.siteParam;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public String getSize() {
		return this.size;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getStatus() {
		return this.status;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}
	public String getStorey() {
		return this.storey;
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
		return "taobao.hotel.room.update";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("area", this.area);
		txtParams.put("bbn", this.bbn);
		txtParams.put("bed_type", this.bedType);
		txtParams.put("breakfast", this.breakfast);
		txtParams.put("deposit", this.deposit);
		txtParams.put("desc", this.desc);
		txtParams.put("fee", this.fee);
		txtParams.put("gid", this.gid);
		txtParams.put("guide", this.guide);
		txtParams.put("has_receipt", this.hasReceipt);
		txtParams.put("multi_room_quotas", this.multiRoomQuotas);
		txtParams.put("payment_type", this.paymentType);
		txtParams.put("pic_path", this.picPath);
		txtParams.put("price_type", this.priceType);
		txtParams.put("receipt_info", this.receiptInfo);
		txtParams.put("receipt_other_type_desc", this.receiptOtherTypeDesc);
		txtParams.put("receipt_type", this.receiptType);
		txtParams.put("refund_policy_info", this.refundPolicyInfo);
		txtParams.put("room_quotas", this.roomQuotas);
		txtParams.put("service", this.service);
		txtParams.put("site_param", this.siteParam);
		txtParams.put("size", this.size);
		txtParams.put("status", this.status);
		txtParams.put("storey", this.storey);
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
		params.put("pic", this.pic);
		return params;
	}

	public Class<HotelRoomUpdateResponse> getResponseClass() {
		return HotelRoomUpdateResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkMaxLength(area,1,"area");
		RequestCheckUtils.checkMaxLength(bbn,1,"bbn");
		RequestCheckUtils.checkMaxLength(bedType,1,"bedType");
		RequestCheckUtils.checkMaxLength(breakfast,1,"breakfast");
		RequestCheckUtils.checkMaxValue(deposit,99999900L,"deposit");
		RequestCheckUtils.checkMinValue(deposit,0L,"deposit");
		RequestCheckUtils.checkMaxLength(desc,50000,"desc");
		RequestCheckUtils.checkMaxValue(fee,99999900L,"fee");
		RequestCheckUtils.checkMinValue(fee,0L,"fee");
		RequestCheckUtils.checkNotEmpty(gid,"gid");
		RequestCheckUtils.checkMaxLength(guide,8000,"guide");
		RequestCheckUtils.checkMaxLength(paymentType,1,"paymentType");
		RequestCheckUtils.checkMaxLength(pic,512000,"pic");
		RequestCheckUtils.checkMaxLength(priceType,1,"priceType");
		RequestCheckUtils.checkMaxLength(size,1,"size");
		RequestCheckUtils.checkMaxLength(storey,8,"storey");
		RequestCheckUtils.checkMaxLength(title,90,"title");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
