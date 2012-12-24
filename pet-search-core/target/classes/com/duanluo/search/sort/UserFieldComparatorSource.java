package com.duanluo.search.sort;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.FieldComparator;
import org.apache.lucene.search.FieldComparatorSource;

public class UserFieldComparatorSource extends FieldComparatorSource {

	private static final long serialVersionUID = -5192083573760670575L;

	@Override
	public FieldComparator newComparator(String fieldname, int numHits,
			int sortPos, boolean reversed) throws IOException {

		return null;
	}

//	final class UserComparator extends FieldComparator {
//		private String[] currentReaderValues;  
//		private String[] values;
//		private String bottom;
//		String fieldName;
//
//		public UserComparator(String fieldName, int numHits) {
//			values = new String[numHits];
//			this.fieldName = fieldName;
//		}
//
//		public int compare(int slot1, int slot2) {
//			if (StringUtils.isEmpty(values[slot1])  ){
//				return -1;
//			}else if(StringUtils.isEmpty(values[slot2])){
//				return 1;
//			}else if(){
//				
//			}else if(){
//				
//			}
//				
//			
//			return 0;
//		}
//
//		public int compareBottom(int doc) throws IOException {
//			final String v2=  currentReaderValues[doc];
//			if (StringUtils.isEmpty(v2))
//				return -1;
//			
//			return 0;
//		}
//
//
//		public void copy(int slot, int doc) throws IOException {
//			 values[slot] = currentReaderValues[doc];  
//		}
//
//		public void setBottom(int slot) {
//			bottom = values[slot];
//		}
//
//		public void setNextReader(IndexReader reader, int docBase)
//				throws IOException {
//			currentReaderValues=FieldCache.DEFAULT.getStrings(reader, fieldName);  
//		}
//
//		public Comparable value(int slot) {
//			return values[slot];
//		}
//	}
}
