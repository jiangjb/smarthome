/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoResendVerification
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoDevice boDevice;
/*    */   private String deviceAddress;
/*    */   private String acceptState;
/*    */   private String command;
/*    */   private String time;
/*    */   private String deviceType;
/*    */ 
/*    */   public BoResendVerification()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoResendVerification(BoDevice boDevice, String deviceAddress, String acceptState, String command, String time, String deviceType)
/*    */   {
/* 28 */     this.boDevice = boDevice;
/* 29 */     this.deviceAddress = deviceAddress;
/* 30 */     this.acceptState = acceptState;
/* 31 */     this.command = command;
/* 32 */     this.time = time;
/* 33 */     this.deviceType = deviceType;
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
/*    */   public BoDevice getBoDevice() {
/* 47 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 51 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getDeviceAddress() {
/* 55 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 59 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */ 
/*    */   public String getAcceptState() {
/* 63 */     return this.acceptState;
/*    */   }
/*    */ 
/*    */   public void setAcceptState(String acceptState) {
/* 67 */     this.acceptState = acceptState;
/*    */   }
/*    */ 
/*    */   public String getCommand() {
/* 71 */     return this.command;
/*    */   }
/*    */ 
/*    */   public void setCommand(String command) {
/* 75 */     this.command = command;
/*    */   }
/*    */ 
/*    */   public String getTime() {
/* 79 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setTime(String time) {
/* 83 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public String getDeviceType() {
/* 87 */     return this.deviceType;
/*    */   }
/*    */ 
/*    */   public void setDeviceType(String deviceType) {
/* 91 */     this.deviceType = deviceType;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoResendVerification
 * JD-Core Version:    0.6.2
 */