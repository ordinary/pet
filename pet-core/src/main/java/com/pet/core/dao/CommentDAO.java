package com.pet.core.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.core.domain.Comment;
@DAO(catalog="pet")
public interface CommentDAO {
	
	
	String INSERT_SQL = "commodity_id ,annoy,buyer_name  ,credit,`date`, deal,rate_id,text,`type`";
	
	String SELECT_SQL = "id ,"+INSERT_SQL;

	String TABLE = " comment ";
	
	@SQL("insert into " + TABLE +"(" +INSERT_SQL +" ) values (:1.commodityId,:1.annoy,:1.buyerName" +
    		",:1.credit,:1.date,:1.deal,:1.rateId,:1.text,:1.type")
	public int save(Comment comment);
	
    @SQL("select " +SELECT_SQL + " from " +TABLE +"where commodity_id =:1" )
	public List<Comment> queryByCommodityId(long commodityId);
    
    @SQL("select " +SELECT_SQL + " from " +TABLE +"where rate_id =:1" )
 	public Comment queryByRateId(long rateId);
	
	
	

}
