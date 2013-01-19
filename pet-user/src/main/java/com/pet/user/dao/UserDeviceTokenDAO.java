package com.pet.user.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.user.domain.UserDeviceToken;

@DAO(catalog = "pet")
public interface UserDeviceTokenDAO {
	
	String TABELE_NAME = "user_device_token";
	String INSERT_VIEW = "user_id, device_token, device_type, device_version, create_time, modify_time";
	String SELECT_VIEW = " id, " + INSERT_VIEW;
	
	
	@SQL("insert into "
			+ TABELE_NAME
			+ "("
			+ INSERT_VIEW
			+ ") values(:1.userId, :1.deviceToken, :1.deviceType, :1.deviceVersion, now(),now())")
	@ReturnGeneratedKeys
	public int insert(UserDeviceToken userDeviceTokener);

}
