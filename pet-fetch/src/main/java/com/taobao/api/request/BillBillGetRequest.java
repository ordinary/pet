package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.BillBillGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.bill.bill.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class BillBillGetRequest implements TaobaoRequest<BillBillGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 账单编号
	 */
	private Long bid;

	/** 
	* 传入需要返回的字段
	 */
	private String fields;

	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Long getBid() {
		return this.bid;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getFields() {
		return this.fields;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.bill.bill.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("bid", this.bid);
		txtParams.put("fields", this.fields);
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

	public Class<BillBillGetResponse> getResponseClass() {
		return BillBillGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(bid,"bid");
		RequestCheckUtils.checkNotEmpty(fields,"fields");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
