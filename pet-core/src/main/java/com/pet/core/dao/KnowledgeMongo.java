package com.pet.core.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.pet.core.domain.Knowledge;

@Component
public class KnowledgeMongo {

	@Resource(name = "petMongoTemplate")
	MongoOperations mongoOps;

	private final static String COLLECTION_NAME = "knowledge_inc";

	private final static String NAME = "name";

	@Resource(name = "increaseIdMongo")
	private IncreaseIdMongo increaseIdMongo;

	public void save(Knowledge knowledge) {
		if (knowledge != null
				&& queryKnowledgeAndModify(knowledge.getUrl(), knowledge) == null) {
			knowledge.setId(new ObjectId(increaseIdMongo.getAutoIncreaseID(
					COLLECTION_NAME, NAME) + ""));
			mongoOps.save(knowledge);
		}

	}

	public Knowledge queryCommodityById(String id) {
		return mongoOps.findById(new ObjectId(id), Knowledge.class);
	}

	public Knowledge queryKnowledgeAndModify(String url, Knowledge knowledge) {
		if (knowledge != null) {
			return mongoOps.findAndModify(query(where("url").is(url)),
					Update.update("content", knowledge.getContent()),
					Knowledge.class);
		}
		return null;

	}

}
