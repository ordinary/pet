package com.pet.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.pet.core.domain.Commodity;

@Component
public class CommodityMongo {

	@Resource(name = "petMongoTemplate")
	MongoOperations mongoOps;

	public void save(Commodity commodity) {
		if (commodity != null
				&& queryCommodityAndModify(commodity.getNumIid(), commodity) == null) {
			mongoOps.save(commodity);
		}

	}

	public List<Commodity> queryCommodityByCid(long cid) {
		return mongoOps.find(query(where("cid").is(cid)), Commodity.class);
	}

	public Commodity queryCommodityAndModify(long numIid, Commodity commodity) {
		if (commodity != null) {
			return mongoOps.findAndModify(
					query(where("numIid").is(numIid)),
					Update.update("commission_num",
							commodity.getCommissionNum())
							.set("commission_volume",
									commodity.getCommissionVolume())
							.set("coupon_end_time",
									commodity.getCouponEndTime())
							.set("coupon_rate", commodity.getCouponRate())
							.set("coupon_price", commodity.getCouponPrice())
							.set("price", commodity.getPrice())
							.set("seller_credit_score",
									commodity.getSellerCreditScore())
							.set("volume", commodity.getVolume()),
					Commodity.class);
		} else {
			return mongoOps.findOne(query(where("numIid").is(numIid)),
					Commodity.class);
		}

	}

}
