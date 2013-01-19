package com.pet.passport.model;

public class PassportTicketInfo {

    private int ticketType;//类型

    private String ticketSig;//签名

    private long ticketLife;//生命周期

    private long ticketExpires;//过期时间

    private int uid;//用户id

    private String userAgent;

    private int appId;//AppId

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getTicketType() {
        return ticketType;
    }

    public void setTicketType(int ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketSig() {
        return ticketSig;
    }

    public void setTicketSig(String ticketSig) {
        this.ticketSig = ticketSig;
    }

    public long getTicketLife() {
        return ticketLife;
    }

    public void setTicketLife(long ticketLife) {
        this.ticketLife = ticketLife;
    }

    public long getTicketExpires() {
        return ticketExpires;
    }

    public void setTicketExpires(long ticketExpires) {
        this.ticketExpires = ticketExpires;
    }

    private String ptEncryptionKey;//加密密钥

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPtEncryptionKey() {
        return ptEncryptionKey;
    }

    public void setPtEncryptionKey(String ptEncryptionKey) {
        this.ptEncryptionKey = ptEncryptionKey;
    }
}

