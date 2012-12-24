package com.pet.domain;

import java.io.Serializable;
import java.util.List;

import com.pet.core.domain.Commodity;

public class CommodityResult  implements Serializable{

	private static final long serialVersionUID = 2427487290408072037L;

	private int count;
	
	private List<Commodity> commoditys;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Commodity> getCommoditys() {
		return commoditys;
	}

	public void setCommoditys(List<Commodity> commoditys) {
		this.commoditys = commoditys;
	}

}
