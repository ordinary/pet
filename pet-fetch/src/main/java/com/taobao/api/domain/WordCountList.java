package com.taobao.api.domain;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 关键词统计
 *
 * @author auto create
 * @since 1.0, null
 */
public class WordCountList extends TaobaoObject {

	private static final long serialVersionUID = 3772713276469498732L;

	/**
	 * 关键词数量
	 */
	@ApiField("count")
	private Long count;

	/**
	 * 关键词
	 */
	@ApiField("word")
	private String word;

	public Long getCount() {
		return this.count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

	public String getWord() {
		return this.word;
	}
	public void setWord(String word) {
		this.word = word;
	}

}
