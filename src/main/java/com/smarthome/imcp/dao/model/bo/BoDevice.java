/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoDevice extends AbstractData
/*     */ {
/*     */   private Integer deviceId;
/*     */   private BoDeviceType boDeviceType;
/*     */   private BoFactory boFactory;
/*     */   private String deviceCode;
/*     */   private String typeCode;
/*     */   private String deviceName;
/*     */   private Date deviceDate;
/*     */   private Date expDate;
/*     */   private Integer status;
/*     */   private Date buyDate;
/*     */   private String buyUser;
/*     */   private Double buyPrice;
/*     */   private String buyDdr;
/*     */   private String hostStatus;
/*     */   private String devIp;
/*     */   private String latitude;
/*     */   private String longitude;
/*     */   private String province;
/*     */   private String city;
/*     */   private String region;
/*     */   private Integer source;
/*  43 */   private Integer water = Integer.valueOf(0);
/*     */   private String address;
/*     */   private String type;
/*  47 */   private Set boLockPasswordManages = new HashSet(0);
/*  48 */   private Set boUserDevices = new HashSet(0);
/*     */ 
/*  50 */   private Set boDevicePlans = new HashSet(0);
/*     */ 
/*  52 */   private Set boSimplifies = new HashSet(0);
/*  53 */   private Set boLockVerdicts = new HashSet(0);
/*  54 */   private Set boHostDevices = new HashSet(0);
/*  55 */   private Set boControlEnclosures = new HashSet(0);
/*  56 */   private Set boDeviceStates = new HashSet(0);
/*     */ 
/*  58 */   private Set boInfraredDevices = new HashSet(0);
/*     */ 
/*  60 */   private Set boModelInfos = new HashSet(0);
/*     */ 
/*  62 */   private Set boInfraredButtonses = new HashSet(0);
/*  63 */   private Set boResendVerifications = new HashSet(0);
/*     */ 
/*  65 */   private Set boSensorLines = new HashSet(0);
/*  66 */   private Set boSensors = new HashSet(0);
/*  67 */   private Set boInfraredLearnControlMaps = new HashSet(0);
/*  68 */   private Set boNetworkNumbers = new HashSet(0);
/*  69 */   private Set boChannels = new HashSet(0);
/*  70 */   private Set boAirBindingPanels = new HashSet(0);
/*     */   private String statusName;
/*     */ 
/*     */   public BoDevice()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoDevice(BoDeviceType boDeviceType, BoFactory boFactory, String deviceCode, String typeCode, String deviceName, Date deviceDate, Date expDate, Integer status, Date buyDate, String buyUser, Double buyPrice, String buyDdr, String accessToken, String type, Set boInfraredButtonses, Set boLockVerdicts, Set boChannels, Set boNetworkNumbers, Set boControlEnclosures, String hostStatus, Set boUserDevices, Set boHostDevices, Set boDeviceStates, Set boSensorLines, Set boSensors, Set boInfraredDevices, Set boModelInfos, Set boResendVerifications, Set boLockPasswordManages, Set boInfraredLearnControlMaps, Set boAirBindingPanels)
/*     */   {
/*  91 */     this.boDeviceType = boDeviceType;
/*  92 */     this.boFactory = boFactory;
/*  93 */     this.deviceCode = deviceCode;
/*  94 */     this.typeCode = typeCode;
/*  95 */     this.deviceName = deviceName;
/*  96 */     this.deviceDate = deviceDate;
/*  97 */     this.expDate = expDate;
/*  98 */     this.status = status;
/*  99 */     this.buyDate = buyDate;
/* 100 */     this.buyUser = buyUser;
/* 101 */     this.hostStatus = hostStatus;
/* 102 */     this.buyPrice = buyPrice;
/* 103 */     this.type = type;
/* 104 */     this.buyDdr = buyDdr;
/* 105 */     this.boLockVerdicts = boLockVerdicts;
/* 106 */     this.boSensors = boSensors;
/* 107 */     this.boSensorLines = boSensorLines;
/* 108 */     this.boUserDevices = boUserDevices;
/* 109 */     this.boHostDevices = boHostDevices;
/* 110 */     this.boDeviceStates = boDeviceStates;
/* 111 */     this.boInfraredDevices = boInfraredDevices;
/* 112 */     this.boControlEnclosures = boControlEnclosures;
/* 113 */     this.boModelInfos = boModelInfos;
/* 114 */     this.boLockPasswordManages = boLockPasswordManages;
/* 115 */     this.boInfraredButtonses = boInfraredButtonses;
/* 116 */     this.boResendVerifications = boResendVerifications;
/* 117 */     this.boInfraredLearnControlMaps = boInfraredLearnControlMaps;
/* 118 */     this.boChannels = boChannels;
/* 119 */     this.boNetworkNumbers = boNetworkNumbers;
/* 120 */     this.boAirBindingPanels = boAirBindingPanels;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceId()
/*     */   {
/* 126 */     return this.deviceId;
/*     */   }
/*     */ 
/*     */   public void setDeviceId(Integer deviceId) {
/* 130 */     this.deviceId = deviceId;
/*     */   }
/*     */ 
/*     */   @JSON(serialize=false)
/*     */   public BoDeviceType getBoDeviceType() {
/* 135 */     return this.boDeviceType;
/*     */   }
/*     */ 
/*     */   public void setBoDeviceType(BoDeviceType boDeviceType) {
/* 139 */     this.boDeviceType = boDeviceType;
/*     */   }
/*     */ 
/*     */   public Set getBoLockVerdicts() {
/* 143 */     return this.boLockVerdicts;
/*     */   }
/*     */ 
/*     */   public void setBoLockVerdicts(Set boLockVerdicts) {
/* 147 */     this.boLockVerdicts = boLockVerdicts;
/*     */   }
/*     */   public Set getBoAirBindingPanels() {
/* 150 */     return this.boAirBindingPanels;
/*     */   }
/*     */ 
/*     */   public void setBoAirBindingPanels(Set boAirBindingPanels) {
/* 154 */     this.boAirBindingPanels = boAirBindingPanels;
/*     */   }
/*     */   public String getHostStatus() {
/* 157 */     return this.hostStatus;
/*     */   }
/*     */ 
/*     */   public void setHostStatus(String hostStatus) {
/* 161 */     this.hostStatus = hostStatus;
/*     */   }
/*     */ 
///*     */   public String getTypeName() {
///* 165 */     return this.boDeviceType.getTypeName();
///*     */   }
/*     */ 
/*     */   @JSON(serialize=false)
/*     */   public BoFactory getBoFactory() {
/* 170 */     return this.boFactory;
/*     */   }
/*     */ 
/*     */   public void setBoFactory(BoFactory boFactory) {
/* 174 */     this.boFactory = boFactory;
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/* 178 */     return this.deviceCode;
/*     */   }
/*     */ 
/*     */   public void setDeviceCode(String deviceCode) {
/* 182 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public String getType() {
/* 186 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/* 190 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getAddress() {
/* 194 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 198 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public Set getBoSensors() {
/* 202 */     return this.boSensors;
/*     */   }
/*     */ 
/*     */   public void setBoSensors(Set boSensors) {
/* 206 */     this.boSensors = boSensors;
/*     */   }
/*     */ 
/*     */   public Set getBoLockPasswordManages() {
/* 210 */     return this.boLockPasswordManages;
/*     */   }
/*     */ 
/*     */   public void setBoLockPasswordManages(Set boLockPasswordManages) {
/* 214 */     this.boLockPasswordManages = boLockPasswordManages;
/*     */   }
/*     */ 
/*     */   public Set getBoSensorLines() {
/* 218 */     return this.boSensorLines;
/*     */   }
/*     */ 
/*     */   public void setBoSensorLines(Set boSensorLines) {
/* 222 */     this.boSensorLines = boSensorLines;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredLearnControlMaps()
/*     */   {
/* 227 */     return this.boInfraredLearnControlMaps;
/*     */   }
/*     */ 
/*     */   public Set getBoControlEnclosures() {
/* 231 */     return this.boControlEnclosures;
/*     */   }
/*     */ 
/*     */   public void setBoControlEnclosures(Set boControlEnclosures) {
/* 235 */     this.boControlEnclosures = boControlEnclosures;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredLearnControlMaps(Set boInfraredLearnControlMaps) {
/* 239 */     this.boInfraredLearnControlMaps = boInfraredLearnControlMaps;
/*     */   }
/*     */ 
/*     */   public String getTypeCode() {
/* 243 */     return this.typeCode;
/*     */   }
/*     */ 
/*     */   public void setTypeCode(String typeCode) {
/* 247 */     this.typeCode = typeCode;
/*     */   }
/*     */ 
/*     */   public String getDeviceName() {
/* 251 */     return this.deviceName;
/*     */   }
/*     */ 
/*     */   public void setDeviceName(String deviceName) {
/* 255 */     this.deviceName = deviceName;
/*     */   }
/*     */ 
/*     */   public Date getDeviceDate() {
/* 259 */     return this.deviceDate;
/*     */   }
/*     */ 
/*     */   public void setDeviceDate(Date deviceDate) {
/* 263 */     this.deviceDate = deviceDate;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredButtonses() {
/* 267 */     return this.boInfraredButtonses;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredButtonses(Set boInfraredButtonses) {
/* 271 */     this.boInfraredButtonses = boInfraredButtonses;
/*     */   }
/*     */ 
/*     */   public Date getExpDate() {
/* 275 */     return this.expDate;
/*     */   }
/*     */ 
/*     */   public void setExpDate(Date expDate) {
/* 279 */     this.expDate = expDate;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/* 283 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 287 */     this.status = status;
/* 288 */     this.statusName = (status.intValue() == 1 ? "在线" : "离线");
/*     */   }
/*     */ 
/*     */   public Date getBuyDate() {
/* 292 */     return this.buyDate;
/*     */   }
/*     */ 
/*     */   public void setBuyDate(Date buyDate) {
/* 296 */     this.buyDate = buyDate;
/*     */   }
/*     */ 
/*     */   public String getBuyUser() {
/* 300 */     return this.buyUser;
/*     */   }
/*     */ 
/*     */   public void setBuyUser(String buyUser) {
/* 304 */     this.buyUser = buyUser;
/*     */   }
/*     */ 
/*     */   public Double getBuyPrice() {
/* 308 */     return this.buyPrice;
/*     */   }
/*     */ 
/*     */   public void setBuyPrice(Double buyPrice) {
/* 312 */     this.buyPrice = buyPrice;
/*     */   }
/*     */ 
/*     */   public String getBuyDdr() {
/* 316 */     return this.buyDdr;
/*     */   }
/*     */ 
/*     */   public void setBuyDdr(String buyDdr) {
/* 320 */     this.buyDdr = buyDdr;
/*     */   }
/*     */ 
/*     */   @JSON(serialize=false)
/*     */   public Set getBoUserDevices() {
/* 325 */     return this.boUserDevices;
/*     */   }
/*     */ 
/*     */   public void setBoUserDevices(Set boUserDevices) {
/* 329 */     this.boUserDevices = boUserDevices;
/*     */   }
/*     */ 
/*     */   public String getDevIp() {
/* 333 */     return this.devIp;
/*     */   }
/*     */ 
/*     */   public void setDevIp(String devIp) {
/* 337 */     this.devIp = devIp;
/*     */   }
/*     */ 
/*     */   public String getLatitude() {
/* 341 */     return this.latitude;
/*     */   }
/*     */ 
/*     */   public void setLatitude(String latitude) {
/* 345 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */   public String getLongitude() {
/* 349 */     return this.longitude;
/*     */   }
/*     */ 
/*     */   public void setLongitude(String longitude) {
/* 353 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */   public String getProvince() {
/* 357 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(String province) {
/* 361 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public String getCity() {
/* 365 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(String city) {
/* 369 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getRegion() {
/* 373 */     return this.region;
/*     */   }
/*     */ 
/*     */   public void setRegion(String region) {
/* 377 */     this.region = region;
/*     */   }
/*     */ 
/*     */   public Set getBoModelInfos() {
/* 381 */     return this.boModelInfos;
/*     */   }
/*     */ 
/*     */   public void setBoModelInfos(Set boModelInfos) {
/* 385 */     this.boModelInfos = boModelInfos;
/*     */   }
/*     */ 
/*     */   public Integer getSource() {
/* 389 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(Integer source) {
/* 393 */     this.source = source;
/*     */   }
/*     */ 
/*     */   public String getStatusName()
/*     */   {
/* 398 */     return this.statusName;
/*     */   }
/*     */ 
/*     */   public void setStatusName(String statusName) {
/* 402 */     this.statusName = statusName;
/*     */   }
/*     */ 
/*     */   public Integer getWater()
/*     */   {
/* 413 */     return this.water;
/*     */   }
/*     */ 
/*     */   public void setWater(Integer water) {
/* 417 */     this.water = water;
/*     */   }
/*     */ 
/*     */   public Set getBoDevicePlans()
/*     */   {
/* 424 */     return this.boDevicePlans;
/*     */   }
/*     */ 
/*     */   public void setBoDevicePlans(Set boDevicePlans)
/*     */   {
/* 432 */     this.boDevicePlans = boDevicePlans;
/*     */   }
/*     */ 
/*     */   public Set getBoSimplifies() {
/* 436 */     return this.boSimplifies;
/*     */   }
/*     */ 
/*     */   public void setBoSimplifies(Set boSimplifies) {
/* 440 */     this.boSimplifies = boSimplifies;
/*     */   }
/*     */ 
/*     */   public Set getBoHostDevices() {
/* 444 */     return this.boHostDevices;
/*     */   }
/*     */ 
/*     */   public void setBoHostDevices(Set boHostDevices) {
/* 448 */     this.boHostDevices = boHostDevices;
/*     */   }
/*     */ 
/*     */   public Set getBoDeviceStates() {
/* 452 */     return this.boDeviceStates;
/*     */   }
/*     */ 
/*     */   public void setBoDeviceStates(Set boDeviceStates) {
/* 456 */     this.boDeviceStates = boDeviceStates;
/*     */   }
/*     */ 
/*     */   public Set getBoInfraredDevices()
/*     */   {
/* 461 */     return this.boInfraredDevices;
/*     */   }
/*     */ 
/*     */   public void setBoInfraredDevices(Set boInfraredDevices) {
/* 465 */     this.boInfraredDevices = boInfraredDevices;
/*     */   }
/*     */ 
/*     */   public Set getBoResendVerifications() {
/* 469 */     return this.boResendVerifications;
/*     */   }
/*     */ 
/*     */   public void setBoResendVerifications(Set boResendVerifications) {
/* 473 */     this.boResendVerifications = boResendVerifications;
/*     */   }
/*     */   public Set getBoNetworkNumbers() {
/* 476 */     return this.boNetworkNumbers;
/*     */   }
/*     */ 
/*     */   public void setBoNetworkNumbers(Set boNetworkNumbers) {
/* 480 */     this.boNetworkNumbers = boNetworkNumbers;
/*     */   }
/*     */   public Set getBoChannels() {
/* 483 */     return this.boChannels;
/*     */   }
/*     */ 
/*     */   public void setBoChannels(Set boChannels) {
/* 487 */     this.boChannels = boChannels;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoDevice
 * JD-Core Version:    0.6.2
 */