package com.pet.search.dataProvider;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import proj.zoie.api.DataConsumer.DataEvent;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.pet.core.dao.CommodityDAO;
import com.pet.core.domain.Commodity;
import com.pet.search.wrapper.CommodityWrapper;

public class CommodityDataProvider extends StreamDataProvider<CommodityWrapper> {

	private static final int PER_NUMBER = 1;

	private int start = 0;
	
	@Autowired
	private CommodityDAO commodityDao;


	public CommodityDataProvider() {
		super(new Comparator<String>() {
			public int compare(String str1, String str2) {
				if (str1 == null) {
					return -1;
				} else if (str2 == null) {
					return 1;
				} else {
					return str1.compareTo(str2);
				}
			}
		});
	}

	@Override
	public DataEvent<CommodityWrapper> next() {
		List<Commodity> commodities =commodityDao.queryCommodityByNumIidOrderbyNumIid(start, PER_NUMBER);
		if (CollectionUtils.isEmpty(commodities)) {
			for (Commodity commodity : commodities) {
				CommodityWrapper data = new CommodityWrapper();
				data.setCommodityId(commodity.getNumIid());
				start++;
				return new DataEvent<CommodityWrapper>(data, "1.0");
			}
		}
		return null;
	}

	@Override
	public void setStartingOffset(String version) {

	}

	@Override
	public void reset() {

	}

}
