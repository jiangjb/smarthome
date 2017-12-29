/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.APIConnectionException;
/*     */ import com.pingplusplus.exception.APIException;
/*     */ import com.pingplusplus.exception.AuthenticationException;
/*     */ import com.pingplusplus.exception.ChannelException;
/*     */ import com.pingplusplus.exception.InvalidRequestException;
/*     */ import com.pingplusplus.model.Charge;
/*     */ import com.pingplusplus.model.ChargeRefundCollection;
/*     */ import com.pingplusplus.model.Refund;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RefundsExample
/*     */ {
/*  33 */   public static String apiKey = "YOUR-KEY";
/*     */ 
/*  37 */   public static String chargeId = "YOUR-CHARGEID";
/*     */ 
/*  39 */   public static void main(String[] args) { com.pingplusplus.Pingpp.apiKey = apiKey;
/*  40 */     RefundsExample refundsExample = new RefundsExample();
/*     */ 
/*  42 */     Charge ch = null;
/*     */     try {
/*  44 */       ch = Charge.retrieve(chargeId);
/*     */     } catch (AuthenticationException e) {
/*  46 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/*  48 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/*  50 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/*  52 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/*  55 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  63 */     System.out.println("---------查询refund列表");
/*  64 */     refundsExample.all(ch);
/*     */   }
/*     */ 
/*     */   public Refund refund(Charge charge)
/*     */   {
/*  79 */     Refund refund = null;
/*  80 */     Map params = new HashMap();
/*  81 */     params.put("description", "苹果被咬了一口。");
/*     */     try
/*     */     {
/*  84 */       refund = charge.getRefunds().create(params);
/*  85 */       System.out.println(refund);
/*     */     } catch (AuthenticationException e) {
/*  87 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/*  89 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/*  91 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/*  93 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*  98 */     return refund;
/*     */   }
/*     */ 
/*     */   public void retrieve(String id, Charge charge)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       Refund refund = charge.getRefunds().retrieve(id);
/* 114 */       System.out.println(refund);
/*     */     } catch (AuthenticationException e) {
/* 116 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 118 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 120 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 122 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 125 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void all(Charge charge)
/*     */   {
/* 138 */     Map refundParams = new HashMap();
/* 139 */     refundParams.put("limit", Integer.valueOf(3));
/*     */     try {
/* 141 */       ChargeRefundCollection refunds = charge.getRefunds().all(refundParams);
/* 142 */       System.out.println(refunds);
/*     */     } catch (AuthenticationException e) {
/* 144 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 146 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 148 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 150 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 153 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.RefundsExample
 * JD-Core Version:    0.6.2
 */