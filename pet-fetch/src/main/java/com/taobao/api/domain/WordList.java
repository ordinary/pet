package com.taobao.api.domain;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 关键词列表
 *
 * @author auto create
 * @since 1.0, null
 */
public class WordList extends TaobaoObject {

	private static final long serialVersionUID = 6467858372763133983L;

	/**
	 * 关键词
	 */
	@ApiField("word")
	private String word;

	public String getWord() {
		return this.word;
	}
	public void setWord(String word) {
		this.word = word;
	}

}
