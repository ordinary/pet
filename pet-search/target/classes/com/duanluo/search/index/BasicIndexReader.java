package com.duanluo.search.index;

import org.apache.lucene.index.FilterIndexReader;
import org.apache.lucene.index.IndexReader;

import proj.zoie.api.ZoieIndexReader;

public class BasicIndexReader extends FilterIndexReader {

	public BasicIndexReader(ZoieIndexReader<BasicIndexReader> in) {
		super(in);
	}
	public void updateInnerReader(ZoieIndexReader<BasicIndexReader> inner) {
		in = inner;
	}
	public IndexReader getInnerReader(){
		return in;
	}
}