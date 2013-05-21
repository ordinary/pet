package com.taobao.api.domain;

import java.util.List;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;

/**
 * 旅游商品类目属性结构。
 *
 * @author auto create
 * @since 1.0, null
 */
public class TravelItemProp extends TaobaoObject {

	private static final long serialVersionUID = 4235891172366511279L;

	/**
	 * 商品所属类目ID。发布旅游线路商品有两个值：1(国内线路类目ID)，2(国际线路类目ID)。
	 */
	@ApiField("cid")
	private Long cid;

	/**
	 * 是否销售属性。可选值:true(是),false(否)。
	 */
	@ApiField("is_sale_prop")
	private Boolean isSaleProp;

	/**
	 * 发布商品时是否可以多选。可选值: true (是) , false(否)。
	 */
	@ApiField("multi")
	private Boolean multi;

	/**
	 * 发布商品时是否必选。可选值: true (是) , false(否)。
	 */
	@ApiField("must")
	private Boolean must;

	/**
	 * 属性名称。
	 */
	@ApiField("name")
	private String name;

	/**
	 * 旅游商品类目属性ID。
	 */
	@ApiField("pid")
	private Long pid;

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
	 * 类目属性值集合。
	 */
	@ApiListField("travel_prop_values")
	@ApiField("travel_prop_value")
	private List<TravelPropValue> travelPropValues;

	public Long getCid() {
		return this.cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Boolean getIsSaleProp() {
		return this.isSaleProp;
	}
	public void setIsSaleProp(Boolean isSaleProp) {
		this.isSaleProp = isSaleProp;
	}

	public Boolean getMulti() {
		return this.multi;
	}
	public void setMulti(Boolean multi) {
		this.multi = multi;
	}

	public Boolean getMust() {
		return this.must;
	}
	public void setMust(Boolean must) {
		this.must = must;
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

	public List<TravelPropValue> getTravelPropValues() {
		return this.travelPropValues;
	}
	public void setTravelPropValues(List<TravelPropValue> travelPropValues) {
		this.travelPropValues = travelPropValues;
	}

}
