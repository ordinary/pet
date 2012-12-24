package com.pet.search.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proj.zoie.api.ZoieIndexReader;
import proj.zoie.api.ZoieIndexReader.SubReaderAccessor;
import proj.zoie.api.ZoieIndexReader.SubReaderInfo;
import proj.zoie.impl.indexing.ZoieSystem;

import com.pet.core.dao.KnowledgeMongo;
import com.pet.core.domain.Knowledge;
import com.pet.domain.ResultKnowledge;
import com.pet.search.reader.BasicIndexReader;
import com.pet.search.wrapper.KnowledgeWrapper;

@Component(value = "knowledgeSearcher")
public class KnowledgeSearcher extends
		BasicSearcher<ResultKnowledge, KnowledgeWrapper> {
	

	private final static String FIELD = "name";
	
	@Autowired
	private KnowledgeMongo knowledgeMongo;
	
	@Resource(name="knowledgeIndexingSystem")
	public void setZoieSystem(ZoieSystem<BasicIndexReader, KnowledgeWrapper> zoieSystem) {
		this.zoieSystem = zoieSystem;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected ResultKnowledge search(TopDocsCollector collector,
			SubReaderAccessor<BasicIndexReader> subReaderAccessor, int begin,
			int count) {
		ResultKnowledge result = new ResultKnowledge();
		TopDocs docs = collector.topDocs(begin, count);
		result.setCount(collector.getTotalHits());
		List<Knowledge> knowledges = new ArrayList<Knowledge>();
		result.setKnowledges(knowledges);
		ScoreDoc[] scoreDocs = docs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docId = scoreDoc.doc;
			SubReaderInfo<BasicIndexReader> readerInfo = subReaderAccessor
					.getSubReaderInfo(docId);
			IndexReader innerReader = readerInfo.subreader.getInnerReader();
			if (innerReader instanceof ZoieIndexReader) {
				long id = ((ZoieIndexReader) innerReader)
						.getUID(readerInfo.subdocid);
				Knowledge knowledge = knowledgeMongo.queryCommodityById(id+"");
				if (knowledge != null) {
					knowledges.add(knowledge);
				}
			}
		}
		return result;
	}

	@Override
	public ResultKnowledge search(String queryString, int begin, int count) {
		try {
			QueryParser parser = new QueryParser(Version.LUCENE_30, FIELD,
					zoieSystem.getAnalyzer());
			Query query = parser.parse(queryString);
			return search(queryString, query, begin, count);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
