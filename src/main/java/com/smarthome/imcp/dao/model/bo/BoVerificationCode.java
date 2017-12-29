/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoVerificationCode
/*    */ {
/*    */   private String userPhone;
/*    */   private String verificationCode;
/*    */   private Date generationTimestamp;
/*    */ 
/*    */   public String getUserPhone()
/*    */   {
/* 13 */     return this.userPhone;
/*    */   }
/*    */ 
/*    */   public void setUserPhone(String userPhone)
/*    */   {
/* 19 */     this.userPhone = userPhone;
/*    */   }
/*    */ 
/*    */   public String getVerificationCode()
/*    */   {
/* 25 */     return this.verificationCode;
/*    */   }
/*    */ 
/*    */   public void setVerificationCode(String verificationCode)
/*    */   {
/* 31 */     this.verificationCode = verificationCode;
/*    */   }
/*    */ 
/*    */   public Date getGenerationTimestamp()
/*    */   {
/* 37 */     return this.generationTimestamp;
/*    */   }
/*    */ 
/*    */   public void setGenerationTimestamp(Date generationTimestamp)
/*    */   {
/* 43 */     this.generationTimestamp = generationTimestamp;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoVerificationCode
 * JD-Core Version:    0.6.2
 */