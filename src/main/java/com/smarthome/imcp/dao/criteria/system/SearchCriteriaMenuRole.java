/*    */ package com.smarthome.imcp.dao.criteria.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public class SearchCriteriaMenuRole
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String userType;
/*    */   private String menuRoleName;
/*    */ 
/*    */   public String getUserType()
/*    */   {
/* 12 */     return this.userType;
/*    */   }
/*    */ 
/*    */   public void setUserType(String userType) {
/* 16 */     this.userType = userType;
/*    */   }
/*    */ 
/*    */   public String getMenuRoleName() {
/* 20 */     return this.menuRoleName;
/*    */   }
/*    */ 
/*    */   public void setMenuRoleName(String menuRoleName) {
/* 24 */     this.menuRoleName = menuRoleName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenuRole
 * JD-Core Version:    0.6.2
 */