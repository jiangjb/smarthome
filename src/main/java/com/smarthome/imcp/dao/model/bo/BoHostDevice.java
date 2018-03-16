/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BoHostDevice
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private BoIconLibrary boIconLibrary;
/*     */   private BoDevice boDevice;
/*     */   private BoRoom boRoom;
/*     */   private String nickName;
/*     */   private String ico;
/*     */   private String deviceType;
/*     */   private String deviceAddress;
/*     */   private String validationCode;
/*     */   private Integer deviceNum;
/*     */   private Boolean deviceClassify;//数据库中是bit
/*     */   private String mntDelete;
/*     */   private String whetherQueryStateSign;
/*     */   private String pushSet;
/*     */   private String state;
			private Boolean isAuthorized;//new 2018-3-12
			
/*  29 */   private Set boControlEnclosures = new HashSet(0);
/*     */ 
/*     */   public BoHostDevice()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoHostDevice(BoUsers boUsers, BoIconLibrary boIconLibrary, String validationCode, BoDevice boDevice, BoRoom boRoom, String deviceType, String nickName, String pushSet, String deviceAddress, String ico, Integer deviceNum, Boolean deviceClassify, String mntDelete, String whetherQueryStateSign, String state, Set boControlEnclosures)
/*     */   {
/*  40 */     this.boUsers = boUsers;
/*  41 */     this.boIconLibrary = boIconLibrary;
/*  42 */     this.boDevice = boDevice;
/*  43 */     this.boRoom = boRoom;
/*  44 */     this.deviceType = deviceType;
/*  45 */     this.deviceAddress = deviceAddress;
/*  46 */     this.validationCode = validationCode;
/*  47 */     this.deviceNum = deviceNum;
/*  48 */     this.nickName = nickName;
/*  49 */     this.ico = ico;
/*  50 */     this.deviceClassify = deviceClassify;
/*  51 */     this.mntDelete = mntDelete;
/*  52 */     this.pushSet = pushSet;
/*  53 */     this.state = state;
/*  54 */     this.whetherQueryStateSign = whetherQueryStateSign;
/*  55 */     this.boControlEnclosures = boControlEnclosures;
/*     */   }
/*     */   public Boolean getIsAuthorized() {
				return isAuthorized;
			}
			public void setIsAuthorized(Boolean isAuthorized) {
				this.isAuthorized = isAuthorized;
			}
/*     */   public Integer getId()
/*     */   {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  69 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  73 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoIconLibrary getBoIconLibrary() {
/*  77 */     return this.boIconLibrary;
/*     */   }
/*     */ 
/*     */   public void setBoIconLibrary(BoIconLibrary boIconLibrary) {
/*  81 */     this.boIconLibrary = boIconLibrary;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  85 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  89 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public BoRoom getBoRoom() {
/*  93 */     return this.boRoom;
/*     */   }
/*     */ 
/*     */   public void setBoRoom(BoRoom boRoom) {
/*  97 */     this.boRoom = boRoom;
/*     */   }
/*     */ 
/*     */   public String getValidationCode() {
/* 101 */     return this.validationCode;
/*     */   }
/*     */ 
/*     */   public void setValidationCode(String validationCode) {
/* 105 */     this.validationCode = validationCode;
/*     */   }
/*     */ 
/*     */   public String getIco() {
/* 109 */     return this.ico;
/*     */   }
/*     */ 
/*     */   public void setIco(String ico) {
/* 113 */     this.ico = ico;
/*     */   }
/*     */ 
/*     */   public String getDeviceType() {
/* 117 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(String deviceType) {
/* 121 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/* 125 */     return this.deviceAddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceAddress(String deviceAddress) {
/* 129 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceNum() {
/* 133 */     return this.deviceNum;
/*     */   }
/*     */ 
/*     */   public void setDeviceNum(Integer deviceNum) {
/* 137 */     this.deviceNum = deviceNum;
/*     */   }
/*     */   public String getNickName() {
/* 140 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   public void setNickName(String nickName) {
/* 144 */     this.nickName = nickName;
/*     */   }
/*     */   public Boolean getDeviceClassify() {
/* 147 */     return this.deviceClassify;
/*     */   }
/*     */ 
/*     */   public void setDeviceClassify(Boolean deviceClassify) {
/* 151 */     this.deviceClassify = deviceClassify;
/*     */   }
/*     */   public String getState() {
/* 154 */     return this.state;
/*     */   }
/*     */ 
/*     */   public void setState(String state) {
/* 158 */     this.state = state;
/*     */   }
/*     */   public String getMntDelete() {
/* 161 */     return this.mntDelete;
/*     */   }
/*     */ 
/*     */   public void setMntDelete(String mntDelete) {
/* 165 */     this.mntDelete = mntDelete;
/*     */   }
/*     */ 
/*     */   public String getWhetherQueryStateSign() {
/* 169 */     return this.whetherQueryStateSign;
/*     */   }
/*     */ 
/*     */   public void setWhetherQueryStateSign(String whetherQueryStateSign) {
/* 173 */     this.whetherQueryStateSign = whetherQueryStateSign;
/*     */   }
/*     */ 
/*     */   public String getPushSet() {
/* 177 */     return this.pushSet;
/*     */   }
/*     */ 
/*     */   public void setPushSet(String pushSet) {
/* 181 */     this.pushSet = pushSet;
/*     */   }
/*     */ 
/*     */   public Set getBoControlEnclosures() {
/* 185 */     return this.boControlEnclosures;
/*     */   }
/*     */ 
/*     */   public void setBoControlEnclosures(Set boControlEnclosures) {
/* 189 */     this.boControlEnclosures = boControlEnclosures;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoHostDevice
 * JD-Core Version:    0.6.2
 */