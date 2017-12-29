/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class SearchCriteriaAlarm
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String deviceCode;
/*    */   private Date timeStart;
/*    */   private Date timeEnd;
/*    */   private Integer type;
/*    */ 
/*    */   public String getDeviceCode()
/*    */   {
/* 15 */     return this.deviceCode;
/*    */   }
/*    */   public void setDeviceCode(String deviceCode) {
/* 18 */     this.deviceCode = deviceCode;
/*    */   }
/*    */   public Date getTimeStart() {
/* 21 */     return this.timeStart;
/*    */   }
/*    */   public void setTimeStart(Date timeStart) {
/* 24 */     this.timeStart = timeStart;
/*    */   }
/*    */   public Date getTimeEnd() {
/* 27 */     return this.timeEnd;
/*    */   }
/*    */   public void setTimeEnd(Date timeEnd) {
/* 30 */     this.timeEnd = timeEnd;
/*    */   }
/*    */   public Integer getType() {
/* 33 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 36 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAlarm
 * JD-Core Version:    0.6.2
 */