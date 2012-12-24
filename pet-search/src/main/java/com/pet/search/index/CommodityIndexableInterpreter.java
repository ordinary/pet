package com.pet.search.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proj.zoie.api.indexing.ZoieIndexable;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;

import com.pet.core.dao.CommodityMongo;
import com.pet.core.domain.Commodity;
import com.pet.search.wrapper.CommodityWrapper;

@Component(value = "commodityIndexableInterpreter")
public class CommodityIndexableInterpreter implements
		ZoieIndexableInterpreter<CommodityWrapper> {

	@Autowired
	private CommodityMongo commodityMongo;

	@Override
	public ZoieIndexable convertAndInterpret(CommodityWrapper commodityWrapper) {
		return new CommodityIndexable(commodityWrapper,commodityMongo);
	}

}

class CommodityIndexable implements ZoieIndexable {
	private CommodityWrapper commodityWrapper;
	
	private CommodityMongo commodityMongo;

	public CommodityIndexable(CommodityWrapper commodityWrapper , CommodityMongo commodityMongo) {
		this.commodityWrapper = commodityWrapper;
		this.commodityMongo=commodityMongo;
	}

	@Override
	public IndexingReq[] buildIndexingReqs() {
		System.out.println("**buildIndexingReqs**");
		long commodityId = commodityWrapper.getCommodityId();
		Document doc = null;
		List<IndexingReq> reqs = new ArrayList<IndexingReq>();
		try {
			Commodity commodity = commodityMongo.queryCommodityAndModify(
					commodityId, null);
			if (commodity != null) {
				doc = new Document();
				doc.add(new Field("orderNo", String.valueOf(commodityId),
						Store.YES, Index.NOT_ANALYZED));
				doc.add(new Field("name", commodity.getTitle(), Store.YES,
						Index.ANALYZED));
				if (doc != null) {
					reqs.add(new IndexingReq(doc));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqs.toArray(new IndexingReq[0]);
	}

	@Override
	public long getUID() {
		return commodityWrapper.getCommodityId();
	}

	@Override
	public boolean isDeleted() {
		return commodityWrapper.isDelete();
	}

	@Override
	public boolean isSkip() {
		return false;
	}

	@Override
	public byte[] getStoreValue() {
		return (commodityWrapper.getCommodityId() + "").getBytes();
	}

	@Override
	public boolean isStorable() {
		return true;
	}

}