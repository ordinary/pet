package com.duanluo.search.similarity;

import org.apache.lucene.search.DefaultSimilarity;

public class UserSimilarity extends DefaultSimilarity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3413168476923226282L;

	@Override
	public float tf(float freq) {
		return 1.0f;
	}

}
