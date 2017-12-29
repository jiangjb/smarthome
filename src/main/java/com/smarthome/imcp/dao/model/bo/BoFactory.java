/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ 
/*     */ public class BoFactory extends AbstractData
/*     */ {
/*     */   private Integer factoryId;
/*     */   private String factoryName;
/*     */   private String factoryLegal;
/*     */   private String factoryAddr;
/*     */   private String factoryPhone;
/*     */   private String factoryEmail;
/*     */   private String factoryPage;
/*     */   private String factoryRmk;
/*     */   private Integer source;
/*     */ 
/*     */   public BoFactory()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoFactory(String factoryName, String factoryLegal, String factoryAddr, String factoryPhone, String factoryEmail, String factoryPage, String factoryRmk)
/*     */   {
/*  32 */     this.factoryName = factoryName;
/*  33 */     this.factoryLegal = factoryLegal;
/*  34 */     this.factoryAddr = factoryAddr;
/*  35 */     this.factoryPhone = factoryPhone;
/*  36 */     this.factoryEmail = factoryEmail;
/*  37 */     this.factoryPage = factoryPage;
/*  38 */     this.factoryRmk = factoryRmk;
/*     */   }
/*     */ 
/*     */   public Integer getFactoryId()
/*     */   {
/*  44 */     return this.factoryId;
/*     */   }
/*     */ 
/*     */   public void setFactoryId(Integer factoryId) {
/*  48 */     this.factoryId = factoryId;
/*     */   }
/*     */ 
/*     */   public String getFactoryName() {
/*  52 */     return this.factoryName;
/*     */   }
/*     */ 
/*     */   public void setFactoryName(String factoryName) {
/*  56 */     this.factoryName = factoryName;
/*     */   }
/*     */ 
/*     */   public String getFactoryLegal() {
/*  60 */     return this.factoryLegal;
/*     */   }
/*     */ 
/*     */   public void setFactoryLegal(String factoryLegal) {
/*  64 */     this.factoryLegal = factoryLegal;
/*     */   }
/*     */ 
/*     */   public String getFactoryAddr() {
/*  68 */     return this.factoryAddr;
/*     */   }
/*     */ 
/*     */   public void setFactoryAddr(String factoryAddr) {
/*  72 */     this.factoryAddr = factoryAddr;
/*     */   }
/*     */ 
/*     */   public String getFactoryPhone() {
/*  76 */     return this.factoryPhone;
/*     */   }
/*     */ 
/*     */   public void setFactoryPhone(String factoryPhone) {
/*  80 */     this.factoryPhone = factoryPhone;
/*     */   }
/*     */ 
/*     */   public String getFactoryEmail() {
/*  84 */     return this.factoryEmail;
/*     */   }
/*     */ 
/*     */   public void setFactoryEmail(String factoryEmail) {
/*  88 */     this.factoryEmail = factoryEmail;
/*     */   }
/*     */ 
/*     */   public String getFactoryPage() {
/*  92 */     return this.factoryPage;
/*     */   }
/*     */ 
/*     */   public void setFactoryPage(String factoryPage) {
/*  96 */     this.factoryPage = factoryPage;
/*     */   }
/*     */ 
/*     */   public String getFactoryRmk() {
/* 100 */     return this.factoryRmk;
/*     */   }
/*     */ 
/*     */   public void setFactoryRmk(String factoryRmk) {
/* 104 */     this.factoryRmk = factoryRmk;
/*     */   }
/*     */ 
/*     */   public Integer getSource() {
/* 108 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(Integer source) {
/* 112 */     this.source = source;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoFactory
 * JD-Core Version:    0.6.2
 */