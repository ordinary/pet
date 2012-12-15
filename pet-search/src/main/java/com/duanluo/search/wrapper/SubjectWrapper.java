package com.duanluo.search.wrapper;

import com.duanluo.search.annotations.Search;

@Search(indexPath="",similarity="")
public class SubjectWrapper {
	
	private String subjectId;
	
	private String type;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
