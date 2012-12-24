package com.pet.search.utils;

import java.util.Comparator;

import org.springframework.stereotype.Component;


@Component(value="zoieComparator")
public class ZoieComparator implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}

}
