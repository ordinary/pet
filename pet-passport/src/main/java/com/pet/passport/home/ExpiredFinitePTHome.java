package com.pet.passport.home;

import com.pet.passport.cache.ExpiredFinitePTMemCachedUtil;
import com.pet.passport.model.PassportTicketInfo;
import com.pet.passport.util.PassportTicketUtil;

/**
 * ticket过期的操作，用户退出的时候将ticket置为过期，保证安全
 * 
 * @author Terry haobo.cui
 * 
 */
public class ExpiredFinitePTHome {

    private static ExpiredFinitePTHome instance = new ExpiredFinitePTHome();

    public static ExpiredFinitePTHome getInstance() {
        return instance;
    }

    private ExpiredFinitePTHome() {
    }

    /**
     * 验证ticket是否处于过期队列中
     * 
     * @param passportTicket
     * @return
     */
    public int get(String passportTicket) {
        return ExpiredFinitePTMemCachedUtil.getInstance().get(passportTicket);
    }

    /**
     * 将ticket置为过期
     * 
     * @param sessionKey
     * @return
     */
    public boolean set(String passportTicket) {
        PassportTicketInfo ticketInfo = PassportTicketUtil.parseFiniteSessionKey(passportTicket);
        boolean done = false;
        if (passportTicket != null) {
            long currentSeconds = (System.currentTimeMillis() / 1000L);
            if (ticketInfo.getTicketExpires() > currentSeconds) {
                //获取过期时间，同时延长60s
                long memCachedLife = (ticketInfo.getTicketExpires() - currentSeconds) + 60;
                done = ExpiredFinitePTMemCachedUtil.getInstance().set(passportTicket, memCachedLife);
            }
        }
        return done;
    }

    public boolean delete(String passportTicket) {
        return ExpiredFinitePTMemCachedUtil.getInstance().delete(passportTicket);
    }
}

