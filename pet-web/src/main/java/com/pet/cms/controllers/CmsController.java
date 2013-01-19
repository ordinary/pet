package com.pet.cms.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.pet.core.dao.CommodityDAO;
import com.pet.core.dao.HotCommodityDAO;
import com.pet.core.domain.Commodity;
import com.pet.core.domain.HotCommodity;

@Path("cms")
public class CmsController {

	@Autowired
	private HotCommodityDAO hotCommodityDao;
	
	@Autowired
	private CommodityDAO commodityDao;

	@Get("hot/pet/all")
	public String hotPets(Invocation inv) {
		List<HotCommodity> commodities = hotCommodityDao.queryByType(0);
		inv.addModel("commodities", commodities);
		return "pets";
	}

	@Get("hot/pet/{type}")
	public String hotPet(Invocation inv, @Param("type") int type) {
		List<HotCommodity> commodities = hotCommodityDao.queryByType(type);
		inv.addModel("commodities", commodities);
		return "pet";
	}
	
	@Get("{type}/{page}/{count}")
	public String pet(Invocation inv, @Param("type") int type,
			@Param("page") int page, @Param("count") int count) {
		List<Commodity> commodities = commodityDao
				.queryCmsByCid(type, page, count);
		inv.addModel("commodities", commodities);
		return "@" + JSON.toJSON(commodities);
	}
	
	
	@Post("hot/pet")
	public String hotPet(Invocation inv,HotCommodity hotCommodity) {
		hotCommodityDao.delete(hotCommodity.getType(),hotCommodity.getOrder());
		hotCommodityDao.save(hotCommodity);
		return "a:hotPet?type="+hotCommodity.getType();
	}

}
