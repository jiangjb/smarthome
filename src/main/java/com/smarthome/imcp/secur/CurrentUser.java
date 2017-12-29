/*     */ package com.smarthome.imcp.secur;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CurrentUser
/*     */   implements Serializable
/*     */ {
/*     */   private Integer userId;
/*     */   private String userType;
/*     */   private String userTypeName;
/*     */   private String userName;
/*  14 */   private int navMenuCount = 0;
/*     */   private Integer factoryId;
/*     */   private String menuRole;
/*     */   private Long roleId;
/*     */   private Integer regionId;
/*     */   private String regionName;
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
/*     */   private String regionType;
/*     */   private String politicalType;
/*     */ 
/*     */   public Integer getUserId()
/*     */   {
/*  45 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId) {
/*  49 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public String getUserType() {
/*  53 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(String userType) {
/*  57 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public String getUserTypeName() {
/*  61 */     return this.userTypeName;
/*     */   }
/*     */ 
/*     */   public void setUserTypeName(String userTypeName) {
/*  65 */     this.userTypeName = userTypeName;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/*  69 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/*  73 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public int getNavMenuCount() {
/*  77 */     return this.navMenuCount;
/*     */   }
/*     */ 
/*     */   public void setNavMenuCount(int navMenuCount) {
/*  81 */     this.navMenuCount = navMenuCount;
/*     */   }
/*     */ 
/*     */   public String getMenuRole() {
/*  85 */     return this.menuRole;
/*     */   }
/*     */ 
/*     */   public void setMenuRole(String menuRole) {
/*  89 */     this.menuRole = menuRole;
/*     */   }
/*     */ 
/*     */   public Long getRoleId() {
/*  93 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   public void setRoleId(Long roleId) {
/*  97 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */   public Integer getRegionId() {
/* 101 */     return this.regionId;
/*     */   }
/*     */ 
/*     */   public void setRegionId(Integer regionId) {
/* 105 */     this.regionId = regionId;
/*     */   }
/*     */ 
/*     */   public String getRegionName() {
/* 109 */     return this.regionName;
/*     */   }
/*     */ 
/*     */   public void setRegionName(String regionName) {
/* 113 */     this.regionName = regionName;
/*     */   }
/*     */ 
/*     */   public String getRegionType() {
/* 117 */     return this.regionType;
/*     */   }
/*     */ 
/*     */   public void setRegionType(String regionType) {
/* 121 */     this.politicalType = (this.regionType = regionType);
/*     */   }
/*     */ 
/*     */   public String getPoliticalType() {
/* 125 */     return this.politicalType;
/*     */   }
/*     */ 
/*     */   public void setPoliticalType(String politicalType) {
/* 129 */     this.politicalType = (this.regionType = politicalType);
/*     */   }
/*     */ 
/*     */   public Integer getProvinceId() {
/* 133 */     return this.provinceId;
/*     */   }
/*     */ 
/*     */   public void setProvinceId(Integer provinceId) {
/* 137 */     this.provinceId = provinceId;
/*     */   }
/*     */ 
/*     */   public String getProvinceName() {
/* 141 */     return this.provinceName;
/*     */   }
/*     */ 
/*     */   public void setProvinceName(String provinceName) {
/* 145 */     this.provinceName = provinceName;
/*     */   }
/*     */ 
/*     */   public Integer getCityId() {
/* 149 */     return this.cityId;
/*     */   }
/*     */ 
/*     */   public void setCityId(Integer cityId) {
/* 153 */     this.cityId = cityId;
/*     */   }
/*     */ 
/*     */   public String getCityName() {
/* 157 */     return this.cityName;
/*     */   }
/*     */ 
/*     */   public void setCityName(String cityName) {
/* 161 */     this.cityName = cityName;
/*     */   }
/*     */ 
/*     */   public Integer getCountyId() {
/* 165 */     return this.countyId;
/*     */   }
/*     */ 
/*     */   public void setCountyId(Integer countyId) {
/* 169 */     this.countyId = countyId;
/*     */   }
/*     */ 
/*     */   public String getCountyName() {
/* 173 */     return this.countyName;
/*     */   }
/*     */ 
/*     */   public void setCountyName(String countyName) {
/* 177 */     this.countyName = countyName;
/*     */   }
/*     */ 
/*     */   public Integer getTownId() {
/* 181 */     return this.townId;
/*     */   }
/*     */ 
/*     */   public void setTownId(Integer townId) {
/* 185 */     this.townId = townId;
/*     */   }
/*     */ 
/*     */   public String getTownName() {
/* 189 */     return this.townName;
/*     */   }
/*     */ 
/*     */   public void setTownName(String townName) {
/* 193 */     this.townName = townName;
/*     */   }
/*     */ 
/*     */   public Integer getVillageId() {
/* 197 */     return this.villageId;
/*     */   }
/*     */ 
/*     */   public void setVillageId(Integer villageId) {
/* 201 */     this.villageId = villageId;
/*     */   }
/*     */ 
/*     */   public String getVillageName() {
/* 205 */     return this.villageName;
/*     */   }
/*     */ 
/*     */   public void setVillageName(String villageName) {
/* 209 */     this.villageName = villageName;
/*     */   }
/*     */ 
/*     */   public Integer getOrganizeId() {
/* 213 */     return this.organizeId;
/*     */   }
/*     */ 
/*     */   public void setOrganizeId(Integer organizeId) {
/* 217 */     this.organizeId = organizeId;
/*     */   }
/*     */ 
/*     */   public String getOrganizeName() {
/* 221 */     return this.organizeName;
/*     */   }
/*     */ 
/*     */   public void setOrganizeName(String organizeName) {
/* 225 */     this.organizeName = organizeName;
/*     */   }
/*     */ 
/*     */   public String getUserRegionNames() {
/* 229 */     StringBuffer sb = new StringBuffer();
/* 230 */     if (this.provinceId != null) {
/* 231 */       sb.append(this.provinceName);
/*     */     }
/* 233 */     if (this.cityId != null) {
/* 234 */       sb.append(" ").append(this.cityName);
/*     */     }
/* 236 */     if (this.countyId != null) {
/* 237 */       sb.append(" ").append(this.countyName);
/*     */     }
/* 239 */     if (this.townId != null) {
/* 240 */       sb.append(" ").append(this.townName);
/*     */     }
/* 242 */     if (this.villageId != null) {
/* 243 */       sb.append(" ").append(this.villageName);
/*     */     }
/* 245 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public Integer getFactoryId() {
/* 249 */     return this.factoryId;
/*     */   }
/*     */ 
/*     */   public void setFactoryId(Integer factoryId) {
/* 253 */     this.factoryId = factoryId;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.secur.CurrentUser
 * JD-Core Version:    0.6.2
 */