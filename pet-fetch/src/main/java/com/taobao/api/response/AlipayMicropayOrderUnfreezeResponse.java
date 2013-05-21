package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.UnfreezeOrderDetail;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: alipay.micropay.order.unfreeze response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class AlipayMicropayOrderUnfreezeResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8843145769811234914L;

	/** 
	 * 冻结订单详情结果
	 */
	@ApiField("unfreeze_order_detail")
	private UnfreezeOrderDetail unfreezeOrderDetail;

	public void setUnfreezeOrderDetail(UnfreezeOrderDetail unfreezeOrderDetail) {
		this.unfreezeOrderDetail = unfreezeOrderDetail;
	}
	public UnfreezeOrderDetail getUnfreezeOrderDetail( ) {
		return this.unfreezeOrderDetail;
	}

}
