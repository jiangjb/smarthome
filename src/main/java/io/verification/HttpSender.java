/*    */ package io.verification;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.InputStream;
/*    */ import java.net.URLDecoder;
/*    */ import org.apache.commons.httpclient.HttpClient;
/*    */ import org.apache.commons.httpclient.NameValuePair;
/*    */ import org.apache.commons.httpclient.URI;
/*    */ import org.apache.commons.httpclient.methods.GetMethod;
/*    */ 
/*    */ public class HttpSender
/*    */ {
/*    */   public static String send(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno)
/*    */     throws Exception
/*    */   {
/* 31 */     HttpClient client = new HttpClient();
/* 32 */     GetMethod method = new GetMethod();
/*    */     try {
/* 34 */       URI base = new URI(uri, false);
/* 35 */       method.setURI(new URI(base, "HttpSendSM", false));
/* 36 */       method.setQueryString(new NameValuePair[] { 
/* 37 */         new NameValuePair("account", account), 
/* 38 */         new NameValuePair("pswd", pswd), 
/* 39 */         new NameValuePair("mobile", mobiles), 
/* 40 */         new NameValuePair("needstatus", String.valueOf(needstatus)), 
/* 41 */         new NameValuePair("msg", content), 
/* 42 */         new NameValuePair("product", product), 
/* 43 */         new NameValuePair("extno", extno) });
/*    */ 
/* 45 */       int result = client.executeMethod(method);
/* 46 */       if (result == 200) {
/* 47 */         InputStream in = method.getResponseBodyAsStream();
/* 48 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 49 */         byte[] buffer = new byte[1024];
/* 50 */         int len = 0;
/* 51 */         while ((len = in.read(buffer)) != -1) {
/* 52 */           baos.write(buffer, 0, len);
/*    */         }
/* 54 */         return URLDecoder.decode(baos.toString(), "UTF-8");
/*    */       }
/* 56 */       throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
/*    */     }
/*    */     finally {
/* 59 */       method.releaseConnection();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String batchSend(String uri, String account, String pswd, String mobiles, String content, boolean needstatus, String product, String extno)
/*    */     throws Exception
/*    */   {
/* 77 */     HttpClient client = new HttpClient();
/* 78 */     GetMethod method = new GetMethod();
/*    */     try {
/* 80 */       URI base = new URI(uri, false);
/* 81 */       method.setURI(new URI(base, "HttpBatchSendSM", false));
/* 82 */       method.setQueryString(new NameValuePair[] { 
/* 83 */         new NameValuePair("account", account), 
/* 84 */         new NameValuePair("pswd", pswd), 
/* 85 */         new NameValuePair("mobile", mobiles), 
/* 86 */         new NameValuePair("needstatus", String.valueOf(needstatus)), 
/* 87 */         new NameValuePair("msg", content), 
/* 88 */         new NameValuePair("product", product), 
/* 89 */         new NameValuePair("extno", extno) });
/*    */ 
/* 91 */       int result = client.executeMethod(method);
/* 92 */       if (result == 200) {
/* 93 */         InputStream in = method.getResponseBodyAsStream();
/* 94 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 95 */         byte[] buffer = new byte[1024];
/* 96 */         int len = 0;
/* 97 */         while ((len = in.read(buffer)) != -1) {
/* 98 */           baos.write(buffer, 0, len);
/*    */         }
/* 100 */         return URLDecoder.decode(baos.toString(), "UTF-8");
/*    */       }
/* 102 */       throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
/*    */     }
/*    */     finally {
/* 105 */       method.releaseConnection();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.verification.HttpSender
 * JD-Core Version:    0.6.2
 */