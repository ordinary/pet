package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.HanoiDatatemplateDetailDeleteResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.hanoi.datatemplate.detail.delete request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class HanoiDatatemplateDetailDeleteRequest implements TaobaoRequest<HanoiDatatemplateDetailDeleteResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* appName
	 */
	private String appName;

	/** 
	* Long类型数组的JSON格式
	 */
	private String dataTemplateDetailIds;

	/** 
	* id:数据模板唯一标识
	 */
	private String dataTemplateVo;

	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppName() {
		return this.appName;
	}

	public void setDataTemplateDetailIds(String dataTemplateDetailIds) {
		this.dataTemplateDetailIds = dataTemplateDetailIds;
	}
	public String getDataTemplateDetailIds() {
		return this.dataTemplateDetailIds;
	}

	public void setDataTemplateVo(String dataTemplateVo) {
		this.dataTemplateVo = dataTemplateVo;
	}
	public String getDataTemplateVo() {
		return this.dataTemplateVo;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.hanoi.datatemplate.detail.delete";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("app_name", this.appName);
		txtParams.put("data_template_detail_ids", this.dataTemplateDetailIds);
		txtParams.put("data_template_vo", this.dataTemplateVo);
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

	public Class<HanoiDatatemplateDetailDeleteResponse> getResponseClass() {
		return HanoiDatatemplateDetailDeleteResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(appName,"appName");
		RequestCheckUtils.checkNotEmpty(dataTemplateDetailIds,"dataTemplateDetailIds");
		RequestCheckUtils.checkNotEmpty(dataTemplateVo,"dataTemplateVo");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
