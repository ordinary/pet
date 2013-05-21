package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.wlb.item.add response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class WlbItemAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2413334347527843485L;

	/** 
	 * 新增的商品
	 */
	@ApiField("item_id")
	private Long itemId;

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getItemId( ) {
		return this.itemId;
	}

}
