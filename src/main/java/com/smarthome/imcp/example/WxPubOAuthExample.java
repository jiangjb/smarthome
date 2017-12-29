/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.PingppException;
/*     */ import com.pingplusplus.model.Charge;
/*     */ import com.pingplusplus.util.WxpubOAuth;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WxPubOAuthExample
/*     */ {
/*  30 */   public static String apiKey = "YOUR-KEY";
/*     */ 
/*  34 */   public static String appId = "YOUR-APPID";
/*     */ 
/*  38 */   public static String openId = "YOUR-OPENID";
/*     */ 
/*  43 */   public static String url = "YOUR-URL";
/*     */ 
/*  47 */   public static String wx_app_id = "YOUR-WX_APPID";
/*     */ 
/*  51 */   public static String wx_app_secret = "YOUR-WX_SECRET";
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  59 */       String ticket = WxpubOAuth.getJsapiTicket(wx_app_id, wx_app_secret);
/*  60 */       System.out.println("ticket " + ticket);
/*     */ 
/*  62 */       com.pingplusplus.Pingpp.apiKey = apiKey;
/*  63 */       Charge charge = charge();
/*     */ 
/*  65 */       String signature = WxpubOAuth.getSignature(charge.toString(), ticket, url);
/*  66 */       System.out.println("signature " + signature);
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/*  69 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Charge charge()
/*     */   {
/*  81 */     Charge charge = null;
/*  82 */     Map chargeMap = new HashMap();
/*  83 */     chargeMap.put("amount", Integer.valueOf(100));
/*  84 */     chargeMap.put("currency", "cny");
/*  85 */     chargeMap.put("subject", "Your Subject");
/*  86 */     chargeMap.put("body", "Your Body");
/*  87 */     chargeMap.put("order_no", "123456789");
/*  88 */     chargeMap.put("channel", "wx_pub");
/*  89 */     chargeMap.put("client_ip", "127.0.0.1");
/*     */ 
/*  91 */     Map extra = new HashMap();
/*  92 */     extra.put("open_id", openId);
/*  93 */     chargeMap.put("extra", extra);
/*  94 */     Map app = new HashMap();
/*  95 */     app.put("id", appId);
/*  96 */     chargeMap.put("app", app);
/*     */     try
/*     */     {
/*  99 */       charge = Charge.create(chargeMap);
/* 100 */       System.out.println(charge);
/*     */     } catch (PingppException e) {
/* 102 */       e.printStackTrace();
/*     */     }
/* 104 */     return charge;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.WxPubOAuthExample
 * JD-Core Version:    0.6.2
 */