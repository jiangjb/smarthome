/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.security.Key;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ public class DesEncrypt
/*     */ {
/*     */   Key key;
/*     */ 
/*     */   public void getKey(String strKey)
/*     */   {
/*     */     try
/*     */     {
/*  34 */       KeyGenerator _generator = KeyGenerator.getInstance("DES");
/*  35 */       _generator.init(new SecureRandom(strKey.getBytes()));
/*  36 */       this.key = _generator.generateKey();
/*  37 */       _generator = null;
/*     */     } catch (Exception e) {
/*  39 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getEncString(String strMing)
/*     */   {
/*  50 */     byte[] byteMi = null;
/*  51 */     byte[] byteMing = null;
/*  52 */     String strMi = "";
/*  53 */     BASE64Encoder base64en = new BASE64Encoder();
/*     */     try {
/*  55 */       byteMing = strMing.getBytes("UTF8");
/*  56 */       byteMi = getEncCode(byteMing);
/*  57 */       strMi = base64en.encode(byteMi);
/*     */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     } finally {
/*  61 */       base64en = null;
/*  62 */       byteMing = null;
/*  63 */       byteMi = null;
/*     */     }
/*  65 */     return strMi;
/*     */   }
/*     */ 
/*     */   public String getDesString(String strMi)
/*     */   {
/*  75 */     BASE64Decoder base64De = new BASE64Decoder();
/*  76 */     byte[] byteMing = null;
/*  77 */     byte[] byteMi = null;
/*  78 */     String strMing = "";
/*     */     try {
/*  80 */       byteMi = base64De.decodeBuffer(strMi);
/*  81 */       byteMing = getDesCode(byteMi);
/*  82 */       strMing = new String(byteMing, "UTF8");
/*     */     } catch (Exception e) {
/*  84 */       e.printStackTrace();
/*     */     } finally {
/*  86 */       base64De = null;
/*  87 */       byteMing = null;
/*  88 */       byteMi = null;
/*     */     }
/*  90 */     return strMing;
/*     */   }
/*     */ 
/*     */   private byte[] getEncCode(byte[] byteS)
/*     */   {
/* 100 */     byte[] byteFina = null;
/*     */     Cipher cipher;
///*     */     Cipher cipher;
/*     */     try
/*     */     {
///* 103 */       Cipher cipher = Cipher.getInstance("DES");
				cipher = Cipher.getInstance("DES");
/* 104 */       cipher.init(1, this.key);
/* 105 */       byteFina = cipher.doFinal(byteS);
/*     */     } catch (Exception e) {
/* 107 */       e.printStackTrace();
/*     */     }
/*     */     finally
/*     */     {
///*     */       Cipher cipher;
/* 109 */       cipher = null;
/*     */     }
/* 111 */     return byteFina;
/*     */   }
/*     */ 
/*     */   private byte[] getDesCode(byte[] byteD)
/*     */   {
/* 122 */     byte[] byteFina = null;
/*     */     Cipher cipher;
///*     */     Cipher cipher;
/*     */     try
/*     */     {
///* 124 */       Cipher cipher = Cipher.getInstance("DES");
				cipher = Cipher.getInstance("DES");
/* 125 */       cipher.init(2, this.key);
/* 126 */       byteFina = cipher.doFinal(byteD);
/*     */     } catch (Exception e) {
/* 128 */       e.printStackTrace();
/*     */     }
/*     */     finally
/*     */     {
///*     */       Cipher cipher;
/* 130 */       cipher = null;
/*     */     }
/* 132 */     return byteFina;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 138 */     System.out.println("hello");
/* 139 */     DesEncrypt des = new DesEncrypt();
/* 140 */     des.getKey("aadd");
/*     */ 
/* 142 */     String strEnc = des.getEncString("钟汉康");
/* 143 */     System.out.println(strEnc);
/*     */ 
/* 145 */     String strDes = des.getDesString(strEnc);
/* 146 */     System.out.println(strDes);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.DesEncrypt
 * JD-Core Version:    0.6.2
 */