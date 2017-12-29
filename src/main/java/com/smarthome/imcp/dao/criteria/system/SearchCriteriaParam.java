/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaParam
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String paramCode;
/*    */   private String paramName;
/*    */   private String paramValue;
/*    */ 
/*    */   public String getParamCode()
/*    */   {
/* 13 */     return this.paramCode;
/*    */   }
/*    */ 
/*    */   public void setParamCode(String paramCode) {
/* 17 */     this.paramCode = paramCode;
/*    */   }
/*    */ 
/*    */   public String getParamName() {
/* 21 */     return this.paramName;
/*    */   }
/*    */ 
/*    */   public void setParamName(String paramName) {
/* 25 */     this.paramName = paramName;
/*    */   }
/*    */ 
/*    */   public String getParamValue() {
/* 29 */     return this.paramValue;
/*    */   }
/*    */ 
/*    */   public void setParamValue(String paramValue) {
/* 33 */     this.paramValue = paramValue;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaParam
 * JD-Core Version:    0.6.2
 */