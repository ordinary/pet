package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.Qscore;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.simba.keywordscat.qscore.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class SimbaKeywordscatQscoreGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5744185545345264283L;

	/** 
	 * 类目出价和词的质量得分对象
	 */
	@ApiField("qscore")
	private Qscore qscore;

	public void setQscore(Qscore qscore) {
		this.qscore = qscore;
	}
	public Qscore getQscore( ) {
		return this.qscore;
	}

}
