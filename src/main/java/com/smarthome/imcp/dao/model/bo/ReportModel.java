/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ReportModel
/*     */   implements Serializable
/*     */ {
/*     */   protected Integer id;
/*   7 */   protected String deviceCode = "";
/*   8 */   protected Integer frameType = Integer.valueOf(0);
/*   9 */   protected Integer deviceType = Integer.valueOf(0);
/*     */   private Integer userId;
/*     */   private Integer deviceId;
/*     */   private BoUser boUser;
/*     */   private BoDevice boDevice;
/*     */   protected Date time;
/*  16 */   protected String ip = "";
/*  17 */   protected Integer port = Integer.valueOf(0);
/*     */ 
/*     */   public Integer getId() {
/*  20 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  24 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/*  28 */     return this.deviceCode;
/*     */   }
/*     */ 
/*     */   public void setDeviceCode(String deviceCode) {
/*  32 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public Integer getFrameType() {
/*  36 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(Integer frameType) {
/*  40 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceType() {
/*  44 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(Integer deviceType) {
/*  48 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public Date getTime() {
/*  52 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time) {
/*  56 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/*  60 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/*  64 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public Integer getPort() {
/*  68 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(Integer port) {
/*  72 */     this.port = port;
/*     */   }
/*     */ 
/*     */   public Integer getUserId()
/*     */   {
/*  79 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId)
/*     */   {
/*  86 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceId()
/*     */   {
/*  93 */     return this.deviceId;
/*     */   }
/*     */ 
/*     */   public void setDeviceId(Integer deviceId)
/*     */   {
/* 100 */     this.deviceId = deviceId;
/*     */   }
/*     */ 
/*     */   public BoUser getBoUser()
/*     */   {
/* 107 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser)
/*     */   {
/* 114 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice()
/*     */   {
/* 121 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice)
/*     */   {
/* 128 */     this.boDevice = boDevice;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.ReportModel
 * JD-Core Version:    0.6.2
 */