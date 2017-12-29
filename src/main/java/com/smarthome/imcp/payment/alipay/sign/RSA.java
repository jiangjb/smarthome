/*     */ package com.smarthome.imcp.payment.alipay.sign;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.PublicKey;
/*     */ import java.security.Signature;
/*     */ import java.security.spec.PKCS8EncodedKeySpec;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import javax.crypto.Cipher;
/*     */ 
/*     */ public class RSA
/*     */ {
/*     */   public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
/*     */ 
/*     */   public static String sign(String content, String privateKey, String input_charset)
/*     */   {
/*     */     try
/*     */     {
/*  30 */       PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
/*  31 */       KeyFactory keyf = KeyFactory.getInstance("RSA");
/*  32 */       PrivateKey priKey = keyf.generatePrivate(priPKCS8);
/*     */ 
/*  34 */       Signature signature = 
/*  35 */         Signature.getInstance("SHA1WithRSA");
/*     */ 
/*  37 */       signature.initSign(priKey);
/*  38 */       signature.update(content.getBytes(input_charset));
/*     */ 
/*  40 */       byte[] signed = signature.sign();
/*     */ 
/*  42 */       return Base64.encode(signed);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  46 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
/*     */   {
/*     */     try
/*     */     {
/*  64 */       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
/*  65 */       byte[] encodedKey = Base64.decode(ali_public_key);
/*  66 */       PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
/*     */ 
/*  69 */       Signature signature = 
/*  70 */         Signature.getInstance("SHA1WithRSA");
/*     */ 
/*  72 */       signature.initVerify(pubKey);
/*  73 */       signature.update(content.getBytes(input_charset));
/*     */ 
/*  75 */       return signature.verify(Base64.decode(sign));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  81 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static String decrypt(String content, String private_key, String input_charset)
/*     */     throws Exception
/*     */   {
/*  95 */     PrivateKey prikey = getPrivateKey(private_key);
/*     */ 
/*  97 */     Cipher cipher = Cipher.getInstance("RSA");
/*  98 */     cipher.init(2, prikey);
/*     */ 
/* 100 */     InputStream ins = new ByteArrayInputStream(Base64.decode(content));
/* 101 */     ByteArrayOutputStream writer = new ByteArrayOutputStream();
/*     */ 
/* 103 */     byte[] buf = new byte[''];
/*     */     int bufl;
/* 106 */     while ((bufl = ins.read(buf)) != -1)
/*     */     {
///*     */       int bufl;
/* 107 */       byte[] block = null;
/*     */ 
/* 109 */       if (buf.length == bufl) {
/* 110 */         block = buf;
/*     */       } else {
/* 112 */         block = new byte[bufl];
/* 113 */         for (int i = 0; i < bufl; i++) {
/* 114 */           block[i] = buf[i];
/*     */         }
/*     */       }
/*     */ 
/* 118 */       writer.write(cipher.doFinal(block));
/*     */     }
/*     */ 
/* 121 */     return new String(writer.toByteArray(), input_charset);
/*     */   }
/*     */ 
/*     */   public static PrivateKey getPrivateKey(String key)
/*     */     throws Exception
/*     */   {
/* 134 */     byte[] keyBytes = Base64.decode(key);
/*     */ 
/* 136 */     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
/*     */ 
/* 138 */     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
/*     */ 
/* 140 */     PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
/*     */ 
/* 142 */     return privateKey;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.payment.alipay.sign.RSA
 * JD-Core Version:    0.6.2
 */