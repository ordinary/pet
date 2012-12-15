package com.duanluo.search.search;

import org.apache.lucene.search.Collector;
import org.apache.lucene.search.Query;

import com.google.protobuf.Message;

public interface Searcher {
	
	public Message search(Query query, Collector collector,int offset,int count);
}
