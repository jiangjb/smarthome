/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoUserDevice
/*     */   implements Serializable
/*     */ {
/*     */   private Integer userDeviceId;
/*     */   private BoUser boUser;
/*     */   private BoDevice boDevice;
/*     */   private String userCode;
/*     */   private String nickName;
/*     */   private char bindingType;
/*     */ 
/*     */   public BoUserDevice()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoUserDevice(BoUser boUser, BoDevice boDevice)
/*     */   {
/*  33 */     this.boUser = boUser;
/*  34 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public Integer getUserDeviceId()
/*     */   {
/*  40 */     return this.userDeviceId;
/*     */   }
/*     */ 
/*     */   public void setUserDeviceId(Integer userDeviceId) {
/*  44 */     this.userDeviceId = userDeviceId;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoUser getBoUser() {
/*  48 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser) {
/*  52 */     this.boUser = boUser;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoDevice getBoDevice() {
/*  56 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  60 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/*  64 */     return this.boUser.getUserName();
/*     */   }
/*     */ 
/*     */   public String getDeviceName() {
/*  68 */     return this.boDevice.getDeviceName();
/*     */   }
/*     */ 
/*     */   public String getFactoryName() {
/*  72 */     return this.boDevice.getBoFactory().getFactoryName();
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/*  76 */     return this.boDevice.getDeviceCode();
/*     */   }
/*     */ 
/*     */   public Date getDeviceDate() {
/*  80 */     return this.boDevice.getDeviceDate();
/*     */   }
/*     */ 
/*     */   public String getNickName() {
/*  84 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   public void setNickName(String nickName) {
/*  88 */     this.nickName = nickName;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/*  92 */     this.userCode = this.boUser.getUserCode();
/*  93 */     return this.userCode;
/*     */   }
/*     */ 
/*     */   public void setUserCode(String userCode) {
/*  97 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public char getBindingType()
/*     */   {
/* 104 */     return this.bindingType;
/*     */   }
/*     */ 
/*     */   public void setBindingType(char bindingType)
/*     */   {
/* 111 */     this.bindingType = bindingType;
/*     */   }
/*     */ 
/*     */   public Integer getUserId() {
/* 115 */     return getBoUser().getUserId();
/*     */   }
/*     */ 
/*     */   public Integer getDeviceId() {
/* 119 */     return getBoDevice().getDeviceId();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUserDevice
 * JD-Core Version:    0.6.2
 */