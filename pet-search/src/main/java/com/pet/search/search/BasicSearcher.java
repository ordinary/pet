package com.pet.search.search;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.search.TopScoreDocCollector;

import proj.zoie.api.ZoieIndexReader;
import proj.zoie.api.ZoieIndexReader.SubReaderAccessor;
import proj.zoie.impl.indexing.ZoieSystem;

import com.pet.search.reader.BasicIndexReader;
import com.pet.search.similarity.SubjectSimilarity;

public abstract class BasicSearcher<R, MW> implements Searcher<R> {

	protected ZoieSystem<BasicIndexReader, MW> zoieSystem;

	public void setZoieSystem(ZoieSystem<BasicIndexReader, MW> zoieSystem) {
		this.zoieSystem = zoieSystem;
	}

	@SuppressWarnings({ "resource" })
	public R search(String queryString, Query query, int start, int count) {
		try {
			List<ZoieIndexReader<BasicIndexReader>> readers = zoieSystem
					.getIndexReaders();
			List<BasicIndexReader> decoratedReaders = ZoieIndexReader
					.extractDecoratedReaders(readers);
			SubReaderAccessor<BasicIndexReader> subReaderAccessor = ZoieIndexReader
					.getSubReaderAccessor(decoratedReaders);
			MultiReader reader = new MultiReader(
					readers.toArray(new IndexReader[readers.size()]), false);
			IndexSearcher searcher = new IndexSearcher(reader);
			searcher.setSimilarity(new SubjectSimilarity());
			TopScoreDocCollector collector = TopScoreDocCollector.create(start
					+ count, false);
			searcher.search(query, collector);
			R result = search(collector, subReaderAccessor, start, count);
			zoieSystem.returnIndexReaders(readers);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected abstract R search(
			@SuppressWarnings("rawtypes") TopDocsCollector collector,
			SubReaderAccessor<BasicIndexReader> subReaderAccessor, int begin,
			int count);

	BasicSearcher() {
	}

}
