package com.pet.passport.mgr;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

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
 * 用于用户创建ticket，验证ticket的有效性个等 由登陆业务或者base interceptor来提供ticket的验证和生成操作
 * 
 * @author Terry haobo.cui
 * 
 */
public class PassportTicketManager {

    private static PassportTicketManager instance = new PassportTicketManager();

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static PassportTicketManager getInstance() {
        return instance;
    }

    private PassportTicketManager() {
    }

    public static final int TICKET4WWW = 0;//主站登录使用

    public static final int TICKET4AUTOLOGIN = 1;//主动登录使用

    public static final int TICKET4WAP = 2;//WAP站点使用

    public static final int TICKET4APP = 3;//APP使用

    public static final int TICKET4MOBILE = 4;//MobileAPP使用

    public static final int TICKET4REFRUSH = 5;//刷新票使用

    public static final long PASSPORT_TICKET_1HOURS = 3600L;//1个小时

    public static final long PASSPORT_TICKET_6HOURS = 21600L;//6个小时

    public static final long PASSPORT_TICKET_DAY = 86400L;//24小时

    public static final long PASSPORT_TICKET_WEEK = 604800L;//1周有效期

    public static final long PASSPORT_TICKET_MONTH = 2592000L;//1个月有效期

    /**
     * 指定返回一个PassportTicket对象，里面包含当前用户和登录的ticket；
     * 
     * @param ticketType 登录类型
     * @param uid 用户id
     * @return
     */
    public PassportTicket getPassportTicket(int ticketType, int uid, HttpServletRequest httpRequest) {

        PassportTicketInfo ticketInfo = new PassportTicketInfo();
        long currentTime = System.currentTimeMillis() / 1000;// 当前系统时间以秒为单位
        if (ticketType == TICKET4WWW) {
            //常规网站登录			    
            ticketInfo.setTicketLife(PASSPORT_TICKET_6HOURS);
        } else if (ticketType == TICKET4AUTOLOGIN) {
            //网站自动登录
            ticketInfo.setTicketLife(PASSPORT_TICKET_MONTH);
        } else if (ticketType == TICKET4WAP) {
            //wap站点登录
            ticketInfo.setTicketLife(PASSPORT_TICKET_DAY);
        }
        long expiresTime = PassportTicketUtil.getExpiresTime(ticketType,
                ticketInfo.getTicketLife(), currentTime);// 取过期时间
        ticketInfo.setTicketExpires(expiresTime);// 设置ticket的有效期
        ticketInfo.setTicketType(ticketType);// 设置ticket的类型
        ticketInfo.setUid(uid);// 设置用户id
        Date date = PassportTicketUtil.getDateIgnoreHMS((currentTime - 1) * 1000L); // 减1秒是为了在每天0点取前一天的加密key
        PassportTicketKey encryptKeys = PassportTicketKeyHome.getInstance()
                .getAndCreateEncryptionKeys(date, ticketType);// 同时取得加密密钥
        ticketInfo.setPtEncryptionKey(encryptKeys.getTicketKey());
        //获取用户的user-agent来保证签名的安全性
        String userAgent = httpRequest.getHeader("User-Agent");
        if (StringUtils.isNotEmpty(userAgent)) {
            ticketInfo.setUserAgent(userAgent.trim());
        } else {
            ticketInfo.setUserAgent("---");
        }

        // 调用只返回sessionKey和expiresTime的接口
        PassportTicket pt = FinitePassportTicketGenerator.getInstance().generateFiniteSessionKey(
                ticketInfo);
        //        this.runDelete(pt); // 暂时不需要异步删除操作，如果有持久化的ticket考虑加入
        return pt;
    }

    /**
     * 验证ticket是否有效，验证结果封装在一个TicketValidationResult对象里，
     * 同时，验证结果中还顺带返回一个userId， 如果验证失败，则返回的验证结果中的userId未定义
     * 如果有效期时间小于一个则重新生成这个ticket放置在验证对象中返回
     * 
     * @param passportTicket
     * @return
     */
    public TicketValidationResult validateTicket(String passportTicket,
            HttpServletRequest httpRequest) {
        String userAgent = httpRequest.getHeader("User-Agent");
        // 解析passportTicket,反解除所有相关的参数
        PassportTicketInfo ticketInfo = PassportTicketUtil.parseFiniteSessionKey(passportTicket);
        if (StringUtils.isNotEmpty(userAgent)) {
            ticketInfo.setUserAgent(userAgent.trim());
        } else {
            ticketInfo.setUserAgent("---");
        }
        //结果对象类
        TicketValidationResult validationResult = new TicketValidationResult();
        validationResult.setPassportTicket(passportTicket);//设置原始的ticket
        /**
         * FinitePassportTicketValidator用于来验证ticket的合法性，过期等操作
         */
        FinitePassportTicketValidator.getInstance().validatePassportTicket(validationResult,
                ticketInfo);//返回验证结果
        //当验证通过的情况下，小于一个小时的话下发最新的ticket
        long curTime = System.currentTimeMillis() / 1000L;
        if ((validationResult.getValidationCode() == TicketValidationResult.PT_VALIDATION_OK)
                && (ticketInfo.getTicketExpires() - curTime) < 3600) {
            //生成新的ticket放入到验证对象中提供给登陆等业务提取
            this.expireTicket(validationResult.getPassportTicket());
            validationResult.setPassportTicket(this.getPassportTicket(ticketInfo.getTicketType(),
                    ticketInfo.getUid(), httpRequest).getPassportTicket());
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
        //内部应该解析ticket，设置合理的memcache过期时间
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

