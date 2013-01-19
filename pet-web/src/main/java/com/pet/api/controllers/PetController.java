package com.pet.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.pet.core.dao.CommodityDAO;
import com.pet.core.dao.HotCommodityDAO;
import com.pet.core.domain.Commodity;
import com.pet.core.domain.HotCommodity;
import com.pet.core.enums.HotCommodityType;
import com.pet.domain.CommodityResult;
import com.pet.service.ISearcher;

@Path("pet")
public class PetController {

	@Autowired
	private CommodityDAO commodityDao;

	@Reference(version = "1.0.0")
	private ISearcher searcher;

	@Autowired
	private HotCommodityDAO hotCommodityDao;

	@Get("hot/pet/cids")
	public String hotPets(Invocation inv) {
		List<HotCommodity> commodities = hotCommodityDao.queryByType(0);
		Map<String, List<HotCommodity>> hotMap = new HashMap<String, List<HotCommodity>>();
		for (HotCommodity hotCommodity : commodities) {
			List<HotCommodity> hotCommodities = hotMap.get(HotCommodityType
					.getHotCommodityTypeById(hotCommodity.getType()).getName());
			if (CollectionUtils.isEmpty(hotCommodities)) {
				hotCommodities = new ArrayList<HotCommodity>();
				hotMap.put(
						HotCommodityType.getHotCommodityTypeById(
								hotCommodity.getType()).getName(),
						hotCommodities);
			}
			hotCommodities.add(hotCommodity);
		}

		inv.addModel("commodities", commodities);
		return "@" + JSON.toJSON(commodities);
	}

	@Get("{type}/{page}/{count}")
	public String pet(Invocation inv, @Param("type") int type,
			@Param("page") int page, @Param("count") int count) {
		List<Commodity> commodities = commodityDao
				.queryByCid(type, page, count);
		return "@" + JSON.toJSON(commodities);
	}

	@Get("search/{query}/{page}/{count}")
	public String searchPet(Invocation inv, @Param("query") String query,
			@Param("page") int page, @Param("count") int count) {
		CommodityResult commodityResult = searcher.searchCommodity(query, page,
				count);
		return "@" + JSON.toJSON(commodityResult.getCommoditys());
	}

}
