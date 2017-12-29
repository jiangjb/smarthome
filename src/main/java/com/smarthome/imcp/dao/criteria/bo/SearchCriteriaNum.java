/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class SearchCriteriaNum
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String deviceCode;
/*    */   private Date timeEnd;
/*    */ 
/*    */   public String getDeviceCode()
/*    */   {
/* 12 */     return this.deviceCode;
/*    */   }
/*    */   public void setDeviceCode(String deviceCode) {
/* 15 */     this.deviceCode = deviceCode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaNum
 * JD-Core Version:    0.6.2
 */