package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.PageResult;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.hanoi.datatemplate.detail.query response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class HanoiDatatemplateDetailQueryResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5763971968857183436L;

	/** 
	 * 如果查询时需要分页，则返回分页的信息
	 */
	@ApiField("page_result")
	private PageResult pageResult;

	/** 
	 * 返回的是Detail详情列表：<br/>
paramsMap:创建数据模板时，填入PK的值<br/>
attr:AttributeVO的json格式<br/>
dataTemplateId:数据模板的唯一标识<br/>
id:数据模板详情的唯一标识<br/>
name:数据模板详情的名称<br/>
	 */
	@ApiField("values")
	private String values;

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public PageResult getPageResult( ) {
		return this.pageResult;
	}

	public void setValues(String values) {
		this.values = values;
	}
	public String getValues( ) {
		return this.values;
	}

}
