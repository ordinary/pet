package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.simba.insight.catsrelatedword.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class SimbaInsightCatsrelatedwordGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5112672847817997878L;

	/** 
	 * 类目相关词对象列表
	 */
	@ApiListField("related_words")
	@ApiField("string")
	private List<String> relatedWords;

	public void setRelatedWords(List<String> relatedWords) {
		this.relatedWords = relatedWords;
	}
	public List<String> getRelatedWords( ) {
		return this.relatedWords;
	}

}
