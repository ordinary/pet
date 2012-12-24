package com.pet.search.search;


public interface Searcher<R> {
	
	public R search(String query,int offset,int count);
}
