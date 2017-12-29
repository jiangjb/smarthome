/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoNetworkNumber
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String networkNumber;
/*    */   private String deviceOrHost;
/*    */ 
/*    */   public BoNetworkNumber()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoNetworkNumber(BoUsers boUsers, BoDevice boDevice, String networkNumber, String deviceOrHost)
/*    */   {
/* 26 */     this.boUsers = boUsers;
/* 27 */     this.boDevice = boDevice;
/* 28 */     this.networkNumber = networkNumber;
/* 29 */     this.deviceOrHost = deviceOrHost;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 35 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 39 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 43 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 47 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 51 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 55 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getNetworkNumber() {
/* 59 */     return this.networkNumber;
/*    */   }
/*    */ 
/*    */   public void setNetworkNumber(String networkNumber) {
/* 63 */     this.networkNumber = networkNumber;
/*    */   }
/*    */ 
/*    */   public String getDeviceOrHost() {
/* 67 */     return this.deviceOrHost;
/*    */   }
/*    */ 
/*    */   public void setDeviceOrHost(String deviceOrHost) {
/* 71 */     this.deviceOrHost = deviceOrHost;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoNetworkNumber
 * JD-Core Version:    0.6.2
 */