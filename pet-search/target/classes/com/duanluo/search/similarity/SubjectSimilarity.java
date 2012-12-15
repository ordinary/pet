package com.duanluo.search.similarity;

import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.Similarity;

public class SubjectSimilarity extends Similarity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7483487031561751213L;
	// Default true
	protected boolean discountOverlaps = true;

	/**
	 * Determines whether overlap tokens (Tokens with 0 position increment) are
	 * ignored when computing norm. By default this is false, meaning overlap
	 * tokens are counted just like non-overlap tokens.
	 * 
	 * <p>
	 * <b>WARNING</b>: This API is new and experimental, and may suddenly
	 * change.
	 * </p>
	 * 
	 * @see #computeNorm
	 */
	public void setDiscountOverlaps(boolean v) {
		discountOverlaps = v;
	}

	/** @see #setDiscountOverlaps */
	public boolean getDiscountOverlaps() {
		return discountOverlaps;
	}

	@Override
	public float computeNorm(String field, FieldInvertState state) {
		final int numTerms;
		if (discountOverlaps) {
			numTerms = state.getLength() - state.getNumOverlap();
			System.out.println("*****state  Length******" + state.getLength()+" state  NumOverlap" +state.getNumOverlap());
		}
		else {
			numTerms = state.getLength();
			System.out.println("*****state  Length******" + state.getLength());
		}

		return state.getBoost() * ((float) (1.0 / Math.sqrt(numTerms)));
	}

	@Override
	public float queryNorm(float sumOfSquaredWeights) {
		return 1.0f;
	}

	@Override
	public float sloppyFreq(int distance) {
		return 1.0f;
	}

	@Override
	public float tf(float freq) {
		return 1.0f;
	}

	@Override
	public float idf(int docFreq, int numDocs) {
		return 1.0f;
	}

	@Override
	public float coord(int overlap, int maxOverlap) {
		return 1.0f;
	}

}
