package com.taobao.api.internal.ws.push.mqtt.publish;

import com.taobao.api.internal.ws.push.mqtt.MqttHeader;
import com.taobao.api.internal.ws.push.mqtt.MqttQos;
import com.taobao.api.internal.ws.push.mqtt.MqttVariableHeader;

public class MqttPublishVariableHeader extends MqttVariableHeader {

	private MqttHeader header;

	public MqttPublishVariableHeader(MqttHeader header) {
		this.header = header;
	}

	@Override
	protected int getReadFlags() {
		if (this.header.Qos == MqttQos.AtLeastOnce
				|| this.header.Qos == MqttQos.ExactlyOnce) {
			return ReadWriteFlags.TopicName | ReadWriteFlags.MessageIdentifier;
		} else {
			return ReadWriteFlags.TopicName;
		}
	}

	@Override
	protected int getWriteFlags() {
		return this.getReadFlags();
	}
}
