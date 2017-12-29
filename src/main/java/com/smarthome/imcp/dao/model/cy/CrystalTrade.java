/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CrystalTrade
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUser boUser;
/*     */   private BoDevice boDevice;
/*     */   private Integer water;
/*     */   private Integer crystal;
/*     */   private Date tradeTime;
/*     */   private Integer oldTotalWater;
/*     */   private Integer newTotalWater;
/*     */   private String userName;
/*     */   private String deviceName;
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUser getBoUser() {
/*  45 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser) {
/*  49 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  53 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  57 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public Integer getWater() {
/*  61 */     return this.water;
/*     */   }
/*     */ 
/*     */   public void setWater(Integer water) {
/*  65 */     this.water = water;
/*     */   }
/*     */ 
/*     */   public Integer getCrystal() {
/*  69 */     return this.crystal;
/*     */   }
/*     */ 
/*     */   public void setCrystal(Integer crystal) {
/*  73 */     this.crystal = crystal;
/*     */   }
/*     */ 
/*     */   public Date getTradeTime() {
/*  77 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(Date tradeTime) {
/*  81 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   public Integer getOldTotalWater() {
/*  85 */     return this.oldTotalWater;
/*     */   }
/*     */ 
/*     */   public void setOldTotalWater(Integer oldTotalWater) {
/*  89 */     this.oldTotalWater = oldTotalWater;
/*     */   }
/*     */ 
/*     */   public Integer getNewTotalWater() {
/*  93 */     return this.newTotalWater;
/*     */   }
/*     */ 
/*     */   public void setNewTotalWater(Integer newTotalWater) {
/*  97 */     this.newTotalWater = newTotalWater;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 101 */     this.userName = this.boUser.getUserName();
/* 102 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 106 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getDeviceName() {
/* 110 */     this.deviceName = this.boDevice.getDeviceName();
/* 111 */     return this.deviceName;
/*     */   }
/*     */ 
/*     */   public void setDeviceName(String deviceName) {
/* 115 */     this.deviceName = deviceName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.CrystalTrade
 * JD-Core Version:    0.6.2
 */