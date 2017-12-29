/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class UserMode
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUser boUser;
/*     */   private BoDevice boDevice;
/*     */   private Integer mode;
/*     */   private Integer newMode;
/*     */   private Date setTime;
/*     */   private Integer oldMode;
/*     */   private Integer status;
/*     */   private Date useTime;
/*     */   private String userName;
/*     */   private String deviceName;
/*     */   private String modeName;
/*     */   private String newModeName;
/*     */   private String oldModeName;
/*     */   private String statusName;
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  46 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  50 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUser getBoUser() {
/*  54 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser) {
/*  58 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice() {
/*  62 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  66 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public Integer getMode() {
/*  70 */     return this.mode;
/*     */   }
/*     */ 
/*     */   public void setMode(Integer mode) {
/*  74 */     this.mode = mode;
/*     */   }
/*     */ 
/*     */   public Integer getNewMode() {
/*  78 */     return this.newMode;
/*     */   }
/*     */ 
/*     */   public void setNewMode(Integer newMode) {
/*  82 */     this.newMode = newMode;
/*     */   }
/*     */ 
/*     */   public Date getSetTime() {
/*  86 */     return this.setTime;
/*     */   }
/*     */ 
/*     */   public void setSetTime(Date setTime) {
/*  90 */     this.setTime = setTime;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/*  94 */     this.userName = this.boUser.getUserName();
/*  95 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/*  99 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   public String getDeviceName() {
/* 103 */     this.deviceName = this.boDevice.getDeviceName();
/* 104 */     return this.deviceName;
/*     */   }
/*     */ 
/*     */   public void setDeviceName(String deviceName) {
/* 108 */     this.deviceName = deviceName;
/*     */   }
/*     */ 
/*     */   public Integer getOldMode() {
/* 112 */     return this.oldMode;
/*     */   }
/*     */ 
/*     */   public void setOldMode(Integer oldMode) {
/* 116 */     this.oldMode = oldMode;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/* 120 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 124 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getUseTime() {
/* 128 */     return this.useTime;
/*     */   }
/*     */ 
/*     */   public void setUseTime(Date useTime) {
/* 132 */     this.useTime = useTime;
/*     */   }
/*     */ 
/*     */   public String getModeName() {
/* 136 */     switch (this.mode.intValue()) {
/*     */     case 0:
/* 138 */       this.modeName = "按流量";
/* 139 */       break;
/*     */     case 1:
/* 141 */       this.modeName = "包天";
/*     */     }
/*     */ 
/* 144 */     return this.modeName;
/*     */   }
/*     */ 
/*     */   public void setModeName(String modeName) {
/* 148 */     this.modeName = modeName;
/*     */   }
/*     */ 
/*     */   public String getNewModeName() {
/* 152 */     switch (this.newMode.intValue()) {
/*     */     case 0:
/* 154 */       this.newModeName = "按流量";
/* 155 */       break;
/*     */     case 1:
/* 157 */       this.newModeName = "包天";
/*     */     }
/*     */ 
/* 160 */     return this.newModeName;
/*     */   }
/*     */ 
/*     */   public void setNewModeName(String newModeName) {
/* 164 */     this.newModeName = newModeName;
/*     */   }
/*     */ 
/*     */   public String getOldModeName() {
/* 168 */     switch (this.oldMode.intValue()) {
/*     */     case 0:
/* 170 */       this.oldModeName = "按流量";
/* 171 */       break;
/*     */     case 1:
/* 173 */       this.oldModeName = "包天";
/*     */     }
/*     */ 
/* 176 */     return this.oldModeName;
/*     */   }
/*     */ 
/*     */   public void setOldModeName(String oldModeName) {
/* 180 */     this.oldModeName = oldModeName;
/*     */   }
/*     */ 
/*     */   public String getStatusName() {
/* 184 */     switch (this.status.intValue()) {
/*     */     case 0:
/* 186 */       this.statusName = "未生效";
/* 187 */       break;
/*     */     case 1:
/* 189 */       this.statusName = "已失效";
/*     */     }
/*     */ 
/* 192 */     return this.statusName;
/*     */   }
/*     */ 
/*     */   public void setStatusName(String statusName) {
/* 196 */     this.statusName = statusName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.UserMode
 * JD-Core Version:    0.6.2
 */