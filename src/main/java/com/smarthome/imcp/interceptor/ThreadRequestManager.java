/*    */ package com.smarthome.imcp.interceptor;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ThreadRequestManager
/*    */ {
/*  7 */   @SuppressWarnings({ "unchecked", "rawtypes" })
          private static ThreadLocal<HttpServletRequest> request = new ThreadLocal();
/*    */ 
/*    */   public static HttpServletRequest getRequest() {
/* 10 */     return (HttpServletRequest)request.get();
/*    */   }
/*    */ 
/*    */  
		   public static void setRequest(HttpServletRequest request1) {
///* 14 */     ((ThreadLocal<HttpServletRequest>) request).set(request);// org.apache.struts2.dispatcher.StrutsRequestWrapper cannot be cast to java.lang.ThreadLocal
				 request.set(request1);
/*    */   }
/*    */ 
/*    */   public static void clear() {
/* 18 */     request.set(null);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.interceptor.ThreadRequestManager
 * JD-Core Version:    0.6.2
 */