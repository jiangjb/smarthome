/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import com.smarthome.imcp.dao.model.system.SysParam;
/*     */ import java.util.Date;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoRepair extends AbstractData
/*     */ {
/*     */   private Integer repairId;
/*     */   private BoUser boUser;
/*     */   private BoDevice boDevice;
/*     */   private BoFactory boFactory;
/*     */   private Date repairDate;
/*     */   private String repairStatus;
/*     */   private String workName;
/*     */   private String workTel;
/*     */   private Date orderDate;
/*     */   private String workTxt;
/*     */   private Date workDate;
/*     */   private Double workTime;
/*     */   private Integer workGsi;
/*     */   private String userTxt;
/*     */   private String userPhone;
/*     */   private String userAddr;
/*     */   private String userName;
/*     */   private String deviceName;
/*     */   private String factoryName;
/*     */   private String repairStatusName;
/*     */ 
/*     */   public BoRepair()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoRepair(BoUser boUser, BoDevice boDevice, BoFactory boFactory, Date repairDate, String repairStatus)
/*     */   {
/*  57 */     this.boUser = boUser;
/*  58 */     this.boDevice = boDevice;
/*  59 */     this.boFactory = boFactory;
/*  60 */     this.repairDate = repairDate;
/*  61 */     this.repairStatus = repairStatus;
/*     */   }
/*     */ 
/*     */   public Integer getRepairId()
/*     */   {
/*  67 */     return this.repairId;
/*     */   }
/*     */ 
/*     */   public void setRepairId(Integer repairId) {
/*  71 */     this.repairId = repairId;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoUser getBoUser() {
/*  75 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser) {
/*  79 */     this.boUser = boUser;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoDevice getBoDevice() {
/*  83 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  87 */     this.boDevice = boDevice;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoFactory getBoFactory() {
/*  91 */     return this.boFactory;
/*     */   }
/*     */ 
/*     */   public void setBoFactory(BoFactory boFactory) {
/*  95 */     this.boFactory = boFactory;
/*     */   }
/*     */ 
/*     */   public Date getRepairDate() {
/*  99 */     return this.repairDate;
/*     */   }
/*     */ 
/*     */   public void setRepairDate(Date repairDate) {
/* 103 */     this.repairDate = repairDate;
/*     */   }
/*     */ 
/*     */   public String getRepairStatus() {
/* 107 */     return this.repairStatus;
/*     */   }
/*     */ 
/*     */   public void setRepairStatus(String repairStatus) {
/* 111 */     this.repairStatus = repairStatus;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 115 */     return this.boUser.getUserName();
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 119 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getDeviceName() {
/* 123 */     return this.boDevice.getDeviceName();
/*     */   }
/*     */ 
/*     */   public void setDeviceName(String deviceName) {
/* 127 */     this.deviceName = deviceName;
/*     */   }
/*     */ 
/*     */   public String getFactoryName() {
/* 131 */     return this.boFactory.getFactoryName();
/*     */   }
/*     */ 
/*     */   public void setFactoryName(String factoryName) {
/* 135 */     this.factoryName = factoryName;
/*     */   }
/*     */ 
/*     */   public String getRepairStatusName() {
/* 139 */     this.repairStatusName = SysParamCacheManager.getInstance().getSysParam("REPAIR_STATUS", this.repairStatus).getParamName();
/* 140 */     return this.repairStatusName;
/*     */   }
/*     */ 
/*     */   public void setRepairStatusName(String repairStatusName) {
/* 144 */     this.repairStatusName = repairStatusName;
/*     */   }
/*     */ 
/*     */   public String getWorkName() {
/* 148 */     return this.workName;
/*     */   }
/*     */ 
/*     */   public void setWorkName(String workName) {
/* 152 */     this.workName = workName;
/*     */   }
/*     */ 
/*     */   public String getWorkTel() {
/* 156 */     return this.workTel;
/*     */   }
/*     */ 
/*     */   public void setWorkTel(String workTel) {
/* 160 */     this.workTel = workTel;
/*     */   }
/*     */ 
/*     */   public Date getOrderDate() {
/* 164 */     return this.orderDate;
/*     */   }
/*     */ 
/*     */   public void setOrderDate(Date orderDate) {
/* 168 */     this.orderDate = orderDate;
/*     */   }
/*     */ 
/*     */   public String getWorkTxt() {
/* 172 */     return this.workTxt;
/*     */   }
/*     */ 
/*     */   public void setWorkTxt(String workTxt) {
/* 176 */     this.workTxt = workTxt;
/*     */   }
/*     */ 
/*     */   public Date getWorkDate() {
/* 180 */     return this.workDate;
/*     */   }
/*     */ 
/*     */   public void setWorkDate(Date workDate) {
/* 184 */     this.workDate = workDate;
/*     */   }
/*     */ 
/*     */   public Double getWorkTime() {
/* 188 */     return this.workTime;
/*     */   }
/*     */ 
/*     */   public void setWorkTime(Double workTime) {
/* 192 */     this.workTime = workTime;
/*     */   }
/*     */ 
/*     */   public Integer getWorkGsi() {
/* 196 */     return this.workGsi;
/*     */   }
/*     */ 
/*     */   public void setWorkGsi(Integer workGsi) {
/* 200 */     this.workGsi = workGsi;
/*     */   }
/*     */ 
/*     */   public String getUserTxt() {
/* 204 */     return this.userTxt;
/*     */   }
/*     */ 
/*     */   public void setUserTxt(String userTxt) {
/* 208 */     this.userTxt = userTxt;
/*     */   }
/*     */ 
/*     */   public String getUserPhone() {
/* 212 */     if ((this.userPhone == null) || ("".equals(this.userPhone))) {
/* 213 */       this.userPhone = this.boUser.getUserPhone();
/*     */     }
/*     */ 
/* 216 */     return this.userPhone;
/*     */   }
/*     */ 
/*     */   public void setUserPhone(String userPhone) {
/* 220 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public String getUserAddr() {
/* 224 */     if ((this.userAddr == null) || ("".equals(this.userAddr))) {
/* 225 */       this.userAddr = this.boUser.getUserAddr();
/*     */     }
/* 227 */     return this.userAddr;
/*     */   }
/*     */ 
/*     */   public void setUserAddr(String userAddr) {
/* 231 */     this.userAddr = userAddr;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoRepair
 * JD-Core Version:    0.6.2
 */