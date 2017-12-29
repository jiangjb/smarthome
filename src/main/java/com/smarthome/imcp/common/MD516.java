/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class MD516
/*    */ {
/*    */   private static String Md5(String plainText)
/*    */   {
/*  8 */     String result = null;
/*    */     try {
/* 10 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 11 */       md.update(plainText.getBytes());
/* 12 */       byte[] b = md.digest();
/*    */ 
/* 14 */       StringBuffer buf = new StringBuffer("");
/* 15 */       for (int offset = 0; offset < b.length; offset++) {
/* 16 */         int i = b[offset];
/* 17 */         if (i < 0)
/* 18 */           i += 256;
/* 19 */         if (i < 16)
/* 20 */           buf.append("0");
/* 21 */         buf.append(Integer.toHexString(i));
/*    */       }
/*    */ 
/* 25 */       result = buf.toString().substring(8, 24);
/* 26 */       System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
/* 27 */       System.out.println("md5 32bit: " + buf.toString());
/*    */     } catch (NoSuchAlgorithmException e) {
/* 29 */       e.printStackTrace();
/*    */     }
/* 31 */     return result;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 36 */     String passwd = null;
/* 37 */     String loginpasswd = null;
/* 38 */     passwd = "20141014112031821000";
/* 39 */     loginpasswd = Md5(passwd);
/* 40 */     System.out.println("MD5 16Bit : " + loginpasswd);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.MD516
 * JD-Core Version:    0.6.2
 */