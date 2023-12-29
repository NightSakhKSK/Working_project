package com.utarex.youngone.api.utils;

import java.util.HashMap;
import java.util.Map;

public class Const {
	public final static Map<String, String> HashItemIdToName = new HashMap<String, String>();
	static {
		HashItemIdToName.put("Cylinder", "Cylinder");
		HashItemIdToName.put("Strawberry", "딸기");
		HashItemIdToName.put("Mint", "민트");
		HashItemIdToName.put("Herb", "허브");
		HashItemIdToName.put("Cola", "콜라");
		HashItemIdToName.put("Filter", "필터");
	}
}
