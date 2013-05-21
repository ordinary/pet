package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.MicroPayOrderDetail;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: alipay.micropay.order.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class AlipayMicropayOrderGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6442352555557939585L;

	/** 
	 * 冻结订单详情
	 */
	@ApiField("micro_pay_order_detail")
	private MicroPayOrderDetail microPayOrderDetail;

	public void setMicroPayOrderDetail(MicroPayOrderDetail microPayOrderDetail) {
		this.microPayOrderDetail = microPayOrderDetail;
	}
	public MicroPayOrderDetail getMicroPayOrderDetail( ) {
		return this.microPayOrderDetail;
	}

}
