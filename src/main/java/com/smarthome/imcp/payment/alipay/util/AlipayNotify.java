/*     */ package com.smarthome.imcp.payment.alipay.util;
/*     */ 
/*     */ import com.smarthome.imcp.payment.alipay.config.AlipayConfig;
/*     */ import com.smarthome.imcp.payment.alipay.sign.RSA;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AlipayNotify
/*     */ {
/*     */   private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
/*     */ 
/*     */   public static boolean verify(Map<String, String> params)
/*     */   {
/*  42 */     String responseTxt = "true";
/*  43 */     if (params.get("notify_id") != null) {
/*  44 */       String notify_id = (String)params.get("notify_id");
/*  45 */       responseTxt = verifyResponse(notify_id);
/*     */     }
/*  47 */     String sign = "";
/*  48 */     if (params.get("sign") != null) sign = (String)params.get("sign");
/*  49 */     boolean isSign = getSignVeryfy(params, sign);
/*     */ 
/*  55 */     if ((isSign) && (responseTxt.equals("true"))) {
/*  56 */       return true;
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean getSignVeryfy(Map<String, String> Params, String sign)
/*     */   {
/*  70 */     Map sParaNew = AlipayCore.paraFilter(Params);
/*     */ 
/*  72 */     String preSignStr = AlipayCore.createLinkString(sParaNew);
/*     */ 
/*  74 */     boolean isSign = false;
/*  75 */     if (AlipayConfig.sign_type.equals("RSA")) {
/*  76 */       isSign = RSA.verify(preSignStr, sign, AlipayConfig.ali_public_key, AlipayConfig.input_charset);
/*     */     }
/*  78 */     return isSign;
/*     */   }
/*     */ 
/*     */   private static String verifyResponse(String notify_id)
/*     */   {
/*  93 */     String partner = AlipayConfig.partner;
/*  94 */     String veryfy_url = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner=" + partner + "&notify_id=" + notify_id;
/*     */ 
/*  96 */     return checkUrl(veryfy_url);
/*     */   }
/*     */ 
/*     */   private static String checkUrl(String urlvalue)
/*     */   {
/* 109 */     String inputLine = "";
/*     */     try
/*     */     {
/* 112 */       URL url = new URL(urlvalue);
/* 113 */       HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
/* 114 */       BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
/* 115 */         .getInputStream()));
/* 116 */       inputLine = in.readLine().toString();
/*     */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */       inputLine = "";
/*     */     }
/*     */ 
/* 122 */     return inputLine;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.payment.alipay.util.AlipayNotify
 * JD-Core Version:    0.6.2
 */