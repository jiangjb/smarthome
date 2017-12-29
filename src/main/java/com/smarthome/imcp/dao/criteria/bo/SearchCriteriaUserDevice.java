/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ import org.springframework.format.annotation.DateTimeFormat;
/*    */ 
/*    */ public final class SearchCriteriaUserDevice
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String userName;
/*    */   private String factoryName;
/*    */ 
/*    */   @DateTimeFormat(pattern="yyyy-MM-dd")
/*    */   private Date deviceDate;
/*    */ 
/*    */   public String getUserName()
/*    */   {
/* 16 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 19 */     this.userName = userName;
/*    */   }
/*    */   public String getFactoryName() {
/* 22 */     return this.factoryName;
/*    */   }
/*    */   public void setFactoryName(String factoryName) {
/* 25 */     this.factoryName = factoryName;
/*    */   }
/*    */   public Date getDeviceDate() {
/* 28 */     return this.deviceDate;
/*    */   }
/*    */   public void setDeviceDate(Date deviceDate) {
/* 31 */     this.deviceDate = deviceDate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserDevice
 * JD-Core Version:    0.6.2
 */