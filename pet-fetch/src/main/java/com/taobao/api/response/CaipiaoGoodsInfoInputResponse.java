package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.caipiao.goods.info.input response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class CaipiaoGoodsInfoInputResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1321657183485458417L;

	/** 
	 * 录入操作是否成功
	 */
	@ApiField("input_result")
	private Boolean inputResult;

	public void setInputResult(Boolean inputResult) {
		this.inputResult = inputResult;
	}
	public Boolean getInputResult( ) {
		return this.inputResult;
	}

}
