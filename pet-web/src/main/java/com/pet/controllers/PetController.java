package com.pet.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.pet.core.dao.CommodityDAO;
import com.pet.core.domain.Commodity;

@Path("pet")
public class PetController {
	
	@Autowired
	private CommodityDAO  commodityDao;
	
    @Get("{type}")
    public String pet(Invocation inv,@Param("type") int type) {
    	List<Commodity> commodities=commodityDao.queryByCid(type, 0, 100);
        return "@"+JSON.toJSON(commodities);
    }
}
