package com.duanluo.search.search;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopDocsCollector;

import proj.zoie.api.ZoieIndexReader;
import proj.zoie.api.ZoieIndexReader.SubReaderAccessor;
import proj.zoie.api.ZoieIndexReader.SubReaderInfo;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.duanluo.search.index.BasicIndexReader;
import com.duanluo.search.proto.SearchTvPublishResponseProto.SearchTVPublishResponse;
import com.duanluo.search.similarity.TVSimilarity;
import com.google.protobuf.Message;

public class TVSearcher<R extends BasicIndexReader, MW, M> extends
		BasicSearcher<R, MW, M> {

	TVSearcher(
			com.duanluo.search.search.BasicSearcher.SearcherConfig<R, MW, M> config,
			StreamDataProvider<MW> dataProvider) {
		super(config, dataProvider);
	}

	@Override
	public Message search(Query query, Collector collector, int begin, int count) {
		SearchTVPublishResponse.Builder build = SearchTVPublishResponse
				.newBuilder();
		try {
			List<ZoieIndexReader<R>> readers = zoieSystem.getIndexReaders();
			List<R> decoratedReaders = ZoieIndexReader
					.extractDecoratedReaders(readers);
			SubReaderAccessor<R> subReaderAccessor = ZoieIndexReader
					.getSubReaderAccessor(decoratedReaders);
			MultiReader reader = new MultiReader(
					readers.toArray(new IndexReader[readers.size()]), false);
			IndexSearcher searcher = new IndexSearcher(reader);
			searcher.setSimilarity(new TVSimilarity());
			searcher.search(query, collector);
			@SuppressWarnings("rawtypes")
			TopDocs docs = ((TopDocsCollector) collector).topDocs(begin, count);
			build.setCount(docs.totalHits);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				int docId = scoreDoc.doc;
				SubReaderInfo<R> readerInfo = subReaderAccessor
						.getSubReaderInfo(docId);
				IndexReader innerReader = readerInfo.subreader.getInnerReader();
				if (innerReader instanceof ZoieIndexReader) {
					@SuppressWarnings("rawtypes")
					long id = ((ZoieIndexReader) innerReader)
							.getUID(readerInfo.subdocid);
					build.addTvIds(id);
				}
			}
			zoieSystem.returnIndexReaders(readers);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return build.build();
	}

}
