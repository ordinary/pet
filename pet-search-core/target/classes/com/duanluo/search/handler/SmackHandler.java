package com.duanluo.search.handler;

import org.jivesoftware.smack.XMPPException;

import com.duanluo.network.annotation.Handler;
import com.duanluo.network.handler.RpcMessageHandler;
import com.duanluo.proto.NOResponseProto.NOResponse;
import com.duanluo.smack.proto.SmackRequestProto.SmackRequest;
import com.duanluo.smack.service.IMService;
import com.google.protobuf.Message;

@Handler
public class SmackHandler implements RpcMessageHandler {

	@Override
	public Message handleMessage(Message message) throws Exception {
		SmackRequest request = (SmackRequest) message;
		chat(request);
		NOResponse.Builder responseBuilder = NOResponse.newBuilder();
		return responseBuilder.build();
	}

	@Override
	public Message getRequest() {
		return SmackRequest.getDefaultInstance();
	}

	@Override
	public Message getResponse() {
		return NOResponse.getDefaultInstance();
	}
	
	private void chat(SmackRequest request ){
		try {
			IMService.getInstance().chat(request);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

}