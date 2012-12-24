package com.duanluo.search.dataProvider;

import java.util.Comparator;
import java.util.List;

import proj.zoie.api.DataConsumer.DataEvent;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.duanluo.base.home.SearchBaseHome;
import com.duanluo.base.home.TvStationHome;
import com.duanluo.search.model.TVWrapper;

public class TVDataProvider extends StreamDataProvider<TVWrapper> {

	private static final int TV_FLAT = 3;

	private static final int SLEEP_TIME = 10000;

	private static final int PER_NUMBER = 500;

	private List<Integer> tvIds;

	private int current = 0;

	private int lastTVId = 0;

	public TVDataProvider(Comparator<String> versionComparator) {
		super(versionComparator);
	}

	@SuppressWarnings("static-access")
	@Override
	public DataEvent<TVWrapper> next() {
		int tvId = 0;
		try {
			int indexId = SearchBaseHome.getInstance().getIndexId(TV_FLAT);
			if (indexId > 0) {
				TVWrapper tvWrapper = new TVWrapper(indexId);
				SearchBaseHome.getInstance().deleteIndexId(indexId, TV_FLAT);
				return new DataEvent<TVWrapper>(tvWrapper, tvWrapper.getTvId()
						+ "");
			}
			if (tvIds != null && !tvIds.isEmpty() && tvIds.size() <= current) {
				if (lastTVId != 0) {
					tvIds.clear();
					SearchBaseHome.getInstance().addLastId(lastTVId, TV_FLAT);
				}
			}
			if (tvIds == null || tvIds.isEmpty()) {
				Thread.currentThread().sleep(SLEEP_TIME);
				current = 0;
				int lastTVId = SearchBaseHome.getInstance()
						.getLastId(TV_FLAT);
				tvIds = TvStationHome.getInstance().getTvIdsByLastTvId(
						lastTVId, PER_NUMBER);
			}
			if (tvIds != null && !tvIds.isEmpty()) {
				tvId = tvIds.get(current);
				lastTVId = tvId;
				current++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tvId > 0) {
			TVWrapper tvWrapper = new TVWrapper(tvId);
			return new DataEvent<TVWrapper>(tvWrapper, tvWrapper.getTvId() + "");
		}
		return null;
	}

	@Override
	public void setStartingOffset(String version) {

	}

	@Override
	public void reset() {

	}

}
