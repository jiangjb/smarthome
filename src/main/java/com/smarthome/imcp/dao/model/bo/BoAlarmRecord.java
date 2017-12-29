/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class BoAlarmRecord
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String userCode;
/*     */   private String deviceCode;
/*     */   private String sebsorName;
/*     */   private String alarmPhoneType;
/*     */   private String reportTime;
/*     */   private Date reportDate;
/*     */   private String reportTimestamp;
/*     */ 
/*     */   public BoAlarmRecord()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoAlarmRecord(String userCode, String deviceCode, String sebsorName, String alarmPhoneType, String reportTime, Date reportDate, String reportTimestamp)
/*     */   {
/*  33 */     this.userCode = userCode;
/*  34 */     this.deviceCode = deviceCode;
/*  35 */     this.sebsorName = sebsorName;
/*  36 */     this.alarmPhoneType = alarmPhoneType;
/*  37 */     this.reportTime = reportTime;
/*  38 */     this.reportDate = reportDate;
/*  39 */     this.reportTimestamp = reportTimestamp;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  49 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/*  53 */     return this.userCode;
/*     */   }
/*     */ 
/*     */   public void setUserCode(String userCode) {
/*  57 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/*  61 */     return this.deviceCode;
/*     */   }
/*     */ 
/*     */   public String getReportTimestamp() {
/*  65 */     return this.reportTimestamp;
/*     */   }
/*     */ 
/*     */   public void setReportTimestamp(String reportTimestamp) {
/*  69 */     this.reportTimestamp = reportTimestamp;
/*     */   }
/*     */ 
/*     */   public void setDeviceCode(String deviceCode) {
/*  73 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public String getSebsorName() {
/*  77 */     return this.sebsorName;
/*     */   }
/*     */ 
/*     */   public void setSebsorName(String sebsorName) {
/*  81 */     this.sebsorName = sebsorName;
/*     */   }
/*     */ 
/*     */   public String getAlarmPhoneType() {
/*  85 */     return this.alarmPhoneType;
/*     */   }
/*     */ 
/*     */   public void setAlarmPhoneType(String alarmPhoneType) {
/*  89 */     this.alarmPhoneType = alarmPhoneType;
/*     */   }
/*     */ 
/*     */   public String getReportTime() {
/*  93 */     return this.reportTime;
/*     */   }
/*     */ 
/*     */   public void setReportTime(String reportTime) {
/*  97 */     this.reportTime = reportTime;
/*     */   }
/*     */ 
/*     */   public Date getReportDate() {
/* 101 */     return this.reportDate;
/*     */   }
/*     */ 
/*     */   public void setReportDate(Date reportDate) {
/* 105 */     this.reportDate = reportDate;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoAlarmRecord
 * JD-Core Version:    0.6.2
 */