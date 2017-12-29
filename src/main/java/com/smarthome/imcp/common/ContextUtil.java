/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import com.smarthome.imcp.interceptor.ThreadRequestManager;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class ContextUtil
/*    */ {
/*    */   public static CurrentUser getCurrentUser()
/*    */   {
/*    */     try
/*    */     {
/* 17 */       Object o = null;
/* 18 */       if ((ThreadRequestManager.getRequest() != null) && 
/* 19 */         (ThreadRequestManager.getRequest().getSession() != null)) {
/* 20 */         o = ThreadRequestManager.getRequest().getSession()
/* 21 */           .getAttribute("USER_INFO");
/*    */       }
/* 23 */       return (CurrentUser)o;
/*    */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/* 26 */       CurrentUser currentUser = new CurrentUser();
/* 27 */       currentUser.setUserId(Integer.valueOf(-1));
/* 28 */       return currentUser;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getSessionId() {
/* 33 */     String o = "";
/*    */     try {
/* 35 */       if ((ThreadRequestManager.getRequest() != null) && 
/* 36 */         (ThreadRequestManager.getRequest().getSession() != null))
/* 37 */         o = (String)ThreadRequestManager.getRequest().getSession()
/* 38 */           .getAttribute("SESSION_ID");
/*    */     }
/*    */     catch (Exception e) {
/* 41 */       e.printStackTrace();
/*    */     }
/* 43 */     return o;
/*    */   }
/*    */ 
/*    */   public static String getContextPath() {
/* 47 */     HttpServletRequest request = ThreadRequestManager.getRequest();
/* 48 */     return request.getContextPath();
/*    */   }
/*    */ 
/*    */   public static String getIP() {
/* 52 */     HttpServletRequest request = ThreadRequestManager.getRequest();
/* 53 */     String ip = request.getHeader("x-forwarded-for");
/* 54 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 55 */       ip = request.getHeader("Proxy-Client-IP");
/*    */     }
/* 57 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 58 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*    */     }
/* 60 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 61 */       ip = request.getRemoteAddr();
/*    */     }
/* 63 */     return ip;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.ContextUtil
 * JD-Core Version:    0.6.2
 */