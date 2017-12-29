/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoInfraredDevice
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private BoDevice boDevice;
/*     */   private BoRoom boRoom;
/*     */   private String deviceType;
/*     */   private String nickName;
/*     */   private String deviceAddress;
/*     */   private String validationCode;
/*     */   private Integer num;
/*     */   private String numName;
/*     */   private Integer icoId;
/*     */   private String ico;
/*     */   private Boolean deviceClassify;
/*     */   private Integer isStudy;
/*     */   private String mntDelete;
/*     */ 
/*     */   public BoInfraredDevice()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoInfraredDevice(BoUsers boUsers, BoDevice boDevice, BoRoom boRoom, String deviceType, String nickName, String deviceAddress, Integer num, String numName, Integer icoId, String ico, Boolean deviceClassify, Integer isStudy, String validationCode, String mntDelete)
/*     */   {
/*  38 */     this.boUsers = boUsers;
/*  39 */     this.boDevice = boDevice;
/*  40 */     this.boRoom = boRoom;
/*  41 */     this.deviceType = deviceType;
/*  42 */     this.nickName = nickName;
/*  43 */     this.deviceAddress = deviceAddress;
/*  44 */     this.validationCode = validationCode;
/*  45 */     this.num = num;
/*  46 */     this.numName = numName;
/*  47 */     this.icoId = icoId;
/*  48 */     this.ico = ico;
/*  49 */     this.deviceClassify = deviceClassify;
/*  50 */     this.isStudy = isStudy;
/*  51 */     this.mntDelete = mntDelete;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  61 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  65 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  69 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  73 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  77 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public BoRoom getBoRoom() {
/*  81 */     return this.boRoom;
/*     */   }
/*     */ 
/*     */   public void setBoRoom(BoRoom boRoom) {
/*  85 */     this.boRoom = boRoom;
/*     */   }
/*     */ 
/*     */   public String getDeviceType() {
/*  89 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(String deviceType) {
/*  93 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public String getNickName() {
/*  97 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   public void setNickName(String nickName) {
/* 101 */     this.nickName = nickName;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/* 105 */     return this.deviceAddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceAddress(String deviceAddress) {
/* 109 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public String getValidationCode() {
/* 113 */     return this.validationCode;
/*     */   }
/*     */ 
/*     */   public void setValidationCode(String validationCode) {
/* 117 */     this.validationCode = validationCode;
/*     */   }
/*     */ 
/*     */   public Integer getNum() {
/* 121 */     return this.num;
/*     */   }
/*     */ 
/*     */   public void setNum(Integer num) {
/* 125 */     this.num = num;
/*     */   }
/*     */ 
/*     */   public String getNumName() {
/* 129 */     return this.numName;
/*     */   }
/*     */ 
/*     */   public void setNumName(String numName) {
/* 133 */     this.numName = numName;
/*     */   }
/*     */ 
/*     */   public Integer getIcoId() {
/* 137 */     return this.icoId;
/*     */   }
/*     */ 
/*     */   public void setIcoId(Integer icoId) {
/* 141 */     this.icoId = icoId;
/*     */   }
/*     */ 
/*     */   public String getIco() {
/* 145 */     return this.ico;
/*     */   }
/*     */ 
/*     */   public void setIco(String ico) {
/* 149 */     this.ico = ico;
/*     */   }
/*     */ 
/*     */   public Boolean getDeviceClassify() {
/* 153 */     return this.deviceClassify;
/*     */   }
/*     */ 
/*     */   public void setDeviceClassify(Boolean deviceClassify) {
/* 157 */     this.deviceClassify = deviceClassify;
/*     */   }
/*     */ 
/*     */   public Integer getIsStudy() {
/* 161 */     return this.isStudy;
/*     */   }
/*     */ 
/*     */   public void setIsStudy(Integer isStudy) {
/* 165 */     this.isStudy = isStudy;
/*     */   }
/*     */ 
/*     */   public String getMntDelete() {
/* 169 */     return this.mntDelete;
/*     */   }
/*     */ 
/*     */   public void setMntDelete(String mntDelete) {
/* 173 */     this.mntDelete = mntDelete;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoInfraredDevice
 * JD-Core Version:    0.6.2
 */