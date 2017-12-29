/*    */ package com.smarthome.imcp.aspect;
/*    */ 
/*    */ import com.smarthome.imcp.common.ContextUtil;
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
/*    */ import org.aspectj.lang.ProceedingJoinPoint;
/*    */ import org.aspectj.lang.Signature;
/*    */ 
/*    */ public class DataControlAspect
/*    */ {
/*    */   public Object doDataControl(ProceedingJoinPoint point)
/*    */     throws Throwable
/*    */   {
/* 13 */     String methodName = point.getSignature().getName();
/* 14 */     if ((!GlobalMethod.isNullorEmpty(methodName)) && 
/* 15 */       (point.getArgs().length == 1)) {
/* 16 */       Object arg = point.getArgs()[0];
/* 17 */       if ((arg instanceof AbstractData)) {
/* 18 */         setDataControl(methodName, (AbstractData)arg);
/*    */       }
/*    */     }
/*    */ 
/* 22 */     return point.proceed();
/*    */   }
/*    */ 
/*    */   private void setDataControl(String methodName, AbstractData data) throws Exception
/*    */   {
/* 27 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 28 */     if (currentUser == null) {
/* 29 */       currentUser = new CurrentUser();
/* 30 */       currentUser.setUserId(Integer.valueOf(-1));
/* 31 */       currentUser.setUserName("SYSGEN");
/*    */     }
/* 33 */     if ((methodName.startsWith("save")) || 
/* 34 */       (methodName.startsWith("saveOrUpdate"))) {
/* 35 */       DataControl.setCreator(data, currentUser.getUserId(), currentUser.getUserName());
/* 36 */       DataControl.setUpdated(data, currentUser.getUserId(), currentUser.getUserName());
/* 37 */     } else if ((methodName.startsWith("update")) || 
/* 38 */       (methodName.startsWith("deleteLogicByKey"))) {
/* 39 */       DataControl.setUpdated(data, currentUser.getUserId(), currentUser.getUserName());
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.aspect.DataControlAspect
 * JD-Core Version:    0.6.2
 */