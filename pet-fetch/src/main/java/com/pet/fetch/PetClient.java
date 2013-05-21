package com.pet.fetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pet.core.domain.Comment;
import com.taobao.api.ApiException;
import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.ItemProp;
import com.taobao.api.domain.PropValue;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.request.ItempropsGetRequest;
import com.taobao.api.request.TaobaokeItemsDetailGetRequest;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.ItempropsGetResponse;
import com.taobao.api.response.TaobaokeItemsDetailGetResponse;
import com.taobao.api.response.TaobaokeItemsGetResponse;

public class PetClient {

	private static final String serverUrl = "http://gw.api.taobao.com/router/rest";

	private static final String appKey = "21501927";

	private static final String appSecret = "4b7d7dcfc1c216375ac48a97e706c1e1";

	private static final String NICK = "tb46969797";

	private static final String COMMENT_URL = "a.m.taobao.com";

	private static final String COMMENT_PATH = "/ajax/rate_list.do";

	private static TaobaoClient client = new DefaultTaobaoClient(
			serverUrl.trim(), appKey.trim(), appSecret.trim(), Constants.FORMAT_JSON);

	private static PetClient petClient = new PetClient();

	private static Object lock = new Object();

	private PetClient() {

	}

	public List<ItemCat> getItemCats(String cid, long parentCid) {

		List<ItemCat> itemCats = null;
		try {
			ItemcatsGetRequest req = new ItemcatsGetRequest();
			req.setFields("cid,parent_cid,name,is_parent");
			cid = cid.trim();
			if (cid != null && !cid.equals("")) {
				if (cid.trim().endsWith(",")) {
					req.setCids(cid.substring(0, cid.lastIndexOf(",") - 1));
				} else {
					req.setCids(cid);
				}

			}
			if (parentCid != 0) {
				req.setParentCid(parentCid);
			}

			ItemcatsGetResponse response = client.execute(req);
			itemCats = response.getItemCats();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return itemCats;
	}

	public static PetClient getInstance() {
		synchronized (lock) {
			if (petClient == null) {
				petClient = new PetClient();
			}
		}
		return petClient;
	}

	public List<TaobaokeItemDetail> getTaobaokeItemDetails(String numIid)
			throws ApiException {
		List<TaobaokeItemDetail> taobaokeItemDetails = null;
		TaobaokeItemsDetailGetRequest req = new TaobaokeItemsDetailGetRequest();
		req.setFields("click_url,shop_click_url,seller_credit_score,num_iid,title,nick");
		req.setNick(NICK);
		req.setNumIids(numIid);
		TaobaokeItemsDetailGetResponse response = client.execute(req);
		taobaokeItemDetails = response.getTaobaokeItemDetails();
		return taobaokeItemDetails;
	}

	public List<ItemProp> getItemProps(long cid) {
		ItempropsGetRequest request = new ItempropsGetRequest();
		List<ItemProp> itemProps = null;
		try {
			request.setCid(cid);
			ItempropsGetResponse response = client.execute(request);
			itemProps = response.getItemProps();
			for (ItemProp prop : itemProps) {
				List<PropValue> propValues = prop.getPropValues();
				if (propValues != null) {
					for (PropValue value : prop.getPropValues()) {
						System.out.println(prop.getName() + ":"
								+ value.getName());
					}
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return itemProps;
	}

	public List<Comment> getComment(long itemId, int page) {
		JSONObject json = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(COMMENT_URL, 80, "http");
			// client.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
			// 10000);
			HttpMethod method = new GetMethod(COMMENT_PATH);
			NameValuePair itemPair = new NameValuePair("item_id", itemId + "");
			NameValuePair ratePair = new NameValuePair("rateRs", page + "");
			NameValuePair psPair = new NameValuePair("ps", 100 + "");
			method.setQueryString(new NameValuePair[] { itemPair, ratePair,
					psPair });
			client.executeMethod(method);
			// 打印服务器返回的状态
			int methodstatus = method.getStatusCode();
			StringBuffer sb = new StringBuffer();
			if (methodstatus == 200) {
				try {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(
									method.getResponseBodyAsStream(), "UTF-8"));
					String line;
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
					json = JSON.parseObject(sb.toString().toString());

					rd.close();
				} catch (IOException e) {
					throw new RuntimeException("error", e);
				}
			}
			method.releaseConnection();

			if (json != null) {
				JSONArray array = json.getJSONArray("items");
				if (array != null) {
					for (Object object : array) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd");
							JSONObject objectVal = (JSONObject) object;

							Comment comment = new Comment();
							comment.setCommodityId(itemId);
							comment.setAnnoy(objectVal.getIntValue("annoy"));
							comment.setBuyerName(objectVal.getString("buyer"));
							comment.setCredit(objectVal.getIntValue("credit"));
							comment.setDate(sdf.parse(objectVal
									.getString("date")));
							comment.setDeal(objectVal.getString("deal"));
							comment.setRateId(objectVal.getLongValue("rateId"));
							comment.setText(objectVal.getString("text"));
							comment.setType(objectVal.getIntValue("type"));
							comments.add(comment);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;

	}

	public static void main(String[] args) {
		for (TaobaokeItem taobaokeItem : PetClient.getInstance()
				.getTaobaokeItems(50006121)) {
			System.out.println(taobaokeItem.getClickUrl());
			System.out.println(taobaokeItem.getPicUrl());
			PetClient.getInstance().getComment(taobaokeItem.getNumIid(), 1);
		}
		;

	}

	public List<TaobaokeItem> getTaobaokeItems(long cid) {
		List<TaobaokeItem> taobaokeItems = null;
		try {
			TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
			req.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");
			req.setNick(NICK);
			req.setCid(cid);
			TaobaokeItemsGetResponse response = client.execute(req);
			taobaokeItems = response.getTaobaokeItems();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return taobaokeItems;
	}

}
