/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class SearchCriteriaAdvice
/*    */   implements SearchCriteria
/*    */ {
/*    */   private Integer factoryId;
/*    */   private String userName;
/*    */   private Date timeStart;
/*    */   private Date timeEnd;
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
/*    */   public String getUserName() {
/* 24 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 28 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public Date getTimeStart() {
/* 32 */     return this.timeStart;
/*    */   }
/*    */ 
/*    */   public void setTimeStart(Date timeStart) {
/* 36 */     this.timeStart = timeStart;
/*    */   }
/*    */ 
/*    */   public Date getTimeEnd() {
/* 40 */     return this.timeEnd;
/*    */   }
/*    */ 
/*    */   public void setTimeEnd(Date timeEnd) {
/* 44 */     this.timeEnd = timeEnd;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAdvice
 * JD-Core Version:    0.6.2
 */