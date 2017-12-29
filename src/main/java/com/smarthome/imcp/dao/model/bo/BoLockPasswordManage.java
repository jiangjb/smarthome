/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoLockPasswordManage
/*     */   implements Serializable
/*     */ {
/*     */   private String lockPasswordManageId;
/*     */   private BoUsers boUsers;
/*     */   private BoDevice boDevice;
/*     */   private String lockAddress;
/*     */   private Integer lockType;
/*     */   private Integer lockOfTimes;
/*     */   private String startTime;
/*     */   private String endTime;
/*     */   private String adminPwd;
/*     */   private String lockPwd;
/*     */ 
/*     */   public BoLockPasswordManage()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage(String lockPasswordManageId, BoUsers boUsers, BoDevice boDevice, Integer lockType, Integer lockOfTimes)
/*     */   {
/*  31 */     this.lockPasswordManageId = lockPasswordManageId;
/*  32 */     this.boUsers = boUsers;
/*  33 */     this.boDevice = boDevice;
/*  34 */     this.lockType = lockType;
/*  35 */     this.lockOfTimes = lockOfTimes;
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage(String lockPasswordManageId, BoUsers boUsers, BoDevice boDevice, String lockAddress, Integer lockType, Integer lockOfTimes, String startTime, String endTime, String adminPwd, String lockPwd)
/*     */   {
/*  43 */     this.lockPasswordManageId = lockPasswordManageId;
/*  44 */     this.boUsers = boUsers;
/*  45 */     this.boDevice = boDevice;
/*  46 */     this.lockAddress = lockAddress;
/*  47 */     this.lockType = lockType;
/*  48 */     this.lockOfTimes = lockOfTimes;
/*  49 */     this.startTime = startTime;
/*  50 */     this.endTime = endTime;
/*  51 */     this.adminPwd = adminPwd;
/*  52 */     this.lockPwd = lockPwd;
/*     */   }
/*     */ 
/*     */   public String getLockPasswordManageId()
/*     */   {
/*  58 */     return this.lockPasswordManageId;
/*     */   }
/*     */ 
/*     */   public void setLockPasswordManageId(String lockPasswordManageId) {
/*  62 */     this.lockPasswordManageId = lockPasswordManageId;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  66 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  70 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  74 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  78 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public String getLockAddress() {
/*  82 */     return this.lockAddress;
/*     */   }
/*     */ 
/*     */   public void setLockAddress(String lockAddress) {
/*  86 */     this.lockAddress = lockAddress;
/*     */   }
/*     */ 
/*     */   public Integer getLockType() {
/*  90 */     return this.lockType;
/*     */   }
/*     */ 
/*     */   public void setLockType(Integer lockType) {
/*  94 */     this.lockType = lockType;
/*     */   }
/*     */ 
/*     */   public Integer getLockOfTimes() {
/*  98 */     return this.lockOfTimes;
/*     */   }
/*     */ 
/*     */   public void setLockOfTimes(Integer lockOfTimes) {
/* 102 */     this.lockOfTimes = lockOfTimes;
/*     */   }
/*     */ 
/*     */   public String getStartTime() {
/* 106 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(String startTime) {
/* 110 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public String getEndTime() {
/* 114 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(String endTime) {
/* 118 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public String getAdminPwd() {
/* 122 */     return this.adminPwd;
/*     */   }
/*     */ 
/*     */   public void setAdminPwd(String adminPwd) {
/* 126 */     this.adminPwd = adminPwd;
/*     */   }
/*     */ 
/*     */   public String getLockPwd() {
/* 130 */     return this.lockPwd;
/*     */   }
/*     */ 
/*     */   public void setLockPwd(String lockPwd) {
/* 134 */     this.lockPwd = lockPwd;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoLockPasswordManage
 * JD-Core Version:    0.6.2
 */