/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.APIConnectionException;
/*     */ import com.pingplusplus.exception.APIException;
/*     */ import com.pingplusplus.exception.AuthenticationException;
/*     */ import com.pingplusplus.exception.ChannelException;
/*     */ import com.pingplusplus.exception.InvalidRequestException;
/*     */ import com.pingplusplus.model.App;
/*     */ import com.pingplusplus.model.Transfer;
/*     */ import com.pingplusplus.model.TransferCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TransferExample
/*     */ {
/*  41 */   public static String apiKey = "YOUR-KEY";
/*     */ 
/*  45 */   public static String appId = "YOUR-APPID";
/*     */ 
/*  50 */   public static String openId = "OPEN_ID";
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  54 */     com.pingplusplus.Pingpp.apiKey = apiKey;
/*  55 */     TransferExample transferExample = new TransferExample();
/*  56 */     System.out.println("---------创建Transfer");
/*  57 */     Transfer transfer = transferExample.transfer();
/*  58 */     System.out.println("---------查询Transfer");
/*  59 */     transferExample.retrieve(transfer.getId());
/*  60 */     System.out.println("---------查询Transfer列表");
/*  61 */     transferExample.all();
/*     */   }
/*     */ 
/*     */   public Transfer transfer()
/*     */   {
/*  74 */     Transfer transfer = null;
/*  75 */     String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
/*  76 */     Map transferMap = new HashMap();
/*  77 */     transferMap.put("channel", "wx_pub");
/*  78 */     transferMap.put("order_no", orderNo);
/*  79 */     transferMap.put("amount", "10");
/*  80 */     transferMap.put("type", "b2c");
/*  81 */     transferMap.put("currency", "cny");
/*  82 */     transferMap.put("recipient", openId);
/*  83 */     transferMap.put("description", "your description");
/*  84 */     Map app = new HashMap();
/*  85 */     app.put("id", appId);
/*  86 */     transferMap.put("app", app);
/*     */     try
/*     */     {
/*  89 */       transfer = Transfer.create(transferMap);
/*  90 */       System.out.println(transfer);
/*     */     } catch (AuthenticationException e) {
/*  92 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/*  94 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/*  96 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/*  98 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 101 */       e.printStackTrace();
/*     */     }
/* 103 */     return transfer;
/*     */   }
/*     */ 
/*     */   public void retrieve(String id)
/*     */   {
/* 114 */     Map param = new HashMap();
/* 115 */     List expande = new ArrayList();
/* 116 */     expande.add("app");
/* 117 */     param.put("expand", expande);
/*     */     try {
/* 119 */       Transfer transfer = Transfer.retrieve(id, param);
/*     */ 
/* 122 */       if ((transfer.getApp() instanceof App)) {
/* 123 */         App app = (App)transfer.getApp();
/* 124 */         System.out.println("App Object ,appId = " + app.getId());
/*     */       } else {
/* 126 */         System.out.println("String ,appId = " + transfer.getApp());
/*     */       }
/* 128 */       System.out.println(transfer);
/*     */     } catch (AuthenticationException e) {
/* 130 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 132 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 134 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 136 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 139 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void all()
/*     */   {
/* 150 */     Map parm = new HashMap();
/* 151 */     parm.put("limit", Integer.valueOf(3));
/*     */     try
/*     */     {
/* 157 */       TransferCollection transferCollection = Transfer.all(parm);
/* 158 */       System.out.println(transferCollection);
/*     */     } catch (AuthenticationException e) {
/* 160 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 162 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 164 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 166 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 169 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.TransferExample
 * JD-Core Version:    0.6.2
 */