package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.TravelItems;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.items.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemsGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8275353797182174927L;

	/** 
	 * 旅游商品结构
	 */
	@ApiField("travel_items")
	private TravelItems travelItems;

	public void setTravelItems(TravelItems travelItems) {
		this.travelItems = travelItems;
	}
	public TravelItems getTravelItems( ) {
		return this.travelItems;
	}

}
