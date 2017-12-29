/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoUnlockingPushRecord
/*    */   implements Serializable
/*    */ {
/*    */   private String unlockingPushRecordId;
/*    */   private String userCode;
/*    */   private String lockAddress;
/*    */   private String methodsStatus;
/*    */   private String alarmPhoneType;
/*    */   private String reportDate;
/*    */   private String reportTimestamp;
/*    */ 
/*    */   public BoUnlockingPushRecord()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoUnlockingPushRecord(String unlockingPushRecordId, String userCode)
/*    */   {
/* 27 */     this.unlockingPushRecordId = unlockingPushRecordId;
/* 28 */     this.userCode = userCode;
/*    */   }
/*    */ 
/*    */   public BoUnlockingPushRecord(String unlockingPushRecordId, String userCode, String lockAddress, String methodsStatus, String alarmPhoneType, String reportDate, String reportTimestamp)
/*    */   {
/* 35 */     this.unlockingPushRecordId = unlockingPushRecordId;
/* 36 */     this.userCode = userCode;
/* 37 */     this.lockAddress = lockAddress;
/* 38 */     this.methodsStatus = methodsStatus;
/* 39 */     this.alarmPhoneType = alarmPhoneType;
/* 40 */     this.reportDate = reportDate;
/* 41 */     this.reportTimestamp = reportTimestamp;
/*    */   }
/*    */ 
/*    */   public String getUnlockingPushRecordId()
/*    */   {
/* 47 */     return this.unlockingPushRecordId;
/*    */   }
/*    */ 
/*    */   public void setUnlockingPushRecordId(String unlockingPushRecordId) {
/* 51 */     this.unlockingPushRecordId = unlockingPushRecordId;
/*    */   }
/*    */ 
/*    */   public String getUserCode() {
/* 55 */     return this.userCode;
/*    */   }
/*    */ 
/*    */   public void setUserCode(String userCode) {
/* 59 */     this.userCode = userCode;
/*    */   }
/*    */ 
/*    */   public String getLockAddress() {
/* 63 */     return this.lockAddress;
/*    */   }
/*    */ 
/*    */   public void setLockAddress(String lockAddress) {
/* 67 */     this.lockAddress = lockAddress;
/*    */   }
/*    */ 
/*    */   public String getMethodsStatus() {
/* 71 */     return this.methodsStatus;
/*    */   }
/*    */ 
/*    */   public void setMethodsStatus(String methodsStatus) {
/* 75 */     this.methodsStatus = methodsStatus;
/*    */   }
/*    */ 
/*    */   public String getAlarmPhoneType() {
/* 79 */     return this.alarmPhoneType;
/*    */   }
/*    */ 
/*    */   public void setAlarmPhoneType(String alarmPhoneType) {
/* 83 */     this.alarmPhoneType = alarmPhoneType;
/*    */   }
/*    */ 
/*    */   public String getReportDate() {
/* 87 */     return this.reportDate;
/*    */   }
/*    */ 
/*    */   public void setReportDate(String reportDate) {
/* 91 */     this.reportDate = reportDate;
/*    */   }
/*    */ 
/*    */   public String getReportTimestamp() {
/* 95 */     return this.reportTimestamp;
/*    */   }
/*    */ 
/*    */   public void setReportTimestamp(String reportTimestamp) {
/* 99 */     this.reportTimestamp = reportTimestamp;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord
 * JD-Core Version:    0.6.2
 */