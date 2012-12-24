package com.pet.core.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component(value="increaseIdMongo")
public class IncreaseIdMongo {

	@Resource(name = "petMongoTemplate")
	MongoOperations mongoOps;

	public Integer getAutoIncreaseID(String collectName,String idName) {
		BasicDBObject query = new BasicDBObject();
		query.put("name", idName);

		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("id", 1));
		DBObject dbObject2 = mongoOps.getCollection(collectName).findAndModify(
				query, null, null, false, update, true, true);
		Integer id = (Integer) dbObject2.get("id");
		return id;
	}

}
