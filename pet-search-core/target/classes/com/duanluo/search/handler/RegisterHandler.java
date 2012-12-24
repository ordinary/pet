package com.duanluo.search.handler;

import org.jivesoftware.smack.XMPPException;

import com.duanluo.network.annotation.Handler;
import com.duanluo.network.handler.RpcMessageHandler;
import com.duanluo.smack.proto.RegisterRepsonseProto.RegisterRepsonse;
import com.duanluo.smack.proto.RegisterRequestProto.RegisterRequest;
import com.duanluo.smack.service.IMService;
import com.google.protobuf.Message;

@Handler
public class RegisterHandler implements RpcMessageHandler {

	@Override
	public Message handleMessage(Message message) throws Exception {
		RegisterRequest request = (RegisterRequest) message;
		RegisterRepsonse.Builder responseBuilder = RegisterRepsonse
				.newBuilder();
		responseBuilder.setUserName(register(request));
		return responseBuilder.build();
	}

	@Override
	public Message getRequest() {
		return RegisterRequest.getDefaultInstance();
	}

	@Override
	public Message getResponse() {
		return RegisterRepsonse.getDefaultInstance();
	}

	private String register(RegisterRequest request) throws XMPPException {

		return IMService.getInstance().register(request.getUserId(),
				request.getDeviceToken());

	}

}
