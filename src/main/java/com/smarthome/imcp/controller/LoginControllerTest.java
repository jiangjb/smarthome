package com.smarthome.imcp.controller;

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
		lc.hello("Mr jiang");						//2、用类的实例 测试相应方法
	}

}
