/*     */ package com.smarthome.imcp.payment.alipay.util;
/*     */ 
/*     */ import com.smarthome.imcp.payment.alipay.config.AlipayConfig;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.apache.commons.httpclient.methods.multipart.FilePartSource;
/*     */ import org.apache.commons.httpclient.methods.multipart.PartSource;
/*     */ 
/*     */ public class AlipayCore
/*     */ {
/*     */   public static Map<String, String> paraFilter(Map<String, String> sArray)
/*     */   {
/*  37 */     Map result = new HashMap();
/*     */ 
/*  39 */     if ((sArray == null) || (sArray.size() <= 0)) {
/*  40 */       return result;
/*     */     }
/*     */ 
/*  43 */     for (String key : sArray.keySet()) {
/*  44 */       String value = (String)sArray.get(key);
/*  45 */       if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) && 
/*  46 */         (!key.equalsIgnoreCase("sign_type")))
/*     */       {
/*  49 */         result.put(key, value);
/*     */       }
/*     */     }
/*  52 */     return result;
/*     */   }
/*     */ 
/*     */   public static String createLinkString(Map<String, String> params)
/*     */   {
/*  62 */     List keys = new ArrayList(params.keySet());
/*  63 */     Collections.sort(keys);
/*     */ 
/*  65 */     String prestr = "";
/*     */ 
/*  67 */     for (int i = 0; i < keys.size(); i++) {
/*  68 */       String key = (String)keys.get(i);
/*  69 */       String value = (String)params.get(key);
/*     */ 
/*  71 */       if (i == keys.size() - 1)
/*  72 */         prestr = prestr + key + "=" + value;
/*     */       else {
/*  74 */         prestr = prestr + key + "=" + value + "&";
/*     */       }
/*     */     }
/*     */ 
/*  78 */     return prestr;
/*     */   }
/*     */ 
/*     */   public static void logResult(String sWord)
/*     */   {
/*  86 */     FileWriter writer = null;
/*     */     try {
/*  88 */       writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
/*  89 */       writer.write(sWord);
/*     */     } catch (Exception e) {
/*  91 */       e.printStackTrace();
/*     */ 
/*  93 */       if (writer != null)
/*     */         try {
/*  95 */           writer.close();
/*     */         } catch (IOException e1) {
/*  97 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/*  93 */       if (writer != null)
/*     */         try {
/*  95 */           writer.close();
/*     */         } catch (IOException e) {
/*  97 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getAbstract(String strFilePath, String file_digest_type)
/*     */     throws IOException
/*     */   {
/* 110 */     PartSource file = new FilePartSource(new File(strFilePath));
/* 111 */     if (file_digest_type.equals("MD5")) {
/* 112 */       return DigestUtils.md5Hex(file.createInputStream());
/*     */     }
/* 114 */     if (file_digest_type.equals("SHA")) {
/* 115 */       return DigestUtils.sha256Hex(file.createInputStream());
/*     */     }
/*     */ 
/* 118 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.payment.alipay.util.AlipayCore
 * JD-Core Version:    0.6.2
 */