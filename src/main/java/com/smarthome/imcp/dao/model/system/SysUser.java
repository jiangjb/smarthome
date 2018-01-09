/*     */ package com.smarthome.imcp.dao.model.system;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import java.util.Date;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ @SuppressWarnings("serial")
			public class SysUser extends AbstractData
/*     */ {
/*     */   private SysMenuRole sysMenuRole;
/*     */   private Integer userId;
/*     */   private String userName;
/*     */   private String loginName;
/*     */   private String loginPwd;
			private String email;
/*     */   private String confirmLoginPwd;
/*     */   private String userType;
/*     */   private String userTypeName;
/*     */   private String menuRoleName;
/*     */   private Integer factoryId;
/*     */   private Integer bscUserId;
/*     */   private String userCard;
/*     */   private String userSex;
/*     */   private String userSexName;
/*     */   private String userPhone;
/*     */   private Date userBirthday;
/*     */   private String userRmk;
/*     */   private Integer regionId;
/*     */   private String regionName;
/*     */   private String regionType;
/*     */   private Integer provinceId;
/*     */   private String provinceName;
/*     */   private Integer cityId;
/*     */   private String cityName;
/*     */   private Integer countyId;
/*     */   private String countyName;
/*     */   private Integer townId;
/*     */   private String townName;
/*     */   private Integer villageId;
/*     */   private String villageName;
/*     */   private Integer organizeId;
/*     */   private String organizeName;
/*     */   private Integer deptId;
/*     */   private String deptName;
/*     */ 
/*     */   public SysUser()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SysUser(Integer userId)
/*     */   {
/*  68 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public SysUser(String userName, String loginName, String loginPwd, String userType)
/*     */   {
/*  74 */     this.userName = userName;
/*  75 */     this.loginName = loginName;
/*  76 */     this.loginPwd = loginPwd;
/*  77 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public SysUser(Integer userId, String userName, String loginName, String userType, String menuRoleCode, String menuRoleName, String userCard, String userSex, String regionType, Integer provinceId, String provinceName, Integer cityId, String cityName, Integer countyId, String countyName, Integer townId, String townName, Integer villageId, String villageName, Integer organizeId, String organizeName, Integer deptId, String deptName, String userTypeName)
/*     */   {
/*  85 */     this.userId = userId;
/*  86 */     this.userName = userName;
/*  87 */     this.loginName = loginName;
/*  88 */     this.userType = userType;
/*  89 */     this.sysMenuRole = new SysMenuRole();
/*  90 */     this.sysMenuRole.setMenuRoleCode(menuRoleCode);
/*  91 */     this.sysMenuRole.setMenuRoleName(menuRoleName);
/*  92 */     this.menuRoleName = menuRoleName;
/*  93 */     this.userCard = userCard;
/*  94 */     this.userSex = userSex;
/*  95 */     this.regionType = regionType;
/*  96 */     this.provinceId = provinceId;
/*  97 */     this.provinceName = provinceName;
/*  98 */     this.cityId = cityId;
/*  99 */     this.cityName = cityName;
/* 100 */     this.countyId = countyId;
/* 101 */     this.countyName = countyName;
/* 102 */     this.townId = townId;
/* 103 */     this.townName = townName;
/* 104 */     this.villageId = villageId;
/* 105 */     this.villageName = villageName;
/* 106 */     if (villageId != null) {
/* 107 */       this.regionId = villageId;
/* 108 */       this.regionName = villageName;
/* 109 */     } else if (townId != null) {
/* 110 */       this.regionId = townId;
/* 111 */       this.regionName = townName;
/* 112 */     } else if (countyId != null) {
/* 113 */       this.regionId = countyId;
/* 114 */       this.regionName = countyName;
/* 115 */     } else if (cityId != null) {
/* 116 */       this.regionId = cityId;
/* 117 */       this.regionName = cityName;
/* 118 */     } else if (provinceId != null) {
/* 119 */       this.regionId = provinceId;
/* 120 */       this.regionName = provinceName;
/*     */     }
/*     */ 
/* 123 */     this.organizeId = organizeId;
/* 124 */     this.organizeName = organizeName;
/* 125 */     this.deptId = deptId;
/* 126 */     this.deptName = deptName;
/* 127 */     this.userTypeName = SysParamCacheManager.getInstance()
/* 128 */       .getSysParam("USER_TYPE", userTypeName)
/* 129 */       .getParamName();
/*     */   }
/*     */ 
/*     */   @JSON(serialize=false)
/*     */   public SysMenuRole getSysMenuRole()
/*     */   {
/* 136 */     return this.sysMenuRole;
/*     */   }
/*     */ 
/*     */   public void setSysMenuRole(SysMenuRole sysMenuRole) {
/* 140 */     this.sysMenuRole = sysMenuRole;
/*     */   }
/*     */ 
/*     */   public Integer getUserId() {
/* 144 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId) {
/* 148 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 152 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 156 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getLoginName() {
/* 160 */     return this.loginName;
/*     */   }
/*     */ 
/*     */   public void setLoginName(String loginName) {
/* 164 */     this.loginName = loginName;
/*     */   }
/*     */ 
/*     */   public String getLoginPwd() {
/* 168 */     return this.loginPwd;
/*     */   }
/*     */ 
/*     */   public void setLoginPwd(String loginPwd) {
/* 172 */     this.loginPwd = loginPwd;
/*     */   }
/*     */   public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
/*     */   public String getConfirmLoginPwd() {
/* 176 */     return this.confirmLoginPwd;
/*     */   }
/*     */ 
/*     */   public void setConfirmLoginPwd(String confirmLoginPwd) {
/* 180 */     this.confirmLoginPwd = confirmLoginPwd;
/*     */   }
/*     */ 
/*     */   public String getUserType() {
/* 184 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(String userType) {
/* 188 */     this.userType = userType;
/* 189 */     this.userTypeName = SysParamCacheManager.getInstance()
/* 190 */       .getSysParam("USER_TYPE", userType)
/* 191 */       .getParamName();
/*     */   }
/*     */ 
/*     */   public String getUserTypeName() {
/* 195 */     return this.userTypeName;
/*     */   }
/*     */ 
/*     */   public void setUserTypeName(String userTypeName) {
/* 199 */     this.userTypeName = userTypeName;
/*     */   }
/*     */ 
/*     */   public String getMenuRoleName() {
/* 203 */     this.menuRoleName = (this.sysMenuRole != null ? this.sysMenuRole.getMenuRoleName() : "");
/*     */ 
/* 205 */     return this.menuRoleName;
/*     */   }
/*     */ 
/*     */   public void setMenuRoleName(String menuRoleName) {
/* 209 */     this.menuRoleName = menuRoleName;
/*     */   }
/*     */ 
/*     */   public String getRegionType() {
/* 213 */     return this.regionType;
/*     */   }
/*     */ 
/*     */   public void setRegionType(String regionType) {
/* 217 */     this.regionType = regionType;
/*     */   }
/*     */ 
/*     */   public Integer getProvinceId() {
/* 221 */     return this.provinceId;
/*     */   }
/*     */ 
/*     */   public void setProvinceId(Integer provinceId) {
/* 225 */     this.provinceId = provinceId;
/*     */   }
/*     */ 
/*     */   public String getProvinceName() {
/* 229 */     return this.provinceName;
/*     */   }
/*     */ 
/*     */   public void setProvinceName(String provinceName) {
/* 233 */     this.provinceName = provinceName;
/*     */   }
/*     */ 
/*     */   public Integer getCityId() {
/* 237 */     return this.cityId;
/*     */   }
/*     */ 
/*     */   public void setCityId(Integer cityId) {
/* 241 */     this.cityId = cityId;
/*     */   }
/*     */ 
/*     */   public String getCityName() {
/* 245 */     return this.cityName;
/*     */   }
/*     */ 
/*     */   public void setCityName(String cityName) {
/* 249 */     this.cityName = cityName;
/*     */   }
/*     */ 
/*     */   public Integer getCountyId() {
/* 253 */     return this.countyId;
/*     */   }
/*     */ 
/*     */   public void setCountyId(Integer countyId) {
/* 257 */     this.countyId = countyId;
/*     */   }
/*     */ 
/*     */   public String getCountyName() {
/* 261 */     return this.countyName;
/*     */   }
/*     */ 
/*     */   public void setCountyName(String countyName) {
/* 265 */     this.countyName = countyName;
/*     */   }
/*     */ 
/*     */   public Integer getTownId() {
/* 269 */     return this.townId;
/*     */   }
/*     */ 
/*     */   public void setTownId(Integer townId) {
/* 273 */     this.townId = townId;
/*     */   }
/*     */ 
/*     */   public String getTownName() {
/* 277 */     return this.townName;
/*     */   }
/*     */ 
/*     */   public void setTownName(String townName) {
/* 281 */     this.townName = townName;
/*     */   }
/*     */ 
/*     */   public Integer getVillageId() {
/* 285 */     return this.villageId;
/*     */   }
/*     */ 
/*     */   public void setVillageId(Integer villageId) {
/* 289 */     this.villageId = villageId;
/*     */   }
/*     */ 
/*     */   public String getVillageName() {
/* 293 */     return this.villageName;
/*     */   }
/*     */ 
/*     */   public void setVillageName(String villageName) {
/* 297 */     this.villageName = villageName;
/*     */   }
/*     */ 
/*     */   public Integer getOrganizeId() {
/* 301 */     return this.organizeId;
/*     */   }
/*     */ 
/*     */   public void setOrganizeId(Integer organizeId) {
/* 305 */     this.organizeId = organizeId;
/*     */   }
/*     */ 
/*     */   public String getOrganizeName() {
/* 309 */     return this.organizeName;
/*     */   }
/*     */ 
/*     */   public void setOrganizeName(String organizeName) {
/* 313 */     this.organizeName = organizeName;
/*     */   }
/*     */ 
/*     */   public Integer getDeptId() {
/* 317 */     return this.deptId;
/*     */   }
/*     */ 
/*     */   public void setDeptId(Integer deptId) {
/* 321 */     this.deptId = deptId;
/*     */   }
/*     */ 
/*     */   public String getDeptName() {
/* 325 */     return this.deptName;
/*     */   }
/*     */ 
/*     */   public void setDeptName(String deptName) {
/* 329 */     this.deptName = deptName;
/*     */   }
/*     */ 
/*     */   public Integer getBscUserId() {
/* 333 */     return this.bscUserId;
/*     */   }
/*     */ 
/*     */   public void setBscUserId(Integer bscUserId) {
/* 337 */     this.bscUserId = bscUserId;
/*     */   }
/*     */ 
/*     */   public String getUserCard() {
/* 341 */     return this.userCard;
/*     */   }
/*     */ 
/*     */   public void setUserCard(String userCard) {
/* 345 */     this.userCard = userCard;
/*     */   }
/*     */ 
/*     */   public String getUserSex() {
/* 349 */     return this.userSex;
/*     */   }
/*     */ 
/*     */   public void setUserSex(String userSex) {
/* 353 */     this.userSex = userSex;
/*     */   }
/*     */ 
/*     */   public String getUserSexName() {
/* 357 */     return this.userSexName;
/*     */   }
/*     */ 
/*     */   public void setUserSexName(String userSexName) {
/* 361 */     this.userSexName = userSexName;
/*     */   }
/*     */ 
/*     */   public String getUserPhone() {
/* 365 */     return this.userPhone;
/*     */   }
/*     */ 
/*     */   public void setUserPhone(String userPhone) {
/* 369 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public Date getUserBirthday() {
/* 373 */     return this.userBirthday;
/*     */   }
/*     */ 
/*     */   public void setUserBirthday(Date userBirthday) {
/* 377 */     this.userBirthday = userBirthday;
/*     */   }
/*     */ 
/*     */   public String getUserRmk() {
/* 381 */     return this.userRmk;
/*     */   }
/*     */ 
/*     */   public void setUserRmk(String userRmk) {
/* 385 */     this.userRmk = userRmk;
/*     */   }
/*     */ 
/*     */   public Integer getRegionId() {
/* 389 */     return this.regionId;
/*     */   }
/*     */ 
/*     */   public void setRegionId(Integer regionId) {
/* 393 */     this.regionId = regionId;
/*     */   }
/*     */ 
/*     */   public String getRegionName() {
/* 397 */     return this.regionName;
/*     */   }
/*     */ 
/*     */   public void setRegionName(String regionName) {
/* 401 */     this.regionName = regionName;
/*     */   }
/*     */ 
/*     */   public Integer getFactoryId() {
/* 405 */     return this.factoryId;
/*     */   }
/*     */ 
/*     */   public void setFactoryId(Integer factoryId) {
/* 409 */     this.factoryId = factoryId;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysUser
 * JD-Core Version:    0.6.2
 */