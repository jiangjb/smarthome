/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoUser extends AbstractData
/*     */ {
/*     */   private Integer userId;
/*     */   private String userCode;
/*     */   private String userName;
/*  24 */   private String userSex = "";
/*     */   private String userPwd;
/*     */   private String userPhone;
/*     */   private Integer phoneType;
/*     */   private String userEmail;
/*     */   private String userAddr;
/*     */   private String userStatus;
/*     */   private String userIp;
/*     */   private String latitude;
/*     */   private String longitude;
/*     */   private String province;
/*     */   private String city;
/*     */   private String region;
/*     */   private String CID;
/*     */   private Integer source;
/*     */   private String token;
/*  45 */   private String headPic = "";
/*     */ 
/*  47 */   private String sign = "";
/*     */   private BoUserGroup boUserGroup;
/*     */   private Integer groupStatus;
/*     */   private String referredByUserCode;
/*     */ 
/*     */   public BoUser()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoUser(Integer userId)
/*     */   {
/*  64 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public BoUser(Integer userId, String userCode, String userName, String userSex, String userPwd, String userPhone, String userEmail, String userAddr, String userStatus)
/*     */   {
/*  72 */     this.userId = userId;
/*  73 */     this.userCode = userCode;
/*  74 */     this.userName = userName;
/*  75 */     this.userSex = userSex;
/*  76 */     this.userPwd = userPwd;
/*  77 */     this.userPhone = userPhone;
/*  78 */     this.userEmail = userEmail;
/*  79 */     this.userAddr = userAddr;
/*  80 */     this.userStatus = userStatus;
/*     */   }
/*     */ 
/*     */   public Integer getUserId()
/*     */   {
/*  86 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId) {
/*  90 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/*  94 */     return this.userCode;
/*     */   }
/*     */ 
/*     */   public void setUserCode(String userCode) {
/*  98 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 102 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 106 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getUserSex() {
/* 110 */     return this.userSex;
/*     */   }
/*     */ 
/*     */   public void setUserSex(String userSex) {
/* 114 */     this.userSex = userSex;
/*     */   }
/*     */ 
/*     */   public String getUserPwd() {
/* 118 */     return this.userPwd;
/*     */   }
/*     */ 
/*     */   public void setUserPwd(String userPwd) {
/* 122 */     this.userPwd = userPwd;
/*     */   }
/*     */ 
/*     */   public String getUserPhone() {
/* 126 */     return this.userPhone;
/*     */   }
/*     */ 
/*     */   public void setUserPhone(String userPhone) {
/* 130 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public Integer getPhoneType() {
/* 134 */     return this.phoneType;
/*     */   }
/*     */ 
/*     */   public void setPhoneType(Integer phoneType) {
/* 138 */     this.phoneType = phoneType;
/*     */   }
/*     */ 
/*     */   public String getUserEmail() {
/* 142 */     return this.userEmail;
/*     */   }
/*     */ 
/*     */   public void setUserEmail(String userEmail) {
/* 146 */     this.userEmail = userEmail;
/*     */   }
/*     */ 
/*     */   public String getUserAddr() {
/* 150 */     return this.userAddr;
/*     */   }
/*     */ 
/*     */   public void setUserAddr(String userAddr) {
/* 154 */     this.userAddr = userAddr;
/*     */   }
/*     */ 
/*     */   public String getUserStatus() {
/* 158 */     return this.userStatus;
/*     */   }
/*     */ 
/*     */   public void setUserStatus(String userStatus) {
/* 162 */     this.userStatus = userStatus;
/*     */   }
/*     */ 
/*     */   @JSON(serialize=false)
/*     */   public BoUserGroup getBoUserGroup() {
/* 167 */     return this.boUserGroup;
/*     */   }
/*     */ 
/*     */   public void setBoUserGroup(BoUserGroup boUserGroup) {
/* 171 */     this.boUserGroup = boUserGroup;
/*     */   }
/*     */ 
/*     */   public String getUserIp() {
/* 175 */     return this.userIp;
/*     */   }
/*     */ 
/*     */   public void setUserIp(String userIp) {
/* 179 */     this.userIp = userIp;
/*     */   }
/*     */ 
/*     */   public String getLatitude() {
/* 183 */     return this.latitude;
/*     */   }
/*     */ 
/*     */   public void setLatitude(String latitude) {
/* 187 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */   public String getLongitude() {
/* 191 */     return this.longitude;
/*     */   }
/*     */ 
/*     */   public void setLongitude(String longitude) {
/* 195 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */   public String getProvince() {
/* 199 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(String province) {
/* 203 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public String getCity() {
/* 207 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(String city) {
/* 211 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getRegion() {
/* 215 */     return this.region;
/*     */   }
/*     */ 
/*     */   public void setRegion(String region) {
/* 219 */     this.region = region;
/*     */   }
/*     */ 
/*     */   public Integer getGroupStatus() {
/* 223 */     return this.groupStatus;
/*     */   }
/*     */ 
/*     */   public void setGroupStatus(Integer groupStatus) {
/* 227 */     this.groupStatus = groupStatus;
/*     */   }
/*     */ 
/*     */   public String getCID() {
/* 231 */     return this.CID;
/*     */   }
/*     */ 
/*     */   public void setCID(String cID) {
/* 235 */     this.CID = cID;
/*     */   }
/*     */ 
/*     */   public Integer getSource() {
/* 239 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(Integer source) {
/* 243 */     this.source = source;
/*     */   }
/*     */ 
/*     */   public String getToken() {
/* 247 */     return this.token;
/*     */   }
/*     */ 
/*     */   public void setToken(String token) {
/* 251 */     this.token = token;
/*     */   }
/*     */ 
/*     */   public String getHeadPic() {
/* 255 */     return this.headPic;
/*     */   }
/*     */ 
/*     */   public void setHeadPic(String headPic) {
/* 259 */     this.headPic = headPic;
/*     */   }
/*     */ 
/*     */   public String getSign() {
/* 263 */     return this.sign;
/*     */   }
/*     */ 
/*     */   public void setSign(String sign) {
/* 267 */     this.sign = sign;
/*     */   }
/*     */ 
/*     */   public String getReferredByUserCode()
/*     */   {
/* 274 */     return this.referredByUserCode;
/*     */   }
/*     */ 
/*     */   public void setReferredByUserCode(String referredByUserCode)
/*     */   {
/* 281 */     this.referredByUserCode = referredByUserCode;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUser
 * JD-Core Version:    0.6.2
 */