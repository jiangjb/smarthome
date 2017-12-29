/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaFactory
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String factoryName;
/*    */   private String factoryPhone;
/*    */ 
/*    */   public String getFactoryName()
/*    */   {
/* 12 */     return this.factoryName;
/*    */   }
/*    */   public void setFactoryName(String factoryName) {
/* 15 */     this.factoryName = factoryName;
/*    */   }
/*    */   public String getFactoryPhone() {
/* 18 */     return this.factoryPhone;
/*    */   }
/*    */   public void setFactoryPhone(String factoryPhone) {
/* 21 */     this.factoryPhone = factoryPhone;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFactory
 * JD-Core Version:    0.6.2
 */