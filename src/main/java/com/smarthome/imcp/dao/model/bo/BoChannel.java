/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoChannel
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String channel;
/*    */   private String deviceOrHost;
/*    */ 
/*    */   public BoChannel()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoChannel(BoUsers boUsers, BoDevice boDevice, String channel, String deviceOrHost)
/*    */   {
/* 26 */     this.boUsers = boUsers;
/* 27 */     this.boDevice = boDevice;
/* 28 */     this.channel = channel;
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
/*    */   public String getChannel() {
/* 59 */     return this.channel;
/*    */   }
/*    */ 
/*    */   public void setChannel(String channel) {
/* 63 */     this.channel = channel;
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
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoChannel
 * JD-Core Version:    0.6.2
 */