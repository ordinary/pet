package com.duanluo.search.search;

import java.io.File;

import proj.zoie.api.indexing.IndexReaderDecorator;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;
import proj.zoie.impl.indexing.StreamDataProvider;
import proj.zoie.impl.indexing.ZoieConfig;
import proj.zoie.impl.indexing.ZoieSystem;

import com.duanluo.search.index.BasicIndexReader;

public abstract class BasicSearcher<R extends BasicIndexReader, MW, M>
		implements Searcher {

	public static class SearcherConfig<R extends BasicIndexReader, MW, M> {
		public File file;
		public ZoieConfig zoieConfig;
		public ZoieIndexableInterpreter<MW> interpreter;
		public IndexReaderDecorator<R> decorator;
	}

	protected ZoieSystem<R, MW> zoieSystem;
	protected StreamDataProvider<MW> dataProvider;

	BasicSearcher(SearcherConfig<R, MW, M> config,
			StreamDataProvider<MW> dataProvider) {
		System.out.println("***BasicSearcher****" + config.file);
		this.zoieSystem = new ZoieSystem<R, MW>(config.file,
				config.interpreter, config.decorator, config.zoieConfig);
		this.zoieSystem.start();
		this.dataProvider = dataProvider;
		dataProvider.setDataConsumer(zoieSystem);
		this.dataProvider.start();
	}

}
