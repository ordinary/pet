package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.BookBill;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.bill.book.bill.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class BillBookBillGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6861653976765698246L;

	/** 
	 * 虚拟账户账单
	 */
	@ApiField("bookbill")
	private BookBill bookbill;

	public void setBookbill(BookBill bookbill) {
		this.bookbill = bookbill;
	}
	public BookBill getBookbill( ) {
		return this.bookbill;
	}

}
