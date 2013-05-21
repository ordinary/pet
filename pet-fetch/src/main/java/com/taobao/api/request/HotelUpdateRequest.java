package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.FileItem;
import com.taobao.api.TaobaoUploadRequest;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.response.HotelUpdateResponse;
import com.taobao.api.ApiRuleException;
/**
 * TOP API: taobao.hotel.update request
 * 
 * @author auto create
 * @since 1.0, 2013-05-11 16:48:24
 */
public class HotelUpdateRequest implements TaobaoUploadRequest<HotelUpdateResponse> {

	private TaobaoHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

	/** 
	* 酒店地址。长度不能超过120
	 */
	private String address;

	/** 
	* 城市编码。参见：http://kezhan.trip.taobao.com/area.html，domestic为false时，输入对应国家的海外城市编码，可调用海外城市查询接口获取
	 */
	private Long city;

	/** 
	* domestic为true时，固定China；
domestic为false时，传海外国家编码。参见：http://kezhan.trip.taobao.com/countrys.html
	 */
	private String country;

	/** 
	* 装修时间。长度不能超过4。
	 */
	private String decorateTime;

	/** 
	* 酒店介绍。不超过25000个汉字
	 */
	private String desc;

	/** 
	* 区域（县级市）编码。参见：http://kezhan.trip.taobao.com/area.html
	 */
	private Long district;

	/** 
	* 是否国内酒店。可选值：true，false
	 */
	private Boolean domestic;

	/** 
	* 酒店id。必须为数字。
	 */
	private Long hid;

	/** 
	* 酒店级别。可选值：A,B,C,D,E,F。代表客栈公寓、经济连锁、二星级/以下、三星级/舒适、四星级/高档、五星级/豪华
	 */
	private String level;

	/** 
	* 酒店名称。不能超过60字节
	 */
	private String name;

	/** 
	* 开业时间。长度不能超过4。
	 */
	private String openingTime;

	/** 
	* 酒店定位。可选值：T,B。代表旅游度假、商务出行
	 */
	private String orientation;

	/** 
	* 酒店图片。类型:JPG,GIF;最大长度:500K。支持的文件类型：gif,jpg,jpeg,png。
图片没有传，则代表不更新图片，使用原来的图片
	 */
	private FileItem pic;

	/** 
	* 省份编码。参见：http://kezhan.trip.taobao.com/area.html，domestic为false时默认为0
	 */
	private Long province;

	/** 
	* 房间数。长度不能超过4。
	 */
	private Long rooms;

	/** 
	* 交通距离与设施服务。JSON格式。cityCenterDistance、railwayDistance、airportDistance分别代表距离市中心、距离火车站、距离机场公里数，为不超过3位正整数，默认-1代表无数据。
其他key值true代表有此服务，false代表没有。
parking：停车场，airportShuttle：机场接送，rentCar：租车，meetingRoom：会议室，businessCenter：商务中心，swimmingPool：游泳池，fitnessClub：健身中心，laundry：洗衣服务，morningCall：叫早服务，bankCard：接受银行卡，creditCard：接受信用卡，chineseRestaurant：中餐厅，westernRestaurant：西餐厅，cafe：咖啡厅，bar：酒吧，ktv：KTV。
如：
{"airportShuttle":true,"parking":false,"fitnessClub":false,"chineseRestaurant":false,"rentCar":false,"laundry":false,"bankCard":false,"cityCenterDistance":-1,"creditCard":false,"westernRestaurant":false,"ktv":false,"railwayDistance":-1,"swimmingPool":false,"cafe":false,"businessCenter":false,"morningCall":false,"bar":false,"meetingRoom":false,"airportDistance":-1}
	 */
	private String service;

	/** 
	* 楼层数。长度不能超过4。
	 */
	private Long storeys;

	/** 
	* 酒店电话。格式：国家代码（最长6位）#区号（最长4位）#电话（最长20位）。国家代码提示：中国大陆0086、香港00852、澳门00853、台湾00886
	 */
	private String tel;

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public void setCity(Long city) {
		this.city = city;
	}
	public Long getCity() {
		return this.city;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry() {
		return this.country;
	}

	public void setDecorateTime(String decorateTime) {
		this.decorateTime = decorateTime;
	}
	public String getDecorateTime() {
		return this.decorateTime;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}
	public Long getDistrict() {
		return this.district;
	}

	public void setDomestic(Boolean domestic) {
		this.domestic = domestic;
	}
	public Boolean getDomestic() {
		return this.domestic;
	}

	public void setHid(Long hid) {
		this.hid = hid;
	}
	public Long getHid() {
		return this.hid;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return this.level;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getOpeningTime() {
		return this.openingTime;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getOrientation() {
		return this.orientation;
	}

	public void setPic(FileItem pic) {
		this.pic = pic;
	}
	public FileItem getPic() {
		return this.pic;
	}

	public void setProvince(Long province) {
		this.province = province;
	}
	public Long getProvince() {
		return this.province;
	}

	public void setRooms(Long rooms) {
		this.rooms = rooms;
	}
	public Long getRooms() {
		return this.rooms;
	}

	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return this.service;
	}

	public void setStoreys(Long storeys) {
		this.storeys = storeys;
	}
	public Long getStoreys() {
		return this.storeys;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return this.tel;
	}
	private Map<String,String> headerMap=new TaobaoHashMap();
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return "taobao.hotel.update";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("address", this.address);
		txtParams.put("city", this.city);
		txtParams.put("country", this.country);
		txtParams.put("decorate_time", this.decorateTime);
		txtParams.put("desc", this.desc);
		txtParams.put("district", this.district);
		txtParams.put("domestic", this.domestic);
		txtParams.put("hid", this.hid);
		txtParams.put("level", this.level);
		txtParams.put("name", this.name);
		txtParams.put("opening_time", this.openingTime);
		txtParams.put("orientation", this.orientation);
		txtParams.put("province", this.province);
		txtParams.put("rooms", this.rooms);
		txtParams.put("service", this.service);
		txtParams.put("storeys", this.storeys);
		txtParams.put("tel", this.tel);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new TaobaoHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Map<String, FileItem> getFileParams() {
		Map<String, FileItem> params = new HashMap<String, FileItem>();
		params.put("pic", this.pic);
		return params;
	}

	public Class<HotelUpdateResponse> getResponseClass() {
		return HotelUpdateResponse.class;
	}

	public void check() throws ApiRuleException {
		
		RequestCheckUtils.checkMaxLength(address,120,"address");
		RequestCheckUtils.checkMaxValue(city,999999L,"city");
		RequestCheckUtils.checkMinValue(city,0L,"city");
		RequestCheckUtils.checkMaxLength(decorateTime,4,"decorateTime");
		RequestCheckUtils.checkMaxLength(desc,50000,"desc");
		RequestCheckUtils.checkMaxValue(district,999999L,"district");
		RequestCheckUtils.checkMinValue(district,0L,"district");
		RequestCheckUtils.checkNotEmpty(hid,"hid");
		RequestCheckUtils.checkMaxLength(level,1,"level");
		RequestCheckUtils.checkMaxLength(name,60,"name");
		RequestCheckUtils.checkMaxLength(openingTime,4,"openingTime");
		RequestCheckUtils.checkMaxLength(orientation,1,"orientation");
		RequestCheckUtils.checkMaxLength(pic,512000,"pic");
		RequestCheckUtils.checkMaxValue(province,999999L,"province");
		RequestCheckUtils.checkMinValue(province,0L,"province");
		RequestCheckUtils.checkMaxValue(rooms,9999L,"rooms");
		RequestCheckUtils.checkMinValue(rooms,0L,"rooms");
		RequestCheckUtils.checkMaxValue(storeys,9999L,"storeys");
		RequestCheckUtils.checkMinValue(storeys,0L,"storeys");
		RequestCheckUtils.checkMaxLength(tel,32,"tel");
	}
	
	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}
