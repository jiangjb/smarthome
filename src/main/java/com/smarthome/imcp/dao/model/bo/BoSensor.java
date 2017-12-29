/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoSensor
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private BoDevice boDevice;
/*     */   private BoRoom boRoom;
/*     */   private BoModel boModel;
/*     */   private String deviceType;
/*     */   private String nickName;
/*     */   private String deviceAddress;
/*     */   private Integer deviceNum;
/*     */   private String validationCode;
/*     */   private String ico;
/*     */   private Boolean deviceClassify;
/*     */   private String startTimeOne;
/*     */   private String endTimeOne;
/*     */   private String securityOneType;
/*     */   private String startTimeTwo;
/*     */   private String endTimeTwo;
/*     */   private String securityTwoType;
/*     */   private String startTimeThree;
/*     */   private String endTimeThree;
/*     */   private String securityThreeType;
/*     */   private String securityType;
/*     */   private String type;
/*     */   private String pushContent;
/*     */ 
/*     */   public BoSensor()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoSensor(BoUsers boUsers, BoDevice boDevice, BoRoom boRoom, BoModel boModel, String deviceType, String nickName, String deviceAddress, Integer deviceNum, String validationCode, String ico, Boolean deviceClassify, String startTimeOne, String endTimeOne, String securityOneType, String startTimeTwo, String endTimeTwo, String securityTwoType, String startTimeThree, String endTimeThree, String securityThreeType, String securityType, String type, String pushContent)
/*     */   {
/*  48 */     this.boUsers = boUsers;
/*  49 */     this.boDevice = boDevice;
/*  50 */     this.boRoom = boRoom;
/*  51 */     this.boModel = boModel;
/*  52 */     this.deviceType = deviceType;
/*  53 */     this.nickName = nickName;
/*  54 */     this.deviceAddress = deviceAddress;
/*  55 */     this.deviceNum = deviceNum;
/*  56 */     this.validationCode = validationCode;
/*  57 */     this.ico = ico;
/*  58 */     this.deviceClassify = deviceClassify;
/*  59 */     this.startTimeOne = startTimeOne;
/*  60 */     this.endTimeOne = endTimeOne;
/*  61 */     this.securityOneType = securityOneType;
/*  62 */     this.startTimeTwo = startTimeTwo;
/*  63 */     this.endTimeTwo = endTimeTwo;
/*  64 */     this.securityTwoType = securityTwoType;
/*  65 */     this.startTimeThree = startTimeThree;
/*  66 */     this.endTimeThree = endTimeThree;
/*  67 */     this.securityThreeType = securityThreeType;
/*  68 */     this.securityType = securityType;
/*  69 */     this.type = type;
/*  70 */     this.pushContent = pushContent;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  77 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  85 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  89 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  93 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  97 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public BoRoom getBoRoom() {
/* 101 */     return this.boRoom;
/*     */   }
/*     */ 
/*     */   public void setBoRoom(BoRoom boRoom) {
/* 105 */     this.boRoom = boRoom;
/*     */   }
/*     */ 
/*     */   public BoModel getBoModel() {
/* 109 */     return this.boModel;
/*     */   }
/*     */ 
/*     */   public void setBoModel(BoModel boModel) {
/* 113 */     this.boModel = boModel;
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
/*     */   public String getNickName() {
/* 125 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   public void setNickName(String nickName) {
/* 129 */     this.nickName = nickName;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/* 133 */     return this.deviceAddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceAddress(String deviceAddress) {
/* 137 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceNum() {
/* 141 */     return this.deviceNum;
/*     */   }
/*     */ 
/*     */   public void setDeviceNum(Integer deviceNum) {
/* 145 */     this.deviceNum = deviceNum;
/*     */   }
/*     */ 
/*     */   public String getValidationCode() {
/* 149 */     return this.validationCode;
/*     */   }
/*     */ 
/*     */   public void setValidationCode(String validationCode) {
/* 153 */     this.validationCode = validationCode;
/*     */   }
/*     */ 
/*     */   public String getIco() {
/* 157 */     return this.ico;
/*     */   }
/*     */ 
/*     */   public void setIco(String ico) {
/* 161 */     this.ico = ico;
/*     */   }
/*     */ 
/*     */   public Boolean getDeviceClassify() {
/* 165 */     return this.deviceClassify;
/*     */   }
/*     */ 
/*     */   public void setDeviceClassify(Boolean deviceClassify) {
/* 169 */     this.deviceClassify = deviceClassify;
/*     */   }
/*     */ 
/*     */   public String getStartTimeOne() {
/* 173 */     return this.startTimeOne;
/*     */   }
/*     */ 
/*     */   public void setStartTimeOne(String startTimeOne) {
/* 177 */     this.startTimeOne = startTimeOne;
/*     */   }
/*     */ 
/*     */   public String getEndTimeOne() {
/* 181 */     return this.endTimeOne;
/*     */   }
/*     */ 
/*     */   public void setEndTimeOne(String endTimeOne) {
/* 185 */     this.endTimeOne = endTimeOne;
/*     */   }
/*     */ 
/*     */   public String getSecurityOneType() {
/* 189 */     return this.securityOneType;
/*     */   }
/*     */ 
/*     */   public void setSecurityOneType(String securityOneType) {
/* 193 */     this.securityOneType = securityOneType;
/*     */   }
/*     */ 
/*     */   public String getStartTimeTwo() {
/* 197 */     return this.startTimeTwo;
/*     */   }
/*     */ 
/*     */   public void setStartTimeTwo(String startTimeTwo) {
/* 201 */     this.startTimeTwo = startTimeTwo;
/*     */   }
/*     */ 
/*     */   public String getEndTimeTwo() {
/* 205 */     return this.endTimeTwo;
/*     */   }
/*     */ 
/*     */   public void setEndTimeTwo(String endTimeTwo) {
/* 209 */     this.endTimeTwo = endTimeTwo;
/*     */   }
/*     */ 
/*     */   public String getSecurityTwoType() {
/* 213 */     return this.securityTwoType;
/*     */   }
/*     */ 
/*     */   public void setSecurityTwoType(String securityTwoType) {
/* 217 */     this.securityTwoType = securityTwoType;
/*     */   }
/*     */ 
/*     */   public String getStartTimeThree() {
/* 221 */     return this.startTimeThree;
/*     */   }
/*     */ 
/*     */   public void setStartTimeThree(String startTimeThree) {
/* 225 */     this.startTimeThree = startTimeThree;
/*     */   }
/*     */ 
/*     */   public String getEndTimeThree() {
/* 229 */     return this.endTimeThree;
/*     */   }
/*     */ 
/*     */   public void setEndTimeThree(String endTimeThree) {
/* 233 */     this.endTimeThree = endTimeThree;
/*     */   }
/*     */ 
/*     */   public String getSecurityThreeType() {
/* 237 */     return this.securityThreeType;
/*     */   }
/*     */ 
/*     */   public void setSecurityThreeType(String securityThreeType) {
/* 241 */     this.securityThreeType = securityThreeType;
/*     */   }
/*     */ 
/*     */   public String getSecurityType() {
/* 245 */     return this.securityType;
/*     */   }
/*     */ 
/*     */   public void setSecurityType(String securityType) {
/* 249 */     this.securityType = securityType;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 254 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 259 */     this.type = type;
/*     */   }
/*     */   public String getPushContent() {
/* 262 */     return this.pushContent;
/*     */   }
/*     */ 
/*     */   public void setPushContent(String pushContent) {
/* 266 */     this.pushContent = pushContent;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoSensor
 * JD-Core Version:    0.6.2
 */