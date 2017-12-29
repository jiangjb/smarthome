/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaParamCode
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String paramCodeName;
/*    */   private String paramCode;
/*    */ 
/*    */   public String getParamCode()
/*    */   {
/* 12 */     return this.paramCode;
/*    */   }
/*    */ 
/*    */   public void setParamCode(String paramCode) {
/* 16 */     this.paramCode = paramCode;
/*    */   }
/*    */ 
/*    */   public String getParamCodeName() {
/* 20 */     return this.paramCodeName;
/*    */   }
/*    */ 
/*    */   public void setParamCodeName(String paramCodeName) {
/* 24 */     this.paramCodeName = paramCodeName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaParamCode
 * JD-Core Version:    0.6.2
 */