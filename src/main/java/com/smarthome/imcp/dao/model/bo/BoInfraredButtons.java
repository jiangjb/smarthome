/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoInfraredButtons
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String deviceAddress;
/*    */   private Integer infraredButtonsValuess;
/*    */   private String infraredButtonsName;
/*    */   private String template;
/*    */ 
/*    */   public BoInfraredButtons()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoInfraredButtons(BoUsers boUsers, BoDevice boDevice, String deviceAddress, Integer infraredButtonsValuess, String infraredButtonsName, String template)
/*    */   {
/* 28 */     this.boUsers = boUsers;
/* 29 */     this.boDevice = boDevice;
/* 30 */     this.deviceAddress = deviceAddress;
/* 31 */     this.infraredButtonsValuess = infraredButtonsValuess;
/* 32 */     this.template = template;
/* 33 */     this.infraredButtonsName = infraredButtonsName;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 43 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 47 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 51 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 55 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 59 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getDeviceAddress() {
/* 63 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 67 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */ 
/*    */   public Integer getInfraredButtonsValuess() {
/* 71 */     return this.infraredButtonsValuess;
/*    */   }
/*    */ 
/*    */   public void setInfraredButtonsValuess(Integer infraredButtonsValuess) {
/* 75 */     this.infraredButtonsValuess = infraredButtonsValuess;
/*    */   }
/*    */ 
/*    */   public String getInfraredButtonsName() {
/* 79 */     return this.infraredButtonsName;
/*    */   }
/*    */ 
/*    */   public void setInfraredButtonsName(String infraredButtonsName) {
/* 83 */     this.infraredButtonsName = infraredButtonsName;
/*    */   }
/*    */   public String getTemplate() {
/* 86 */     return this.template;
/*    */   }
/*    */ 
/*    */   public void setTemplate(String template) {
/* 90 */     this.template = template;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoInfraredButtons
 * JD-Core Version:    0.6.2
 */