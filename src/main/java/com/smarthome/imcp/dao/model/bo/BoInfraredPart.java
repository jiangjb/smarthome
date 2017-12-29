/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoInfraredPart
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String deviceAddress;
/*    */   private String validationCode;
/*    */ 
/*    */   public BoInfraredPart()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoInfraredPart(String deviceAddress, String validationCode)
/*    */   {
/* 23 */     this.deviceAddress = deviceAddress;
/* 24 */     this.validationCode = validationCode;
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
/*    */   public String getDeviceAddress() {
/* 38 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 42 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */ 
/*    */   public String getValidationCode() {
/* 46 */     return this.validationCode;
/*    */   }
/*    */ 
/*    */   public void setValidationCode(String validationCode) {
/* 50 */     this.validationCode = validationCode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoInfraredPart
 * JD-Core Version:    0.6.2
 */