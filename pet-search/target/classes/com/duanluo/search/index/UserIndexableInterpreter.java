package com.duanluo.search.index;

import java.sql.SQLException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import proj.zoie.api.indexing.ZoieIndexable;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;

import com.duanluo.base.home.UserHome;
import com.duanluo.base.model.User;
import com.duanluo.search.model.UserWrapper;

public class UserIndexableInterpreter implements ZoieIndexableInterpreter<UserWrapper> {
	@Override
	public ZoieIndexable convertAndInterpret(UserWrapper user) {
		return new UserIndexable(user);
	}
}

class UserIndexable implements ZoieIndexable {
	
	private UserWrapper userWrapper;

	public UserIndexable(UserWrapper userWrapper) {
		this.userWrapper = userWrapper;
	}

	// TODO hear near two things : parse annonation & different doc parser
	@Override
	public IndexingReq[] buildIndexingReqs() {
		
		int userId = userWrapper.getUserId();
		Document doc = null;
		try {
			User user = UserHome.getInstance().get(userId);
//			User user = new User();
//			user.setId(1);
//			user.setUserName("非");
//			System.out.println("**buildIndexingReqs**"+user.getUserName());
			if (user != null) {
				doc = new Document();
				System.out.println("*****user name******"+user.getUserName()+ " update Time:" + user.getUpdateTime().toString());
				
//				System.out.println("*****user name******"+PinYingUtil.getPinYin(user.getUserName()));
				doc.add(new Field("name", user.getUserName(), Store.NO, Index.ANALYZED));
//				doc.add(new Field("name", "张爱华", Store.NO, Index.ANALYZED));
				doc.add(new Field("id", user.getId()+"", Store.NO, Index.ANALYZED));
//				doc.add(new Field("name", PinYingUtil.getPinYin(user.getUserName()), Store.YES, Index.ANALYZED));
				doc.add(new Field("updateTime", user.getUpdateTime().toString(), Store.NO, Index.NOT_ANALYZED));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		IndexingReq[] reqs = new IndexingReq[1];
		if (doc != null) {
			reqs[0] = new IndexingReq(doc);
		}
		return reqs;
	}

	@Override
	public long getUID() {
		return (long) userWrapper.getUserId();
	}

	@Override
	public boolean isDeleted() {
		return false;
	}

	@Override
	public boolean isSkip() {
		return false;
	}

	@Override
	public byte[] getStoreValue() {
		// TODO Auto-generated method stub
		return (userWrapper.getUserId()+"").getBytes();
	}

	@Override
	public boolean isStorable() {
		// TODO Auto-generated method stub
		return true;
	}
}
