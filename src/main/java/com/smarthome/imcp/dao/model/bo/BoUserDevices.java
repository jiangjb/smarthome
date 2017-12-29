/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoUserDevices
/*    */   implements Serializable
/*    */ {
/*    */   private Integer userDeviceId;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String nickName;
/*    */ 
/*    */   public BoUserDevices()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoUserDevices(BoUsers boUsers, BoDevice boDevice, String nickName)
/*    */   {
/* 24 */     this.boUsers = boUsers;
/* 25 */     this.boDevice = boDevice;
/* 26 */     this.nickName = nickName;
/*    */   }
/*    */ 
/*    */   public Integer getUserDeviceId()
/*    */   {
/* 32 */     return this.userDeviceId;
/*    */   }
/*    */ 
/*    */   public void setUserDeviceId(Integer userDeviceId) {
/* 36 */     this.userDeviceId = userDeviceId;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 40 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 44 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 48 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 52 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getNickName() {
/* 56 */     return this.nickName;
/*    */   }
/*    */ 
/*    */   public void setNickName(String nickName) {
/* 60 */     this.nickName = nickName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUserDevices
 * JD-Core Version:    0.6.2
 */