package com.pet.core.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.pet.core.domain.Commodity;

@DAO
public interface CommodityDAO {

	String INSERT_SQL = "cid ,commission ,commission_num ," +
			" commission_rate,commission_volume ,coupon_end_time ,coupon_price ,coupon_rate ," +
			"coupon_start_time ,item_location ,keyword_click_url,nick ,num_iid,pic_url ," +
			"price ,seller_credit_score ,seller_id ,shop_click_url ,shop_type ,taobaoke_cat_click_url ," +
			"title ,volume  ";
	
	String SELECT_SQL = "id ,"+INSERT_SQL;

	String TABLE = " commodity_category ";
	
	@SQL("insert into " + TABLE +"(" +INSERT_SQL +" ) values (:1.cid,:1.clickUrl,:1.commission" +
    		",:1.commissionNum,:1.commissionRate,:1.commissionVolume,:1.couponEndTime,:1.couponPrice" +
    		",:1.couponRate,:1.couponStartTime,:1.itemLocation,:1.keywordClickUrl,:1.nick" +
    		",:1.numIid,:1.picUrl,:1.price,:1.sellerCreditScore,:1.sellerId" +
    		",:1.shopClickUrl,:1.shopType,:1.taobaokeCatClickUrl,:1.title,:1.volume")
	public int save(Commodity commodity);
	
	   
    @SQL("select " +SELECT_SQL + " from " +TABLE +"where cid =:1 order by  seller_credit_score limit :2 ,:3  " )
    public List<Commodity> queryByCid( int cid,int startId,int count);
    
    @SQL("select " +SELECT_SQL + " from " +TABLE +"where num_iid =:1" )
    public Commodity queryByNumIid( long numIid);

    @SQL("select " +SELECT_SQL + " from " +TABLE +"where num_iid >:1 order by num_iid  limit :2" )
    public List<Commodity> queryCommodityByNumIidOrderbyNumIid( int startNumIid,int count);
	
	

	    
}
