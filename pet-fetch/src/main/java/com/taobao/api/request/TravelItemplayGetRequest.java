package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.TravelItemplayGetResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.travel.itemplay.get request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class TravelItemplayGetRequest implements TaobaoRequest<TravelItemplayGetResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 商品所属类目ID。旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)
	 */
	private Long cid;

	/** 
	* 目的地code列表，多个目的地code以“,”分隔
	 */
	private String destCodes;

	/** 
	* 玩法类型，1跟团游、2自由行
	 */
	private Long playType;

	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getCid() {
		return this.cid;
	}

	public void setDestCodes(String destCodes) {
		this.destCodes = destCodes;
	}
	public String getDestCodes() {
		return this.destCodes;
	}

	public void setPlayType(Long playType) {
		this.playType = playType;
	}
	public Long getPlayType() {
		return this.playType;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.travel.itemplay.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("cid", this.cid);
		txtParams.put("dest_codes", this.destCodes);
		txtParams.put("play_type", this.playType);
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

	public Class<TravelItemplayGetResponse> getResponseClass() {
		return TravelItemplayGetResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(cid,"cid");
		RequestCheckUtils.checkNotEmpty(destCodes,"destCodes");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
