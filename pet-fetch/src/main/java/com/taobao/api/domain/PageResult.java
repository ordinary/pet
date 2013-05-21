package com.taobao.api.domain;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 分页结果
 *
 * @author auto create
 * @since 1.0, null
 */
public class PageResult extends TaobaoObject {

	private static final long serialVersionUID = 3887424222468934483L;

	/**
	 * 用户查询时是第几页
	 */
	@ApiField("current_page")
	private Long currentPage;

	/**
	 * 每页多少条记录
	 */
	@ApiField("page_size")
	private Long pageSize;

	/**
	 * 查询时是分页查询，则返回总条数。
	 */
	@ApiField("total_amount")
	private Long totalAmount;

	/**
	 * 一共多少页 结果
	 */
	@ApiField("total_page")
	private Long totalPage;

	public Long getCurrentPage() {
		return this.currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return this.pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalAmount() {
		return this.totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getTotalPage() {
		return this.totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

}
