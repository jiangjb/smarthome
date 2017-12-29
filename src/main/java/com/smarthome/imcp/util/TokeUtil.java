/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.Base64Encoder;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class TokeUtil
/*    */ {
/*    */   public static String generateTokeCode()
/*    */   {
/* 10 */     String value = createRandomVcodess();
/*    */     try
/*    */     {
/* 13 */       MessageDigest md = MessageDigest.getInstance("md5");
/* 14 */       byte[] b = md.digest(value.getBytes());
/*    */ 
/* 16 */       Base64Encoder be = new Base64Encoder();
/* 17 */       be.encode(b);
/* 18 */       return be.encode(b);
/*    */     } catch (NoSuchAlgorithmException e) {
/* 20 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   public static String generateTokeCodes() {
/* 27 */     String value = createRandomVcodess();
/*    */     try
/*    */     {
/* 30 */       MessageDigest md = MessageDigest.getInstance("md5");
/* 31 */       byte[] b = md.digest(value.getBytes());
/*    */ 
/* 33 */       Base64Encoder be = new Base64Encoder();
/* 34 */       be.encode(b);
/* 35 */       return be.encode(b);
/*    */     } catch (NoSuchAlgorithmException e) {
/* 37 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   public static String createRandomVcodess()
/*    */   {
/* 53 */     String vcode = "";
/* 54 */     for (int i = 0; i < 16; i++) {
/* 55 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*    */     }
/* 57 */     return vcode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.TokeUtil
 * JD-Core Version:    0.6.2
 */