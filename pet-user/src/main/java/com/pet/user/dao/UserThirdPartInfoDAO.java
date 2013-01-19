package com.pet.user.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.user.domain.UserThirdPartInfo;

@DAO(catalog = "pet")
public interface UserThirdPartInfoDAO {
	String TABELE_NAME = "user_third_part_info";
	String INSERT_VIEW = " user_id, refresh_token, access_token, token_secret, guid, type, status, update_time, is_verified, third_part_name, expired_in";
	String SELECT_VIEW = " id, " + INSERT_VIEW;

	@SQL("select " + SELECT_VIEW + " from " + TABELE_NAME + " where id=:1")
	public UserThirdPartInfo get(long id);

	@SQL("select " + SELECT_VIEW + " from " + TABELE_NAME
			+ " where user_id=:1 and type=:2")
	public UserThirdPartInfo get(long userId, int type);

	@SQL("select " + SELECT_VIEW + " from " + TABELE_NAME + " where user_id=:1")
	public List<UserThirdPartInfo> getList(long userId);

	@SQL("select " + SELECT_VIEW + " from " + TABELE_NAME
			+ " where guid=:1 and type=:2")
	public UserThirdPartInfo get(String guid, int type);

	@SQL("insert into "
			+ TABELE_NAME
			+ "("
			+ INSERT_VIEW
			+ ") values(:1.userId, :1.refreshToken, :1.accessToken, :1.tokenSecret,:1.guid,:1.type, :1.status, now(),:1.isVerified, :1.thirdPartName,:1.expiredIn)")
	@ReturnGeneratedKeys
	public int insert(UserThirdPartInfo userThirdPartInfo);

	@SQL("select " + SELECT_VIEW + " from " + TABELE_NAME
			+ " where guid in (#(:1)) and type=:2")
	public List<Integer> getUserIdList(List<String> guids, int type);

	@SQL("update " + TABELE_NAME + " set status=:3 where id=:1 and type=:2")
	public int updateStatus(int id, int type, int status);

	@SQL("update "
			+ TABELE_NAME
			+ " set access_token=:1.accessToken, token_secret=:1.tokenSecret, refresh_token=:1.refreshToken, status=:1.status, update_time=now(), third_part_name=:1.thirdPartName, is_verified=:1.isVerified, expired_in=:1.expiredIn where user_id=:1.userId and type=:1.type")
	public int update(UserThirdPartInfo userThirdPartInfo);

	@SQL("delete from " + TABELE_NAME + " where id=:1 ")
	public int delete(long id);

	@SQL("delete from " + TABELE_NAME + " where user_id=:1 and type=:2")
	public int delete(int userId, int type);

}
