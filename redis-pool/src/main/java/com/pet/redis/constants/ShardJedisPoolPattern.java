package com.pet.redis.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShardJedisPoolPattern {
//    public static final Pattern COUNTER_KEY_TAG_PATTERN = Pattern.compile("\\{(.+?)\\}");
//    public static final Pattern COUNTER_KEY_TAG_PATTERN_1 = Pattern.compile("-(.+?)$");
//    public static final Pattern COUNTER_KEY_TAG_PATTERN_2 = Pattern.compile("-([0-9]+?)$");
    public static final Pattern NUMBER_ENDED_KEY_TAG_PATTERN = Pattern.compile("([0-9]+?)$");
    public static void main(String[] args) {
		Matcher m = NUMBER_ENDED_KEY_TAG_PATTERN.matcher("2222333-121314");
		while(m.find()){
			System.out.println(m.group());
		}
	}
}
