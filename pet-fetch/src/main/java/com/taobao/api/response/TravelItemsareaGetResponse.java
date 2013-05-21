package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.TravelItemsAreaNode;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.itemsarea.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemsareaGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2448685472225721191L;

	/** 
	 * 旅游度假商品地区（目的地）级联结构。
	 */
	@ApiListField("travel_items_area_nodes")
	@ApiField("travel_items_area_node")
	private List<TravelItemsAreaNode> travelItemsAreaNodes;

	public void setTravelItemsAreaNodes(List<TravelItemsAreaNode> travelItemsAreaNodes) {
		this.travelItemsAreaNodes = travelItemsAreaNodes;
	}
	public List<TravelItemsAreaNode> getTravelItemsAreaNodes( ) {
		return this.travelItemsAreaNodes;
	}

}
