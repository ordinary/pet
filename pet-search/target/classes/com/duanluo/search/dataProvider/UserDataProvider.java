package com.duanluo.search.dataProvider;

import java.util.Comparator;
import java.util.List;

import proj.zoie.api.DataConsumer.DataEvent;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.duanluo.base.home.SearchBaseHome;
import com.duanluo.base.home.UserHome;
import com.duanluo.search.model.UserWrapper;

public class UserDataProvider extends StreamDataProvider<UserWrapper> {

	private static final int USER_FLAT = 2;

	private static final int SLEEP_TIME = 2000;

	private static final int PER_NUMBER = 500;

	private List<Integer> userIds;

	private int current = 0;

	private int lastUserId = 0;

	public UserDataProvider(Comparator<String> versionComparator) {
		super(versionComparator);
	}

	@SuppressWarnings("static-access")
	@Override
	public DataEvent<UserWrapper> next() {
		int userId = 0;
		try {
			int indexId = SearchBaseHome.getInstance().getIndexId(USER_FLAT);
			if (indexId > 0) {
				UserWrapper userWrapper = new UserWrapper(indexId);
				SearchBaseHome.getInstance().deleteIndexId(indexId, USER_FLAT);
				return new DataEvent<UserWrapper>(userWrapper,
						userWrapper.getUserId() + "");
			}
			if (userIds != null && !userIds.isEmpty()
					&& userIds.size() <= current) {
				if (lastUserId != 0) {
					userIds.clear();
					SearchBaseHome.getInstance().addLastId(lastUserId,
							USER_FLAT);
				}
			}
			if (userIds == null || userIds.isEmpty()) {
				Thread.currentThread().sleep(SLEEP_TIME);
				current = 0;
				int lastUserId = SearchBaseHome.getInstance().getLastId(
						USER_FLAT);
				userIds = UserHome.getInstance().getUserIdsByLastUserId(
						lastUserId, PER_NUMBER);
			}
			if (userIds != null && !userIds.isEmpty()) {
				userId = userIds.get(current);
				lastUserId = userId;
				current++;
			}
			// } catch (SQLException e) {
			// e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (userId > 0) {
			UserWrapper userWrapper = new UserWrapper(userId);
			return new DataEvent<UserWrapper>(userWrapper,
					userWrapper.getUserId() + "");
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
