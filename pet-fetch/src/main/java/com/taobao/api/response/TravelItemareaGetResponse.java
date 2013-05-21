package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.TravelAreaNode;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.itemarea.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemareaGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7319599888993566542L;

	/** 
	 * 旅游度假商品地区结构列表。
	 */
	@ApiListField("travel_area_nodes")
	@ApiField("travel_area_node")
	private List<TravelAreaNode> travelAreaNodes;

	public void setTravelAreaNodes(List<TravelAreaNode> travelAreaNodes) {
		this.travelAreaNodes = travelAreaNodes;
	}
	public List<TravelAreaNode> getTravelAreaNodes( ) {
		return this.travelAreaNodes;
	}

}
