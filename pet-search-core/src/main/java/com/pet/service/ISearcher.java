package com.pet.service;

import com.pet.domain.CommodityResult;

public interface ISearcher {

	CommodityResult searchCommodity(String query,int start, int count);
	
}
