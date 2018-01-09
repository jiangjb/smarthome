package com.smarthome.imcp.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.record.formula.functions.T;

import com.smarthome.imcp.dao.model.system.SysUser;

public class List2Set {

	//list转换成set

	public static Set<String>  list2Set(List<SysUser> list) {
		List<String> list01 = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			String roleName=list.get(i).getEmail();
		}
		Set<String> set = new HashSet<String>(list01);
		return set;
	}

	public static void main(String[] args) {
		
	}

}