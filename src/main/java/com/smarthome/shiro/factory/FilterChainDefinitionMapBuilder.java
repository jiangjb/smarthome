package com.smarthome.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//静态资源免过滤
		map.put("/css/*", "anon");
		map.put("/font/*", "anon");
		map.put("/images/*", "anon");
		map.put("/js/*", "anon");
		map.put("/plugins/*", "anon");
		map.put("/UI_new/*", "anon");
		map.put("/login.jsp", "anon");
		map.put("/register.jsp", "anon");
		map.put("/TelOrEmail.jsp", "anon");
		map.put("/findPwdByEmail.jsp", "anon");
		map.put("/findPwdByTel.jsp", "anon");
		map.put("/login.do", "anon");//不然登录的方法无法使用      免拦截的一种方式
		map.put("/register.do", "anon");
//		map.put("/index.jsp", "roles[admin]");
//		map.put("/list.jsp", "user");
//		map.put("/**", "authc");
		return map;
	}
	
}
