package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.Bill;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.bill.bill.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class BillBillGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8195231877741894911L;

	/** 
	 * 账单明细
	 */
	@ApiField("bill")
	private Bill bill;

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Bill getBill( ) {
		return this.bill;
	}

}
