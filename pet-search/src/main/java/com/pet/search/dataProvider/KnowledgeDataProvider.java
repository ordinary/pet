package com.pet.search.dataProvider;

import java.util.Comparator;

import proj.zoie.api.DataConsumer.DataEvent;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.pet.search.wrapper.KnowledgeWrapper;

public class KnowledgeDataProvider  extends StreamDataProvider<KnowledgeWrapper>{

	public KnowledgeDataProvider() {
		super(new Comparator<String>() {
			public int compare(String str1, String str2) {
				if (str1 == null) {
					return -1;
				} else if (str2 == null) {
					return 1;
				} else {
					return str1.compareTo(str2);
				}
			}
		});
	}

	@Override
	public DataEvent<KnowledgeWrapper> next() {
		return null;
	}

	@Override
	public void setStartingOffset(String version) {
		
	}

	@Override
	public void reset() {
		
	}

}
