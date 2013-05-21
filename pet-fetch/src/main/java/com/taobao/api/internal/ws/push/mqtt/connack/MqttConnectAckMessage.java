package com.taobao.api.internal.ws.push.mqtt.connack;

import com.taobao.api.internal.ws.push.mqtt.MqttHeader;
import com.taobao.api.internal.ws.push.mqtt.MqttMessage;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageType;

public class MqttConnectAckMessage extends MqttMessage {
	public MqttConnectAckVariableHeader VariableHeader;

	public MqttConnectAckMessage() {
		this.Header = new MqttHeader();
		this.Header.MessageType = MqttMessageType.ConnectAck;
		this.VariableHeader = new MqttConnectAckVariableHeader();
	}
}
