package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.TravelItem;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.item.update response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemUpdateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 3576447581378447484L;

	/** 
	 * 更新成功后的数据结构
	 */
	@ApiField("travel_item")
	private TravelItem travelItem;

	public void setTravelItem(TravelItem travelItem) {
		this.travelItem = travelItem;
	}
	public TravelItem getTravelItem( ) {
		return this.travelItem;
	}

}
