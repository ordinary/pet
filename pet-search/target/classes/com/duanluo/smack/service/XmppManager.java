package com.duanluo.smack.service;

import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;

import com.duanluo.base.util.IpUtil;
import com.duanluo.base.util.MD5Util;

public class XmppManager {

	private static final int packetReplyTimeout = 500; // millis

	private ConnectionConfiguration config;
	private XMPPConnection connection;

	private ChatManager chatManager;
	private MessageListener messageListener;
	private Chat chat;

	private static final String DUANLUO_USERNAME = "duanluo";

	private static final String DUANLUO_PASSWORD = "duanluo_0920";

	private static String host = "openFireService";

	private static final String DOMAIN = "@duanluo";

	private static int port = 5222;

	private boolean isExist = false;

	public XmppManager() {

	}

	public void init() throws XMPPException {
		System.out
				.println(String.format(
						"Initializing connection to server %1$s port %2$d",
						host, port));
		SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);
		config = new ConnectionConfiguration(host, port, "serverName.com");
		System.out.println("连接成功：");
		config.setSASLAuthenticationEnabled(true);
		config.setReconnectionAllowed(true);
		config.setSecurityMode(SecurityMode.enabled);
		System.out.println("config设置成功！");
		connection = new XMPPConnection(config);
		connection.connect();
		System.out.println("Connected: " + connection.isConnected());
		chatManager = connection.getChatManager();
		messageListener = new MyMessageListener();
	}

	public void test() {
		System.out.println(connection.getRoster().getEntryCount());
		;
	}

	public static void main(String[] args) throws UnknownHostException,
			Exception {
		XmppManager manager = new XmppManager();
		manager.init();
		// manager.performLogin();
		manager.test();
	}

	public void performLogin() throws XMPPException {
		System.out.println("连接对象的值：" + connection + "是否已经连接的判断："
				+ connection.isConnected());
		if (connection != null && connection.isConnected()) {
			String userName = null;
			try {
				userName = DUANLUO_USERNAME
						+ IpUtil.getIp();
			} catch (Exception e) {
				userName = DUANLUO_USERNAME;
			}

			System.out.println("登录部分的代码执行了");
			try {
				if (!isExist) {
					connection.getAccountManager().createAccount(userName,
							MD5Util.getMD5(DUANLUO_PASSWORD.getBytes()));
				}

			} catch (XMPPException e) {
				if (e.getXMPPError().getCode() == 409) {
					isExist = true;
				}
			}

			connection.login(userName + DOMAIN,
					MD5Util.getMD5(DUANLUO_PASSWORD.getBytes()));

		}
	}

	public String register(int userId, String deviceToken) {
		String userName = null;
		if (StringUtils.isNotEmpty(deviceToken)) {
			userName = userId + "_" + MD5Util.getMD5(deviceToken.getBytes());
		} else {
			userName = userId + "_";
		}
		String password = MD5Util.getMD5(DUANLUO_PASSWORD.getBytes());
		try {
			connection.getAccountManager().createAccount(userName, password);
		} catch (Exception e) {
		}
		return userName;
	}

	public void setStatus(boolean available, String status) {
		Presence.Type type = available ? Type.available : Type.unavailable;
		Presence presence = new Presence(type);

		presence.setStatus(status);
		connection.sendPacket(presence);

	}

	public void destroy() {
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
		}
	}

	public void sendMessage(String message, String userName)
			throws XMPPException {
		System.out.println(String.format("Sending mesage '%1$s' to user %2$s",
				message, userName));
		chat = chatManager.createChat(userName + DOMAIN, messageListener);
		chat.sendMessage(message);
	}
	
	

	
	public void sendBroadcastMessage(String message){
		Message m = new Message("all"+DOMAIN);  
		m.setBody(message);  
		connection.sendPacket(m);  
	}

	public void createEntry(String user, String name) throws Exception {
		System.out.println(String.format(
				"Creating entry for buddy '%1$s' with name %2$s", user, name));
		Roster roster = connection.getRoster();

		roster.createEntry(user, name, null);
	}
	
	
	public void sendSystemMessage(){
	}

	class MyMessageListener implements MessageListener {

		@Override
		public void processMessage(Chat chat, Message message) {
			System.out.println("收到消息！");
			String from = message.getFrom();
			String body = message.getBody();
			System.out.println(String.format(
					"Received message '%1$s' from %2$s", body, from));
		}

	}

}