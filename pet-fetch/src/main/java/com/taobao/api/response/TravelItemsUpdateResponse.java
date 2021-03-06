package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.TravelItems;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.items.update response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemsUpdateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1511168185157663841L;

	/** 
	 * 旅游商品结构。
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
