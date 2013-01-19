package com.pet.passport.model;

/**
 * PassportTicket对象,包含这个ticket的对象的过期时间和
 * 
 * @author Terry haobo.cui
 * @date 2010-10-28
 */
public class PassportTicket {

    private String passportTicket;

    private int userId;
    
    public PassportTicket(){}
    
    public PassportTicket(int userId, String passportTicket){
    	this.userId = userId;
    	this.passportTicket = passportTicket;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private long expiresTime;

    public long getExpiresTime() {
        return expiresTime;
    }

    public String getPassportTicket() {
        return passportTicket;
    }

    public void setPassportTicket(String passportTicket) {
        this.passportTicket = passportTicket;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }
}

