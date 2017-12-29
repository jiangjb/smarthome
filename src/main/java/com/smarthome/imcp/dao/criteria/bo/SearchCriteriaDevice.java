/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaDevice
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String deviceName;
/*    */   private String deviceType;
/*    */   private String deviceCode;
/*    */   private Integer factoryId;
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 13 */     return this.deviceName;
/*    */   }
/*    */   public void setDeviceName(String deviceName) {
/* 16 */     this.deviceName = deviceName;
/*    */   }
/*    */   public String getDeviceType() {
/* 19 */     return this.deviceType;
/*    */   }
/*    */   public void setDeviceType(String deviceType) {
/* 22 */     this.deviceType = deviceType;
/*    */   }
/*    */   public String getDeviceCode() {
/* 25 */     return this.deviceCode;
/*    */   }
/*    */   public void setDeviceCode(String deviceCode) {
/* 28 */     this.deviceCode = deviceCode;
/*    */   }
/*    */   public Integer getFactoryId() {
/* 31 */     return this.factoryId;
/*    */   }
/*    */   public void setFactoryId(Integer factoryId) {
/* 34 */     this.factoryId = factoryId;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDevice
 * JD-Core Version:    0.6.2
 */