package com.pet.core.enums;

public enum CommodityType {
	
	NONE(0,""),

	DOG(217309, "狗狗"),

	DOG_MAIN_FOOD(50015380, "狗主食"),

	DOG_TEMP_FOOD(50015262, "狗零食"),

	CAT(50016383, "猫咪"),

	CAT_MAIN_FOOD(50023066, "猫主食"),

	CAT_TEMP_FOOD(50023067, "猫零食"),

	DOG_CAT_DAY_USE(50015285, "狗/猫日用品"),

	DOG_CAT_BEAUTY_USE(50023206, "狗/猫美容清洁品"),

	DOG_CAT_HEALTH_PRODUCT(50015288, "狗/猫保健品"),

	DOG_CAT_MEDICE(50015289, "狗/猫医疗用品"),

	PET_CLOTHES(50001739, "宠物服饰和配件"),

	DOG_CAT_PLAYER(217311, "狗/猫玩具"),

	WATHE_WORLD(217312, "水族世界"),

	MOUTH_OTHER(50015293, "仓鼠及其它"),

	RABBIT(50015292, "兔类和其用品"),

	BIRD(50008604, "鸟类及其用品"),

	HORSE(50015294, "马类及其用品"),

	;

	private long id;

	private String name;
	
	
	public static CommodityType getCommodityTypeById(int id) {
		for (CommodityType hotCommodityType : CommodityType.values()) {
			if (hotCommodityType.getId()==id) {
				return hotCommodityType;
			}
		}
		return NONE;
	}

	CommodityType(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
