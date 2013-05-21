package com.taobao.api.internal.ws.push.messages;

public class MessageType {
	// following semantics was only known by client
	
	// publish message
	// publisher->server
	// server->subscriber
	public static final int PUBLISH = 1;
	// PUBACK,
	// batch pub and batch confirm
	public static final int PUBCONFIRM = 2;
	// fetch batch messages event
	public static final int FETCH = 3;
}