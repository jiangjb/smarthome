/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoDeviceType extends AbstractData
/*     */ {
/*     */   private Integer typeId;
/*     */   private BoFactory boFactory;
/*     */   private String typeCode;
/*     */   private Integer typeLevel;
/*     */   private String typeName;
/*     */   private String typeRmk;
/*     */   private String typeImage;
/*  26 */   private Set boDevices = new HashSet(0);
/*     */   private String factoryName;
/*     */ 
/*     */   public BoDeviceType()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoDeviceType(BoFactory boFactory, Integer factoryId, String typeCode, Integer typeLevel, String typeName, String typeRmk, String typeImage, Set boDevices)
/*     */   {
/*  40 */     this.boFactory = boFactory;
/*  41 */     this.typeCode = typeCode;
/*  42 */     this.typeLevel = typeLevel;
/*  43 */     this.typeName = typeName;
/*  44 */     this.typeRmk = typeRmk;
/*  45 */     this.typeImage = typeImage;
/*  46 */     this.boDevices = boDevices;
/*     */   }
/*     */ 
/*     */   public Integer getTypeId()
/*     */   {
/*  52 */     return this.typeId;
/*     */   }
/*     */ 
/*     */   public void setTypeId(Integer typeId) {
/*  56 */     this.typeId = typeId;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoFactory getBoFactory() {
/*  60 */     return this.boFactory;
/*     */   }
/*     */ 
/*     */   public void setBoFactory(BoFactory boFactory) {
/*  64 */     this.boFactory = boFactory;
/*     */   }
/*     */ 
/*     */   public String getTypeCode() {
/*  68 */     return this.typeCode;
/*     */   }
/*     */ 
/*     */   public void setTypeCode(String typeCode) {
/*  72 */     this.typeCode = typeCode;
/*     */   }
/*     */ 
/*     */   public Integer getTypeLevel() {
/*  76 */     return this.typeLevel;
/*     */   }
/*     */ 
/*     */   public void setTypeLevel(Integer typeLevel) {
/*  80 */     this.typeLevel = typeLevel;
/*     */   }
/*     */ 
/*     */   public String getTypeName() {
/*  84 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String typeName) {
/*  88 */     this.typeName = typeName;
/*     */   }
/*     */ 
/*     */   public String getTypeRmk() {
/*  92 */     return this.typeRmk;
/*     */   }
/*     */ 
/*     */   public void setTypeRmk(String typeRmk) {
/*  96 */     this.typeRmk = typeRmk;
/*     */   }
/*     */ 
/*     */   public String getTypeImage() {
/* 100 */     return this.typeImage;
/*     */   }
/*     */ 
/*     */   public void setTypeImage(String typeImage) {
/* 104 */     this.typeImage = typeImage;
/*     */   }
/*     */ 
/*     */   public String getFactoryName() {
/* 108 */     return this.boFactory.getFactoryName();
/*     */   }
/*     */ 
/*     */   public void setFactoryName(String factoryName) {
/* 112 */     this.factoryName = factoryName;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public Set getBoDevices() {
/* 116 */     return this.boDevices;
/*     */   }
/*     */ 
/*     */   public void setBoDevices(Set boDevices) {
/* 120 */     this.boDevices = boDevices;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoDeviceType
 * JD-Core Version:    0.6.2
 */