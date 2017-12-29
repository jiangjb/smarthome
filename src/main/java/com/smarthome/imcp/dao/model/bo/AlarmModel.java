/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysDictCacheManager;
/*     */ import com.smarthome.imcp.dao.model.system.SysDict;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AlarmModel
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
/*     */   private String statusName;
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getDeviceCode() {
/*  45 */     return this.deviceCode;
/*     */   }
/*     */ 
/*     */   public void setDeviceCode(String deviceCode) {
/*  49 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public Integer getFrameType() {
/*  53 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(Integer frameType) {
/*  57 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public Integer getDeviceType() {
/*  61 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(Integer deviceType) {
/*  65 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/*  69 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/*  73 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getTime() {
/*  77 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time) {
/*  81 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/*  85 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip) {
/*  89 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public Integer getPort() {
/*  93 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(Integer port) {
/*  97 */     this.port = port;
/*     */   }
/*     */ 
/*     */   public String getStatusName() {
/* 101 */     SysDict dict = SysDictCacheManager.getInstance().getSysDict("alarm" + this.frameType + "_" + this.deviceType, this.status+"");
/* 102 */     if (dict == null)
/* 103 */       this.statusName = "";
/*     */     else {
/* 105 */       this.statusName = dict.getDictName();
/*     */     }
/* 107 */     return this.statusName;
/*     */   }
/*     */ 
/*     */   public void setStatusName(String statusName) {
/* 111 */     this.statusName = statusName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.AlarmModel
 * JD-Core Version:    0.6.2
 */