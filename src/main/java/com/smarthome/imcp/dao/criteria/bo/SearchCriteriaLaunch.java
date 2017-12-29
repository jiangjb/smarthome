/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaLaunch
/*    */   implements SearchCriteria
/*    */ {
/*    */   private Integer type;
/*    */   private Integer groupId;
/*    */ 
/*    */   public Integer getGroupId()
/*    */   {
/* 11 */     return this.groupId;
/*    */   }
/*    */ 
/*    */   public void setGroupId(Integer groupId) {
/* 15 */     this.groupId = groupId;
/*    */   }
/*    */ 
/*    */   public Integer getType() {
/* 19 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(Integer type) {
/* 23 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaLaunch
 * JD-Core Version:    0.6.2
 */