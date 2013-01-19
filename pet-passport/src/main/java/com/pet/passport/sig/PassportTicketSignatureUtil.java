package com.pet.passport.sig;

import com.pet.passport.model.PassportTicketInfo;
import com.pet.passport.util.PassportTicketUtil;

/**
 * 生成特殊tickets的signature
 * 
 * @author Terry haobo.cui
 * @date 2010-10-28
 */
public class PassportTicketSignatureUtil {

    /**
     * @param PassportTicketInfo 保存Passport Ticket相关信息的结构 已经过期
     * @return 根据私有的置换（permutation）规则生成Passport Ticket的签名部分
     * 
     */
    private static int[] permutation = { 1, 8, 27, 14, 15, 29, 25, 28, 21, 3, 5, 4, 11, 10, 31, 18,
            2, 7, 24, 17, 26, 30, 13, 19, 20, 9, 22, 23, 0, 12, 6, 16 };

    /**
     * 
     * @param PassportTicketInfo
     *        保存PassportTicketInfo相关信息的结构,主要将原有的secretKey改为了appId
     * @return 根据私有的置换（permutation）规则生成PassportTicket的签名部分
     */
    public static String sigPassportTicket(PassportTicketInfo ticketInfo) {
        StringBuffer sb = new StringBuffer();
        sb.append(ticketInfo.getTicketType());// 类型
        sb.append(ticketInfo.getTicketLife());// 有效期
        sb.append(ticketInfo.getTicketExpires());// 过期时间
        sb.append(ticketInfo.getUid());// 用户ID
        sb.append(ticketInfo.getPtEncryptionKey());// KEY
        sb.append(ticketInfo.getUserAgent());// KEY
        //如果AppId不等于0的话设置为加密串
        if (ticketInfo.getAppId() != 0) {
            sb.append(ticketInfo.getAppId());// AppId   
        }
        return sig(sb.toString(), PassportTicketSignatureUtil.permutation);
    }

    private static String sig(String strToSig, int[] permutation) {
        String md5str = PassportTicketUtil.digestMD5(strToSig);
        //        char[] signature = new char[32];
        //        for (int i = 0; i < permutation.length; i++) {
        //            signature[i] = md5str.charAt(permutation[i]);
        //        }
        return md5str;
    }

    public static void main(String[] args) {
        String str = "fdsfjdslf";
        String md5str = PassportTicketUtil.digestMD5(str);
        System.out.println(md5str);
        String sig = sig(str, permutation);
        System.out.println(sig);
    }

}

