package com.taobao.api.internal.ws.push.client;

public class ClientException extends Exception {

	private static final long serialVersionUID = 454609188763498119L;

	public ClientException() {
		super();
	}

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(Throwable cause) {
		super(cause);
	}

}
