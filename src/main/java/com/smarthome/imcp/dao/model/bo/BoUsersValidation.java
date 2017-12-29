/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoUsersValidation
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String userPhone;
/*    */   private String verificationCode;
/*    */ 
/*    */   public BoUsersValidation()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoUsersValidation(String userPhone, String verificationCode)
/*    */   {
/* 23 */     this.userPhone = userPhone;
/* 24 */     this.verificationCode = verificationCode;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 34 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getUserPhone() {
/* 38 */     return this.userPhone;
/*    */   }
/*    */ 
/*    */   public void setUserPhone(String userPhone) {
/* 42 */     this.userPhone = userPhone;
/*    */   }
/*    */ 
/*    */   public String getVerificationCode() {
/* 46 */     return this.verificationCode;
/*    */   }
/*    */ 
/*    */   public void setVerificationCode(String verificationCode) {
/* 50 */     this.verificationCode = verificationCode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUsersValidation
 * JD-Core Version:    0.6.2
 */