package com.pet.passport.dao;

import java.sql.Date;
import java.sql.SQLException;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.passport.model.PassportTicketKey;

/**
 * 管理用于生成passport Ticket的加密密钥
 * 
 * @author Terry haobo.cui
 * 
 */
@DAO(catalog = "base")
public interface PassportTicketKeyDAO {

    /**
     * 生成当前的加密密钥
     * 
     * @param skKey
     * @param ssKey
     * @return
     * @throws SQLException
     */
    @SQL("insert ignore into passport_ticket_key(ticket_key,type,create_time) values(:1,:2,:3)")
    public boolean createEncryptionKey(String ticketKey, int type, Date date);

    /**
     * 得到用于加密pt的密钥
     * 
     * @param date
     * @return
     * @throws SQLException
     */
    @SQL("select ticket_key from passport_ticket_key where create_time=:1 and type=:2")
    public PassportTicketKey getEncryptionKey(Date date, int type);
}

