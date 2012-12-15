package com.duanluo.search.dataProvider;

import java.util.Comparator;
import java.util.List;

import proj.zoie.api.DataConsumer.DataEvent;
import proj.zoie.impl.indexing.StreamDataProvider;

import com.duanluo.search.wrapper.SubjectWrapper;


public class SubjectDataProvider extends StreamDataProvider<SubjectWrapper> {
	
	private List<Integer> subjectIds;
	
	
	private static final int SUBJECT_FLAT=1;
	
	private static final int SLEEP_TIME=2000;
	
	private static final int PER_NUMBER=500;
	
	private int current=0;
	
	private int lastSubjectId=0;

	public SubjectDataProvider(Comparator<String> versionComparator) {
		super(versionComparator);
	}

	@SuppressWarnings("static-access")
	@Override
	public DataEvent<SubjectWrapper> next() {
		int subjectId = 0;
//		try {
//           
//            int indexId
//            =SearchBaseHome.getInstance().getIndexId(SUBJECT_FLAT)
            ;
//            if(indexId>0){
//            	SubjectWrapper subjectWrapper =new SubjectWrapper(subjectId) ;
//            	SearchBaseHome.getInstance().deleteIndexId(indexId, SUBJECT_FLAT);
//            	return 	new DataEvent<SubjectWrapper>(
//    					subjectWrapper , subjectWrapper.getSubjectId() + "");
//            }
//			if(subjectIds!=null&&!subjectIds.isEmpty()&&subjectIds.size()<=current){
//				if(lastSubjectId!=0){
//					subjectIds.clear();
//					SearchBaseHome.getInstance().addLastId(lastSubjectId,SUBJECT_FLAT);
//				}
//			}
//			if(subjectIds==null||subjectIds.isEmpty()){
//				Thread.currentThread().sleep(SLEEP_TIME);
//				current=0;
//				int lastSubjectId=SearchBaseHome.getInstance().getLastId(SUBJECT_FLAT);
//				subjectIds=SubjectHome.getInstance().getSubjectIdsByLastSubjectId(lastSubjectId, PER_NUMBER);
//			}
//			if(subjectIds!=null&&!subjectIds.isEmpty()){
//				subjectId=subjectIds.get(current);
//				lastSubjectId=subjectId;
//				current++;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		if(subjectId>0){
//			SubjectWrapper subjectWrapper =new SubjectWrapper(subjectId) ;
//			return 	new DataEvent<SubjectWrapper>(
//					subjectWrapper , subjectWrapper.getSubjectId() + "");
//		}
		return null;
	}

	@Override
	public void setStartingOffset(String version) {
		
	}

	@Override
	public void reset() {
		
	}

}
