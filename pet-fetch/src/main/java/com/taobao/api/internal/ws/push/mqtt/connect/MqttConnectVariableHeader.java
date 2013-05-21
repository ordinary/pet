package com.taobao.api.internal.ws.push.mqtt.connect;

import com.taobao.api.internal.ws.push.mqtt.MqttVariableHeader;

public class MqttConnectVariableHeader extends MqttVariableHeader {

	@Override
	protected int getReadFlags() {
		return ReadWriteFlags.ProtocolName | ReadWriteFlags.ProtocolVersion
				| ReadWriteFlags.ConnectFlags | ReadWriteFlags.KeepAlive;
	}

	@Override
	protected int getWriteFlags() {
		return this.getReadFlags();
	}
}
