package com.taobao.api.request;

import java.util.Date;
import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.LogisticsOrderstorePushResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.logistics.orderstore.push request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class LogisticsOrderstorePushRequest implements TaobaoRequest<LogisticsOrderstorePushResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 表明是否是拆单，默认值0，1表示拆单
	 */
	private Long isSplit;

	/** 
	* 流转状态发生时间
	 */
	private Date occureTime;

	/** 
	* 仓内操作描述
	 */
	private String operateDetail;

	/** 
	* 快递业务员联系方式
	 */
	private String operatorContact;

	/** 
	* 快递业务员名称
	 */
	private String operatorName;

	/** 
	* 拆单子订单列表，对应的数据是：子订单号的列表。可以不传，但是如果传了则必须符合传递的规则。子订单必须是操作的物流订单的子订单的真子集！
	 */
	private String subTid;

	/** 
	* 淘宝订单交易号
	 */
	private Long tradeId;

	public void setIsSplit(Long isSplit) {
		this.isSplit = isSplit;
	}
	public Long getIsSplit() {
		return this.isSplit;
	}

	public void setOccureTime(Date occureTime) {
		this.occureTime = occureTime;
	}
	public Date getOccureTime() {
		return this.occureTime;
	}

	public void setOperateDetail(String operateDetail) {
		this.operateDetail = operateDetail;
	}
	public String getOperateDetail() {
		return this.operateDetail;
	}

	public void setOperatorContact(String operatorContact) {
		this.operatorContact = operatorContact;
	}
	public String getOperatorContact() {
		return this.operatorContact;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setSubTid(String subTid) {
		this.subTid = subTid;
	}
	public String getSubTid() {
		return this.subTid;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public Long getTradeId() {
		return this.tradeId;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.logistics.orderstore.push";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("is_split", this.isSplit);
		txtParams.put("occure_time", this.occureTime);
		txtParams.put("operate_detail", this.operateDetail);
		txtParams.put("operator_contact", this.operatorContact);
		txtParams.put("operator_name", this.operatorName);
		txtParams.put("sub_tid", this.subTid);
		txtParams.put("trade_id", this.tradeId);
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

	public Class<LogisticsOrderstorePushResponse> getResponseClass() {
		return LogisticsOrderstorePushResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(occureTime,"occureTime");
		RequestCheckUtils.checkNotEmpty(operateDetail,"operateDetail");
		RequestCheckUtils.checkMaxLength(operateDetail,200,"operateDetail");
		RequestCheckUtils.checkMaxLength(operatorContact,20,"operatorContact");
		RequestCheckUtils.checkMaxLength(operatorName,20,"operatorName");
		RequestCheckUtils.checkMaxListSize(subTid,50,"subTid");
		RequestCheckUtils.checkNotEmpty(tradeId,"tradeId");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
