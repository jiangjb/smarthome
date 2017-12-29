/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaDict
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String dictCode;
/*    */   private String dictName;
/*    */   private String dictValue;
/*    */ 
/*    */   public String getDictCode()
/*    */   {
/* 13 */     return this.dictCode;
/*    */   }
/*    */ 
/*    */   public void setDictCode(String dictCode) {
/* 17 */     this.dictCode = dictCode;
/*    */   }
/*    */ 
/*    */   public String getDictName() {
/* 21 */     return this.dictName;
/*    */   }
/*    */ 
/*    */   public void setDictName(String dictName) {
/* 25 */     this.dictName = dictName;
/*    */   }
/*    */ 
/*    */   public String getDictValue() {
/* 29 */     return this.dictValue;
/*    */   }
/*    */ 
/*    */   public void setDictValue(String dictValue) {
/* 33 */     this.dictValue = dictValue;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaDict
 * JD-Core Version:    0.6.2
 */