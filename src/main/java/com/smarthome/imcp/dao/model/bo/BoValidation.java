/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoValidation
/*    */   implements Serializable
/*    */ {
/*    */   private Integer veriyId;
/*    */   private String userPhone;
/*    */   private String veriyCode;
/*    */ 
/*    */   public BoValidation()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoValidation(String userPhone, String veriyCode)
/*    */   {
/* 23 */     this.userPhone = userPhone;
/* 24 */     this.veriyCode = veriyCode;
/*    */   }
/*    */ 
/*    */   public Integer getVeriyId()
/*    */   {
/* 30 */     return this.veriyId;
/*    */   }
/*    */ 
/*    */   public void setVeriyId(Integer veriyId) {
/* 34 */     this.veriyId = veriyId;
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
/*    */   public String getVeriyCode() {
/* 46 */     return this.veriyCode;
/*    */   }
/*    */ 
/*    */   public void setVeriyCode(String veriyCode) {
/* 50 */     this.veriyCode = veriyCode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoValidation
 * JD-Core Version:    0.6.2
 */