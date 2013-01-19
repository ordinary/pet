package com.pet.passport.model;

import java.sql.Date;

/**
 * 生成Ticket的加密密钥
 * 
 * @author Terry haobo.cui
 * @date 2010-10-28
 */
public class PassportTicketKey {

    private String ticketKey;// session_key 密钥

    private Date date;// 日期

    private int type;//type，未来使用不同的服务使用不一样的加密密钥

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTicketKey() {
        return ticketKey;
    }

    public void setTicketKey(String ticketKey) {
        this.ticketKey = ticketKey;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

