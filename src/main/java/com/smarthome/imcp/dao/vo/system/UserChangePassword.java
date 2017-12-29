/*    */ package com.smarthome.imcp.dao.vo.system;
/*    */ 
/*    */ public class UserChangePassword
/*    */ {
/*    */   private Integer userId;
/*    */   private String userName;
/*    */   private String loginPwd;
/*    */   private String oldPassword;
/*    */   private String newPassword;
/*    */   private String confirmPassword;
/*    */ 
/*    */   public Integer getUserId()
/*    */   {
/* 13 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(Integer userId) {
/* 17 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public String getUserName() {
/* 21 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 25 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public String getLoginPwd() {
/* 29 */     return this.loginPwd;
/*    */   }
/*    */ 
/*    */   public void setLoginPwd(String loginPwd) {
/* 33 */     this.loginPwd = loginPwd;
/*    */   }
/*    */ 
/*    */   public String getOldPassword() {
/* 37 */     return this.oldPassword;
/*    */   }
/*    */ 
/*    */   public void setOldPassword(String oldPassword) {
/* 41 */     this.oldPassword = oldPassword;
/*    */   }
/*    */ 
/*    */   public String getNewPassword() {
/* 45 */     return this.newPassword;
/*    */   }
/*    */ 
/*    */   public void setNewPassword(String newPassword) {
/* 49 */     this.newPassword = newPassword;
/*    */   }
/*    */ 
/*    */   public String getConfirmPassword() {
/* 53 */     return this.confirmPassword;
/*    */   }
/*    */ 
/*    */   public void setConfirmPassword(String confirmPassword) {
/* 57 */     this.confirmPassword = confirmPassword;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.system.UserChangePassword
 * JD-Core Version:    0.6.2
 */