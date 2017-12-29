/*    */ package io.rong.util;
/*    */ 
/*    */ import io.rong.models.SdkHttpResult;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.ProtocolException;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class HttpUtil
/*    */ {
/*    */   private static final String APPKEY = "RC-App-Key";
/*    */   private static final String NONCE = "RC-Nonce";
/*    */   private static final String TIMESTAMP = "RC-Timestamp";
/*    */   private static final String SIGNATURE = "RC-Signature";
/*    */ 
/*    */   public static void setBodyParameter(StringBuilder sb, HttpURLConnection conn)
/*    */     throws IOException
/*    */   {
/* 24 */     DataOutputStream out = new DataOutputStream(conn.getOutputStream());
/* 25 */     out.writeBytes(sb.toString());
/* 26 */     out.flush();
/* 27 */     out.close();
/*    */   }
/*    */ 
/*    */   public static HttpURLConnection CreatePostHttpConnection(String appKey, String appSecret, String uri)
/*    */     throws MalformedURLException, IOException, ProtocolException
/*    */   {
/* 34 */     String nonce = String.valueOf(Math.random() * 1000000.0D);
/* 35 */     String timestamp = String.valueOf(System.currentTimeMillis() / 1000L);
/* 36 */     StringBuilder toSign = new StringBuilder(appSecret).append(nonce)
/* 37 */       .append(timestamp);
/* 38 */     String sign = CodeUtil.hexSHA1(toSign.toString());
/*    */ 
/* 40 */     URL url = new URL(uri);
/* 41 */     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/* 42 */     conn.setUseCaches(false);
/* 43 */     conn.setDoInput(true);
/* 44 */     conn.setDoOutput(true);
/* 45 */     conn.setRequestMethod("POST");
/* 46 */     conn.setInstanceFollowRedirects(true);
/* 47 */     conn.setConnectTimeout(30000);
/* 48 */     conn.setReadTimeout(30000);
/*    */ 
/* 50 */     conn.setRequestProperty("RC-App-Key", appKey);
/* 51 */     conn.setRequestProperty("RC-Nonce", nonce);
/* 52 */     conn.setRequestProperty("RC-Timestamp", timestamp);
/* 53 */     conn.setRequestProperty("RC-Signature", sign);
/* 54 */     conn.setRequestProperty("Content-Type", 
/* 55 */       "application/x-www-form-urlencoded");
/*    */ 
/* 57 */     return conn;
/*    */   }
/*    */ 
/*    */   public static byte[] readInputStream(InputStream inStream) throws Exception {
/* 61 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 62 */     byte[] buffer = new byte[1024];
/* 63 */     int len = 0;
/* 64 */     while ((len = inStream.read(buffer)) != -1) {
/* 65 */       outStream.write(buffer, 0, len);
/*    */     }
/* 67 */     byte[] data = outStream.toByteArray();
/* 68 */     outStream.close();
/* 69 */     inStream.close();
/* 70 */     return data;
/*    */   }
/*    */ 
/*    */   public static SdkHttpResult returnResult(HttpURLConnection conn)
/*    */     throws Exception, IOException
/*    */   {
/* 76 */     InputStream input = null;
/* 77 */     if (conn.getResponseCode() == 200)
/* 78 */       input = conn.getInputStream();
/*    */     else {
/* 80 */       input = conn.getErrorStream();
/*    */     }
/* 82 */     String result = new String(readInputStream(input));
/* 83 */     return new SdkHttpResult(conn.getResponseCode(), result);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.util.HttpUtil
 * JD-Core Version:    0.6.2
 */