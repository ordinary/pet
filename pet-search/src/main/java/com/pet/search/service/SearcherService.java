package com.pet.search.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pet.domain.CommodityResult;
import com.pet.search.search.CommoditySearcher;
import com.pet.service.ISearcher;

@Component(value = "searcherService")
public class SearcherService implements ISearcher {

	@Resource(name = "commoditySearcher")
	private CommoditySearcher commoditySearcher;
	


	@Override
	public CommodityResult searchCommodity(String query, int start, int count) {
		return commoditySearcher.search(query, start, count);
	}



}
