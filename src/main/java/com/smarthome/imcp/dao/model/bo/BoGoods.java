/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BoGoods
/*     */   implements Serializable
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
/*  29 */   private Set boShoppingCarts = new HashSet(0);
/*  30 */   private Set boOrders = new HashSet(0);
/*     */ 
/*     */   public BoGoods()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoGoods(String goodsTitle, String goodsIntroduce, Double goodsPrice, String goodsColor, String goodsSize, String workingVoltage, String powerConsumption, String materialGoods, String communicatuinMode, String workingTemperature, String workingHumidity, String picturesShow, Integer salesVolumeDegree, Set boShoppingCarts, Set boOrders)
/*     */   {
/*  40 */     this.goodsTitle = goodsTitle;
/*  41 */     this.goodsIntroduce = goodsIntroduce;
/*  42 */     this.goodsPrice = goodsPrice;
/*  43 */     this.goodsColor = goodsColor;
/*  44 */     this.goodsSize = goodsSize;
/*  45 */     this.workingVoltage = workingVoltage;
/*  46 */     this.powerConsumption = powerConsumption;
/*  47 */     this.materialGoods = materialGoods;
/*  48 */     this.communicatuinMode = communicatuinMode;
/*  49 */     this.workingTemperature = workingTemperature;
/*  50 */     this.workingHumidity = workingHumidity;
/*  51 */     this.picturesShow = picturesShow;
/*  52 */     this.salesVolumeDegree = salesVolumeDegree;
/*  53 */     this.boShoppingCarts = boShoppingCarts;
/*  54 */     this.boOrders = boOrders;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getGoodsTitle() {
/*  69 */     return this.goodsTitle;
/*     */   }
/*     */ 
/*     */   public void setGoodsTitle(String goodsTitle) {
/*  73 */     this.goodsTitle = goodsTitle;
/*     */   }
/*     */ 
/*     */   public String getGoodsIntroduce() {
/*  77 */     return this.goodsIntroduce;
/*     */   }
/*     */ 
/*     */   public void setGoodsIntroduce(String goodsIntroduce) {
/*  81 */     this.goodsIntroduce = goodsIntroduce;
/*     */   }
/*     */ 
/*     */   public Double getGoodsPrice() {
/*  85 */     return this.goodsPrice;
/*     */   }
/*     */ 
/*     */   public void setGoodsPrice(Double goodsPrice) {
/*  89 */     this.goodsPrice = goodsPrice;
/*     */   }
/*     */ 
/*     */   public String getGoodsColor() {
/*  93 */     return this.goodsColor;
/*     */   }
/*     */ 
/*     */   public void setGoodsColor(String goodsColor) {
/*  97 */     this.goodsColor = goodsColor;
/*     */   }
/*     */ 
/*     */   public String getGoodsSize() {
/* 101 */     return this.goodsSize;
/*     */   }
/*     */ 
/*     */   public void setGoodsSize(String goodsSize) {
/* 105 */     this.goodsSize = goodsSize;
/*     */   }
/*     */ 
/*     */   public String getWorkingVoltage() {
/* 109 */     return this.workingVoltage;
/*     */   }
/*     */ 
/*     */   public void setWorkingVoltage(String workingVoltage) {
/* 113 */     this.workingVoltage = workingVoltage;
/*     */   }
/*     */ 
/*     */   public String getPowerConsumption() {
/* 117 */     return this.powerConsumption;
/*     */   }
/*     */ 
/*     */   public void setPowerConsumption(String powerConsumption) {
/* 121 */     this.powerConsumption = powerConsumption;
/*     */   }
/*     */ 
/*     */   public String getMaterialGoods() {
/* 125 */     return this.materialGoods;
/*     */   }
/*     */ 
/*     */   public void setMaterialGoods(String materialGoods) {
/* 129 */     this.materialGoods = materialGoods;
/*     */   }
/*     */ 
/*     */   public String getCommunicatuinMode() {
/* 133 */     return this.communicatuinMode;
/*     */   }
/*     */ 
/*     */   public void setCommunicatuinMode(String communicatuinMode) {
/* 137 */     this.communicatuinMode = communicatuinMode;
/*     */   }
/*     */ 
/*     */   public String getWorkingTemperature() {
/* 141 */     return this.workingTemperature;
/*     */   }
/*     */ 
/*     */   public void setWorkingTemperature(String workingTemperature) {
/* 145 */     this.workingTemperature = workingTemperature;
/*     */   }
/*     */ 
/*     */   public String getWorkingHumidity() {
/* 149 */     return this.workingHumidity;
/*     */   }
/*     */ 
/*     */   public void setWorkingHumidity(String workingHumidity) {
/* 153 */     this.workingHumidity = workingHumidity;
/*     */   }
/*     */ 
/*     */   public String getPicturesShow() {
/* 157 */     return this.picturesShow;
/*     */   }
/*     */ 
/*     */   public void setPicturesShow(String picturesShow) {
/* 161 */     this.picturesShow = picturesShow;
/*     */   }
/*     */ 
/*     */   public Integer getSalesVolumeDegree()
/*     */   {
/* 166 */     return this.salesVolumeDegree;
/*     */   }
/*     */ 
/*     */   public void setSalesVolumeDegree(Integer salesVolumeDegree) {
/* 170 */     this.salesVolumeDegree = salesVolumeDegree;
/*     */   }
/*     */ 
/*     */   public Set getBoShoppingCarts() {
/* 174 */     return this.boShoppingCarts;
/*     */   }
/*     */ 
/*     */   public void setBoShoppingCarts(Set boShoppingCarts) {
/* 178 */     this.boShoppingCarts = boShoppingCarts;
/*     */   }
/*     */ 
/*     */   public Set getBoOrders()
/*     */   {
/* 183 */     return this.boOrders;
/*     */   }
/*     */ 
/*     */   public void setBoOrders(Set boOrders) {
/* 187 */     this.boOrders = boOrders;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoGoods
 * JD-Core Version:    0.6.2
 */