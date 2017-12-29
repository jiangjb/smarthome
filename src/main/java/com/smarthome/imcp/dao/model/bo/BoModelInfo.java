/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoModelInfo
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private BoDevice boDevice;
/*     */   private BoModel boModel;
/*     */   private String modelId;
/*     */   private String deviceAddress;
/*     */   private String deviceType;
/*     */   private String controlCommand;
/*     */   private Integer delayValues;
/*     */   private Boolean flay;
/*     */ 
/*     */   public BoModelInfo()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoModelInfo(BoUsers boUsers, BoDevice boDevice, BoModel boModel, String deviceAddress, String deviceType, String controlCommand, Integer delayValues, String modelId, Boolean flay)
/*     */   {
/*  30 */     this.boUsers = boUsers;
/*  31 */     this.boDevice = boDevice;
/*  32 */     this.boModel = boModel;
/*  33 */     this.deviceAddress = deviceAddress;
/*  34 */     this.deviceType = deviceType;
/*  35 */     this.controlCommand = controlCommand;
/*  36 */     this.delayValues = delayValues;
/*  37 */     this.modelId = modelId;
/*  38 */     this.flay = flay;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  44 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  48 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  52 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  56 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  60 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  64 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public BoModel getBoModel() {
/*  68 */     return this.boModel;
/*     */   }
/*     */ 
/*     */   public void setBoModel(BoModel boModel) {
/*  72 */     this.boModel = boModel;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/*  76 */     return this.deviceAddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceAddress(String deviceAddress) {
/*  80 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public String getModelId()
/*     */   {
/*  85 */     return this.modelId;
/*     */   }
/*     */ 
/*     */   public void setModelId(String modelId) {
/*  89 */     this.modelId = modelId;
/*     */   }
/*     */ 
/*     */   public Boolean getFlay() {
/*  93 */     return this.flay;
/*     */   }
/*     */ 
/*     */   public void setFlay(Boolean flay) {
/*  97 */     this.flay = flay;
/*     */   }
/*     */ 
/*     */   public String getDeviceType() {
/* 101 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(String deviceType) {
/* 105 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public String getControlCommand() {
/* 109 */     return this.controlCommand;
/*     */   }
/*     */ 
/*     */   public void setControlCommand(String controlCommand) {
/* 113 */     this.controlCommand = controlCommand;
/*     */   }
/*     */ 
/*     */   public Integer getDelayValues() {
/* 117 */     return this.delayValues;
/*     */   }
/*     */ 
/*     */   public void setDelayValues(Integer delayValues) {
/* 121 */     this.delayValues = delayValues;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoModelInfo
 * JD-Core Version:    0.6.2
 */