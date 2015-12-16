package com.tomgu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tomgu.entity.ASTNodeMappingElement;

public class MapUtil {

	public static void addTokenMapping(Map<String, List> map, String key,
			String value, Map<String, List<ASTNodeMappingElement>> nodemap, ASTNodeMappingElement e) {
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
		
		if(nodemap.containsKey(key)){
			list = nodemap.get(key);
			if(!list.contains(e))
				list.add(e);
		}else{
			list = new ArrayList<ASTNodeMappingElement>();
			list.add(e);
			nodemap.put(key, list);
		}
		
	}

}
