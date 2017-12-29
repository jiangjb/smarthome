/*    */ package io.rong.util;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ import org.apache.commons.codec.binary.Hex;
/*    */ 
/*    */ public class CodeUtil
/*    */ {
/*    */   public static String hexSHA1(String value)
/*    */   {
/*    */     try
/*    */     {
/* 11 */       MessageDigest md = MessageDigest.getInstance("SHA-1");
/* 12 */       md.update(value.getBytes("utf-8"));
/* 13 */       byte[] digest = md.digest();
/* 14 */       return byteToHexString(digest);
/*    */     } catch (Exception ex) {
/* 16 */       throw new RuntimeException(ex);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String byteToHexString(byte[] bytes) {
/* 21 */     return String.valueOf(Hex.encodeHex(bytes));
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.util.CodeUtil
 * JD-Core Version:    0.6.2
 */