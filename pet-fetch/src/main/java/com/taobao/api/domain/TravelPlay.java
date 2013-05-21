package com.taobao.api.domain;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 旅游度假线路玩法结构。
 *
 * @author auto create
 * @since 1.0, null
 */
public class TravelPlay extends TaobaoObject {

	private static final long serialVersionUID = 1168334872288361125L;

	/**
	 * 商品所属类目ID。发布旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)
	 */
	@ApiField("cid")
	private Long cid;

	/**
	 * 玩法描述
	 */
	@ApiField("description")
	private String description;

	/**
	 * 是否通过审核，true通过，false未通过
	 */
	@ApiField("is_auth")
	private Boolean isAuth;

	/**
	 * 线路玩法ID
	 */
	@ApiField("play_id")
	private Long playId;

	/**
	 * 线路玩法名称
	 */
	@ApiField("play_name")
	private String playName;

	/**
	 * 玩法类型，1跟团游，2自由行
	 */
	@ApiField("play_type")
	private Long playType;

	public Long getCid() {
		return this.cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsAuth() {
		return this.isAuth;
	}
	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}

	public Long getPlayId() {
		return this.playId;
	}
	public void setPlayId(Long playId) {
		this.playId = playId;
	}

	public String getPlayName() {
		return this.playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}

	public Long getPlayType() {
		return this.playType;
	}
	public void setPlayType(Long playType) {
		this.playType = playType;
	}

}
