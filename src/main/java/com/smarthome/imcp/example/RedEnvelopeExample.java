/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.APIConnectionException;
/*     */ import com.pingplusplus.exception.APIException;
/*     */ import com.pingplusplus.exception.AuthenticationException;
/*     */ import com.pingplusplus.exception.ChannelException;
/*     */ import com.pingplusplus.exception.InvalidRequestException;
/*     */ import com.pingplusplus.model.RedEnvelope;
/*     */ import com.pingplusplus.model.RedEnvelopeCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RedEnvelopeExample
/*     */ {
/*  36 */   public static String apiKey = "YOUR-KEY";
/*     */ 
/*  40 */   public static String appId = "YOUR-APPID";
/*     */ 
/*  44 */   public static String openId = "YOUR-OPENID";
/*     */ 
/*     */   public static void main(String[] args) {
/*  47 */     com.pingplusplus.Pingpp.apiKey = apiKey;
/*  48 */     RedEnvelopeExample redEnvelopeExample = new RedEnvelopeExample();
/*  49 */     System.out.println("---------创建 RedEnvelope");
/*  50 */     RedEnvelope redEnvelope = redEnvelopeExample.create();
/*  51 */     System.out.println("---------查询 RedEnvelope");
/*  52 */     redEnvelopeExample.retrieve(redEnvelope.getId());
/*  53 */     System.out.println("---------查询 RedEnvelope 列表");
/*  54 */     redEnvelopeExample.all();
/*     */   }
/*     */ 
/*     */   public RedEnvelope create()
/*     */   {
/*  66 */     Map redenvelope = new HashMap();
/*  67 */     redenvelope.put("amount", Integer.valueOf(100));
/*  68 */     redenvelope.put("currency", "cny");
/*  69 */     redenvelope.put("subject", "Your Subject");
/*  70 */     redenvelope.put("body", "Your Body");
/*  71 */     String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
/*  72 */     redenvelope.put("order_no", orderNo);
/*  73 */     redenvelope.put("channel", "wx_pub");
/*  74 */     redenvelope.put("recipient", openId);
/*  75 */     redenvelope.put("description", "Your Description");
/*  76 */     Map app = new HashMap();
/*  77 */     app.put("id", appId);
/*  78 */     redenvelope.put("app", app);
/*  79 */     Map extra = new HashMap();
/*  80 */     extra.put("nick_name", "Nick Name");
/*  81 */     extra.put("send_name", "Send Name");
/*  82 */     redenvelope.put("extra", extra);
/*  83 */     RedEnvelope red = null;
/*     */     try {
/*  85 */       red = RedEnvelope.create(redenvelope);
/*  86 */       System.out.println(red);
/*     */     } catch (AuthenticationException e) {
/*  88 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/*  90 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/*  92 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/*  97 */       e.printStackTrace();
/*     */     }
/*  99 */     return red;
/*     */   }
/*     */ 
/*     */   public void retrieve(String id)
/*     */   {
/*     */     try
/*     */     {
/* 112 */       RedEnvelope redEnvelope = RedEnvelope.retrieve(id);
/* 113 */       System.out.println(redEnvelope);
/*     */     } catch (AuthenticationException e) {
/* 115 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 117 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 119 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 121 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 124 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void all()
/*     */   {
/* 134 */     RedEnvelopeCollection redEnvelopeCollection = null;
/* 135 */     Map chargeParams = new HashMap();
/* 136 */     chargeParams.put("limit", Integer.valueOf(3));
/*     */     try {
/* 138 */       redEnvelopeCollection = RedEnvelope.all(chargeParams);
/* 139 */       System.out.println(redEnvelopeCollection);
/*     */     } catch (AuthenticationException e) {
/* 141 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 143 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 145 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 147 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 150 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.RedEnvelopeExample
 * JD-Core Version:    0.6.2
 */