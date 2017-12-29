/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ public class Plan
/*     */ {
/*     */   private int id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String description;
/*     */   private int deviceTypeId;
/*     */   private int price;
/*     */   private int duration;
/*     */   private int serviceFee;
/*     */   private int dailyFee;
/*     */   private int refillAmount;
/*     */   private int refillDuration;
/*     */   private String picUrl;
/*     */   private char deleted;
/*     */   private boolean deletable;
/*     */   private boolean init;
/*     */ 
/*     */   public Plan()
/*     */   {
/*  21 */     this.deleted = 'N';
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  28 */     this.id = id;
/*     */   }
/*     */   public String getName() {
/*  31 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  34 */     this.name = name;
/*     */   }
/*     */   public String getDescription() {
/*  37 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  40 */     this.description = description;
/*     */   }
/*     */   public int getDeviceTypeId() {
/*  43 */     return this.deviceTypeId;
/*     */   }
/*     */   public void setDeviceTypeId(int deviceTypeId) {
/*  46 */     this.deviceTypeId = deviceTypeId;
/*     */   }
/*     */   public int getPrice() {
/*  49 */     return this.price;
/*     */   }
/*     */   public void setPrice(int price) {
/*  52 */     this.price = price;
/*     */   }
/*     */   public int getDuration() {
/*  55 */     return this.duration;
/*     */   }
/*     */   public void setDuration(int duration) {
/*  58 */     this.duration = duration;
/*     */   }
/*     */   public int getServiceFee() {
/*  61 */     return this.serviceFee;
/*     */   }
/*     */   public void setServiceFee(int serviceFee) {
/*  64 */     this.serviceFee = serviceFee;
/*     */   }
/*     */   public String getPicUrl() {
/*  67 */     return this.picUrl;
/*     */   }
/*     */   public void setPicUrl(String picUrl) {
/*  70 */     this.picUrl = picUrl;
/*     */   }
/*     */ 
/*     */   public int getDailyFee()
/*     */   {
/*  76 */     return this.dailyFee;
/*     */   }
/*     */ 
/*     */   public void setDailyFee(int dailyFee)
/*     */   {
/*  82 */     this.dailyFee = dailyFee;
/*     */   }
/*     */ 
/*     */   public int getRefillAmount()
/*     */   {
/*  88 */     return this.refillAmount;
/*     */   }
/*     */ 
/*     */   public void setRefillAmount(int refillAmount)
/*     */   {
/*  94 */     this.refillAmount = refillAmount;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 100 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 106 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public boolean isDeletable()
/*     */   {
/* 112 */     return this.deletable;
/*     */   }
/*     */ 
/*     */   public void setDeletable(boolean deletable)
/*     */   {
/* 118 */     this.deletable = deletable;
/*     */   }
/*     */ 
/*     */   public boolean isInit()
/*     */   {
/* 124 */     return this.init;
/*     */   }
/*     */ 
/*     */   public void setInit(boolean init)
/*     */   {
/* 130 */     this.init = init;
/*     */   }
/*     */ 
/*     */   public int getRefillDuration()
/*     */   {
/* 136 */     return this.refillDuration;
/*     */   }
/*     */ 
/*     */   public void setRefillDuration(int refillDuration)
/*     */   {
/* 142 */     this.refillDuration = refillDuration;
/*     */   }
/*     */ 
/*     */   public char getDeleted()
/*     */   {
/* 148 */     return this.deleted;
/*     */   }
/*     */ 
/*     */   public void setDeleted(char deleted)
/*     */   {
/* 154 */     this.deleted = deleted;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.Plan
 * JD-Core Version:    0.6.2
 */