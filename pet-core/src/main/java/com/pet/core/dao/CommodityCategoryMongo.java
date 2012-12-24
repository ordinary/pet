package com.pet.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.pet.core.domain.CommodityCategory;

@Component
public class CommodityCategoryMongo {

	@Resource(name = "petMongoTemplate")
	MongoOperations mongoOps;

	public void save(CommodityCategory commodityCategory) {
		if (commodityCategory != null
				&& queryCommodityCategoryByCid(commodityCategory.getCid()) == null) {
			mongoOps.save(commodityCategory);
		}

	}

	public CommodityCategory queryCommodityCategoryByCid(long cid) {
		return mongoOps.findOne(query(where("cid").is(cid)),
				CommodityCategory.class);
	}

}
