package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.fenxiao.refund.create response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class FenxiaoRefundCreateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2512664861448256668L;

	/** 
	 * 退款是否创建成功
	 */
	@ApiField("is_success")
	private Boolean isSuccess;

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Boolean getIsSuccess( ) {
		return this.isSuccess;
	}

}
