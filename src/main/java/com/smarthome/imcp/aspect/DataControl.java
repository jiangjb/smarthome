/*    */ package com.smarthome.imcp.aspect;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ 
/*    */ public abstract class DataControl
/*    */ {
/*    */   public static void setCreator(AbstractData objaData, Integer straUserID, String straUserName)
/*    */     throws Exception
/*    */   {
/* 11 */     objaData.setMntCreatorId(straUserID);
/* 12 */     objaData.setMntCreatorName(straUserName);
/*    */ 
/* 14 */     objaData.setMntCreatorDate(GlobalMethod.formatDate(
/* 15 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 16 */       "yyyy-MM-dd HH:mm:ss"));
/*    */   }
/*    */ 
/*    */   public static void setUpdated(AbstractData objaData, Integer straUserID, String straUserName)
/*    */     throws Exception
/*    */   {
/* 26 */     objaData.setMntUpdatedId(straUserID);
/* 27 */     objaData.setMntUpdatedName(straUserName);
/*    */ 
/* 29 */     objaData.setMntUpdatedDate(GlobalMethod.formatDate(
/* 30 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 31 */       "yyyy-MM-dd HH:mm:ss"));
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.aspect.DataControl
 * JD-Core Version:    0.6.2
 */