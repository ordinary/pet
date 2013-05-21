package com.taobao.api.internal.ws;
public class Message {
	private byte[] bytesBody;
	private int offset;
	private int length;
	
	public int getLength() {
		return length;
	}

	public byte[] getBytesBody() {
		return bytesBody;
	}

	public int getOffset() {
		return offset;
	}

	public String getStringContent() throws Exception{
		return new String(bytesBody,offset,length,"UTF-8");
	}
	public Message(byte[] bytesBody,int offset,int length){
		this.bytesBody=bytesBody;
		this.offset=offset;
		this.length=length;
	}

}
