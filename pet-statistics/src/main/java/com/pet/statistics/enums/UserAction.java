package com.pet.statistics.enums;

public enum UserAction {

	NONE(0, 0),

	LOOK(1, 1),

	COLLECTION(2, 1),

	SHOPCHART(3, 1),

	BUY(4, 1),

	;

	private int id;

	private int weight;

	UserAction(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public int getWeight() {
		return weight;
	}

}
