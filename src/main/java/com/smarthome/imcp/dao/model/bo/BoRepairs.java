/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class BoRepairs
/*     */   implements Serializable
/*     */ {
/*     */   private Integer repairId;
/*     */   private BoUsers boUsers;
/*     */   private Integer deviceId;
/*     */   private Integer factoryId;
/*     */   private Date repairDate;
/*     */   private String repairStatus;
/*     */   private Integer mntCreatorId;
/*     */   private String mntCreatorName;
/*     */   private Date mntCreatorDate;
/*     */   private Integer mntUpdatedId;
/*     */   private String mntUpdatedName;
/*     */   private Date mntUpdatedDate;
/*     */   private Integer mntAutId;
/*     */   private String mntAutName;
/*     */   private Date mntAutDate;
/*     */   private String mntDelete;
/*     */   private String mntSttsF;
/*     */   private String mntAutsttsF;
/*     */   private Integer mntPosition;
/*     */   private Short mntVersionNo;
/*     */   private String userTxt;
/*     */   private String userPhone;
/*     */   private String userAddr;
/*     */   private String workName;
/*     */   private String workTel;
/*     */   private Date orderDate;
/*     */   private String workTxt;
/*     */   private Date workDate;
/*     */   private Double workTime;
/*     */   private Integer workGsi;
/*     */ 
/*     */   public BoRepairs()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoRepairs(BoUsers boUsers, Integer deviceId, Integer factoryId, Date repairDate, String repairStatus, Integer mntCreatorId, String mntCreatorName, Date mntCreatorDate, Integer mntUpdatedId, String mntUpdatedName, Date mntUpdatedDate, Integer mntAutId, String mntAutName, Date mntAutDate, String mntDelete, String mntSttsF, String mntAutsttsF, Integer mntPosition, Short mntVersionNo, String userTxt, String userPhone, String userAddr, String workName, String workTel, Date orderDate, String workTxt, Date workDate, Double workTime, Integer workGsi)
/*     */   {
/*  60 */     this.boUsers = boUsers;
/*  61 */     this.deviceId = deviceId;
/*  62 */     this.factoryId = factoryId;
/*  63 */     this.repairDate = repairDate;
/*  64 */     this.repairStatus = repairStatus;
/*  65 */     this.mntCreatorId = mntCreatorId;
/*  66 */     this.mntCreatorName = mntCreatorName;
/*  67 */     this.mntCreatorDate = mntCreatorDate;
/*  68 */     this.mntUpdatedId = mntUpdatedId;
/*  69 */     this.mntUpdatedName = mntUpdatedName;
/*  70 */     this.mntUpdatedDate = mntUpdatedDate;
/*  71 */     this.mntAutId = mntAutId;
/*  72 */     this.mntAutName = mntAutName;
/*  73 */     this.mntAutDate = mntAutDate;
/*  74 */     this.mntDelete = mntDelete;
/*  75 */     this.mntSttsF = mntSttsF;
/*  76 */     this.mntAutsttsF = mntAutsttsF;
/*  77 */     this.mntPosition = mntPosition;
/*  78 */     this.mntVersionNo = mntVersionNo;
/*  79 */     this.userTxt = userTxt;
/*  80 */     this.userPhone = userPhone;
/*  81 */     this.userAddr = userAddr;
/*  82 */     this.workName = workName;
/*  83 */     this.workTel = workTel;
/*  84 */     this.orderDate = orderDate;
/*  85 */     this.workTxt = workTxt;
/*  86 */     this.workDate = workDate;
/*  87 */     this.workTime = workTime;
/*  88 */     this.workGsi = workGsi;
/*     */   }
/*     */ 
/*     */   public Integer getRepairId()
/*     */   {
/*  94 */     return this.repairId;
/*     */   }
/*     */ 
/*     */   public void setRepairId(Integer repairId) {
/*  98 */     this.repairId = repairId;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/* 102 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/* 106 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceId() {
/* 110 */     return this.deviceId;
/*     */   }
/*     */ 
/*     */   public void setDeviceId(Integer deviceId) {
/* 114 */     this.deviceId = deviceId;
/*     */   }
/*     */ 
/*     */   public Integer getFactoryId() {
/* 118 */     return this.factoryId;
/*     */   }
/*     */ 
/*     */   public void setFactoryId(Integer factoryId) {
/* 122 */     this.factoryId = factoryId;
/*     */   }
/*     */ 
/*     */   public Date getRepairDate() {
/* 126 */     return this.repairDate;
/*     */   }
/*     */ 
/*     */   public void setRepairDate(Date repairDate) {
/* 130 */     this.repairDate = repairDate;
/*     */   }
/*     */ 
/*     */   public String getRepairStatus() {
/* 134 */     return this.repairStatus;
/*     */   }
/*     */ 
/*     */   public void setRepairStatus(String repairStatus) {
/* 138 */     this.repairStatus = repairStatus;
/*     */   }
/*     */ 
/*     */   public Integer getMntCreatorId() {
/* 142 */     return this.mntCreatorId;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorId(Integer mntCreatorId) {
/* 146 */     this.mntCreatorId = mntCreatorId;
/*     */   }
/*     */ 
/*     */   public String getMntCreatorName() {
/* 150 */     return this.mntCreatorName;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorName(String mntCreatorName) {
/* 154 */     this.mntCreatorName = mntCreatorName;
/*     */   }
/*     */ 
/*     */   public Date getMntCreatorDate() {
/* 158 */     return this.mntCreatorDate;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorDate(Date mntCreatorDate) {
/* 162 */     this.mntCreatorDate = mntCreatorDate;
/*     */   }
/*     */ 
/*     */   public Integer getMntUpdatedId() {
/* 166 */     return this.mntUpdatedId;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedId(Integer mntUpdatedId) {
/* 170 */     this.mntUpdatedId = mntUpdatedId;
/*     */   }
/*     */ 
/*     */   public String getMntUpdatedName() {
/* 174 */     return this.mntUpdatedName;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedName(String mntUpdatedName) {
/* 178 */     this.mntUpdatedName = mntUpdatedName;
/*     */   }
/*     */ 
/*     */   public Date getMntUpdatedDate() {
/* 182 */     return this.mntUpdatedDate;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedDate(Date mntUpdatedDate) {
/* 186 */     this.mntUpdatedDate = mntUpdatedDate;
/*     */   }
/*     */ 
/*     */   public Integer getMntAutId() {
/* 190 */     return this.mntAutId;
/*     */   }
/*     */ 
/*     */   public void setMntAutId(Integer mntAutId) {
/* 194 */     this.mntAutId = mntAutId;
/*     */   }
/*     */ 
/*     */   public String getMntAutName() {
/* 198 */     return this.mntAutName;
/*     */   }
/*     */ 
/*     */   public void setMntAutName(String mntAutName) {
/* 202 */     this.mntAutName = mntAutName;
/*     */   }
/*     */ 
/*     */   public Date getMntAutDate() {
/* 206 */     return this.mntAutDate;
/*     */   }
/*     */ 
/*     */   public void setMntAutDate(Date mntAutDate) {
/* 210 */     this.mntAutDate = mntAutDate;
/*     */   }
/*     */ 
/*     */   public String getMntDelete() {
/* 214 */     return this.mntDelete;
/*     */   }
/*     */ 
/*     */   public void setMntDelete(String mntDelete) {
/* 218 */     this.mntDelete = mntDelete;
/*     */   }
/*     */ 
/*     */   public String getMntSttsF() {
/* 222 */     return this.mntSttsF;
/*     */   }
/*     */ 
/*     */   public void setMntSttsF(String mntSttsF) {
/* 226 */     this.mntSttsF = mntSttsF;
/*     */   }
/*     */ 
/*     */   public String getMntAutsttsF() {
/* 230 */     return this.mntAutsttsF;
/*     */   }
/*     */ 
/*     */   public void setMntAutsttsF(String mntAutsttsF) {
/* 234 */     this.mntAutsttsF = mntAutsttsF;
/*     */   }
/*     */ 
/*     */   public Integer getMntPosition() {
/* 238 */     return this.mntPosition;
/*     */   }
/*     */ 
/*     */   public void setMntPosition(Integer mntPosition) {
/* 242 */     this.mntPosition = mntPosition;
/*     */   }
/*     */ 
/*     */   public Short getMntVersionNo() {
/* 246 */     return this.mntVersionNo;
/*     */   }
/*     */ 
/*     */   public void setMntVersionNo(Short mntVersionNo) {
/* 250 */     this.mntVersionNo = mntVersionNo;
/*     */   }
/*     */ 
/*     */   public String getUserTxt() {
/* 254 */     return this.userTxt;
/*     */   }
/*     */ 
/*     */   public void setUserTxt(String userTxt) {
/* 258 */     this.userTxt = userTxt;
/*     */   }
/*     */ 
/*     */   public String getUserPhone() {
/* 262 */     return this.userPhone;
/*     */   }
/*     */ 
/*     */   public void setUserPhone(String userPhone) {
/* 266 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public String getUserAddr() {
/* 270 */     return this.userAddr;
/*     */   }
/*     */ 
/*     */   public void setUserAddr(String userAddr) {
/* 274 */     this.userAddr = userAddr;
/*     */   }
/*     */ 
/*     */   public String getWorkName() {
/* 278 */     return this.workName;
/*     */   }
/*     */ 
/*     */   public void setWorkName(String workName) {
/* 282 */     this.workName = workName;
/*     */   }
/*     */ 
/*     */   public String getWorkTel() {
/* 286 */     return this.workTel;
/*     */   }
/*     */ 
/*     */   public void setWorkTel(String workTel) {
/* 290 */     this.workTel = workTel;
/*     */   }
/*     */ 
/*     */   public Date getOrderDate() {
/* 294 */     return this.orderDate;
/*     */   }
/*     */ 
/*     */   public void setOrderDate(Date orderDate) {
/* 298 */     this.orderDate = orderDate;
/*     */   }
/*     */ 
/*     */   public String getWorkTxt() {
/* 302 */     return this.workTxt;
/*     */   }
/*     */ 
/*     */   public void setWorkTxt(String workTxt) {
/* 306 */     this.workTxt = workTxt;
/*     */   }
/*     */ 
/*     */   public Date getWorkDate() {
/* 310 */     return this.workDate;
/*     */   }
/*     */ 
/*     */   public void setWorkDate(Date workDate) {
/* 314 */     this.workDate = workDate;
/*     */   }
/*     */ 
/*     */   public Double getWorkTime() {
/* 318 */     return this.workTime;
/*     */   }
/*     */ 
/*     */   public void setWorkTime(Double workTime) {
/* 322 */     this.workTime = workTime;
/*     */   }
/*     */ 
/*     */   public Integer getWorkGsi() {
/* 326 */     return this.workGsi;
/*     */   }
/*     */ 
/*     */   public void setWorkGsi(Integer workGsi) {
/* 330 */     this.workGsi = workGsi;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoRepairs
 * JD-Core Version:    0.6.2
 */