/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoControlEnclosure
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoHostDevice boHostDevice;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String nickName;
/*    */   private String deviceAddress;
/*    */   private String state;
/*    */ 
/*    */   public BoControlEnclosure()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoControlEnclosure(BoHostDevice boHostDevice, BoUsers boUsers, BoDevice boDevice, String nickName, String deviceAddress, String state)
/*    */   {
/* 29 */     this.boHostDevice = boHostDevice;
/* 30 */     this.boUsers = boUsers;
/* 31 */     this.boDevice = boDevice;
/* 32 */     this.nickName = nickName;
/* 33 */     this.deviceAddress = deviceAddress;
/* 34 */     this.state = state;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 40 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 44 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoHostDevice getBoHostDevice() {
/* 48 */     return this.boHostDevice;
/*    */   }
/*    */ 
/*    */   public void setBoHostDevice(BoHostDevice boHostDevice) {
/* 52 */     this.boHostDevice = boHostDevice;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 56 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 60 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 64 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 68 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getNickName() {
/* 72 */     return this.nickName;
/*    */   }
/*    */ 
/*    */   public void setNickName(String nickName) {
/* 76 */     this.nickName = nickName;
/*    */   }
/*    */ 
/*    */   public String getDeviceAddress() {
/* 80 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 84 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */   public String getState() {
/* 87 */     return this.state;
/*    */   }
/*    */ 
/*    */   public void setState(String state) {
/* 91 */     this.state = state;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoControlEnclosure
 * JD-Core Version:    0.6.2
 */