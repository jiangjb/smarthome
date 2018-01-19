package com.smarthome.imcp.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Map2List {

	//map转换成list
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List  mapTransitionList(Map map) {
		List list = new ArrayList();
		Iterator iter = map.entrySet().iterator();  //获得map的Iterator
		while(iter.hasNext()) {
			Entry entry = (Entry)iter.next();
			list.add(entry.getValue());
		}
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("aaa", 11);
		map.put("bbb", "22ss");
		map.put("ccc", "汉字");
		List list = mapTransitionList(map);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}