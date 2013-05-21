package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.hanoi.datatemplate.detail.delete response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class HanoiDatatemplateDetailDeleteResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8759729263127815896L;

	/** 
	 * 返回删除的条数
	 */
	@ApiField("value")
	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}
	public Long getValue( ) {
		return this.value;
	}

}
