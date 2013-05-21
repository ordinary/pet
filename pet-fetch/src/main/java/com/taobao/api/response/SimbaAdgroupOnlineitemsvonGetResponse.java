package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.SubwayItemPartition;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.simba.adgroup.onlineitemsvon.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class SimbaAdgroupOnlineitemsvonGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5834795565143771625L;

	/** 
	 * 带分页的淘宝商品
	 */
	@ApiField("page_item")
	private SubwayItemPartition pageItem;

	public void setPageItem(SubwayItemPartition pageItem) {
		this.pageItem = pageItem;
	}
	public SubwayItemPartition getPageItem( ) {
		return this.pageItem;
	}

}
