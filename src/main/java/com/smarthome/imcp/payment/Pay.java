/*     */ package com.smarthome.imcp.payment;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Pay
/*     */ {
/*     */   private Date notifyTime;
/*     */   private String notifyType;
/*     */   private String notifyId;
/*     */   private String signType;
/*     */   private String sign;
/*     */   private String outTradeNo;
/*     */   private String subject;
/*     */   private String paymentType;
/*     */   private String tradeNO;
/*     */   private String tradeStatus;
/*     */   private String sellerId;
/*     */   private String sellerEmail;
/*     */   private String email;
/*     */   private String buyerId;
/*     */   private String buyerEmail;
/*     */   private double total_fee;
/*     */   private int quantity;
/*     */   private double price;
/*     */   private String body;
/*     */   private Date gmtCreate;
/*     */   private Date gmtPayment;
/*     */   private String isTotalFeeAdjust;
/*     */   private String useCoupon;
/*     */   private String discount;
/*     */   private String refundStatus;
/*     */   private String gmtRefund;
/*     */ 
/*     */   public Date getNotifyTime()
/*     */   {
/*  37 */     return this.notifyTime;
/*     */   }
/*     */   public void setNotifyTime(Date notifyTime) {
/*  40 */     this.notifyTime = notifyTime;
/*     */   }
/*     */   public String getNotifyType() {
/*  43 */     return this.notifyType;
/*     */   }
/*     */   public void setNotifyType(String notifyType) {
/*  46 */     this.notifyType = notifyType;
/*     */   }
/*     */   public String getNotifyId() {
/*  49 */     return this.notifyId;
/*     */   }
/*     */   public void setNotifyId(String notifyId) {
/*  52 */     this.notifyId = notifyId;
/*     */   }
/*     */   public String getSignType() {
/*  55 */     return this.signType;
/*     */   }
/*     */   public void setSignType(String signType) {
/*  58 */     this.signType = signType;
/*     */   }
/*     */   public String getSign() {
/*  61 */     return this.sign;
/*     */   }
/*     */   public void setSign(String sign) {
/*  64 */     this.sign = sign;
/*     */   }
/*     */   public String getOutTradeNo() {
/*  67 */     return this.outTradeNo;
/*     */   }
/*     */   public void setOutTradeNo(String outTradeNo) {
/*  70 */     this.outTradeNo = outTradeNo;
/*     */   }
/*     */   public String getSubject() {
/*  73 */     return this.subject;
/*     */   }
/*     */   public void setSubject(String subject) {
/*  76 */     this.subject = subject;
/*     */   }
/*     */   public String getPaymentType() {
/*  79 */     return this.paymentType;
/*     */   }
/*     */   public void setPaymentType(String paymentType) {
/*  82 */     this.paymentType = paymentType;
/*     */   }
/*     */   public String getTradeNO() {
/*  85 */     return this.tradeNO;
/*     */   }
/*     */   public void setTradeNO(String tradeNO) {
/*  88 */     this.tradeNO = tradeNO;
/*     */   }
/*     */   public String getTradeStatus() {
/*  91 */     return this.tradeStatus;
/*     */   }
/*     */   public void setTradeStatus(String tradeStatus) {
/*  94 */     this.tradeStatus = tradeStatus;
/*     */   }
/*     */   public String getSellerId() {
/*  97 */     return this.sellerId;
/*     */   }
/*     */   public void setSellerId(String sellerId) {
/* 100 */     this.sellerId = sellerId;
/*     */   }
/*     */   public String getSellerEmail() {
/* 103 */     return this.sellerEmail;
/*     */   }
/*     */   public void setSellerEmail(String sellerEmail) {
/* 106 */     this.sellerEmail = sellerEmail;
/*     */   }
/*     */   public String getEmail() {
/* 109 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/* 112 */     this.email = email;
/*     */   }
/*     */   public String getBuyerId() {
/* 115 */     return this.buyerId;
/*     */   }
/*     */   public void setBuyerId(String buyerId) {
/* 118 */     this.buyerId = buyerId;
/*     */   }
/*     */   public String getBuyerEmail() {
/* 121 */     return this.buyerEmail;
/*     */   }
/*     */   public void setBuyerEmail(String buyerEmail) {
/* 124 */     this.buyerEmail = buyerEmail;
/*     */   }
/*     */   public double getTotal_fee() {
/* 127 */     return this.total_fee;
/*     */   }
/*     */   public void setTotal_fee(double total_fee) {
/* 130 */     this.total_fee = total_fee;
/*     */   }
/*     */   public int getQuantity() {
/* 133 */     return this.quantity;
/*     */   }
/*     */   public void setQuantity(int quantity) {
/* 136 */     this.quantity = quantity;
/*     */   }
/*     */   public double getPrice() {
/* 139 */     return this.price;
/*     */   }
/*     */   public void setPrice(double price) {
/* 142 */     this.price = price;
/*     */   }
/*     */   public String getBody() {
/* 145 */     return this.body;
/*     */   }
/*     */   public void setBody(String body) {
/* 148 */     this.body = body;
/*     */   }
/*     */   public Date getGmtCreate() {
/* 151 */     return this.gmtCreate;
/*     */   }
/*     */   public void setGmtCreate(Date gmtCreate) {
/* 154 */     this.gmtCreate = gmtCreate;
/*     */   }
/*     */   public Date getGmtPayment() {
/* 157 */     return this.gmtPayment;
/*     */   }
/*     */   public void setGmtPayment(Date gmtPayment) {
/* 160 */     this.gmtPayment = gmtPayment;
/*     */   }
/*     */   public String getIsTotalFeeAdjust() {
/* 163 */     return this.isTotalFeeAdjust;
/*     */   }
/*     */   public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
/* 166 */     this.isTotalFeeAdjust = isTotalFeeAdjust;
/*     */   }
/*     */   public String getUseCoupon() {
/* 169 */     return this.useCoupon;
/*     */   }
/*     */   public void setUseCoupon(String useCoupon) {
/* 172 */     this.useCoupon = useCoupon;
/*     */   }
/*     */   public String getDiscount() {
/* 175 */     return this.discount;
/*     */   }
/*     */   public void setDiscount(String discount) {
/* 178 */     this.discount = discount;
/*     */   }
/*     */   public String getRefundStatus() {
/* 181 */     return this.refundStatus;
/*     */   }
/*     */   public void setRefundStatus(String refundStatus) {
/* 184 */     this.refundStatus = refundStatus;
/*     */   }
/*     */   public String getGmtRefund() {
/* 187 */     return this.gmtRefund;
/*     */   }
/*     */   public void setGmtRefund(String gmtRefund) {
/* 190 */     this.gmtRefund = gmtRefund;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.payment.Pay
 * JD-Core Version:    0.6.2
 */