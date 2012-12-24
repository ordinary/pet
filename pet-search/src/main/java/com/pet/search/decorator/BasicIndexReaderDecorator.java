package com.pet.search.decorator;

import java.io.IOException;

import org.apache.lucene.search.DocIdSet;
import org.springframework.stereotype.Component;

import com.pet.search.reader.BasicIndexReader;

import proj.zoie.api.ZoieIndexReader;
import proj.zoie.api.indexing.IndexReaderDecorator;


@Component(value="basicIndexReaderDecorator")
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
