package com.pet.passport.model;

/**
 * Passport Ticket的验证结果，同时包含新的ticket结果
 * 
 * @author Terry haobo.cui
 * @date 2010-10-28
 * 
 */
public class TicketValidationResult {

    /**
     *Passport Ticket验证通过
     */
    public static final int PT_VALIDATION_OK = 1;

    /**
     * 无效的Passport Ticket
     */
    public static final int PT_VALIDATION_INVALID = 2;

    /**
     * 过期的Passport Ticket
     */
    public static final int PT_VALIDATION_EXPIRED = 3;

    /**
     * validationCode: passport Tickets验证结果码，可选值有： PT_VALIDATION_OK,
     * PT_VALIDATION_INVALID, PT_VALIDATION_EXPIRED
     */
    private int validationCode;

    private String passportTicket;

    public String getPassportTicket() {
        return passportTicket;
    }

    public void setPassportTicket(String passportTicket) {
        this.passportTicket = passportTicket;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(int validationCode) {
        this.validationCode = validationCode;
    }
}
