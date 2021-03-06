/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.alipay.api;

import com.taobao.api.ApiException;

/**
 * 
 * @author runzhi
 */
public class AlipayApiException extends ApiException {

    /**  */
    private static final long serialVersionUID = -3681931870669185915L;

    public AlipayApiException() {
        super();
    }

    public AlipayApiException(Throwable cause) {
        super(cause);
    }

    public AlipayApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
