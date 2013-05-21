package com.taobao.api.domain;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 旅游商品类目属性值结构。
 *
 * @author auto create
 * @since 1.0, null
 */
public class TravelPropValue extends TaobaoObject {

	private static final long serialVersionUID = 2826991789273485386L;

	/**
	 * 商品所属类目ID。发布旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)。
	 */
	@ApiField("cid")
	private Long cid;

	/**
	 * 属性值名称。
	 */
	@ApiField("name")
	private String name;

	/**
	 * 旅游商品类目属性ID。
	 */
	@ApiField("pid")
	private Long pid;

	/**
	 * 属性名称。
	 */
	@ApiField("prop_name")
	private String propName;

	/**
	 * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数。
	 */
	@ApiField("sort_order")
	private Long sortOrder;

	/**
	 * 状态。可选值:normal(正常),deleted(删除)。
	 */
	@ApiField("status")
	private String status;

	/**
	 * 属性值ID。
	 */
	@ApiField("vid")
	private Long vid;

	public Long getCid() {
		return this.cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return this.pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPropName() {
		return this.propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Long getSortOrder() {
		return this.sortOrder;
	}
	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getVid() {
		return this.vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}

}
