package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.TravelPlay;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.travel.itemplay.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemplayGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7799555147367414656L;

	/** 
	 * 线路玩法列表
	 */
	@ApiListField("plays")
	@ApiField("travel_play")
	private List<TravelPlay> plays;

	public void setPlays(List<TravelPlay> plays) {
		this.plays = plays;
	}
	public List<TravelPlay> getPlays( ) {
		return this.plays;
	}

}
