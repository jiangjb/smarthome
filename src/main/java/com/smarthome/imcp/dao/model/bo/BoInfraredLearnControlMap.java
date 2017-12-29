/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoInfraredLearnControlMap
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String deviceAddress;
/*    */   private String originalValue;
/*    */   private String changeValue;
/*    */   private String mntDelete;
/*    */ 
/*    */   public BoInfraredLearnControlMap()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoInfraredLearnControlMap(BoUsers boUsers, BoDevice boDevice, String deviceAddress, String originalValue, String changeValue, String mntDelete)
/*    */   {
/* 29 */     this.boUsers = boUsers;
/* 30 */     this.boDevice = boDevice;
/* 31 */     this.deviceAddress = deviceAddress;
/* 32 */     this.originalValue = originalValue;
/* 33 */     this.changeValue = changeValue;
/* 34 */     this.mntDelete = mntDelete;
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
/*    */   public BoUsers getBoUsers() {
/* 48 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 52 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 56 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 60 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getDeviceAddress() {
/* 64 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 68 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */ 
/*    */   public String getOriginalValue() {
/* 72 */     return this.originalValue;
/*    */   }
/*    */ 
/*    */   public void setOriginalValue(String originalValue) {
/* 76 */     this.originalValue = originalValue;
/*    */   }
/*    */ 
/*    */   public String getChangeValue() {
/* 80 */     return this.changeValue;
/*    */   }
/*    */ 
/*    */   public void setChangeValue(String changeValue) {
/* 84 */     this.changeValue = changeValue;
/*    */   }
/*    */ 
/*    */   public String getMntDelete() {
/* 88 */     return this.mntDelete;
/*    */   }
/*    */ 
/*    */   public void setMntDelete(String mntDelete) {
/* 92 */     this.mntDelete = mntDelete;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap
 * JD-Core Version:    0.6.2
 */