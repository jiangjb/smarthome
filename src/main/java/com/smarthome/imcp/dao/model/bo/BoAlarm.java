/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysDictCacheManager;
/*     */ import com.smarthome.imcp.dao.model.system.SysDict;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class BoAlarm
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String deviceCode;
/*     */   private Integer frameType;
/*     */   private Integer deviceType;
/*     */   private Integer status;
/*     */   private Date time;
/*     */   private String ip;
/*     */   private Integer port;
/*     */   private String url;
/*     */   private String statusName;
/*     */ 
/*     */   public BoAlarm()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoAlarm(String deviceCode, Integer frameType, Integer deviceType, Integer status)
/*     */   {
/*  38 */     this.deviceCode = deviceCode;
/*  39 */     this.frameType = frameType;
/*  40 */     this.deviceType = deviceType;
/*  41 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  47 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  51 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/*  55 */     return this.deviceCode;
/*     */   }
/*     */ 
/*     */   public void setDeviceCode(String deviceCode) {
/*  59 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public Integer getFrameType() {
/*  63 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(Integer frameType) {
/*  67 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceType() {
/*  71 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(Integer deviceType) {
/*  75 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/*  79 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/*  83 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getTime() {
/*  87 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time) {
/*  91 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/*  95 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/*  99 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public Integer getPort() {
/* 103 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(Integer port) {
/* 107 */     this.port = port;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 111 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 115 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getStatusName() {
/* 119 */     SysDict dict = SysDictCacheManager.getInstance().getSysDict("alarm" + this.frameType + "_" + this.deviceType, this.status+"");
/* 120 */     if (dict == null)
/* 121 */       this.statusName = "";
/*     */     else {
/* 123 */       this.statusName = dict.getDictName();
/*     */     }
/* 125 */     return this.statusName;
/*     */   }
/*     */ 
/*     */   public void setStatusName(String statusName) {
/* 129 */     this.statusName = statusName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoAlarm
 * JD-Core Version:    0.6.2
 */