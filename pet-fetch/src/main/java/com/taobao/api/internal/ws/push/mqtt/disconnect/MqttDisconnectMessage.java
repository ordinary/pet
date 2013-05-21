package com.taobao.api.internal.ws.push.mqtt.disconnect;

import com.taobao.api.internal.ws.push.mqtt.MqttHeader;
import com.taobao.api.internal.ws.push.mqtt.MqttMessage;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageType;

public class MqttDisconnectMessage extends MqttMessage {

	public MqttDisconnectMessage() {
		this.Header = new MqttHeader();
		this.Header.MessageType = MqttMessageType.Disconnect;
	}
}
