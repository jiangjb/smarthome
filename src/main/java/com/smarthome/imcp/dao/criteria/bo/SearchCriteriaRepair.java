/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class SearchCriteriaRepair
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String deviceCode;
/*    */   private String userName;
/*    */   private Date timeStart;
/*    */   private Date timeEnd;
/*    */ 
/*    */   public String getDeviceCode()
/*    */   {
/* 15 */     return this.deviceCode;
/*    */   }
/*    */   public void setDeviceCode(String deviceCode) {
/* 18 */     this.deviceCode = deviceCode;
/*    */   }
/*    */   public String getUserName() {
/* 21 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 24 */     this.userName = userName;
/*    */   }
/*    */   public Date getTimeStart() {
/* 27 */     return this.timeStart;
/*    */   }
/*    */   public void setTimeStart(Date timeStart) {
/* 30 */     this.timeStart = timeStart;
/*    */   }
/*    */   public Date getTimeEnd() {
/* 33 */     return this.timeEnd;
/*    */   }
/*    */   public void setTimeEnd(Date timeEnd) {
/* 36 */     this.timeEnd = timeEnd;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaRepair
 * JD-Core Version:    0.6.2
 */