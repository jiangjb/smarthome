/*     */ package com.smarthome.imcp.dao.vo.cy;
/*     */ 
/*     */ public class PlanVo
/*     */ {
/*     */   private int id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String description;
/*     */   private int deviceTypeId;
/*     */   private int price;
/*     */   private int duration;
/*     */   private int serviceFee;
/*     */   private String picUrl;
/*     */   private int refillAmount;
/*     */   private boolean deleted;
/*     */   private boolean deletable;
/*     */   private boolean init;
/*     */ 
/*     */   public int getId()
/*     */   {
/*  19 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  22 */     this.id = id;
/*     */   }
/*     */   public String getName() {
/*  25 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  28 */     this.name = name;
/*     */   }
/*     */   public String getDescription() {
/*  31 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  34 */     this.description = description;
/*     */   }
/*     */   public int getDeviceTypeId() {
/*  37 */     return this.deviceTypeId;
/*     */   }
/*     */   public void setDeviceTypeId(int deviceTypeId) {
/*  40 */     this.deviceTypeId = deviceTypeId;
/*     */   }
/*     */ 
/*     */   public int getPrice() {
/*  44 */     return this.price;
/*     */   }
/*     */   public void setPrice(int price) {
/*  47 */     this.price = price;
/*     */   }
/*     */   public int getDuration() {
/*  50 */     return this.duration;
/*     */   }
/*     */   public void setDuration(int duration) {
/*  53 */     this.duration = duration;
/*     */   }
/*     */   public int getServiceFee() {
/*  56 */     return this.serviceFee;
/*     */   }
/*     */   public void setServiceFee(int serviceFee) {
/*  59 */     this.serviceFee = serviceFee;
/*     */   }
/*     */   public String getPicUrl() {
/*  62 */     return this.picUrl;
/*     */   }
/*     */   public void setPicUrl(String picUrl) {
/*  65 */     this.picUrl = picUrl;
/*     */   }
/*     */ 
/*     */   public int getRefillAmount()
/*     */   {
/*  71 */     return this.refillAmount;
/*     */   }
/*     */ 
/*     */   public void setRefillAmount(int refillAmount)
/*     */   {
/*  77 */     this.refillAmount = refillAmount;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  83 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  89 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public boolean isDeleted()
/*     */   {
/*  95 */     return this.deleted;
/*     */   }
/*     */ 
/*     */   public void setDeleted(boolean deleted)
/*     */   {
/* 101 */     this.deleted = deleted;
/*     */   }
/*     */ 
/*     */   public boolean isDeletable()
/*     */   {
/* 107 */     return this.deletable;
/*     */   }
/*     */ 
/*     */   public void setDeletable(boolean deletable)
/*     */   {
/* 113 */     this.deletable = deletable;
/*     */   }
/*     */ 
/*     */   public boolean isInit()
/*     */   {
/* 119 */     return this.init;
/*     */   }
/*     */ 
/*     */   public void setInit(boolean init)
/*     */   {
/* 125 */     this.init = init;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.cy.PlanVo
 * JD-Core Version:    0.6.2
 */