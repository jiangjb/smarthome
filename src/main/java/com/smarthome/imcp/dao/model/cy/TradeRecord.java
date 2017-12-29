/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class TradeRecord
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private Integer userId;
/*     */   private String userCode;
/*     */   private String tradeNo;
/*     */   private Double tradeMoney;
/*     */   private Date tradeTime;
/*     */   private Date notifyTime;
/*     */   private String code;
/*     */   private String name;
/*     */   private String sign;
/*     */   private Date addTime;
/*     */   private String addIp;
/*     */   private String orderNo;
/*     */   private Integer status;
/*     */   private String reqParam;
/*     */   private BoUser boUser;
/*     */   private String userPhone;
/*     */ 
/*     */   public TradeRecord()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TradeRecord(Integer userId, String tradeNo, Double tradeMoney, Date tradeTime, Date notifyTime, String code, String name, String sign, Date addTime, String addIp)
/*     */   {
/*  53 */     this.userId = userId;
/*  54 */     this.tradeNo = tradeNo;
/*  55 */     this.tradeMoney = tradeMoney;
/*  56 */     this.tradeTime = tradeTime;
/*  57 */     this.notifyTime = notifyTime;
/*  58 */     this.code = code;
/*  59 */     this.name = name;
/*  60 */     this.sign = sign;
/*  61 */     this.addTime = addTime;
/*  62 */     this.addIp = addIp;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  67 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Integer getUserId() {
/*  75 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer userId) {
/*  79 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public String getTradeNo() {
/*  83 */     return this.tradeNo;
/*     */   }
/*     */ 
/*     */   public void setTradeNo(String tradeNo) {
/*  87 */     this.tradeNo = tradeNo;
/*     */   }
/*     */ 
/*     */   public Double getTradeMoney() {
/*  91 */     return this.tradeMoney;
/*     */   }
/*     */ 
/*     */   public void setTradeMoney(Double tradeMoney) {
/*  95 */     this.tradeMoney = tradeMoney;
/*     */   }
/*     */ 
/*     */   public Date getTradeTime() {
/*  99 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(Date tradeTime) {
/* 103 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   public Date getNotifyTime() {
/* 107 */     return this.notifyTime;
/*     */   }
/*     */ 
/*     */   public void setNotifyTime(Date notifyTime) {
/* 111 */     this.notifyTime = notifyTime;
/*     */   }
/*     */ 
/*     */   public String getCode() {
/* 115 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code) {
/* 119 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 123 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 127 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getSign() {
/* 131 */     return this.sign;
/*     */   }
/*     */ 
/*     */   public void setSign(String sign) {
/* 135 */     this.sign = sign;
/*     */   }
/*     */ 
/*     */   public Date getAddTime() {
/* 139 */     return this.addTime;
/*     */   }
/*     */ 
/*     */   public void setAddTime(Date addTime) {
/* 143 */     this.addTime = addTime;
/*     */   }
/*     */ 
/*     */   public String getAddIp() {
/* 147 */     return this.addIp;
/*     */   }
/*     */ 
/*     */   public void setAddIp(String addIp) {
/* 151 */     this.addIp = addIp;
/*     */   }
/*     */ 
/*     */   public String getOrderNo() {
/* 155 */     return this.orderNo;
/*     */   }
/*     */ 
/*     */   public void setOrderNo(String orderNo) {
/* 159 */     this.orderNo = orderNo;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/* 163 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 167 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/* 171 */     return this.userCode;
/*     */   }
/*     */   public void setUserCode(String userCode) {
/* 174 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public String getReqParam() {
/* 178 */     return this.reqParam;
/*     */   }
/*     */ 
/*     */   public void setReqParam(String reqParam) {
/* 182 */     this.reqParam = reqParam;
/*     */   }
/*     */ 
/*     */   public BoUser getBoUser()
/*     */   {
/* 189 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser)
/*     */   {
/* 196 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public String getUserPhone()
/*     */   {
/* 203 */     return this.boUser.getUserPhone();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.TradeRecord
 * JD-Core Version:    0.6.2
 */