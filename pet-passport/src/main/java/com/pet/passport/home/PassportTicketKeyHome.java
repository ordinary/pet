package com.pet.passport.home;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.paoding.rose.scanning.context.RoseAppContext;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.pet.passport.dao.PassportTicketKeyDAO;
import com.pet.passport.model.PassportTicketKey;
import com.pet.passport.util.PassportTicketUtil;

/**
 * 管理用于生成ticket的加密密钥
 * 
 * @author haobo.cui
 * @date 2010-10-28
 */
@Component
public class PassportTicketKeyHome {

    @Autowired
    PassportTicketKeyDAO passportTicketDAO;

    private static PassportTicketKeyHome home;

    private static ApplicationContext context;

    @Autowired
    public void setApplicationContext(ApplicationContext ac) {
        context = ac;
    }

    public static PassportTicketKeyHome getInstance() {
        if (home == null) {
        	System.out.println( "======++++++++++||" + context==null );
        	
            home = (PassportTicketKeyHome) BeanFactoryUtils.beanOfType(context, PassportTicketKeyHome.class);
        }
        return home;
    }

    // 用于缓存
    private Map<String, PassportTicketKey> encryptionKeysMap = new ConcurrentHashMap<String, PassportTicketKey>(
            64);

    /**
     * 生成当前的加密密钥
     * 
     * @param ticketKey
     * @param type
     * @param date
     */
    private void createEncryptionKey(String ticketKey, int type, Date date) {
        try {
            passportTicketDAO.createEncryptionKey(ticketKey, type, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到用于加密密钥PTEncryptionKeys对象，没有的话创建一个
     * 
     * @param date
     * @return
     * @throws SQLException
     */
    public PassportTicketKey getAndCreateEncryptionKeys(Date date, int ticketType) {
        String ticketMapKey = date.toString() + ticketType;
        PassportTicketKey ptEnKeys = encryptionKeysMap.get(date);
        if (ptEnKeys == null) {
            synchronized (PassportTicketKeyHome.class) {//加锁避免同时并发查询DB
                ptEnKeys = encryptionKeysMap.get(ticketMapKey);
                if (ptEnKeys == null) {
                	ptEnKeys = passportTicketDAO.getEncryptionKey(date, ticketType);
                	if( ptEnKeys != null ){
                		encryptionKeysMap.put(ticketMapKey, ptEnKeys);
                	}
                    try {
                        System.out
                                .println("Passport Ticket[Generation]: Try to get the encryption keys for date "
                                        + date);
                        String ptToken = PassportTicketUtil.getUUID();// 得到一个加密的密钥
                        createEncryptionKey(ptToken, ticketType, date);
                        ptEnKeys = passportTicketDAO.getEncryptionKey(date, ticketType);// 避免同时竞争创建的情况
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    encryptionKeysMap.put(ticketMapKey, ptEnKeys);
                }

            }

        }

        return encryptionKeysMap.get(ticketMapKey);
    }

    /**
     * 得到用于加密密钥SkEncryptionKeys对象，没有的话返回nul
     * 
     * @param date
     * @return
     * @throws SQLException
     */
    public PassportTicketKey getEncryptionKeys(final Date date, int ticketType) {
        String ticketMapKey = date.toString() + ticketType;
        PassportTicketKey ptEnKeys = encryptionKeysMap.get(ticketMapKey);
        if (ptEnKeys == null) {
            try {
                System.out
                        .println("Passport Ticket [Validation]: Try to get the encryption keys for date "
                                + date);
                ptEnKeys = passportTicketDAO.getEncryptionKey(date, ticketType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            encryptionKeysMap.put(ticketMapKey, ptEnKeys);
        }
        return encryptionKeysMap.get(ticketMapKey);
    }

    /**
     * 进行本地缓存复制，加入新的token
     * 
     * @param date
     * @param ptEnKeys
     */
    public static void main(String args[]) {
        RoseAppContext rst = new RoseAppContext();
        Date date = PassportTicketUtil.getDateIgnoreHMS( (System.currentTimeMillis() - 1) ); // 减1秒是为了在每天0点取前一天的加密key
        PassportTicketKey ptk = PassportTicketKeyHome.getInstance().getAndCreateEncryptionKeys( date, 0 );
        System.out.println(ptk.getTicketKey());
    }
}
