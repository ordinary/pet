package com.pet.passport.logic;

import com.pet.passport.model.PassportTicket;
import com.pet.passport.model.PassportTicketInfo;
import com.pet.passport.sig.PassportTicketSignatureUtil;
import com.pet.passport.util.PassportTicketUtil;

/**
 * 生成passportTicket的工具类，包含生成非持久的passportTicket得到过期时间等
 * 
 * @author haobo.cui
 * @date 2010-10-28
 */
public class FinitePassportTicketGenerator {

    private static FinitePassportTicketGenerator instance = new FinitePassportTicketGenerator();

    public static FinitePassportTicketGenerator getInstance() {
        return instance;
    }

    private FinitePassportTicketGenerator() {
    }

    /**
     * 得到非持久化的ticket，加密串的排列顺序为类型(Type)+生命周期(Life)+过期时间(Expires)
     * +用户(uid)+当天的密钥(token)
     * 
     * @param ticketInfo
     * @return
     */
    public PassportTicket generateFiniteSessionKey(PassportTicketInfo ticketInfo) {
        PassportTicket pt = new PassportTicket();
        String sigTicket = PassportTicketSignatureUtil.sigPassportTicket(ticketInfo);// 计算出sig
        pt.setExpiresTime(ticketInfo.getTicketExpires());
        pt.setPassportTicket(PassportTicketUtil.getPassportTicket(ticketInfo, sigTicket));
        pt.setUserId(ticketInfo.getUid());
        return pt;// 返回正确格式1.fdewr2f1lsafl.3600.1237194000-66271
    }

}
