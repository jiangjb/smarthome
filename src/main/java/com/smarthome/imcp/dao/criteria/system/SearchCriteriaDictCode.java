/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaDictCode
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String dictCodeName;
/*    */   private String dictCode;
/*    */ 
/*    */   public String getDictCode()
/*    */   {
/* 12 */     return this.dictCode;
/*    */   }
/*    */ 
/*    */   public void setDictCode(String dictCode) {
/* 16 */     this.dictCode = dictCode;
/*    */   }
/*    */ 
/*    */   public String getDictCodeName() {
/* 20 */     return this.dictCodeName;
/*    */   }
/*    */ 
/*    */   public void setDictCodeName(String dictCodeName) {
/* 24 */     this.dictCodeName = dictCodeName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaDictCode
 * JD-Core Version:    0.6.2
 */