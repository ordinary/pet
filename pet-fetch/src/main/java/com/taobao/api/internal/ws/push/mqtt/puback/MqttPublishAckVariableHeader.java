package com.taobao.api.internal.ws.push.mqtt.puback;

import com.taobao.api.internal.ws.push.mqtt.MqttVariableHeader;

public class MqttPublishAckVariableHeader extends MqttVariableHeader {

	@Override
	protected int getReadFlags() {
		return ReadWriteFlags.MessageIdentifier;
	}

	@Override
	protected int getWriteFlags() {
		return ReadWriteFlags.MessageIdentifier;
	}
}
