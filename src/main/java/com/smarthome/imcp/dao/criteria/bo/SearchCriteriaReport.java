/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class SearchCriteriaReport
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String deviceCode;
/*    */   private Date timeStart;
/*    */   private Date timeEnd;
/*    */   private Integer type;
/*    */   private Integer frameType;
/*    */ 
/*    */   public String getDeviceCode()
/*    */   {
/* 17 */     return this.deviceCode;
/*    */   }
/*    */   public void setDeviceCode(String deviceCode) {
/* 20 */     this.deviceCode = deviceCode;
/*    */   }
/*    */   public Date getTimeStart() {
/* 23 */     return this.timeStart;
/*    */   }
/*    */   public void setTimeStart(Date timeStart) {
/* 26 */     this.timeStart = timeStart;
/*    */   }
/*    */   public Date getTimeEnd() {
/* 29 */     return this.timeEnd;
/*    */   }
/*    */   public void setTimeEnd(Date timeEnd) {
/* 32 */     this.timeEnd = timeEnd;
/*    */   }
/*    */   public Integer getType() {
/* 35 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 38 */     this.type = type;
/*    */   }
/*    */   public Integer getFrameType() {
/* 41 */     return this.frameType;
/*    */   }
/*    */   public void setFrameType(Integer frameType) {
/* 44 */     this.frameType = frameType;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaReport
 * JD-Core Version:    0.6.2
 */