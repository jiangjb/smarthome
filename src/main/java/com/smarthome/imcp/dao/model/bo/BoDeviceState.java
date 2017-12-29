/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoDeviceState
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private BoDevice boDevice;
/*     */   private String deviceAddress;
/*     */   private Integer keyValue;
/*     */   private Integer key1;
/*     */   private Integer key2;
/*     */   private Integer key3;
/*     */ 
/*     */   public BoDeviceState()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoDeviceState(BoUsers boUsers, BoDevice boDevice, String deviceAddress, Integer keyValue, Integer key1, Integer key2, Integer key3)
/*     */   {
/*  30 */     this.boUsers = boUsers;
/*  31 */     this.boDevice = boDevice;
/*  32 */     this.deviceAddress = deviceAddress;
/*  33 */     this.keyValue = keyValue;
/*  34 */     this.key1 = key1;
/*  35 */     this.key2 = key2;
/*  36 */     this.key3 = key3;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  42 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  46 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  50 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  54 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  58 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  62 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/*  66 */     return this.deviceAddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceAddress(String deviceAddress) {
/*  70 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public Integer getKeyValue() {
/*  74 */     return this.keyValue;
/*     */   }
/*     */ 
/*     */   public void setKeyValue(Integer keyValue) {
/*  78 */     this.keyValue = keyValue;
/*     */   }
/*     */ 
/*     */   public Integer getKey1() {
/*  82 */     return this.key1;
/*     */   }
/*     */ 
/*     */   public void setKey1(Integer key1) {
/*  86 */     this.key1 = key1;
/*     */   }
/*     */ 
/*     */   public Integer getKey2() {
/*  90 */     return this.key2;
/*     */   }
/*     */ 
/*     */   public void setKey2(Integer key2) {
/*  94 */     this.key2 = key2;
/*     */   }
/*     */ 
/*     */   public Integer getKey3() {
/*  98 */     return this.key3;
/*     */   }
/*     */ 
/*     */   public void setKey3(Integer key3) {
/* 102 */     this.key3 = key3;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoDeviceState
 * JD-Core Version:    0.6.2
 */