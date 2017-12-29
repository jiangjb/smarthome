/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaOperate
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String operateUserName;
/*    */   private String operateModuleTxt;
/*    */   private String operateType;
/*    */ 
/*    */   public String getOperateUserName()
/*    */   {
/* 15 */     return this.operateUserName;
/*    */   }
/*    */ 
/*    */   public void setOperateUserName(String operateUserName) {
/* 19 */     this.operateUserName = operateUserName;
/*    */   }
/*    */ 
/*    */   public String getOperateModuleTxt() {
/* 23 */     return this.operateModuleTxt;
/*    */   }
/*    */ 
/*    */   public void setOperateModuleTxt(String operateModuleTxt) {
/* 27 */     this.operateModuleTxt = operateModuleTxt;
/*    */   }
/*    */ 
/*    */   public String getOperateType() {
/* 31 */     return this.operateType;
/*    */   }
/*    */ 
/*    */   public void setOperateType(String operateType) {
/* 35 */     this.operateType = operateType;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaOperate
 * JD-Core Version:    0.6.2
 */