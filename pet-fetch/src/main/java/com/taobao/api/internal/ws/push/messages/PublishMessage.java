package com.taobao.api.internal.ws.push.messages;

@Deprecated
public class PublishMessage extends Message {
	public String id;

	public PublishMessage() {
		this.messageType = MessageType.PUBLISH;
	}

	// @Override
	// protected void internalClear() {
	// this.messageType = MessageType.PUBLISH;
	// this.id = null;
	// }
}
