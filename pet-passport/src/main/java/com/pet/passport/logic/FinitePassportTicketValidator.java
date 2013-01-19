package com.pet.passport.logic;

import java.sql.Date;

import com.pet.passport.home.ExpiredFinitePTHome;
import com.pet.passport.home.PassportTicketKeyHome;
import com.pet.passport.mgr.AccessTokenManager;
import com.pet.passport.model.PassportTicketInfo;
import com.pet.passport.model.PassportTicketKey;
import com.pet.passport.model.TicketValidationResult;
import com.pet.passport.sig.PassportTicketSignatureUtil;
import com.pet.passport.util.PassportTicketUtil;

/**
 * 验证ticket有效性的验证器
 * 
 * @author Terry haobo.cui
 *@date 2010-10-28
 */
public class FinitePassportTicketValidator {

    private static FinitePassportTicketValidator instance = new FinitePassportTicketValidator();

    public static FinitePassportTicketValidator getInstance() {
        return instance;
    }

    private FinitePassportTicketValidator() {
    }

//    private static final Logger logger = Logger.getLogger("pt_ticket_log");

    /**
     * 这里验证ticket是否有效，如果小于一个小时重新生成最新的
     * 验证顺序先判断是否ticket过期，是否在memcache中存在，签名是否有效
     * 
     * @param skInfo
     * @return 验证结果码
     */
    public TicketValidationResult validatePassportTicket(TicketValidationResult validationResult,
            PassportTicketInfo ticketInfo) {
        if (ticketInfo == null) {
            // 这个地方加入判断,会出现pt格式错误,无法解析pt的情况
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
            return validationResult;
        }
        validationResult.setUserId(ticketInfo.getUid());//加入uid

        // 检查过期时间
        long curTime = System.currentTimeMillis() / 1000L;
//        if (logger.isDebugEnabled()) {
//            logger.debug("FinitePassportTicketValidator: curTime = " + curTime + ", expires = "
//                    + ticketInfo.getTicketExpires());
//        }
        if (ticketInfo.getTicketExpires() < curTime) {
            //过期的情况
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_EXPIRED);
            return validationResult;
        }
        //获取到当前加密的token
        PassportTicketKey encryptionKeys = getValidateKeys(ticketInfo.getTicketLife(), ticketInfo
                .getTicketExpires(), ticketInfo.getTicketType());

        if (encryptionKeys == null) {
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
            return validationResult;
        }
//        if (logger.isDebugEnabled()) {
//            logger.debug("FinitePassportTicketValidator: get PTEncryption key = "
//                    + encryptionKeys.getTicketKey());
//        }
        //FIXME:
        //platform do not judge expire or not
        if( ticketInfo.getTicketType() != AccessTokenManager.TICKET4APP ){
	        int val = ExpiredFinitePTHome.getInstance().get(validationResult.getPassportTicket());
	        //验证是否在memcached中过期
	        if (val > 0) {
	            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_EXPIRED);
	            return validationResult;
	        }
        }
        /*
         * 下面开始验证是否签名合法
         */
        String ticketSigToValidate = ticketInfo.getTicketSig();//原始signature token验证合法性
        ticketInfo.setPtEncryptionKey(encryptionKeys.getTicketKey());//加密密钥
        String expectedPTSig = PassportTicketSignatureUtil.sigPassportTicket(ticketInfo);
//        if (logger.isDebugEnabled()) {
//            logger.debug("FiniteSessionKeyValidator: SK sig to validate = " + ticketSigToValidate);
//            logger.debug("FiniteSessionKeyValidator: expected SK sig    = " + expectedPTSig);
//        }
        if (expectedPTSig.equals(ticketSigToValidate)) {

            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_OK);

        } else {
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
        }
        return validationResult;
    }
    
    /**
     * 验证token是否有效
     * @param validationResult
     * @param ticketInfo token的信息
     * @param tokenType  token的类型
     * @return
     */
    public TicketValidationResult validateToken(TicketValidationResult validationResult,
            PassportTicketInfo ticketInfo,int tokenType) {
        if (ticketInfo == null) {
            // 这个地方加入判断,会出现pt格式错误,无法解析pt的情况
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
            return validationResult;
        }
        validationResult.setUserId(ticketInfo.getUid());//加入uid
        //获取到当前加密的token
        PassportTicketKey encryptionKeys = getValidateKeys(ticketInfo.getTicketLife(), ticketInfo
                .getTicketExpires(), ticketInfo.getTicketType());

        if (encryptionKeys == null) {
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
            return validationResult;
        }
        //FIXME:
        //platform do not judge expire or not
        if( ticketInfo.getTicketType() != tokenType ){
	      validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
	            return validationResult;
        }
        /*
         * 下面开始验证是否签名合法
         */
        String ticketSigToValidate = ticketInfo.getTicketSig();//原始signature token验证合法性
        ticketInfo.setPtEncryptionKey(encryptionKeys.getTicketKey());//加密密钥
        String expectedPTSig = PassportTicketSignatureUtil.sigPassportTicket(ticketInfo);
        if (expectedPTSig.equals(ticketSigToValidate)) {

            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_OK);

        } else {
            validationResult.setValidationCode(TicketValidationResult.PT_VALIDATION_INVALID);
        }
        return validationResult;
    }
    
    

    /**
     * 获取到当前的加密token
     * 
     * @param ticketLife
     * @param ticketExpires
     * @return
     */
    private PassportTicketKey getValidateKeys(long ticketLife, long ticketExpires, int ticketType) {
        long generatedTime = ticketExpires - ticketLife;
        // 计算得到生成Session Key的那一天
        Date generatedDate = PassportTicketUtil.getDateIgnoreHMS((generatedTime - 1) * 1000L); //减1秒是为了在每天0点取前一天的加密key

        PassportTicketKey encryptionKeys = PassportTicketKeyHome.getInstance().getEncryptionKeys(
                generatedDate, ticketType);
        return encryptionKeys;
    }

}

