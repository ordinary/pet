package com.taobao.api.internal.ws.push.mqtt.puback;

import com.taobao.api.internal.ws.push.mqtt.MqttConnectFlags;
import com.taobao.api.internal.ws.push.mqtt.MqttHeader;
import com.taobao.api.internal.ws.push.mqtt.MqttMessage;
import com.taobao.api.internal.ws.push.mqtt.MqttMessageType;

public class MqttPublishAckMessage extends MqttMessage {
	// http://public.dhe.ibm.com/software/dw/webservices/ws-mqtt/mqtt-v3r1.html#puback
	// A PUBACK message is the response to a PUBLISH message with QoS level 1. A
	// PUBACK message is sent by a server in response to a PUBLISH message from
	// a publishing client, and by a subscriber in response to a PUBLISH message
	// from the server.

	public MqttPublishAckVariableHeader VariableHeader;

	public MqttPublishAckMessage() {
		this.Header = new MqttHeader();
		this.Header.MessageType = MqttMessageType.PublishAck;
		this.VariableHeader = new MqttPublishAckVariableHeader();
		this.VariableHeader.ConnectFlags = new MqttConnectFlags();
	}
	
	@Override
	public void clear() {
		super.clear();
		// TODO:clear VariableHeader
	}
}
