package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.WangwangAbstractDeletewordResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.wangwang.abstract.deleteword request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class WangwangAbstractDeletewordRequest implements TaobaoRequest<WangwangAbstractDeletewordResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 传入参数的字符集
	 */
	private String charset;

	/** 
	* 关键词，长度大于0
	 */
	private String word;

	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getCharset() {
		return this.charset;
	}

	public void setWord(String word) {
		this.word = word;
	}
	public String getWord() {
		return this.word;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.wangwang.abstract.deleteword";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("charset", this.charset);
		txtParams.put("word", this.word);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new TaobaoHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Class<WangwangAbstractDeletewordResponse> getResponseClass() {
		return WangwangAbstractDeletewordResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(word,"word");
		RequestCheckUtils.checkMaxLength(word,12,"word");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
