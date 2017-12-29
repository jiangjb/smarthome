/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoAirBindingPanel
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private String panelAddress;
/*    */   private String deviceAddress;
/*    */   private String patternType;
/*    */   private String logoWhetherIsBound;
/*    */ 
/*    */   public BoAirBindingPanel()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoAirBindingPanel(BoUsers boUsers, BoDevice boDevice, String panelAddress, String deviceAddress, String patternType, String logoWhetherIsBound)
/*    */   {
/* 32 */     this.boUsers = boUsers;
/* 33 */     this.boDevice = boDevice;
/* 34 */     this.panelAddress = panelAddress;
/* 35 */     this.deviceAddress = deviceAddress;
/* 36 */     this.patternType = patternType;
/* 37 */     this.logoWhetherIsBound = logoWhetherIsBound;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 44 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 48 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 52 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 56 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 60 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 64 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getPanelAddress() {
/* 68 */     return this.panelAddress;
/*    */   }
/*    */ 
/*    */   public void setPanelAddress(String panelAddress) {
/* 72 */     this.panelAddress = panelAddress;
/*    */   }
/*    */ 
/*    */   public String getDeviceAddress() {
/* 76 */     return this.deviceAddress;
/*    */   }
/*    */ 
/*    */   public void setDeviceAddress(String deviceAddress) {
/* 80 */     this.deviceAddress = deviceAddress;
/*    */   }
/*    */ 
/*    */   public String getPatternType() {
/* 84 */     return this.patternType;
/*    */   }
/*    */ 
/*    */   public void setPatternType(String patternType) {
/* 88 */     this.patternType = patternType;
/*    */   }
/*    */ 
/*    */   public String getLogoWhetherIsBound() {
/* 92 */     return this.logoWhetherIsBound;
/*    */   }
/*    */ 
/*    */   public void setLogoWhetherIsBound(String logoWhetherIsBound) {
/* 96 */     this.logoWhetherIsBound = logoWhetherIsBound;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoAirBindingPanel
 * JD-Core Version:    0.6.2
 */