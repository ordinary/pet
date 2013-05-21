package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.TaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.SimbaCreativeUpdateResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.simba.creative.update request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class SimbaCreativeUpdateRequest implements TaobaoRequest<SimbaCreativeUpdateResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 推广组Id
	 */
	private Long adgroupId;

	/** 
	* 创意Id
	 */
	private Long creativeId;

	/** 
	* 创意图片地址，必须是推广组对应商品的图片之一
	 */
	private String imgUrl;

	/** 
	* 主人昵称
	 */
	private String nick;

	/** 
	* 创意标题，最多20个汉字
	 */
	private String title;

	public void setAdgroupId(Long adgroupId) {
		this.adgroupId = adgroupId;
	}
	public Long getAdgroupId() {
		return this.adgroupId;
	}

	public void setCreativeId(Long creativeId) {
		this.creativeId = creativeId;
	}
	public Long getCreativeId() {
		return this.creativeId;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getNick() {
		return this.nick;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.simba.creative.update";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("adgroup_id", this.adgroupId);
		txtParams.put("creative_id", this.creativeId);
		txtParams.put("img_url", this.imgUrl);
		txtParams.put("nick", this.nick);
		txtParams.put("title", this.title);
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

	public Class<SimbaCreativeUpdateResponse> getResponseClass() {
		return SimbaCreativeUpdateResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkNotEmpty(adgroupId,"adgroupId");
		RequestCheckUtils.checkNotEmpty(creativeId,"creativeId");
		RequestCheckUtils.checkNotEmpty(imgUrl,"imgUrl");
		RequestCheckUtils.checkNotEmpty(title,"title");
		RequestCheckUtils.checkMaxLength(title,40,"title");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
