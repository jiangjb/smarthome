/*    */ package com.smarthome.imcp.controller;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysUser;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.servlet.view.RedirectView;
/*    */ 
/*    */ @Controller
/*    */ public class TestController
/*    */ {
/*    */   @RequestMapping({"test/login.do"})
/*    */   public String testLogin(@RequestParam("username") String username, String password, HttpServletRequest request)
/*    */   {
/* 21 */     if ((!"admin".equals(username)) || (!"admin".equals(password))) {
/* 22 */       return "loginError";
/*    */     }
/* 24 */     return "test/MyJsp";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/test/login2.do"})
/*    */   public ModelAndView testLogin2(String username, String password, int age)
/*    */   {
/* 32 */     if ((!"admin".equals(username)) || (!"admin".equals(password)) || (age < 5)) {
/* 33 */       return new ModelAndView("loginError");
/*    */     }
/* 35 */     return new ModelAndView(new RedirectView("../index.jsp"));
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/test/login3.do"})
/*    */   public ModelAndView testLogin3(SysUser user)
/*    */   {
/* 43 */     String username = user.getUserName();
/* 44 */     String password = user.getLoginPwd();
/*    */ 
/* 46 */     if ((!"admin".equals(username)) || (!"admin".equals(password))) {
/* 47 */       return new ModelAndView("loginError");
/*    */     }
/* 49 */     return new ModelAndView("loginSuccess");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.TestController
 * JD-Core Version:    0.6.2
 */