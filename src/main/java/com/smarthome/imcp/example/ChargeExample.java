/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.APIConnectionException;
/*     */ import com.pingplusplus.exception.APIException;
/*     */ import com.pingplusplus.exception.AuthenticationException;
/*     */ import com.pingplusplus.exception.ChannelException;
/*     */ import com.pingplusplus.exception.InvalidRequestException;
/*     */ import com.pingplusplus.exception.PingppException;
/*     */ import com.pingplusplus.model.App;
/*     */ import com.pingplusplus.model.Charge;
/*     */ import com.pingplusplus.model.ChargeCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ChargeExample
/*     */ {
/*  36 */   public static String apiKey = "sk_live_0CG88GOWHKG4TqTifDC0ivLK";
/*     */ 
/*  40 */   public static String appId = "app_8aDS0OXXLqbDyLCS";
/*     */ 
/*     */   public static void main(String[] args) {
/*  43 */     com.pingplusplus.Pingpp.apiKey = apiKey;
/*  44 */     ChargeExample ce = new ChargeExample();
/*  45 */     System.out.println("---------创建 charge");
/*  46 */     Charge charge = ce.charge();
/*  47 */     System.out.println("---------查询 charge");
/*  48 */     ce.retrieve(charge.getId());
/*  49 */     System.out.println("---------查询 charge列表");
/*  50 */     ce.all();
/*     */   }
/*     */ 
/*     */   public Charge charge()
/*     */   {
/*  61 */     Charge charge = null;
/*  62 */     Map chargeMap = new HashMap();
/*     */ 
/*  70 */     Map app = new HashMap();
/*  71 */     app.put("id", appId);
/*  72 */     chargeMap.put("app", app);
/*     */     try
/*     */     {
/*  75 */       charge = Charge.create(chargeMap);
/*  76 */       System.out.println(charge);
/*     */     } catch (PingppException e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*  80 */     return charge;
/*     */   }
/*     */ 
/*     */   public void retrieve(String id)
/*     */   {
/*     */     try
/*     */     {
/*  95 */       Map param = new HashMap();
/*  96 */       List expande = new ArrayList();
/*  97 */       expande.add("app");
/*  98 */       param.put("expand", expande);
/*     */ 
/* 101 */       Charge charge = Charge.retrieve(id, param);
///* 102 */       (charge.getApp() instanceof App);
				if(charge.getApp() instanceof App)
/*     */ 
/* 109 */       	System.out.println(charge);
/*     */     }
/*     */     catch (PingppException e) {
/* 112 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ChargeCollection all()
/*     */   {
/* 126 */     ChargeCollection chargeCollection = null;
/* 127 */     Map chargeParams = new HashMap();
/* 128 */     chargeParams.put("limit", Integer.valueOf(3));
/*     */     try
/*     */     {
/* 136 */       chargeCollection = Charge.all(chargeParams);
/* 137 */       System.out.println(chargeCollection);
/*     */     } catch (AuthenticationException e) {
/* 139 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 141 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 143 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 145 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 148 */       e.printStackTrace();
/*     */     }
/* 150 */     return chargeCollection;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.ChargeExample
 * JD-Core Version:    0.6.2
 */