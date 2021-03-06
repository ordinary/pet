package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.ProductBooks;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: tmall.product.books.add response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TmallProductBooksAddResponse extends TaobaoResponse {

	private static final long serialVersionUID = 2351918612954935561L;

	/** 
	 * 请求相应结构
	 */
	@ApiField("product_books")
	private ProductBooks productBooks;

	public void setProductBooks(ProductBooks productBooks) {
		this.productBooks = productBooks;
	}
	public ProductBooks getProductBooks( ) {
		return this.productBooks;
	}

}
