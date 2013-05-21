package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TopatsFenxiaoOrdersGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.topats.fenxiao.orders.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class TopatsFenxiaoOrdersGetRequest implements TaobaoRequest<TopatsFenxiaoOrdersGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 结束时间 格式 yyyyMMdd表示yyyy-MM-dd 00:00:00.开始与结束时间不能超过90天。
	 */
	private String endCreated;

	/** 
	* 多个字段用","分隔。

fields
如果为空：返回所有采购单对象(purchase_orders)字段。
如果不为空：返回指定采购单对象(purchase_orders)字段。

例1：
sub_purchase_orders.tc_order_id 表示只返回tc_order_id 
例2：
sub_purchase_orders表示只返回子采购单列表
	 */
	private String fields;

	/** 
	* 起始时间 格式 yyyyMMdd表示yyyy-MM-dd 00:00:00.开始与结束时间不能超过90天且开始时间不能为90天前
	 */
	private String startCreated;

	/** 
	* 交易状态，不传默认查询所有采购单根据身份选择自身状态可选值:<br> *供应商：<br> WAIT_SELLER_SEND_GOODS(等待发货)<br> WAIT_SELLER_CONFIRM_PAY(待确认收款)<br> WAIT_BUYER_PAY(等待付款)<br> WAIT_BUYER_CONFIRM_GOODS(已发货)<br> TRADE_REFUNDING(退款中)<br> TRADE_FINISHED(采购成功)<br> TRADE_CLOSED(已关闭)<br> *分销商：<br> WAIT_BUYER_PAY(等待付款)<br> WAIT_BUYER_CONFIRM_GOODS(待收货确认)<br> TRADE_FOR_PAY(已付款)<br> TRADE_REFUNDING(退款中)<br> TRADE_FINISHED(采购成功)<br> TRADE_CLOSED(已关闭)<br>
	 */
	private String status;

	public void setEndCreated(String endCreated) {
		this.endCreated = endCreated;
	}
	public String getEndCreated() {
		return this.endCreated;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getFields() {
		return this.fields;
	}

	public void setStartCreated(String startCreated) {
		this.startCreated = startCreated;
	}
	public String getStartCreated() {
		return this.startCreated;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.topats.fenxiao.orders.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("end_created", this.endCreated);
		txtParams.put("fields", this.fields);
		txtParams.put("start_created", this.startCreated);
		txtParams.put("status", this.status);
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

	public Class<TopatsFenxiaoOrdersGetResponse> getResponseClass() {
		return TopatsFenxiaoOrdersGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(endCreated,"endCreated");
		RequestCheckUtils.checkNotEmpty(startCreated,"startCreated");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
