package com.tomgu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil {

	public static void addTokenMapping(Map<String, List> map, String key,
			String value) {
		List list;
		if(map.containsKey(key)){
			list= map.get(key);
			if(!list.contains(value))
				list.add(value);
		}else{
			list = new ArrayList<String>();
			list.add(value);
			map.put(key, list);
		}
		
		
	}

}
