package com.pet.user.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.user.domain.User;

@DAO(catalog = "pet")
public interface UserDAO {	
	String TABELE_NAME = "user";
	String INSERT_VIEW = "user_name, password, email, birthday, birthday, header_pic , city, address, backgroud_pic, create_time,modify_time";
	String SELECT_VIEW = " id, " + INSERT_VIEW;
	
	@SQL("insert into "
			+ TABELE_NAME
			+ "("
			+ INSERT_VIEW
			+ ") values(:1.userName, :1.password, :1.email, :1.birthday,:1.headerPic,:1.city, :1.address,:1.backgroudPic, now(),now())")
	@ReturnGeneratedKeys
	public int insert(User user);
	
	

}
