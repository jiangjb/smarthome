/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BoUsers
/*     */   implements Serializable
/*     */ {
/*     */   private Integer userId;
/*     */   private String userCode;
/*     */   private String authorizationUserCode;
/*     */   private String logoAccountType;
/*     */   private String accountOperationType;
/*     */   private String userName;
/*     */   private String userSex;
/*     */   private String userAge;
/*     */   private String userPwd;
/*     */   private String userPhone;
/*     */   private String userEmail;
/*     */   private Integer phoneType;
/*     */   private String securityTotalSwitch;
/*     */   private Boolean isupdate;
/*     */   private Boolean isFirst;
/*     */   private String userAddr;
/*     */   private String headPic;
/*     */   private String cid;
/*     */   private String userDevicetoken;
/*     */   private Integer version;
/*     */   private String accessToken;
/*     */   private String accessTokenTime;
/*     */   private String refreshToken;
/*     */   private String refreshTokenTime;
/*     */   private String fluoriteAccessToken;
/*     */   private String fluoriteAccessTokenCreateTime;
/*     */   private String fluoriteAccessTokenExpireTime;
/*     */   private String signature;
/*     */   private String city;
/*     */   private String versionType;
/*     */   private Boolean whetherSetPwd;
			private String phpPasswd;//6-15
			private String salt;//6-15
/*  45 */   private Set boHosts = new HashSet(0);
/*  46 */   private Set boSimplifies = new HashSet(0);
/*  47 */   private Set boRepairses = new HashSet(0);
/*  48 */   private Set boHostDevices = new HashSet(0);
/*  49 */   private Set boDeviceStates = new HashSet(0);
/*  50 */   private Set boFeedBacks = new HashSet(0);
/*  51 */   private Set boShoppingCarts = new HashSet(0);
/*  52 */   private Set boUserDeviceses = new HashSet(0);
/*  53 */   private Set boOrders = new HashSet(0);
/*  54 */   private Set boInfraredDevices = new HashSet(0);
/*  55 */   private Set boInfraredButtonses = new HashSet(0);
/*  56 */   private Set boSensors = new HashSet(0);
/*  57 */   private Set boSensorLines = new HashSet(0);
/*  58 */   private Set boModelInfos = new HashSet(0);
/*  59 */   private Set boModels = new HashSet(0);
/*  60 */   private Set boInfraredLearnControlMaps = new HashSet(0);
/*  61 */   private Set boControlEnclosures = new HashSet(0);
/*  62 */   private Set boNetworkNumbers = new HashSet(0);
/*  63 */   private Set boChannels = new HashSet(0);
/*  64 */   private Set boLockPasswordManages = new HashSet(0);
/*  65 */   private Set boAirTimingPerforms = new HashSet(0);
/*  66 */   private Set boAirBindingPanels = new HashSet(0);
/*     */ 
/*     */   public BoUsers()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoUsers(String userCode, String authorizationUserCode, String logoAccountType, String accountOperationType, String userName, String userSex, String userAge, String userPwd, String userPhone, String userEmail, String securityTotalSwitch, Integer phoneType, Boolean isupdate, String userAddr, String headPic, String cid, String userDevicetoken, Integer version, Set boHosts, String accessToken, String accessTokenTime, String refreshToken, String refreshTokenTime, String fluoriteAccessToken, String fluoriteAccessTokenCreateTime, String fluoriteAccessTokenExpireTime, String signature, String city, Set boSimplifies, Set boHostDevices, Set boDeviceStates, Set boFeedBacks, Boolean isFirst, Set boRepairses, Set boUserDeviceses, Set boShoppingCarts, Set boInfraredDevices, Set boChannels, Set boNetworkNumbers, String versionType, Set boLockPasswordManages, Set boInfraredButtonses, Set boOrders, Set boControlEnclosures, Set boModelInfos, Set boModels, Set boSensors, Boolean whetherSetPwd, Set boSensorLines, Set boInfraredLearnControlMaps, Set boAirTimingPerforms, Set boAirBindingPanels)
/*     */   {
/*  86 */     this.userCode = userCode;
/*  87 */     this.authorizationUserCode = authorizationUserCode;
/*  88 */     this.logoAccountType = logoAccountType;
/*  89 */     this.accountOperationType = accountOperationType;
/*  90 */     this.userName = userName;
/*  91 */     this.userSex = userSex;
/*  92 */     this.userAge = userAge;
/*  93 */     this.userPwd = userPwd;
/*  94 */     this.userPhone = userPhone;
/*  95 */     this.userEmail = userEmail;
/*  96 */     this.phoneType = phoneType;
/*  97 */     this.securityTotalSwitch = securityTotalSwitch;
/*  98 */     this.isupdate = isupdate;
/*  99 */     this.userAddr = userAddr;
/* 100 */     this.headPic = headPic;
/* 101 */     this.cid = cid;
/* 102 */     this.versionType = versionType;
/* 103 */     this.whetherSetPwd = whetherSetPwd;
/* 104 */     this.userDevicetoken = userDevicetoken;
/*     */ 
/* 106 */     this.version = version;
/* 107 */     this.isFirst = isFirst;
/* 108 */     this.boHosts = boHosts;
/* 109 */     this.boSimplifies = boSimplifies;
/* 110 */     this.boShoppingCarts = boShoppingCarts;
/* 111 */     this.boRepairses = boRepairses;
/* 112 */     this.boHostDevices = boHostDevices;
/* 113 */     this.boDeviceStates = boDeviceStates;
/* 114 */     this.boUserDeviceses = boUserDeviceses;
/* 115 */     this.boFeedBacks = boFeedBacks;
/* 116 */     this.accessToken = accessToken;
/* 117 */     this.accessTokenTime = accessTokenTime;
/* 118 */     this.refreshToken = refreshToken;
/* 119 */     this.refreshTokenTime = refreshTokenTime;
/* 120 */     this.fluoriteAccessToken = fluoriteAccessToken;
/* 121 */     this.boLockPasswordManages = boLockPasswordManages;
/* 122 */     this.fluoriteAccessTokenCreateTime = fluoriteAccessTokenCreateTime;
/* 123 */     this.fluoriteAccessTokenExpireTime = fluoriteAccessTokenExpireTime;
/* 124 */     this.signature = signature;
/* 125 */     this.city = city;
/* 126 */     this.boSensors = boSensors;
/* 127 */     this.boSensorLines = boSensorLines;
/* 128 */     this.boOrders = boOrders;
/* 129 */     this.boInfraredDevices = boInfraredDevices;
/* 130 */     this.boInfraredButtonses = boInfraredButtonses;
/* 131 */     this.boControlEnclosures = boControlEnclosures;
/* 132 */     this.boModels = boModels;
/* 133 */     this.boModelInfos = boModelInfos;
/* 134 */     this.boInfraredLearnControlMaps = boInfraredLearnControlMaps;
/* 135 */     this.boChannels = boChannels;
/* 136 */     this.boNetworkNumbers = boNetworkNumbers;
/* 137 */     this.boAirBindingPanels = boAirBindingPanels;
/* 138 */     this.boAirTimingPerforms = boAirTimingPerforms;
/*     */   }
			//6-15
			public String getPhpPasswd() {
				return phpPasswd;
			}
			public void setPhpPasswd(String phpPasswd) {
				this.phpPasswd = phpPasswd;
			}			
			public String getSalt() {
				return salt;
			}
			public void setSalt(String salt) {
				this.salt = salt;
			}
			
/*     */   public Integer getUserId()
/*     */   {
/* 147 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId) {
/* 151 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/* 155 */     return this.userCode;
/*     */   }
/*     */ 
/*     */   public void setUserCode(String userCode) {
/* 159 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public String getAuthorizationUserCode() {
/* 163 */     return this.authorizationUserCode;
/*     */   }
/*     */ 
/*     */   public void setAuthorizationUserCode(String authorizationUserCode) {
/* 167 */     this.authorizationUserCode = authorizationUserCode;
/*     */   }
/*     */ 
/*     */   public String getUserEmail() {
/* 171 */     return this.userEmail;
/*     */   }
/*     */ 
/*     */   public void setUserEmail(String userEmail) {
/* 175 */     this.userEmail = userEmail;
/*     */   }
/*     */ 
/*     */   public String getLogoAccountType() {
/* 179 */     return this.logoAccountType;
/*     */   }
/*     */ 
/*     */   public void setLogoAccountType(String logoAccountType) {
/* 183 */     this.logoAccountType = logoAccountType;
/*     */   }
/*     */ 
/*     */   public String getAccountOperationType() {
/* 187 */     return this.accountOperationType;
/*     */   }
/*     */ 
/*     */   public void setAccountOperationType(String accountOperationType) {
/* 191 */     this.accountOperationType = accountOperationType;
/*     */   }
/*     */   public Boolean getIsFirst() {
/* 194 */     return this.isFirst;
/*     */   }
/*     */ 
/*     */   public void setIsFirst(Boolean isFirst) {
/* 198 */     this.isFirst = isFirst;
/*     */   }
/*     */   public String getSecurityTotalSwitch() {
/* 201 */     return this.securityTotalSwitch;
/*     */   }
/*     */ 
/*     */   public void setSecurityTotalSwitch(String securityTotalSwitch) {
/* 205 */     this.securityTotalSwitch = securityTotalSwitch;
/*     */   }
/*     */   public String getUserName() {
/* 208 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 212 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getVersionType() {
/* 216 */     return this.versionType;
/*     */   }
/*     */ 
/*     */   public void setVersionType(String versionType) {
/* 220 */     this.versionType = versionType;
/*     */   }
/*     */ 
/*     */   public Boolean getWhetherSetPwd() {
/* 224 */     return this.whetherSetPwd;
/*     */   }
/*     */ 
/*     */   public void setWhetherSetPwd(Boolean whetherSetPwd) {
/* 228 */     this.whetherSetPwd = whetherSetPwd;
/*     */   }
/*     */ 
/*     */   public Set getBoRepairses() {
/* 232 */     return this.boRepairses;
/*     */   }
/*     */ 
/*     */   public Set getBoControlEnclosures() {
/* 236 */     return this.boControlEnclosures;
/*     */   }
/*     */ 
/*     */   public void setBoControlEnclosures(Set boControlEnclosures) {
/* 240 */     this.boControlEnclosures = boControlEnclosures;
/*     */   }
/*     */ 
/*     */   public Set getBoSensorLines() {
/* 244 */     return this.boSensorLines;
/*     */   }
/*     */ 
/*     */   public void setBoSensorLines(Set boSensorLines) {
/* 248 */     this.boSensorLines = boSensorLines;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredLearnControlMaps() {
/* 252 */     return this.boInfraredLearnControlMaps;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredLearnControlMaps(Set boInfraredLearnControlMaps) {
/* 256 */     this.boInfraredLearnControlMaps = boInfraredLearnControlMaps;
/*     */   }
/*     */ 
/*     */   public Set getBoSensors()
/*     */   {
/* 262 */     return this.boSensors;
/*     */   }
/*     */ 
/*     */   public Set getBoLockPasswordManages() {
/* 266 */     return this.boLockPasswordManages;
/*     */   }
/*     */ 
/*     */   public void setBoLockPasswordManages(Set boLockPasswordManages) {
/* 270 */     this.boLockPasswordManages = boLockPasswordManages;
/*     */   }
/*     */ 
/*     */   public void setBoSensors(Set boSensors) {
/* 274 */     this.boSensors = boSensors;
/*     */   }
/*     */ 
/*     */   public void setBoRepairses(Set boRepairses) {
/* 278 */     this.boRepairses = boRepairses;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredDevices() {
/* 282 */     return this.boInfraredDevices;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredDevices(Set boInfraredDevices) {
/* 286 */     this.boInfraredDevices = boInfraredDevices;
/*     */   }
/*     */   public String getUserSex() {
/* 289 */     return this.userSex;
/*     */   }
/*     */ 
/*     */   public void setUserSex(String userSex) {
/* 293 */     this.userSex = userSex;
/*     */   }
/*     */ 
/*     */   public Set getBoShoppingCarts() {
/* 297 */     return this.boShoppingCarts;
/*     */   }
/*     */ 
/*     */   public void setBoShoppingCarts(Set boShoppingCarts) {
/* 301 */     this.boShoppingCarts = boShoppingCarts;
/*     */   }
/*     */ 
/*     */   public Set getBoUserDeviceses() {
/* 305 */     return this.boUserDeviceses;
/*     */   }
/*     */ 
/*     */   public void setBoUserDeviceses(Set boUserDeviceses) {
/* 309 */     this.boUserDeviceses = boUserDeviceses;
/*     */   }
/*     */ 
/*     */   public String getUserAge() {
/* 313 */     return this.userAge;
/*     */   }
/*     */ 
/*     */   public void setUserAge(String userAge) {
/* 317 */     this.userAge = userAge;
/*     */   }
/*     */ 
/*     */   public String getCity() {
/* 321 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(String city) {
/* 325 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getSignature() {
/* 329 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 333 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   public Set getBoFeedBacks() {
/* 337 */     return this.boFeedBacks;
/*     */   }
/*     */ 
/*     */   public void setBoFeedBacks(Set boFeedBacks) {
/* 341 */     this.boFeedBacks = boFeedBacks;
/*     */   }
/*     */ 
/*     */   public String getUserPwd() {
/* 345 */     return this.userPwd;
/*     */   }
/*     */ 
/*     */   public void setUserPwd(String userPwd) {
/* 349 */     this.userPwd = userPwd;
/*     */   }
/*     */ 
/*     */   public String getUserPhone() {
/* 353 */     return this.userPhone;
/*     */   }
/*     */ 
/*     */   public void setUserPhone(String userPhone) {
/* 357 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public Integer getPhoneType() {
/* 361 */     return this.phoneType;
/*     */   }
/*     */ 
/*     */   public void setPhoneType(Integer phoneType) {
/* 365 */     this.phoneType = phoneType;
/*     */   }
/*     */ 
/*     */   public Boolean getIsupdate() {
/* 369 */     return this.isupdate;
/*     */   }
/*     */ 
/*     */   public void setIsupdate(Boolean isupdate) {
/* 373 */     this.isupdate = isupdate;
/*     */   }
/*     */ 
/*     */   public String getUserAddr() {
/* 377 */     return this.userAddr;
/*     */   }
/*     */ 
/*     */   public void setUserAddr(String userAddr) {
/* 381 */     this.userAddr = userAddr;
/*     */   }
/*     */ 
/*     */   public String getHeadPic() {
/* 385 */     return this.headPic;
/*     */   }
/*     */ 
/*     */   public void setHeadPic(String headPic) {
/* 389 */     this.headPic = headPic;
/*     */   }
/*     */ 
/*     */   public String getCid() {
/* 393 */     return this.cid;
/*     */   }
/*     */ 
/*     */   public void setCid(String cid) {
/* 397 */     this.cid = cid;
/*     */   }
/*     */ 
/*     */   public Integer getVersion() {
/* 401 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(Integer version) {
/* 405 */     this.version = version;
/*     */   }
/*     */ 
/*     */   public Set getBoOrders()
/*     */   {
/* 410 */     return this.boOrders;
/*     */   }
/*     */ 
/*     */   public void setBoOrders(Set boOrders) {
/* 414 */     this.boOrders = boOrders;
/*     */   }
/*     */   public String getUserDevicetoken() {
/* 417 */     return this.userDevicetoken;
/*     */   }
/*     */ 
/*     */   public void setUserDevicetoken(String userDevicetoken) {
/* 421 */     this.userDevicetoken = userDevicetoken;
/*     */   }
/*     */ 
/*     */   public Set getBoHosts() {
/* 425 */     return this.boHosts;
/*     */   }
/*     */ 
/*     */   public void setBoHosts(Set boHosts) {
/* 429 */     this.boHosts = boHosts;
/*     */   }
/*     */ 
/*     */   public Set getBoSimplifies() {
/* 433 */     return this.boSimplifies;
/*     */   }
/*     */ 
/*     */   public void setBoSimplifies(Set boSimplifies) {
/* 437 */     this.boSimplifies = boSimplifies;
/*     */   }
/*     */ 
/*     */   public Set getBoHostDevices() {
/* 441 */     return this.boHostDevices;
/*     */   }
/*     */ 
/*     */   public void setBoHostDevices(Set boHostDevices) {
/* 445 */     this.boHostDevices = boHostDevices;
/*     */   }
/*     */ 
/*     */   public Set getBoAirTimingPerforms() {
/* 449 */     return this.boAirTimingPerforms;
/*     */   }
/*     */ 
/*     */   public void setBoAirTimingPerforms(Set boAirTimingPerforms) {
/* 453 */     this.boAirTimingPerforms = boAirTimingPerforms;
/*     */   }
/*     */ 
/*     */   public Set getBoAirBindingPanels() {
/* 457 */     return this.boAirBindingPanels;
/*     */   }
/*     */ 
/*     */   public void setBoAirBindingPanels(Set boAirBindingPanels) {
/* 461 */     this.boAirBindingPanels = boAirBindingPanels;
/*     */   }
/*     */ 
/*     */   public Set getBoDeviceStates() {
/* 465 */     return this.boDeviceStates;
/*     */   }
/*     */ 
/*     */   public void setBoDeviceStates(Set boDeviceStates) {
/* 469 */     this.boDeviceStates = boDeviceStates;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredButtonses() {
/* 473 */     return this.boInfraredButtonses;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredButtonses(Set boInfraredButtonses) {
/* 477 */     this.boInfraredButtonses = boInfraredButtonses;
/*     */   }
/*     */ 
/*     */   public String getAccessToken() {
/* 481 */     return this.accessToken;
/*     */   }
/*     */ 
/*     */   public void setAccessToken(String accessToken) {
/* 485 */     this.accessToken = accessToken;
/*     */   }
/*     */ 
/*     */   public String getAccessTokenTime() {
/* 489 */     return this.accessTokenTime;
/*     */   }
/*     */ 
/*     */   public void setAccessTokenTime(String accessTokenTime) {
/* 493 */     this.accessTokenTime = accessTokenTime;
/*     */   }
/*     */ 
/*     */   public String getRefreshToken() {
/* 497 */     return this.refreshToken;
/*     */   }
/*     */ 
/*     */   public void setRefreshToken(String refreshToken) {
/* 501 */     this.refreshToken = refreshToken;
/*     */   }
/*     */ 
/*     */   public String getRefreshTokenTime()
/*     */   {
/* 507 */     return this.refreshTokenTime;
/*     */   }
/*     */ 
/*     */   public void setRefreshTokenTime(String refreshTokenTime) {
/* 511 */     this.refreshTokenTime = refreshTokenTime;
/*     */   }
/*     */   public String getFluoriteAccessToken() {
/* 514 */     return this.fluoriteAccessToken;
/*     */   }
/*     */ 
/*     */   public void setFluoriteAccessToken(String fluoriteAccessToken) {
/* 518 */     this.fluoriteAccessToken = fluoriteAccessToken;
/*     */   }
/*     */ 
/*     */   public String getFluoriteAccessTokenCreateTime() {
/* 522 */     return this.fluoriteAccessTokenCreateTime;
/*     */   }
/*     */ 
/*     */   public void setFluoriteAccessTokenCreateTime(String fluoriteAccessTokenCreateTime)
/*     */   {
/* 527 */     this.fluoriteAccessTokenCreateTime = fluoriteAccessTokenCreateTime;
/*     */   }
/*     */ 
/*     */   public String getFluoriteAccessTokenExpireTime() {
/* 531 */     return this.fluoriteAccessTokenExpireTime;
/*     */   }
/*     */ 
/*     */   public void setFluoriteAccessTokenExpireTime(String fluoriteAccessTokenExpireTime)
/*     */   {
/* 536 */     this.fluoriteAccessTokenExpireTime = fluoriteAccessTokenExpireTime;
/*     */   }
/*     */   public Set getBoModelInfos() {
/* 539 */     return this.boModelInfos;
/*     */   }
/*     */ 
/*     */   public void setBoModelInfos(Set boModelInfos) {
/* 543 */     this.boModelInfos = boModelInfos;
/*     */   }
/*     */   public Set getBoModels() {
/* 546 */     return this.boModels;
/*     */   }
/*     */ 
/*     */   public void setBoModels(Set boModels) {
/* 550 */     this.boModels = boModels;
/*     */   }
/*     */   public Set getBoChannels() {
/* 553 */     return this.boChannels;
/*     */   }
/*     */ 
/*     */   public void setBoChannels(Set boChannels) {
/* 557 */     this.boChannels = boChannels;
/*     */   }
/*     */   public Set getBoNetworkNumbers() {
/* 560 */     return this.boNetworkNumbers;
/*     */   }
/*     */ 
/*     */   public void setBoNetworkNumbers(Set boNetworkNumbers) {
/* 564 */     this.boNetworkNumbers = boNetworkNumbers;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 569 */     return "BoUsers [userId=" + this.userId + ", userCode=" + this.userCode + 
/* 570 */       ", authorizationUserCode=" + this.authorizationUserCode + 
/* 571 */       ", logoAccountType=" + this.logoAccountType + ", userName=" + 
/* 572 */       this.userName + ", userSex=" + this.userSex + ", userAge=" + this.userAge + 
/* 573 */       ", userPwd=" + this.userPwd + ", userPhone=" + this.userPhone + 
/* 574 */       ", phoneType=" + this.phoneType + ", isupdate=" + this.isupdate + 
/* 575 */       ", isFirst=" + this.isFirst + ", userAddr=" + this.userAddr + 
/* 576 */       ", headPic=" + this.headPic + ", cid=" + this.cid + 
/* 577 */       ", userDevicetoken=" + this.userDevicetoken + ", version=" + 
/* 578 */       this.version + ", accessToken=" + this.accessToken + 
/* 579 */       ", accessTokenTime=" + this.accessTokenTime + ", refreshToken=" + 
/* 580 */       this.refreshToken + ", refreshTokenTime=" + this.refreshTokenTime + 
/* 581 */       ", fluoriteAccessToken=" + this.fluoriteAccessToken + 
/* 582 */       ", fluoriteAccessTokenCreateTime=" + 
/* 583 */       this.fluoriteAccessTokenCreateTime + 
/* 584 */       ", fluoriteAccessTokenExpireTime=" + 
/* 585 */       this.fluoriteAccessTokenExpireTime + ", signature=" + this.signature + 
/* 586 */       ", city=" + this.city + ", versionType=" + this.versionType + 
/* 587 */       ", whetherSetPwd=" + this.whetherSetPwd + ", boHosts=" + this.boHosts + 
/* 588 */       ", boSimplifies=" + this.boSimplifies + ", boRepairses=" + 
/* 589 */       this.boRepairses + ", boHostDevices=" + this.boHostDevices + 
/* 590 */       ", boDeviceStates=" + this.boDeviceStates + ", boFeedBacks=" + 
/* 591 */       this.boFeedBacks + ", boShoppingCarts=" + this.boShoppingCarts + 
/* 592 */       ", boUserDeviceses=" + this.boUserDeviceses + ", boOrders=" + 
/* 593 */       this.boOrders + ", boInfraredDevices=" + this.boInfraredDevices + 
/* 594 */       ", boInfraredButtonses=" + this.boInfraredButtonses + 
/* 595 */       ", boSensors=" + this.boSensors + ", boSensorLines=" + 
/* 596 */       this.boSensorLines + ", boModelInfos=" + this.boModelInfos + 
/* 597 */       ", boModels=" + this.boModels + ", boInfraredLearnControlMaps=" + 
/* 598 */       this.boInfraredLearnControlMaps + ", boControlEnclosures=" + 
/* 599 */       this.boControlEnclosures + ", boNetworkNumbers=" + 
/* 600 */       this.boNetworkNumbers + ", boChannels=" + this.boChannels + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUsers
 * JD-Core Version:    0.6.2
 */