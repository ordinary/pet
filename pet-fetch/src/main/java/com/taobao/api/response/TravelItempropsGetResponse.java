package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.TravelItemProp;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.itemprops.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItempropsGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1741947853722139713L;

	/** 
	 * 旅游商品类目属性结构列表。
	 */
	@ApiListField("travel_item_props")
	@ApiField("travel_item_prop")
	private List<TravelItemProp> travelItemProps;

	public void setTravelItemProps(List<TravelItemProp> travelItemProps) {
		this.travelItemProps = travelItemProps;
	}
	public List<TravelItemProp> getTravelItemProps( ) {
		return this.travelItemProps;
	}

}
