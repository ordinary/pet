package com.pet.domain;

import java.io.Serializable;
import java.util.List;

import com.pet.core.domain.Knowledge;

public class ResultKnowledge implements Serializable{
	
	private static final long serialVersionUID = 1484263344321529457L;

	private int count;
	
	private List<Knowledge> knowledges;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}



}
