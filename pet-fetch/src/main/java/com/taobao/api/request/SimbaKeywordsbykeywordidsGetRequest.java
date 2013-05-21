package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.SimbaKeywordsbykeywordidsGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.simba.keywordsbykeywordids.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class SimbaKeywordsbykeywordidsGetRequest implements TaobaoRequest<SimbaKeywordsbykeywordidsGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 关键词Id数组，最多200个；
	 */
	private String keywordIds;

	/** 
	* 主人昵称
	 */
	private String nick;

	public void setKeywordIds(String keywordIds) {
		this.keywordIds = keywordIds;
	}
	public String getKeywordIds() {
		return this.keywordIds;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getNick() {
		return this.nick;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.simba.keywordsbykeywordids.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("keyword_ids", this.keywordIds);
		txtParams.put("nick", this.nick);
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

	public Class<SimbaKeywordsbykeywordidsGetResponse> getResponseClass() {
		return SimbaKeywordsbykeywordidsGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkMaxListSize(keywordIds,200,"keywordIds");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
