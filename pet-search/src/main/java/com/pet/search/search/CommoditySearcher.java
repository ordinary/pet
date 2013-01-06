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

import com.pet.core.dao.CommodityDAO;
import com.pet.core.domain.Commodity;
import com.pet.domain.CommodityResult;
import com.pet.search.reader.BasicIndexReader;
import com.pet.search.wrapper.CommodityWrapper;

@Component(value = "commoditySearcher")
public class CommoditySearcher extends
		BasicSearcher<CommodityResult, CommodityWrapper> {

	private final static String FIELD = "name";

	@Autowired
	private CommodityDAO commodityDao;

	@Resource(name = "commodityIndexingSystem")
	public void setZoieSystem(
			ZoieSystem<BasicIndexReader, CommodityWrapper> zoieSystem) {
		this.zoieSystem = zoieSystem;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected CommodityResult search(TopDocsCollector collector,
			SubReaderAccessor<BasicIndexReader> subReaderAccessor, int begin,
			int count) {
		CommodityResult result = new CommodityResult();
		TopDocs docs = collector.topDocs(begin, count);
		result.setCount(collector.getTotalHits());
		List<Commodity> commodities = new ArrayList<Commodity>();
		result.setCommoditys(commodities);
		ScoreDoc[] scoreDocs = docs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docId = scoreDoc.doc;
			SubReaderInfo<BasicIndexReader> readerInfo = subReaderAccessor
					.getSubReaderInfo(docId);
			IndexReader innerReader = readerInfo.subreader.getInnerReader();
			if (innerReader instanceof ZoieIndexReader) {
				long id = ((ZoieIndexReader) innerReader)
						.getUID(readerInfo.subdocid);
				Commodity commodity = commodityDao.queryByNumIid(id);
				if (commodity != null) {
					commodities.add(commodity);
				}
			}
		}
		return result;
	}

	@Override
	public CommodityResult search(String queryString, int begin, int count) {
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
