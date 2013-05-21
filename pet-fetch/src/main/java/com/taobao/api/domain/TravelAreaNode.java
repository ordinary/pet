package com.taobao.api.domain;

import java.util.List;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;

/**
 * 旅游度假商品地区结构。
 *
 * @author auto create
 * @since 1.0, null
 */
public class TravelAreaNode extends TaobaoObject {

	private static final long serialVersionUID = 2765396737981296451L;

	/**
	 * 地区属性值。
	 */
	@ApiField("travel_prop_value")
	private TravelPropValue travelPropValue;

	/**
	 * 该地区下所有下级地区集合(目前地区只有两级，国内：省-市；国际：大洲-国家)。
	 */
	@ApiListField("travel_prop_values")
	@ApiField("travel_prop_value")
	private List<TravelPropValue> travelPropValues;

	public TravelPropValue getTravelPropValue() {
		return this.travelPropValue;
	}
	public void setTravelPropValue(TravelPropValue travelPropValue) {
		this.travelPropValue = travelPropValue;
	}

	public List<TravelPropValue> getTravelPropValues() {
		return this.travelPropValues;
	}
	public void setTravelPropValues(List<TravelPropValue> travelPropValues) {
		this.travelPropValues = travelPropValues;
	}

}
