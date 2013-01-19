package com.pet.passport.mgr;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pet.passport.home.ExpiredFinitePTHome;
import com.pet.passport.home.PassportTicketKeyHome;
import com.pet.passport.logic.FinitePassportTicketGenerator;
import com.pet.passport.logic.FinitePassportTicketValidator;
import com.pet.passport.model.PassportTicket;
import com.pet.passport.model.PassportTicketInfo;
import com.pet.passport.model.PassportTicketKey;
import com.pet.passport.model.TicketValidationResult;
import com.pet.passport.util.PassportTicketUtil;

/**
 * 用于用户创建accessToken，验证accessToken的有效性，同时提供refrushToken的功能
 * 
 * @author Terry haobo.cui
 * 
 */
public class AccessTokenManager {

	private static AccessTokenManager instance = new AccessTokenManager();

	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public static AccessTokenManager getInstance() {
		return instance;
	}

	private AccessTokenManager() {
	}

	public static final int TICKET4WWW = 0;// 主站登录使用

	public static final int TICKET4AUTOLOGIN = 1;// 主动登录使用

	public static final int TICKET4WAP = 2;// WAP站点使用

	public static final int TICKET4APP = 3;// APP使用

	public static final int TICKET4MOBILE = 4;// MobileAPP使用

	public static final int TICKET4REFRUSH = 5;// 刷新票使用

	public static final long PASSPORT_TICKET_1HOURS = 3600L;// 1个小时

	public static final long PASSPORT_TICKET_6HOURS = 21600L;// 6个小时

	public static final long PASSPORT_TICKET_DAY = 86400L;// 24小时

	public static final long PASSPORT_TICKET_WEEK = 604800L;// 1周有效期

	public static final long PASSPORT_TICKET_MONTH = 2592000L;// 1个月有效期

	/**
	 * 提供给平台用户accessToken和对应的refrushToken的验证码获取
	 * 
	 * @param ticketType
	 *            查考类的静态定义
	 * @param uid
	 * @param appId
	 * @return
	 */
	public PassportTicket getAccess(int ticketType, int uid, int appId) {
		PassportTicketInfo ticketInfo = new PassportTicketInfo();
		long currentTime = System.currentTimeMillis() / 1000;// 当前系统时间以秒为单位
		if (ticketType == TICKET4APP) {
			// app常规
			ticketInfo.setTicketLife(PASSPORT_TICKET_1HOURS);
		} else if (ticketType == TICKET4MOBILE || ticketType == TICKET4REFRUSH) {
			// app一个月或者刷新
			ticketInfo.setTicketLife(PASSPORT_TICKET_MONTH);
		}
		long expiresTime = PassportTicketUtil.getExpiresTime(ticketType, ticketInfo.getTicketLife(), currentTime);// 取过期时间
		ticketInfo.setTicketExpires(expiresTime);// 设置ticket的有效期
		ticketInfo.setTicketType(ticketType);// 设置ticket的类型
		ticketInfo.setUid(uid);// 设置用户id
		ticketInfo.setAppId(appId);// 设置应用的id

		Date date = PassportTicketUtil.getDateIgnoreHMS((currentTime - 1) * 1000L); // 减1秒是为了在每天0点取前一天的加密key

		PassportTicketKey encryptKeys = PassportTicketKeyHome.getInstance().getAndCreateEncryptionKeys(date, ticketType);// 同时取得加密密钥

		ticketInfo.setPtEncryptionKey(encryptKeys.getTicketKey());
		// 获取用户的user-agent来保证签名的安全性 ,app无法获取且动态变更设置为---
		ticketInfo.setUserAgent("---");
		// 调用只返回sessionKey和expiresTime的接口
		PassportTicket pt = FinitePassportTicketGenerator.getInstance().generateFiniteSessionKey(ticketInfo);
		// this.runDelete(pt); // 暂时不需要异步删除操作，如果有持久化的ticket考虑加入
		return pt;
	}

	/**
	 * 验证accessToken是否有效，验证结果封装在一个TicketValidationResult对象里，
	 * 同时，验证结果中还顺带返回一个userId， 如果验证失败，则返回的验证结果中的userId未定义
	 * 如果有效期时间小于一个则重新生成这个accessToken放置在验证对象中返回
	 * 
	 * @param passportTicket
	 * @return
	 */
	public TicketValidationResult validateTicket(String accessToken, int appId) {
		// 解析passportTicket,反解除所有相关的参数
		PassportTicketInfo ticketInfo = PassportTicketUtil.parseFiniteSessionKey(accessToken);
		if (ticketInfo == null) {
			return null;
		}
		ticketInfo.setUserAgent("---");
		ticketInfo.setAppId(appId);
		// 结果对象类
		TicketValidationResult validationResult = new TicketValidationResult();
		validationResult.setPassportTicket(accessToken);// 设置原始的ticket
		/**
		 * FinitePassportTicketValidator用于来验证ticket的合法性，过期等操作
		 */
		FinitePassportTicketValidator.getInstance().validatePassportTicket(validationResult, ticketInfo);// 返回验证结果
		// 当验证通过的情况下，小于一个小时的话下发最新的ticket
		long curTime = System.currentTimeMillis() / 1000L;
		if ((validationResult.getValidationCode() == TicketValidationResult.PT_VALIDATION_OK) && (ticketInfo.getTicketExpires() - curTime) < 3600) {
			// 生成新的ticket放入到验证对象中提供给登陆等业务提取
			this.expireTicket(validationResult.getPassportTicket());
			validationResult.setPassportTicket(this.getAccess(ticketInfo.getTicketType(), ticketInfo.getUid(), ticketInfo.getAppId()).getPassportTicket());
		}
		return validationResult;
	}

	/**
	 * 验证Token是否有效，验证结果封装在一个TicketValidationResult对象里， 同时，验证结果中还顺带返回一个userId，
	 * 如果验证失败，则返回的验证结果中的userId未定义 如果有效期时间小于一个则重新生成这个Token放置在验证对象中返回
	 * 该类并不会去验证token有效期
	 * 
	 * @param token
	 * @param appId
	 * @param tokenType
	 * @return
	 */
	public TicketValidationResult validateTicket(String token, int appId, int tokenType) {
		// 解析passportTicket,反解除所有相关的参数
		PassportTicketInfo ticketInfo = PassportTicketUtil.parseFiniteSessionKey(token);
		if (ticketInfo == null) {
			return null;
		}
		ticketInfo.setUserAgent("---");
		ticketInfo.setAppId(appId);
		// 结果对象类
		TicketValidationResult validationResult = new TicketValidationResult();
		validationResult.setPassportTicket(token);// 设置原始的ticket
		/**
		 * FinitePassportTicketValidator用于来验证ticket的合法性，过期等操作
		 */
		FinitePassportTicketValidator.getInstance().validateToken(validationResult, ticketInfo, tokenType);// 返回验证结果
		// 当验证通过的情况下，小于一个小时的话下发最新的ticket
		long curTime = System.currentTimeMillis() / 1000L;
		if ((validationResult.getValidationCode() == TicketValidationResult.PT_VALIDATION_OK) && (ticketInfo.getTicketExpires() - curTime) < 3600) {
			// 生成新的ticket放入到验证对象中提供给登陆等业务提取
			this.expireTicket(validationResult.getPassportTicket());
			validationResult.setPassportTicket(this.getAccess(ticketInfo.getTicketType(), ticketInfo.getUid(), ticketInfo.getAppId()).getPassportTicket());
		}
		return validationResult;
	}

	/**
	 * 明确地将ticket置为过期，操作成功返回true，操作失败返回false,当用户使用退出操作的时候使用
	 * 
	 * @param passportTicket
	 * @return
	 */
	public boolean expireTicket(String passportTicket) {
		// 内部应该解析ticket，设置合理的memcache过期时间
		return ExpiredFinitePTHome.getInstance().set(passportTicket);
	}

	// 异步执行删除过期ticket操作
	public void runDelete(final PassportTicket pt) {
		executor.execute(new Runnable() {

			public void run() {
				try {
					ExpiredFinitePTHome.getInstance().delete(pt.getPassportTicket());
				} catch (Exception e) {
					e.printStackTrace();// 如果出现异常，关闭之前的删除操作任务
				}
			}
		});
	}

}
