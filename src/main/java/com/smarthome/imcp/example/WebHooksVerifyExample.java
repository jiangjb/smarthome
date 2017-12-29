/*    */ package com.smarthome.imcp.example;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.KeyFactory;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.PublicKey;
/*    */ import java.security.Signature;
/*    */ import java.security.SignatureException;
/*    */ import java.security.spec.X509EncodedKeySpec;
/*    */ import org.apache.commons.codec.binary.Base64;
/*    */ 
/*    */ public class WebHooksVerifyExample
/*    */ {
/* 27 */   private static String filePath = "src/my-server.pub";
/* 28 */   private static String eventPath = "src/charge";
/* 29 */   private static String signPath = "src/sign";
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 38 */     boolean result = verifyData(getByteFromFile(eventPath, false), getByteFromFile(signPath, true), getPubKey());
/* 39 */     System.out.println("验签结果：" + result);
/*    */   }
/*    */ 
/*    */   public static byte[] getByteFromFile(String file, boolean base64)
/*    */     throws Exception
/*    */   {
/* 50 */     FileInputStream in = new FileInputStream(file);
/* 51 */     byte[] fileBytes = new byte[in.available()];
/* 52 */     in.read(fileBytes);
/* 53 */     in.close();
/* 54 */     String pubKey = new String(fileBytes, "UTF-8");
/* 55 */     if (base64) {
/* 56 */       fileBytes = Base64.decodeBase64(pubKey);
/*    */     }
/* 58 */     return fileBytes;
/*    */   }
/*    */ 
/*    */   public static PublicKey getPubKey()
/*    */     throws Exception
/*    */   {
/* 68 */     FileInputStream in = new FileInputStream(filePath);
/* 69 */     byte[] keyBytes = new byte[in.available()];
/* 70 */     in.read(keyBytes);
/* 71 */     in.close();
/*    */ 
/* 73 */     String pubKey = new String(keyBytes, "UTF-8");
/* 74 */     pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
/*    */ 
/* 76 */     keyBytes = Base64.decodeBase64(pubKey);
/*    */ 
/* 79 */     X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
/* 80 */     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
/* 81 */     PublicKey publicKey = keyFactory.generatePublic(spec);
/* 82 */     return publicKey;
/*    */   }
/*    */ 
/*    */   public static boolean verifyData(byte[] data, byte[] sigBytes, PublicKey publicKey)
/*    */     throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
/*    */   {
/* 96 */     Signature signature = Signature.getInstance("SHA256withRSA");
/* 97 */     signature.initVerify(publicKey);
/* 98 */     signature.update(data);
/* 99 */     return signature.verify(sigBytes);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.WebHooksVerifyExample
 * JD-Core Version:    0.6.2
 */