package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.LogisticsTraceSearchResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.logistics.trace.search request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class LogisticsTraceSearchRequest implements TaobaoRequest<LogisticsTraceSearchResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 表明是否是拆单，默认值0，1表示拆单
	 */
	private Long isSplit;

	/** 
	* 卖家昵称
	 */
	private String sellerNick;

	/** 
	* 拆单子订单列表，对应的数据是：子订单号的列表。可以不传，但是如果传了则必须符合传递的规则。子订单必须是操作的物流订单的子订单的真子集
	 */
	private String subTid;

	/** 
	* 淘宝交易号，请勿传非淘宝交易号
	 */
	private Long tid;

	public void setIsSplit(Long isSplit) {
		this.isSplit = isSplit;
	}
	public Long getIsSplit() {
		return this.isSplit;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public String getSellerNick() {
		return this.sellerNick;
	}

	public void setSubTid(String subTid) {
		this.subTid = subTid;
	}
	public String getSubTid() {
		return this.subTid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Long getTid() {
		return this.tid;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.logistics.trace.search";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("is_split", this.isSplit);
		txtParams.put("seller_nick", this.sellerNick);
		txtParams.put("sub_tid", this.subTid);
		txtParams.put("tid", this.tid);
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

	public Class<LogisticsTraceSearchResponse> getResponseClass() {
		return LogisticsTraceSearchResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(sellerNick,"sellerNick");
		RequestCheckUtils.checkMaxListSize(subTid,50,"subTid");
		RequestCheckUtils.checkNotEmpty(tid,"tid");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
