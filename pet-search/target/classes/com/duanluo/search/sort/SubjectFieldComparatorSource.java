package com.duanluo.search.sort;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.FieldComparator;
import org.apache.lucene.search.FieldComparatorSource;

import com.duanluo.base.home.PlayTimeHome;
import com.duanluo.base.model.PlayTime;

public class SubjectFieldComparatorSource extends FieldComparatorSource {

	private long now;

	public SubjectFieldComparatorSource(long now) {
		this.now = now;
	}

	private static final long serialVersionUID = -4162624247547307728L;

	@Override
	public FieldComparator newComparator(String fieldname, int numHits,
			int sortPos, boolean reversed) throws IOException {
		return new SubjectComparator(fieldname, numHits, now);
	}

	final class SubjectComparator extends FieldComparator {
		private String[] currentReaderValues;
		private int[] values;
		private int bottom;
		private String fieldName;
		private long now;

		public SubjectComparator(String fieldName, int numHits, long now) {
			values = new int[numHits];
			this.fieldName = fieldName;
			this.now = now;
		}

		public int compare(int slot1, int slot2) {
			try {
				if (values[slot1] == 0) {
					return -1;
				} else if (values[slot2] == 0) {
					return 1;
				}

				List<PlayTime> playerTimes1 = PlayTimeHome.getInstance()
						.getMostRecentPlayTimeBySubjectIdOrderByNo(values[slot1], 0, 1);

				List<PlayTime> playerTimes2 = PlayTimeHome.getInstance()
						.getMostRecentPlayTimeBySubjectIdOrderByNo(values[slot2], 0, 1);

				if (playerTimes1 == null || playerTimes1.isEmpty()) {
					return -1;
				}

				if (playerTimes2 == null || playerTimes2.isEmpty()) {
					return 1;
				}
				if (playerTimes1.get(0).getOrderNo() < playerTimes2.get(0)
						.getOrderNo()) {
					return 1;
				} else if (playerTimes1.get(0).getOrderNo() > playerTimes2.get(
						0).getOrderNo()) {
					return -1;
				} else {
					if (Math.abs(playerTimes1.get(0).getPlayTime().getTime()
							- now) > Math.abs(playerTimes2.get(0).getPlayTime()
							.getTime()
							- now)) {
						return 1;
					} else if (Math.abs(playerTimes1.get(0).getPlayTime()
							.getTime()
							- now) < Math.abs(playerTimes2.get(0).getPlayTime()
							.getTime()
							- now)) {
						return -1;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return 0;
		}

		public int compareBottom(int doc) throws IOException {
			
			return 0;
		}

		public void copy(int slot, int doc) throws IOException {
			values[slot] = Integer.parseInt(currentReaderValues[doc]);
		}

		public void setBottom(int slot) {
			bottom = values[slot];
		}

		public void setNextReader(IndexReader reader, int docBase)
				throws IOException {
			currentReaderValues = FieldCache.DEFAULT.getStrings(reader,
					fieldName);
		}

		public Comparable value(int slot) {
			return values[slot];
		}
	}

}
