package com.pet.core.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.core.domain.HotCommodity;

@DAO(catalog = "pet")
public interface HotCommodityDAO {

	String INSERT_SQL = "type ,order,click_url ,nick , num_iid,pic_url ,price ,title  ";

	String SELECT_SQL = "id ," + INSERT_SQL;

	String TABLE = " hot_commodity ";

	@SQL("insert into " + TABLE + "(" + INSERT_SQL
			+ " ) values (:1.type,:1.order,:1.clickUrl"
			+ ",:1.nick,:1.numIid,:1.picUrl,:1.price,:1.title)")
	public int save(HotCommodity hotCommodity);

	@SQL("delete  from " + TABLE + "where  type=:1 and order=:2")
	public boolean delete(int type ,int order);

	@SQL("select " + SELECT_SQL + " from " + TABLE
			+ "  where  #if((:1!=0)){type=:1} order by type ,order desc")
	public List<HotCommodity> queryByType(int type);

}
