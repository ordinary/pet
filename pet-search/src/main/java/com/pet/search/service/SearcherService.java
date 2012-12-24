package com.pet.search.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pet.domain.CommodityResult;
import com.pet.domain.ResultKnowledge;
import com.pet.search.search.CommoditySearcher;
import com.pet.search.search.KnowledgeSearcher;
import com.pet.service.ISearcher;

@Component(value = "searcherService")
public class SearcherService implements ISearcher {

	@Resource(name = "commoditySearcher")
	private CommoditySearcher commoditySearcher;
	
	@Resource(name = "knowledgeSearcher")
	private KnowledgeSearcher knowledgeSearcher;

	@Override
	public CommodityResult searchCommodity(String query, int start, int count) {
		return commoditySearcher.search(query, start, count);
	}

	@Override
	public ResultKnowledge searchKnowledge(String query, int start, int count) {
		return knowledgeSearcher.search(query, start, count);
	}



}
