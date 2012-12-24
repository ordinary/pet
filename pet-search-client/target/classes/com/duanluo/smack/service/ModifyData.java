package com.duanluo.smack.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.duanluo.notify.home.PushMessageHome;
import com.duanluo.notify.model.DeviceToken;

public class ModifyData {

	public void modify() {
		List<DeviceToken> deviceTokens = PushMessageHome.getInstance()
				.getDeviceTokenDao().getDeviceToken(0);
		for (DeviceToken deviceToken : deviceTokens) {
			if (StringUtils.isNotEmpty(deviceToken.getToken())) {
				PushMessageHome
						.getInstance()
						.getDeviceTokenDao()
						.updateDeviceTokenById(deviceToken.getId(),
								dealwithToken(deviceToken.getToken()));
			}
		}
	}

	private String dealwithToken(String deviceToken) {
		String token = "";
		if (deviceToken.startsWith("<")) {
			token = StringUtils.deleteWhitespace(deviceToken.substring(1,
					deviceToken.length() - 1));
		} else {
			token = StringUtils.deleteWhitespace(deviceToken);
		}
		return token;
	}

}
