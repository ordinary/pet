package com.duanluo.search.index;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.duanluo.search.wrapper.SubjectWrapper;
import com.pet.core.domain.Subject;

import proj.zoie.api.indexing.ZoieIndexable;
import proj.zoie.api.indexing.ZoieIndexableInterpreter;

public class SubjectIndexableInterpreter implements
		ZoieIndexableInterpreter<SubjectWrapper> {

	@Override
	public ZoieIndexable convertAndInterpret(SubjectWrapper subjectWrapper) {
		return new SubjectIndexable(subjectWrapper);
	}

}

class SubjectIndexable implements ZoieIndexable {
	private SubjectWrapper subjectWrapper;

	public SubjectIndexable(SubjectWrapper subjectWrapper) {
		this.subjectWrapper = subjectWrapper;
	}

	@Override
	public IndexingReq[] buildIndexingReqs() {
		System.out.println("**buildIndexingReqs**");
		String subjectId = subjectWrapper.getSubjectId();
		Document doc = null;
		List<IndexingReq> reqs = new ArrayList<IndexingReq>();
		try {

//			Subject subject= SubjectHome.getInstance().get(subjectId);
	
			// System.out.println("**subject**" + subject + "  subjectId:"
			// + subjectId);
//			if (subject != null) {
				// System.out.println("*****subject name******" +
				// subject.getName());
				//
				// System.out.println("*****orderNo******" + orderNo);
				// doc = new Document();
				//
				// Field nameField=new Field("name", subject.getName(),
				// Store.YES, Index.ANALYZED);
				// Field nameFieldId= new Field("name",
				// String.valueOf(subject.getId()), Store.YES, Index.ANALYZED);
				// nameFieldId.setBoost(100);
				// doc.add(new Field("uuid", String.valueOf(subject.getId()),
				// Store.YES, Index.NOT_ANALYZED));
				// doc.add(new Field("orderNo", String.valueOf(orderNo),
				// Store.YES, Index.NOT_ANALYZED));
				// doc.add(nameField);
				// doc.add(nameFieldId);
				// if (doc != null) {
				// reqs.add(new IndexingReq(doc));
				// }
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqs.toArray(new IndexingReq[0]);
	}

	@Override
	public long getUID() {
		// return subjectWrapper.getSubjectId();
		return 0;
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
		return null;
		// (subjectWrapper.getSubjectId() + "").getBytes();
	}

	@Override
	public boolean isStorable() {
		return true;
	}

}