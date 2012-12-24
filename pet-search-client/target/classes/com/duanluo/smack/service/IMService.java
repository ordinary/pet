package com.duanluo.smack.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.XMPPException;

import com.duanluo.base.util.MD5Util;
import com.duanluo.notify.home.TokenDeviceHome;
import com.duanluo.notify.model.DeviceToken;
import com.duanluo.smack.proto.SmackRequestProto.SmackRequest;

public class IMService {

	private static IMService service;

	public XmppManager xmppManager;

	// private static String buddyName = "friend";

	public static IMService getInstance() throws XMPPException {
		if (service == null) {
			XmppManager xmppManager = new XmppManager();
			System.out.println("连接服务器成功！");
			xmppManager.init();
			System.out.println("初始化成功！");
			xmppManager.performLogin();
			System.out.println("登录成功！");
			xmppManager.setStatus(true, "Hello everyone");
			service = new IMService();
			service.setXmppManager(xmppManager);
		}
		return service;
	}

	public void chat(SmackRequest request) {
		try {
			List<DeviceToken> deviceTokens = TokenDeviceHome.getInstance()
					.getDeviceToken(request.getUserId());
			if (deviceTokens != null && !deviceTokens.isEmpty()) {
				String userName = null;
				for (DeviceToken deviceToken : deviceTokens) {
					if (StringUtils.isNotEmpty(deviceToken.getToken())) {
						userName = request.getUserId()
								+ "_"
								+ MD5Util.getMD5(transfer(
										deviceToken.getToken()).getBytes());
					} else {
						userName = request.getUserId() + "_";
					}
					System.out.println("Content :" + request.getContent());
					if (request.getUserId() != 0) {
						xmppManager.sendMessage(new String(request.getContent().getBytes(),"UTF-8"),new String(userName.getBytes(),"UTF-8") );
					} else {
						xmppManager.sendBroadcastMessage(request.getContent());
					}
					Thread.sleep(50);
				}
			} else {
				String userName = request.getUserId() + "_";
				System.out.println("Content :" + request.getContent());
				if (request.getUserId() != 0) {
					xmppManager.sendMessage(request.getContent(), userName);
				} else {
					xmppManager.sendBroadcastMessage(request.getContent());
				}
				Thread.sleep(50);
			}

		} catch (Exception e) {
		}
	}

	public static String transfer(String token) {
		String newToken = "";
		if (token.length() == 64) {
			newToken = "<" + token.substring(0, 8) + " "
					+ token.substring(8, 16) + " " + token.substring(16, 24)
					+ " " + token.substring(24, 32) + " "
					+ token.substring(32, 40) + " " + token.substring(40, 48)
					+ " " + token.substring(48, 56) + " "
					+ token.substring(56, 64) + ">";
		} else {
			newToken = token;
		}
		return newToken;
	}

	public String register(int userId, String deviceToken) {
		return xmppManager.register(userId, deviceToken);
	}

	public void setXmppManager(XmppManager xmppManager) {
		this.xmppManager = xmppManager;
	}

	
	public static void main(String[] args) {
		
	System.out.println(MD5Util.getMD5(IMService.transfer("b6c5f7a3cba5e09bf38d4b0bee22dcaf39424cbd1bb6c34b3628c5ddcffd97ba").getBytes()));
		
	}
}
