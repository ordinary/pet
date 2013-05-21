package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.AlipayMicropayOrderGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: alipay.micropay.order.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class AlipayMicropayOrderGetRequest implements TaobaoRequest<AlipayMicropayOrderGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 支付宝订单号，冻结流水号(创建冻结订单返回)
	 */
	private String alipayOrderNo;

	/** 
	* 支付宝用户给应用的授权.
	 */
	private String authToken;

	public void setAlipayOrderNo(String alipayOrderNo) {
		this.alipayOrderNo = alipayOrderNo;
	}
	public String getAlipayOrderNo() {
		return this.alipayOrderNo;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getAuthToken() {
		return this.authToken;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "alipay.micropay.order.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("alipay_order_no", this.alipayOrderNo);
		txtParams.put("auth_token", this.authToken);
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

	public Class<AlipayMicropayOrderGetResponse> getResponseClass() {
		return AlipayMicropayOrderGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(alipayOrderNo,"alipayOrderNo");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
