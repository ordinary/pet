package com.pet.fetch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pet.core.dao.CommentMongo;
import com.pet.core.dao.CommodityCategoryMongo;
import com.pet.core.dao.CommodityMongo;
import com.pet.core.domain.Comment;
import com.pet.core.domain.Commodity;
import com.pet.core.domain.CommodityCategory;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.TaobaokeItem;

@Component
public class FetchItemDealwith{

	@Autowired
	private CommodityCategoryMongo commodityCategoryMongo;

	@Autowired
	private CommodityMongo commodityMongo;
	
	@Autowired
	private CommentMongo commentMongo;

	

	public void execute(ItemCat itemCat) {
		if (itemCat != null) {
			CommodityCategory commodityCategory = new CommodityCategory();
			commodityCategory.setCid(itemCat.getCid());
			commodityCategory.setName(itemCat.getName());
			commodityCategory.setParent(itemCat.getIsParent());
			commodityCategory.setParentId(itemCat.getParentCid());
			commodityCategoryMongo.save(commodityCategory);
			if (!itemCat.getIsParent()) {
				List<TaobaokeItem> taobaokeItems = PetClient.getInstance()
						.getTaobaokeItems(itemCat.getCid());
				for (TaobaokeItem taobaokeItem : taobaokeItems) {
					Commodity commodity = new Commodity();
					commodity.setCid(itemCat.getCid());
					commodity.setClickUrl(taobaokeItem.getClickUrl());
					commodity.setCommission(taobaokeItem.getCommission());
					commodity.setCommissionNum(taobaokeItem.getCommissionNum());
					commodity.setCommissionRate(taobaokeItem
							.getCommissionRate());
					commodity.setCommissionVolume(taobaokeItem
							.getCommissionVolume());
					commodity.setCouponEndTime(taobaokeItem.getCouponEndTime());
					commodity.setCouponPrice(taobaokeItem.getCouponPrice());
					commodity.setCouponRate(taobaokeItem.getCouponRate());
					commodity.setCouponStartTime(taobaokeItem
							.getCouponStartTime());
					commodity.setItemLocation(taobaokeItem.getItemLocation());
					commodity.setKeywordClickUrl(taobaokeItem
							.getKeywordClickUrl());
					commodity.setNick(taobaokeItem.getNick());
					commodity.setNumIid(taobaokeItem.getNumIid());
					commodity.setPicUrl(taobaokeItem.getPicUrl());
					commodity.setPrice(taobaokeItem.getPrice());
					commodity.setSellerCreditScore(taobaokeItem
							.getSellerCreditScore());
					commodity.setSellerId(taobaokeItem.getSellerId());
					commodity.setShopClickUrl(taobaokeItem.getShopClickUrl());
					commodity.setShopType(taobaokeItem.getShopType());
					commodity.setTaobaokeCatClickUrl(taobaokeItem
							.getTaobaokeCatClickUrl());
					commodity.setTitle(taobaokeItem.getTitle());
					commodity.setVolume(taobaokeItem.getVolume());
					commodityMongo.save(commodity);
					List<Comment> comments =PetClient.getInstance().getComment(taobaokeItem.getNumIid(), 1);
					if (comments!=null) {
						for (Comment comment : comments) {
							commentMongo.save(comment);
						}
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
