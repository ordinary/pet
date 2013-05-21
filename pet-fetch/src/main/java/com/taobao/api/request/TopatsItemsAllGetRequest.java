package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TopatsItemsAllGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.topats.items.all.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class TopatsItemsAllGetRequest implements TaobaoRequest<TopatsItemsAllGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 商品修改结束时间，格式yyyyMMdd，取值范围：前90天内~昨天，其中start_time<=end_time，如20120531相当于取商品修改时间到2012-05-31 23:59:59为止的商品。注：如果start_time和end_time相同，表示取一天的商品数据。<span style="color:red">强烈建议图书类卖家把三个月商品拆分成3次来获取，否则单个任务会消耗很长时间。<span>
	 */
	private String endTime;

	/** 
	* Item结构体中的所有字段。<span style="color:red">请尽量按需获取，如果只取num_iid和modified字段，获取商品数据速度会超快。</span>
	 */
	private String fields;

	/** 
	* 商品修改开始时间，格式yyyyMMdd，取值范围：前90天内~昨天。如：20120501相当于取商品修改时间从2012-05-01 00:00:00开始的订单。
	 */
	private String startTime;

	/** 
	* 商品状态，可选值：onsale,instock。多个值用半角逗号分隔。默认查询两个状态的商品。
	 */
	private String status;

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndTime() {
		return this.endTime;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getFields() {
		return this.fields;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartTime() {
		return this.startTime;
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
		return "taobao.topats.items.all.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("end_time", this.endTime);
		txtParams.put("fields", this.fields);
		txtParams.put("start_time", this.startTime);
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

	public Class<TopatsItemsAllGetResponse> getResponseClass() {
		return TopatsItemsAllGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(endTime,"endTime");
		RequestCheckUtils.checkNotEmpty(fields,"fields");
		RequestCheckUtils.checkNotEmpty(startTime,"startTime");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
