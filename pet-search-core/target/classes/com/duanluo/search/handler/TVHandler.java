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

import com.duanluo.base.model.TvStation;
import com.duanluo.network.annotation.Handler;
import com.duanluo.network.handler.RpcMessageHandler;
import com.duanluo.search.model.TVWrapper;
import com.duanluo.search.proto.SearchTvPublishRequestProto.SearchTvPublishRequest;
import com.duanluo.search.proto.SearchTvPublishResponseProto.SearchTVPublishResponse;
import com.duanluo.search.search.Searcher;
import com.duanluo.search.search.SearcherFactory;
import com.google.protobuf.Message;

@Handler
public class TVHandler implements RpcMessageHandler {

	@Override
	public Message handleMessage(Message message) throws Exception {
		SearchTvPublishRequest request =(SearchTvPublishRequest) message;
		return searchTVs(request.getQuery(),request.getStart(),request.getCount());
	}

	@Override
	public Message getRequest() {
		return SearchTvPublishRequest.getDefaultInstance();
	}

	@Override
	public Message getResponse() {
		return SearchTVPublishResponse.getDefaultInstance();
	}

	/**
	 * @param info
	 *            查询所以的用户信息时，info 设置为"".
	 * @return
	 */
	public Message searchTVs(String query, int start, int count) {

		SearchTVPublishResponse.Builder build = SearchTVPublishResponse
				.newBuilder();
		try {
			Searcher searcher = SearcherFactory.getSearcher(TVWrapper.class,
					TvStation.class);

			Query queryVal = null;
			if (StringUtils.isNotEmpty(query)) {
				QueryParser parser = new QueryParser(Version.LUCENE_30, "name",
						new StandardAnalyzer(Version.LUCENE_30));
				queryVal = parser.parse(query);
			} else {
				queryVal = new MatchAllDocsQuery("name");
			}

			SortField[] sortFields = new SortField[] { new SortField("orderNo",
					SortField.STRING, true) };
			Sort orderNoSort = new Sort(sortFields);
			TopFieldCollector collector = TopFieldCollector.create(orderNoSort,
					start + count, false, false, true, false);
			return searcher.search(queryVal, collector, start, count);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return build.build();
	}

}
