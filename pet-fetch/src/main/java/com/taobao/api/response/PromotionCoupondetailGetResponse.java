package com.taobao.api.response;

import java.util.List;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import com.taobao.api.domain.CouponDetail;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.promotion.coupondetail.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class PromotionCoupondetailGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6518148432361627359L;

	/** 
	 * 优惠券详细信息
	 */
	@ApiListField("coupon_details")
	@ApiField("coupon_detail")
	private List<CouponDetail> couponDetails;

	/** 
	 * 是否还有下一页  true表示当前查询的结果还有下一页，false表示当前查询的结果已经是最后一页
	 */
	@ApiField("is_have_next_page")
	private Boolean isHaveNextPage;

	/** 
	 * 查询数量总数
	 */
	@ApiField("total_results")
	private Long totalResults;

	public void setCouponDetails(List<CouponDetail> couponDetails) {
		this.couponDetails = couponDetails;
	}
	public List<CouponDetail> getCouponDetails( ) {
		return this.couponDetails;
	}

	public void setIsHaveNextPage(Boolean isHaveNextPage) {
		this.isHaveNextPage = isHaveNextPage;
	}
	public Boolean getIsHaveNextPage( ) {
		return this.isHaveNextPage;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	public Long getTotalResults( ) {
		return this.totalResults;
	}

}
