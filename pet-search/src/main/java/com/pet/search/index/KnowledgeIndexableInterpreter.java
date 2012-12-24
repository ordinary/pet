package com.pet.search.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proj.zoie.api.indexing.ZoieIndexable;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;

import com.pet.core.dao.KnowledgeMongo;
import com.pet.core.domain.Knowledge;
import com.pet.search.wrapper.KnowledgeWrapper;

@Component(value = "knowledgeIndexableInterpreter")
public class KnowledgeIndexableInterpreter implements
		ZoieIndexableInterpreter<KnowledgeWrapper> {
	
	@Autowired
	private KnowledgeMongo knowledgeMongo;

	@Override
	public ZoieIndexable convertAndInterpret(KnowledgeWrapper knowledgeWrapper) {
		return new KnowledgeIndexable(knowledgeWrapper,knowledgeMongo);
	}

}

class KnowledgeIndexable implements ZoieIndexable {
	private KnowledgeWrapper knowledgeWrapper;
	private KnowledgeMongo knowledgeMongo;

	public KnowledgeIndexable(KnowledgeWrapper knowledgeWrapper,KnowledgeMongo knowledgeMongo) {
		this.knowledgeWrapper = knowledgeWrapper;
		this.knowledgeMongo=knowledgeMongo;
	}

	@Override
	public IndexingReq[] buildIndexingReqs() {
		System.out.println("**buildIndexingReqs**");
		String id = knowledgeWrapper.getId();
		Document doc = null;
		List<IndexingReq> reqs = new ArrayList<IndexingReq>();
		try {
			Knowledge knowledge = knowledgeMongo
					.queryCommodityById(id);
			if (knowledge != null) {
				doc = new Document();
				doc.add(new Field("id", String.valueOf(knowledge.getId()),
						Store.YES, Index.NOT_ANALYZED));
				doc.add(new Field("content", knowledge.getContent(), Store.YES,
						Index.ANALYZED));
				if (doc != null) {
					reqs.add(new IndexingReq(doc));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqs.toArray(new IndexingReq[0]);
	}

	@Override
	public long getUID() {
		return Long.valueOf(knowledgeWrapper.getId());
	}

	@Override
	public boolean isDeleted() {
		return knowledgeWrapper.isDelete();
	}

	@Override
	public boolean isSkip() {
		return false;
	}

	@Override
	public byte[] getStoreValue() {
		return (knowledgeWrapper.getId()).getBytes();
	}

	@Override
	public boolean isStorable() {
		return true;
	}

}