package com.pet.service;

import com.pet.domain.CommodityResult;
import com.pet.domain.ResultKnowledge;

public interface ISearcher {

	CommodityResult searchCommodity(String query,int start, int count);
	
	ResultKnowledge searchKnowledge(String query,int start, int count);

}
