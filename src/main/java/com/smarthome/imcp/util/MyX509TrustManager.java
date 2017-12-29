/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ public class MyX509TrustManager
/*    */   implements X509TrustManager
/*    */ {
/*    */   public void checkClientTrusted(X509Certificate[] arg0, String arg1)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void checkServerTrusted(X509Certificate[] arg0, String arg1)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public X509Certificate[] getAcceptedIssuers()
/*    */   {
/* 34 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.MyX509TrustManager
 * JD-Core Version:    0.6.2
 */