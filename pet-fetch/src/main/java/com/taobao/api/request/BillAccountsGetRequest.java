package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.BillAccountsGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.bill.accounts.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class BillAccountsGetRequest implements TaobaoRequest<BillAccountsGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 需要获取的科目ID
	 */
	private String aids;

	/** 
	* 需要返回的字段
	 */
	private String fields;

	public void setAids(String aids) {
		this.aids = aids;
	}
	public String getAids() {
		return this.aids;
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
		return "taobao.bill.accounts.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("aids", this.aids);
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

	public Class<BillAccountsGetResponse> getResponseClass() {
		return BillAccountsGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkMaxListSize(aids,200,"aids");
		RequestCheckUtils.checkNotEmpty(fields,"fields");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
