package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.TravelItem;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.item.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 4855746181541419198L;

	/** 
	 * 旅游商品结构
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
