package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.LotteryWangcaiSellerGoodsInfo;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.caipiao.goods.info.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class CaipiaoGoodsInfoGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1614252457595392768L;

	/** 
	 * 查询的结果列表
	 */
	@ApiListField("results")
	@ApiField("lottery_wangcai_seller_goods_info")
	private List<LotteryWangcaiSellerGoodsInfo> results;

	/** 
	 * 返回列表的大小
	 */
	@ApiField("total_results")
	private Long totalResults;

	public void setResults(List<LotteryWangcaiSellerGoodsInfo> results) {
		this.results = results;
	}
	public List<LotteryWangcaiSellerGoodsInfo> getResults( ) {
		return this.results;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	public Long getTotalResults( ) {
		return this.totalResults;
	}

}
