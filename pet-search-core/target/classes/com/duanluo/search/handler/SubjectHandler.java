package com.duanluo.search.handler;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.util.Version;

import com.duanluo.base.model.Subject;
import com.duanluo.network.annotation.Handler;
import com.duanluo.network.handler.RpcMessageHandler;
import com.duanluo.search.model.SubjectWrapper;
import com.duanluo.search.proto.SearchSubjectPublishRequestProto.SearchSubjectPublishRequest;
import com.duanluo.search.proto.SearchSubjectPublishResponseProto.SearchSubjectPublishResponse;
import com.duanluo.search.search.Searcher;
import com.duanluo.search.search.SearcherFactory;
import com.google.protobuf.Message;

@Handler
public class SubjectHandler implements RpcMessageHandler {

	@Override
	public Message handleMessage(Message message) throws Exception {
		SearchSubjectPublishRequest request = (SearchSubjectPublishRequest) message;
		return searchSubjects(request.getQuery(), request.getStart(),
				request.getCount());
	}

	@Override
	public Message getRequest() {
		return SearchSubjectPublishRequest.getDefaultInstance();
	}

	@Override
	public Message getResponse() {
		return SearchSubjectPublishResponse.getDefaultInstance();
	}

	public Message searchSubjects(String query, int start, int count) {

		SearchSubjectPublishResponse.Builder build = SearchSubjectPublishResponse
				.newBuilder();
		try {
			Searcher searcher = SearcherFactory.getSearcher(
					SubjectWrapper.class, Subject.class);

			Query queryVal = null;
			if (StringUtils.isNotEmpty(query)) {
				QueryParser parser = new QueryParser(Version.LUCENE_30, "name",
						new StandardAnalyzer(Version.LUCENE_30));
				queryVal = parser.parse(query);
			} else {
				queryVal = new MatchAllDocsQuery("name");
			}
			
			
			SortField[] sortFields = new SortField[] {
//					new SortField("name", SortField.SCORE, true),
					new SortField("orderNo", SortField.INT, false),
					new SortField("name", SortField.STRING, true), };
			Sort orderNoSort = new Sort(sortFields);
			TopFieldCollector collector = TopFieldCollector.create(orderNoSort,
					start+count, false, false, true, false);
			return searcher.search(queryVal, collector, start,count);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return build.build();
	}

}
