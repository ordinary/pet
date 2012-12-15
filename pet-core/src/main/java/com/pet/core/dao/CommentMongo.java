package com.pet.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.pet.core.domain.Comment;

@Component
public class CommentMongo {
	
	@Resource(name="petMongoTemplate")
	MongoOperations mongoOps;
	
	public void save(Comment comment){
		if (comment!=null&&queryByCommodityRateId(comment.getRateId())==null) {
			mongoOps.save(comment);
		}

	}
	
	public List<Comment> queryByCommodityId(long commodityId, int start, int count){
		return mongoOps.find(query(where("commodity_id").is(commodityId)).skip(start).limit(count), Comment.class);
	}
	
	public Comment queryByCommodityRateId(long rateId) {
		return mongoOps.findOne(query(where("rate_id").is(rateId)), Comment.class);
	}

}
