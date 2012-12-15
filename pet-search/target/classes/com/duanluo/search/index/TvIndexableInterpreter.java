package com.duanluo.search.index;

import java.sql.SQLException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import proj.zoie.api.indexing.ZoieIndexable;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;

import com.duanluo.base.home.TvStationHome;
import com.duanluo.base.model.TvStation;
import com.duanluo.search.model.TVWrapper;

public class TvIndexableInterpreter implements
		ZoieIndexableInterpreter<TVWrapper> {
	


	@Override
	public ZoieIndexable convertAndInterpret(TVWrapper src) {
		return new TVIndexable(src);
	}

	class TVIndexable implements ZoieIndexable {

		private TVWrapper tvWrapper;

		public TVIndexable(TVWrapper tvWrapper) {
			this.tvWrapper = tvWrapper;
		}

		// TODO hear near two things : parse annonation & different doc parser
		@Override
		public IndexingReq[] buildIndexingReqs() {

			int tvId = tvWrapper.getTvId();
			Document doc = null;
			try {
				TvStation tvStation = TvStationHome.getInstance().getTvStation(
						tvId);
			
				System.out.println("**id**" + tvStation.getId());
				System.out.println("**buildIndexingReqs**"
						+ tvStation.getTvName());
				if (tvStation != null) {
					doc = new Document();
				
					doc.add(new Field("name", tvStation.getTvName(), Store.YES,
							Index.ANALYZED));
				
					doc.add(new Field("id", tvStation.getId() + "", Store.YES,
							Index.ANALYZED));
					doc.add(new Field("orderNo", tvStation.getOrderNo() + "",
							Store.NO, Index.NOT_ANALYZED));
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
			return (long) tvWrapper.getTvId();
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
			return (tvWrapper.getTvId() + "").getBytes();
		}

		@Override
		public boolean isStorable() {
			// TODO Auto-generated method stub
			return true;
		}
	}

}
