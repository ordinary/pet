package com.pet.search.service;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.pet.domain.CommodityResult;
import com.pet.search.search.CommoditySearcher;
import com.pet.service.ISearcher;

@Service(version="1.0.0")
public class SearcherService implements ISearcher {

	@Resource(name = "commoditySearcher")
	private CommoditySearcher commoditySearcher;
	


	@Override
	public CommodityResult searchCommodity(String query, int start, int count) {
		return commoditySearcher.search(query, start, count);
	}



}
