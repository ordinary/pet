package com.duanluo.search.index;

import java.io.IOException;

import org.apache.lucene.search.DocIdSet;

import proj.zoie.api.ZoieIndexReader;
import proj.zoie.api.indexing.IndexReaderDecorator;

public class BasicIndexReaderDecorator implements IndexReaderDecorator<BasicIndexReader> {

	@Override
	public BasicIndexReader decorate(ZoieIndexReader<BasicIndexReader> zoieReader) throws IOException {
		// System.out.println("------decorate--------");
		return new BasicIndexReader(zoieReader);
	}

	@Override
	public BasicIndexReader redecorate(BasicIndexReader filterReader, ZoieIndexReader<BasicIndexReader> zoieReader, boolean flag) throws IOException {
		// System.out.println("-------------redecorate-----------");
		filterReader.updateInnerReader(zoieReader);
		return filterReader;
	}

	@Override
	public void setDeleteSet(BasicIndexReader filterReader, DocIdSet docIdSet) {

	}

}
