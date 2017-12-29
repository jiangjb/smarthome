/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaUser
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String userName;
/*    */   private String loginName;
/*    */   private String userType;
/*    */   private Integer provinceId;
/*    */   private Integer cityId;
/*    */   private Integer countyId;
/*    */   private Integer tonwId;
/*    */   private Integer villageId;
/*    */ 
/*    */   public String getUserName()
/*    */   {
/* 19 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 23 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public String getLoginName() {
/* 27 */     return this.loginName;
/*    */   }
/*    */ 
/*    */   public void setLoginName(String loginName) {
/* 31 */     this.loginName = loginName;
/*    */   }
/*    */ 
/*    */   public String getUserType() {
/* 35 */     return this.userType;
/*    */   }
/*    */ 
/*    */   public void setUserType(String userType) {
/* 39 */     this.userType = userType;
/*    */   }
/*    */ 
/*    */   public Integer getProvinceId() {
/* 43 */     return this.provinceId;
/*    */   }
/*    */ 
/*    */   public void setProvinceId(Integer provinceId) {
/* 47 */     this.provinceId = provinceId;
/*    */   }
/*    */ 
/*    */   public Integer getCityId() {
/* 51 */     return this.cityId;
/*    */   }
/*    */ 
/*    */   public void setCityId(Integer cityId) {
/* 55 */     this.cityId = cityId;
/*    */   }
/*    */ 
/*    */   public Integer getCountyId() {
/* 59 */     return this.countyId;
/*    */   }
/*    */ 
/*    */   public void setCountyId(Integer countyId) {
/* 63 */     this.countyId = countyId;
/*    */   }
/*    */ 
/*    */   public Integer getTonwId() {
/* 67 */     return this.tonwId;
/*    */   }
/*    */ 
/*    */   public void setTonwId(Integer tonwId) {
/* 71 */     this.tonwId = tonwId;
/*    */   }
/*    */ 
/*    */   public Integer getVillageId() {
/* 75 */     return this.villageId;
/*    */   }
/*    */ 
/*    */   public void setVillageId(Integer villageId) {
/* 79 */     this.villageId = villageId;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaUser
 * JD-Core Version:    0.6.2
 */