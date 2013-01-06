package com.pet.core.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.core.domain.CommodityCategory;

@DAO(catalog="pet")
public interface CommodityCategoryDAO {

	String INSERT_SQL = "cid ,name,is_parent  ,parent_id ";
	
	String SELECT_SQL = "id ,"+INSERT_SQL;

	String TABLE = " commodity_category ";
	
	
	
	@SQL("insert into " + TABLE +"(" +INSERT_SQL +" ) values (:1.cid,:1.name,:1.isParent" +
    		",:1.parentId")
	public int save(CommodityCategory commodityCategory);
	

    @SQL("select " +SELECT_SQL + " from " +TABLE +"where cid =:1" )
	public CommodityCategory queryByCid(long cid);
    
    
    
    

}

