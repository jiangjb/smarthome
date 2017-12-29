/*    */ package com.smarthome.imcp.interceptor;
/*    */ 
/*    */ import com.opensymphony.xwork2.ActionContext;
/*    */ import com.opensymphony.xwork2.ActionInvocation;
/*    */ import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/*    */ import com.smarthome.imcp.action.xing.AppLogin_Action;
/*    */ import com.smarthome.imcp.action.xing.Customization_Action;
/*    */ import com.smarthome.imcp.action.xing.DoorLock_Action;
/*    */ import com.smarthome.imcp.action.xing.PM25_Action;
/*    */ import com.smarthome.imcp.action.xing.XingUserAction;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
/*    */ import java.util.Map;
/*    */ import org.apache.struts2.ServletActionContext;
/*    */ 
/*    */ public class AuthCheckInterceptor extends AbstractInterceptor
/*    */ {
/*    */   public String intercept(ActionInvocation ai)
/*    */     throws Exception
/*    */   {
/* 31 */     Map session = ActionContext.getContext().getSession();
/* 32 */     ThreadRequestManager.setRequest(ServletActionContext.getRequest());
/* 33 */     Object action = ai.getAction();
/*    */ 
/* 35 */     if (((action instanceof XingUserAction)) || ((action instanceof DoorLock_Action)) || ((action instanceof Customization_Action)) || ((action instanceof AppLogin_Action)) || ((action instanceof PM25_Action))) {
/* 36 */       return ai.invoke();
/*    */     }
/* 38 */     CurrentUser currentUser = 
/* 39 */       (CurrentUser)session
/* 39 */       .get("USER_INFO");
/* 40 */     if (currentUser == null) {
/* 41 */       return "globalLogin";
/*    */     }
/*    */     try
/*    */     {
/* 45 */       return ai.invoke();
/*    */     } finally {
/* 47 */       ThreadRequestManager.clear();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.interceptor.AuthCheckInterceptor
 * JD-Core Version:    0.6.2
 */