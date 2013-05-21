package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.KeywordForecast;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.simba.keyword.keywordforecast.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class SimbaKeywordKeywordforecastGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7149743545777212435L;

	/** 
	 * 词预估结果信息

预测数据皆为估算结果，以实际发生为准
	 */
	@ApiField("keyword_forecast")
	private KeywordForecast keywordForecast;

	public void setKeywordForecast(KeywordForecast keywordForecast) {
		this.keywordForecast = keywordForecast;
	}
	public KeywordForecast getKeywordForecast( ) {
		return this.keywordForecast;
	}

}
