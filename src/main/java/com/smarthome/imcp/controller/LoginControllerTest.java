package com.smarthome.imcp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;

import junit.framework.TestCase;

/**
 * @author Jiang
 * 用于测试Junit-单元测试
 *
 */
public class LoginControllerTest extends TestCase {
	
	private LoginController lc=new LoginController();//1、引入测试类
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testHello() {
//		lc.hello("Mr jiang");						//2、用类的实例 测试相应方法
		//test json2list,list2json
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("roomCode" , "391c065c611f4cfb8e6ff7597fdce064");
		map.put("isAuthorited" , "1");
		map.put("deviceId" , "VSTD092084AZOLN");
		list.add(map);
		Map<String,String> map1=new HashMap<String,String>();
		map1.put("roomCode" , "391c065c611f4cfb8e6ff7597fdce064");
		map1.put("isAuthorited" , "1");
		map1.put("deviceId" , "52827367");
		list.add(map1);
		
//		System.out.println(list);//[{isAuthorited=1, roomCode=391c065c611f4cfb8e6ff7597fdce064, deviceId=VSTD092084AZOLN}, {isAuthorited=1, roomCode=391c065c611f4cfb8e6ff7597fdce064, deviceId=52827367}]
		String json =JSONArray.fromObject(list).toString();
//		System.out.println(json);//[{"isAuthorited":"1","roomCode":"391c065c611f4cfb8e6ff7597fdce064","deviceId":"VSTD092084AZOLN"},{"isAuthorited":"1","roomCode":"391c065c611f4cfb8e6ff7597fdce064","deviceId":"52827367"}]
		
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<Map<String,String>> list2 = (List<Map<String,String>>) JSONArray.toCollection(jsonArray,Map.class);
//		System.out.println(list2);//[{isAuthorited=1, roomCode=391c065c611f4cfb8e6ff7597fdce064, deviceId=VSTD092084AZOLN}, {isAuthorited=1, roomCode=391c065c611f4cfb8e6ff7597fdce064, deviceId=52827367}]
		for(int i=0;i<list.size();i++) {
			System.out.println(list2.get(i).get("deviceId"));
		}
	}

}
