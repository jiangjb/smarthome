/*     */ package com.smarthome.imcp.dao.criteria.smarthome;
/*     */ 
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class SearchCriteriaGoods
/*     */   implements SearchCriteria
/*     */ {
/*     */   private Integer id;
/*     */   private String goodsTitle;
/*     */   private String goodsIntroduce;
/*     */   private Double goodsPrice;
/*     */   private String goodsColor;
/*     */   private String goodsSize;
/*     */   private String workingVoltage;
/*     */   private String powerConsumption;
/*     */   private String materialGoods;
/*     */   private String communicatuinMode;
/*     */   private String workingTemperature;
/*     */   private String workingHumidity;
/*     */   private String picturesShow;
/*     */   private Integer salesVolumeDegree;
/*  23 */   private Set boShoppingCarts = new HashSet(0);
/*     */ 
/*  25 */   public Integer getId() { return this.id; }
/*     */ 
/*     */   public void setId(Integer id) {
/*  28 */     this.id = id;
/*     */   }
/*     */   public String getGoodsTitle() {
/*  31 */     return this.goodsTitle;
/*     */   }
/*     */   public void setGoodsTitle(String goodsTitle) {
/*  34 */     this.goodsTitle = goodsTitle;
/*     */   }
/*     */   public String getGoodsIntroduce() {
/*  37 */     return this.goodsIntroduce;
/*     */   }
/*     */   public void setGoodsIntroduce(String goodsIntroduce) {
/*  40 */     this.goodsIntroduce = goodsIntroduce;
/*     */   }
/*     */   public Double getGoodsPrice() {
/*  43 */     return this.goodsPrice;
/*     */   }
/*     */   public void setGoodsPrice(Double goodsPrice) {
/*  46 */     this.goodsPrice = goodsPrice;
/*     */   }
/*     */   public String getGoodsColor() {
/*  49 */     return this.goodsColor;
/*     */   }
/*     */   public void setGoodsColor(String goodsColor) {
/*  52 */     this.goodsColor = goodsColor;
/*     */   }
/*     */   public String getGoodsSize() {
/*  55 */     return this.goodsSize;
/*     */   }
/*     */   public void setGoodsSize(String goodsSize) {
/*  58 */     this.goodsSize = goodsSize;
/*     */   }
/*     */   public String getWorkingVoltage() {
/*  61 */     return this.workingVoltage;
/*     */   }
/*     */   public void setWorkingVoltage(String workingVoltage) {
/*  64 */     this.workingVoltage = workingVoltage;
/*     */   }
/*     */   public String getPowerConsumption() {
/*  67 */     return this.powerConsumption;
/*     */   }
/*     */   public void setPowerConsumption(String powerConsumption) {
/*  70 */     this.powerConsumption = powerConsumption;
/*     */   }
/*     */   public String getMaterialGoods() {
/*  73 */     return this.materialGoods;
/*     */   }
/*     */   public void setMaterialGoods(String materialGoods) {
/*  76 */     this.materialGoods = materialGoods;
/*     */   }
/*     */   public String getCommunicatuinMode() {
/*  79 */     return this.communicatuinMode;
/*     */   }
/*     */   public void setCommunicatuinMode(String communicatuinMode) {
/*  82 */     this.communicatuinMode = communicatuinMode;
/*     */   }
/*     */   public String getWorkingTemperature() {
/*  85 */     return this.workingTemperature;
/*     */   }
/*     */   public void setWorkingTemperature(String workingTemperature) {
/*  88 */     this.workingTemperature = workingTemperature;
/*     */   }
/*     */   public String getWorkingHumidity() {
/*  91 */     return this.workingHumidity;
/*     */   }
/*     */   public void setWorkingHumidity(String workingHumidity) {
/*  94 */     this.workingHumidity = workingHumidity;
/*     */   }
/*     */   public String getPicturesShow() {
/*  97 */     return this.picturesShow;
/*     */   }
/*     */   public void setPicturesShow(String picturesShow) {
/* 100 */     this.picturesShow = picturesShow;
/*     */   }
/*     */   public Integer getSalesVolumeDegree() {
/* 103 */     return this.salesVolumeDegree;
/*     */   }
/*     */   public void setSalesVolumeDegree(Integer salesVolumeDegree) {
/* 106 */     this.salesVolumeDegree = salesVolumeDegree;
/*     */   }
/*     */   public Set getBoShoppingCarts() {
/* 109 */     return this.boShoppingCarts;
/*     */   }
/*     */   public void setBoShoppingCarts(Set boShoppingCarts) {
/* 112 */     this.boShoppingCarts = boShoppingCarts;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaGoods
 * JD-Core Version:    0.6.2
 */