/*     */ package com.smarthome.imcp.dao.model;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*     */ import com.smarthome.imcp.dao.model.system.SysParam;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractData
/*     */   implements Serializable
/*     */ {
/*  12 */   private Integer mntPosition = Integer.valueOf(0);
/*     */   private Integer mntCreatorId;
/*     */   private String mntCreatorName;
/*     */   private Date mntCreatorDate;
/*     */   private Integer mntUpdatedId;
/*     */   private String mntUpdatedName;
/*     */   private Date mntUpdatedDate;
/*     */   private Integer mntAutId;
/*     */   private String mntAutName;
/*     */   private Date mntAutDate;
/*     */   private String mntSttsF;
/*     */   private String mntAutsttsF;
/*  36 */   private Integer mntVersionNo = Integer.valueOf(0);
/*     */ 
/*  38 */   private String mntDelete = "N";
/*     */ 
/*     */   public Integer getMntPosition() {
/*  41 */     return this.mntPosition;
/*     */   }
/*     */ 
/*     */   public void setMntPosition(Integer mntPosition) {
/*  45 */     this.mntPosition = mntPosition;
/*     */   }
/*     */ 
/*     */   public Integer getMntCreatorId() {
/*  49 */     return this.mntCreatorId;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorId(Integer mntCreatorId) {
/*  53 */     this.mntCreatorId = mntCreatorId;
/*     */   }
/*     */ 
/*     */   public String getMntCreatorName() {
/*  57 */     return this.mntCreatorName;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorName(String mntCreatorName) {
/*  61 */     this.mntCreatorName = mntCreatorName;
/*     */   }
/*     */ 
/*     */   public Date getMntCreatorDate() {
/*  65 */     return this.mntCreatorDate;
/*     */   }
/*     */ 
/*     */   public void setMntCreatorDate(Date mntCreatorDate) {
/*  69 */     this.mntCreatorDate = mntCreatorDate;
/*     */   }
/*     */ 
/*     */   public Integer getMntUpdatedId() {
/*  73 */     return this.mntUpdatedId;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedId(Integer mntUpdatedId) {
/*  77 */     this.mntUpdatedId = mntUpdatedId;
/*     */   }
/*     */ 
/*     */   public String getMntUpdatedName() {
/*  81 */     return this.mntUpdatedName;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedName(String mntUpdatedName) {
/*  85 */     this.mntUpdatedName = mntUpdatedName;
/*     */   }
/*     */ 
/*     */   public Date getMntUpdatedDate() {
/*  89 */     return this.mntUpdatedDate;
/*     */   }
/*     */ 
/*     */   public void setMntUpdatedDate(Date mntUpdatedDate) {
/*  93 */     this.mntUpdatedDate = mntUpdatedDate;
/*     */   }
/*     */ 
/*     */   public Integer getMntAutId() {
/*  97 */     return this.mntAutId;
/*     */   }
/*     */ 
/*     */   public void setMntAutId(Integer mntAutId) {
/* 101 */     this.mntAutId = mntAutId;
/*     */   }
/*     */ 
/*     */   public String getMntAutName() {
/* 105 */     return this.mntAutName;
/*     */   }
/*     */ 
/*     */   public void setMntAutName(String mntAutName) {
/* 109 */     this.mntAutName = mntAutName;
/*     */   }
/*     */ 
/*     */   public Date getMntAutDate() {
/* 113 */     return this.mntAutDate;
/*     */   }
/*     */ 
/*     */   public void setMntAutDate(Date mntAutDate) {
/* 117 */     this.mntAutDate = mntAutDate;
/*     */   }
/*     */ 
/*     */   public String getMntSttsF() {
/* 121 */     return this.mntSttsF;
/*     */   }
/*     */ 
/*     */   public void setMntSttsF(String mntSttsF) {
/* 125 */     this.mntSttsF = mntSttsF;
/*     */   }
/*     */ 
/*     */   public String getMntAutsttsF() {
/* 129 */     return this.mntAutsttsF;
/*     */   }
/*     */ 
/*     */   public void setMntAutsttsF(String mntAutsttsF) {
/* 133 */     this.mntAutsttsF = mntAutsttsF;
/*     */   }
/*     */ 
/*     */   public Integer getMntVersionNo() {
/* 137 */     return this.mntVersionNo;
/*     */   }
/*     */ 
/*     */   public void setMntVersionNo(Integer mntVersionNo) {
/* 141 */     this.mntVersionNo = mntVersionNo;
/*     */   }
/*     */ 
/*     */   public String getMntDelete() {
/* 145 */     return this.mntDelete;
/*     */   }
/*     */ 
/*     */   public void setMntDelete(String mntDelete) {
/* 149 */     this.mntDelete = mntDelete;
/*     */   }
/*     */ 
/*     */   public String getUpdateStatus() {
/* 153 */     return SysParamCacheManager.getInstance()
/* 154 */       .getSysParam("UPDATE_STATUS", this.mntAutsttsF)
/* 155 */       .getParamName();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.AbstractData
 * JD-Core Version:    0.6.2
 */