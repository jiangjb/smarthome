/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoOrder
/*     */   implements Serializable
/*     */ {
/*     */   private Integer orderId;
/*     */   private BoGoods boGoods;
/*     */   private BoUsers boUsers;
/*     */   private String orderNo;
/*     */   private String tradeNo;
/*     */   private String tradeMoney;
/*     */   private String recipentName;
/*     */   private String recipentPhone;
/*     */   private String recipentAddress;
/*     */   private String paymentTime;
/*     */   private Integer payChannel;
/*     */   private Boolean orderState;
/*     */ 
/*     */   public BoOrder()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoOrder(BoGoods boGoods, BoUsers boUsers, String orderNo, String tradeNo, String tradeMoney, String recipentName, String recipentPhone, String recipentAddress, String paymentTime, Integer payChannel, Boolean orderState)
/*     */   {
/*  37 */     this.boGoods = boGoods;
/*  38 */     this.boUsers = boUsers;
/*  39 */     this.orderNo = orderNo;
/*  40 */     this.tradeNo = tradeNo;
/*  41 */     this.tradeMoney = tradeMoney;
/*  42 */     this.recipentName = recipentName;
/*  43 */     this.recipentPhone = recipentPhone;
/*  44 */     this.recipentAddress = recipentAddress;
/*  45 */     this.paymentTime = paymentTime;
/*  46 */     this.payChannel = payChannel;
/*  47 */     this.orderState = orderState;
/*     */   }
/*     */ 
/*     */   public Integer getOrderId()
/*     */   {
/*  53 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(Integer orderId) {
/*  57 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   public BoGoods getBoGoods() {
/*  61 */     return this.boGoods;
/*     */   }
/*     */ 
/*     */   public void setBoGoods(BoGoods boGoods) {
/*  65 */     this.boGoods = boGoods;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  69 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  73 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public String getOrderNo() {
/*  77 */     return this.orderNo;
/*     */   }
/*     */ 
/*     */   public void setOrderNo(String orderNo) {
/*  81 */     this.orderNo = orderNo;
/*     */   }
/*     */ 
/*     */   public String getTradeNo() {
/*  85 */     return this.tradeNo;
/*     */   }
/*     */ 
/*     */   public void setTradeNo(String tradeNo) {
/*  89 */     this.tradeNo = tradeNo;
/*     */   }
/*     */ 
/*     */   public String getTradeMoney() {
/*  93 */     return this.tradeMoney;
/*     */   }
/*     */ 
/*     */   public void setTradeMoney(String tradeMoney) {
/*  97 */     this.tradeMoney = tradeMoney;
/*     */   }
/*     */ 
/*     */   public String getRecipentName() {
/* 101 */     return this.recipentName;
/*     */   }
/*     */ 
/*     */   public void setRecipentName(String recipentName) {
/* 105 */     this.recipentName = recipentName;
/*     */   }
/*     */ 
/*     */   public String getRecipentPhone() {
/* 109 */     return this.recipentPhone;
/*     */   }
/*     */ 
/*     */   public void setRecipentPhone(String recipentPhone) {
/* 113 */     this.recipentPhone = recipentPhone;
/*     */   }
/*     */ 
/*     */   public String getRecipentAddress() {
/* 117 */     return this.recipentAddress;
/*     */   }
/*     */ 
/*     */   public void setRecipentAddress(String recipentAddress) {
/* 121 */     this.recipentAddress = recipentAddress;
/*     */   }
/*     */ 
/*     */   public String getPaymentTime() {
/* 125 */     return this.paymentTime;
/*     */   }
/*     */ 
/*     */   public void setPaymentTime(String paymentTime) {
/* 129 */     this.paymentTime = paymentTime;
/*     */   }
/*     */ 
/*     */   public Integer getPayChannel() {
/* 133 */     return this.payChannel;
/*     */   }
/*     */ 
/*     */   public void setPayChannel(Integer payChannel) {
/* 137 */     this.payChannel = payChannel;
/*     */   }
/*     */ 
/*     */   public Boolean getOrderState() {
/* 141 */     return this.orderState;
/*     */   }
/*     */ 
/*     */   public void setOrderState(Boolean orderState) {
/* 145 */     this.orderState = orderState;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoOrder
 * JD-Core Version:    0.6.2
 */