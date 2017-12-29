/*    */ package com.smarthome.imcp.dao.criteria.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ 
/*    */ public final class SearchCriteriaUser
/*    */   implements SearchCriteria
/*    */ {
/*    */   private String userName;
/*    */   private String userPhone;
/*    */   private String userEmail;
/*    */   private Integer groupId;
/*    */   private Integer type;
/*    */ 
/*    */   public String getUserName()
/*    */   {
/* 17 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 20 */     this.userName = userName;
/*    */   }
/*    */   public String getUserPhone() {
/* 23 */     return this.userPhone;
/*    */   }
/*    */   public void setUserPhone(String userPhone) {
/* 26 */     this.userPhone = userPhone;
/*    */   }
/*    */   public String getUserEmail() {
/* 29 */     return this.userEmail;
/*    */   }
/*    */   public void setUserEmail(String userEmail) {
/* 32 */     this.userEmail = userEmail;
/*    */   }
/*    */   public Integer getGroupId() {
/* 35 */     return this.groupId;
/*    */   }
/*    */   public void setGroupId(Integer groupId) {
/* 38 */     this.groupId = groupId;
/*    */   }
/*    */   public Integer getType() {
/* 41 */     return this.type;
/*    */   }
/*    */   public void setType(Integer type) {
/* 44 */     this.type = type;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUser
 * JD-Core Version:    0.6.2
 */