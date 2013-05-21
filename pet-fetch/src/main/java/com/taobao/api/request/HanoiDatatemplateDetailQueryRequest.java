package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.HanoiDatatemplateDetailQueryResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.hanoi.datatemplate.detail.query request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class HanoiDatatemplateDetailQueryRequest implements TaobaoRequest<HanoiDatatemplateDetailQueryResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* appName
	 */
	private String appName;

	/** 
	* attrId(Long):AttributeVO的唯一标识<br/>
templateId(Long):数据模板的唯一标识<br/>
name(String):数据模板详情的名称<br/>
id(Long):根据模板唯一标识去查询<br/>
pageSize:分页大小（最大值30）<br/>
currentPage:当前页码<br/>
needRetPage(Boolean 默认False):是否返回总条数<br/>
justQueryParamNotInput（Boolean 默认False）:是否只查询每天如PK的详情列表<br/>
	 */
	private String parameter;

	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppName() {
		return this.appName;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getParameter() {
		return this.parameter;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.hanoi.datatemplate.detail.query";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("app_name", this.appName);
		txtParams.put("parameter", this.parameter);
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

	public Class<HanoiDatatemplateDetailQueryResponse> getResponseClass() {
		return HanoiDatatemplateDetailQueryResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(appName,"appName");
		RequestCheckUtils.checkNotEmpty(parameter,"parameter");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
