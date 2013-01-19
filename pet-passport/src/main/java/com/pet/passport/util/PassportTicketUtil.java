package com.pet.passport.util;

import java.security.MessageDigest;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.pet.passport.model.PassportTicketInfo;

/**
 * 维护passportTicket机制的工具类，包括拼接ticket、取得过期时间、反解ticket串
 * 
 * @author Terry haobo.cui
 * 
 */
public class PassportTicketUtil {

    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    private static final Logger logger = Logger.getLogger("sk_ss_log");

    /**
     * @return 取当前Date对象，不受时分秒的影响
     */
    public static Date getCurdate() {
        Calendar c = Calendar.getInstance();
        c = ignoreHMS(c);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 
     * 按传入的time参数构造Date对象，把时分秒全部置0
     * 
     * @param time 表示时间的long
     * @return
     */
    public static Date getDateIgnoreHMS(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c = ignoreHMS(c);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 将传入Calendar对象的时分秒置0
     * 
     * @param c
     * @return
     */
    private static Calendar ignoreHMS(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    /**
     * 返回对外提供的passportTicket格式
     * 
     * @param ticketInfo
     * @param sigSessionKey
     * @return
     */
    public static String getPassportTicket(PassportTicketInfo ticketInfo, String sigSessionKey) {
        StringBuffer sb = new StringBuffer();
        sb.append(ticketInfo.getTicketType());
        sb.append(".");
        sb.append(sigSessionKey);
        sb.append(".");
        sb.append(ticketInfo.getTicketLife());
        sb.append(".");
        sb.append(ticketInfo.getTicketExpires());
        sb.append("-");
        sb.append(ticketInfo.getUid());
        return sb.toString();
    }

    /**
     * 得到passport Ticket的过期时间
     * 
     * @param lifeTime
     * @return
     */
    public static long getExpiresTime(int skType, long lifeTime, long currentTime) {
        // 取得取整以后的过期时间,例如当前时间为1237189468，实际整取过期时间为1237194000
        return ((currentTime + 3599) / 3600) * 3600 + lifeTime;

    }

    /**
     * 解析passportTicket，返回一个PassportTicketInfo对象；解析失败返回null
     * 
     * @param passportTicket
     * @return
     */
    public static PassportTicketInfo parseFiniteSessionKey(String passportTicket) {

        // ticket的格式为 <type>.<32byte-sig>.<life>.<expires-time>-<uid>
        String[] fields = passportTicket.trim().split("\\.");
        if (fields.length != 4) {
            return null;
        }
        int ticketType = Integer.parseInt(fields[0]);//类型
        String ticketSig = fields[1];
        long ticketLife = Long.parseLong(fields[2]);

        String[] subFields = fields[3].split("-");
        if (subFields.length != 2) {
            return null;
        }
        long ticketExpires = -1;
        int uid = -1;
        try {
            ticketExpires = Long.parseLong(subFields[0]);
            uid = Integer.parseInt(subFields[1]);
        } catch (Exception e) {
            // DO nothing
        }
        if (ticketExpires < 0 || uid < 0) {
            return null;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("PassportTicketUtil: parsed info from session key: <Type:" + ticketType
                    + "><Sig:" + ticketSig + "><Life:" + ticketLife + "><Expires:" + ticketExpires
                    + "><uid:" + uid + ">");
        }
        PassportTicketInfo ticketInfo = new PassportTicketInfo();
        ticketInfo.setTicketType(ticketType);
        ticketInfo.setTicketSig(ticketSig);
        ticketInfo.setTicketLife(ticketLife);
        ticketInfo.setTicketExpires(ticketExpires);
        ticketInfo.setUid(uid);
        return ticketInfo;
    }

    /**
     * 得到一个uuid的串，生成加密token等
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取计算MD5的值
     * 
     * @param baseToken
     * @return
     */
    public static final String digestMD5(String baseToken) {

        try {
            byte[] strTemp = baseToken.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
      
    }

}

