/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaDeviceType
/*    */   implements SearchCriteria
/*    */ {
/*  7 */   private Integer factoryId = Integer.valueOf(-1);
/*    */   private String factoryName;
/*    */   private String typeCode;
/*    */   private String typeName;
/*    */ 
/*    */   public Integer getFactoryId()
/*    */   {
/* 16 */     return this.factoryId;
/*    */   }
/*    */ 
/*    */   public void setFactoryId(Integer factoryId) {
/* 20 */     this.factoryId = factoryId;
/*    */   }
/*    */ 
/*    */   public String getTypeCode() {
/* 24 */     return this.typeCode;
/*    */   }
/*    */ 
/*    */   public void setTypeCode(String typeCode) {
/* 28 */     this.typeCode = typeCode;
/*    */   }
/*    */ 
/*    */   public String getTypeName() {
/* 32 */     return this.typeName;
/*    */   }
/*    */ 
/*    */   public void setTypeName(String typeName) {
/* 36 */     this.typeName = typeName;
/*    */   }
/*    */ 
/*    */   public String getFactoryName() {
/* 40 */     return this.factoryName;
/*    */   }
/*    */ 
/*    */   public void setFactoryName(String factoryName) {
/* 44 */     this.factoryName = factoryName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDeviceType
 * JD-Core Version:    0.6.2
 */