package com.pet.passport.cache;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

//import cache.duanluo.com.home.Cache;
//import cache.duanluo.com.home.RedisClientHome;

/**
 * 
 * @author Terry haobo.cui
 * 
 */

//TODO: FIXME: need 2 complete
public class ExpiredFinitePTMemCachedUtil implements Serializable {

   
    private static final long serialVersionUID = 1L;

    private static ExpiredFinitePTMemCachedUtil instance = new ExpiredFinitePTMemCachedUtil();

    public static ExpiredFinitePTMemCachedUtil getInstance() {
        return instance;
    }

    private ExpiredFinitePTMemCachedUtil() {
    }

    public static final String PREFIX_EXPIRED_PASSPORT_TICKET = "user_expired_passport_ticket_";

    public int get(String sessionKey) {
        String key = PREFIX_EXPIRED_PASSPORT_TICKET + sessionKey;

//        MemcachedManager passportMemManager = MemcachedManagerFactory.getManager(MemcachedManagerFactory.PASSPORT_POOL);
//        Cache<String, String> passportMemManager = RedisClientHome.getInstance().getTestCache();
//        String val = passportMemManager.get(key);
//        if (StringUtils.isNotEmpty(val)) {
//            return 1;
//        } else {
//            return 0;
//        }
        return 0;
    }

    public boolean set(String sessionKey, long expireSeconds) {
        return set(sessionKey, "0", expireSeconds);
    }

    public boolean set(String sessionKey, String val, long expireSeconds) {
        String key = PREFIX_EXPIRED_PASSPORT_TICKET + sessionKey;
//        MemcachedManager passportMemManager = MemcachedManagerFactory.getManager(MemcachedManagerFactory.PASSPORT_POOL);
//        Cache<String, String> passportMemManager = RedisClientHome.getInstance().getTestCache();
//        return passportMemManager.set(key, val, (int) expireSeconds);
        return false;
    }

    public boolean delete(String sessionKey) {
        String key = PREFIX_EXPIRED_PASSPORT_TICKET + sessionKey;
//        MemcachedManager passportMemManager = MemcachedManagerFactory.getManager(MemcachedManagerFactory.PASSPORT_POOL);
//        Cache<String, String> passportMemManager = RedisClientHome.getInstance().getTestCache();
//        boolean done = passportMemManager.remove(key);
//        return done;
        return true;
    }
}
