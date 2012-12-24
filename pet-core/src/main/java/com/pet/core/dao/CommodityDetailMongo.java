package com.pet.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.pet.core.domain.CommodityDetail;

@Component
public class CommodityDetailMongo {

	@Resource(name = "petMongoTemplate")
	MongoOperations mongoOps;

	public void save(CommodityDetail commodityDetail) {
		if (commodityDetail != null
				&& queryCommodityAndModify(commodityDetail.getNumIid(),
						commodityDetail) == null) {
			mongoOps.save(commodityDetail);
		}

	}

	public CommodityDetail queryCommodityAndModify(long numIid,
			CommodityDetail commodityDetail) {
		if (commodityDetail != null) {
			return mongoOps
					.findAndModify(
							query(where("numIid").is(numIid)),
							Update.update("approve_status",
									commodityDetail.getApproveStatus())
									.set("auction_point",
											commodityDetail.getAuctionPoint())
									.set("delist_time",
											commodityDetail.getDelistTime())
									.set("freight_payer",
											commodityDetail.getFreightPayer())
									.set("price", commodityDetail.getPrice()),
							CommodityDetail.class);
		} 
		return null;

	}

}
