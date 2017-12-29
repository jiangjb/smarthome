/*    */ package com.smarthome.imcp.interceptor;
/*    */ 
/*    */ import com.opensymphony.xwork2.ActionContext;
/*    */ import com.opensymphony.xwork2.ActionInvocation;
/*    */ import com.opensymphony.xwork2.ActionProxy;
/*    */ import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/*    */ import com.smarthome.imcp.common.ResponseUtil;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class ExceptionInterceptor extends AbstractInterceptor
/*    */ {
/* 16 */   private static Logger logger = Logger.getLogger(ExceptionInterceptor.class
/* 17 */     .getName());
/*    */ 
/*    */   public String intercept(ActionInvocation actionInvocation) throws Exception
/*    */   {
/* 20 */     ActionContext ctx = ActionContext.getContext();
/* 21 */     HttpServletResponse response = (HttpServletResponse)ctx
/* 22 */       .get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
/* 23 */     String actionName = actionInvocation.getProxy().getActionName();
/*    */     try {
/* 25 */       actionInvocation.invoke();
/*    */     } catch (Exception e) {
/* 27 */       logger.error(e.getMessage(), e.getCause());
/* 28 */       String message = 
/* 29 */         ExceptionUtils.returnExcetionMessage(e, actionName);
/* 30 */       ctx.put("exception", message);
/* 31 */       ResponseUtil.writerError(response, message);
/* 32 */       return "exception";
/*    */     }
/* 34 */     return "success";
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.interceptor.ExceptionInterceptor
 * JD-Core Version:    0.6.2
 */