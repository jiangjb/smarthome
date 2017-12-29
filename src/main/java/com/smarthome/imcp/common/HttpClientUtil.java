/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
/*     */ import org.apache.commons.httpclient.Header;
/*     */ import org.apache.commons.httpclient.HttpClient;
/*     */ import org.apache.commons.httpclient.HttpConnectionManager;
/*     */ import org.apache.commons.httpclient.HttpException;
/*     */ import org.apache.commons.httpclient.HttpMethod;
/*     */ import org.apache.commons.httpclient.methods.GetMethod;
/*     */ import org.apache.commons.httpclient.methods.PostMethod;
/*     */ import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
/*     */ import org.apache.commons.httpclient.params.HttpMethodParams;
/*     */ 
/*     */ public class HttpClientUtil
/*     */ {
/*     */   public static void main(String[] arg)
/*     */     throws Exception
/*     */   {
/*  32 */     String url = "https://www.99bill.com/webapp/receiveDrawbackAction.do";
/*     */ 
/*  35 */     getDoPostResponseDataByURL(url, null, "utf-8", true);
/*     */   }
/*     */ 
/*     */   public static String getDoGetURL(String url, String charset)
/*     */     throws Exception
/*     */   {
/*  47 */     HttpClient client = new HttpClient();
/*  48 */     GetMethod method1 = new GetMethod(url);
/*     */ 
/*  50 */     if ((url == null) || (!url.startsWith("http"))) {
/*  51 */       throw new Exception("请求地址格式不对");
/*     */     }
/*     */ 
/*  55 */     if (charset != null)
/*  56 */       method1.addRequestHeader("Content-Type", 
/*  57 */         "application/x-www-form-urlencoded; charset=" + charset);
/*     */     else {
/*  59 */       method1.addRequestHeader("Content-Type", 
/*  60 */         "application/x-www-form-urlencoded; charset=utf-8");
/*     */     }
/*  62 */     int statusCode = client.executeMethod(method1);
/*     */ 
/*  64 */     if (statusCode != 200) {
/*  65 */       System.out.println("Method failed: " + method1.getStatusLine());
/*     */     }
/*     */ 
/*  68 */     byte[] responseBody = method1.getResponseBodyAsString().getBytes(method1.getResponseCharSet());
/*     */ 
/*  70 */     String response = new String(responseBody, "utf-8");
/*  71 */     System.out.println("------------------response:" + response);
/*     */ 
/*  73 */     method1.releaseConnection();
/*  74 */     return response;
/*     */   }
/*     */ 
/*     */   public static String getDoGetURL2(String url, String charset)
/*     */     throws Exception
/*     */   {
/*  98 */     HttpClient httpClient = new HttpClient();
/*     */ 
/* 100 */     httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
/*     */ 
/* 103 */     GetMethod getMethod = new GetMethod(url);
/*     */ 
/* 105 */     getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(5000));
/*     */ 
/* 107 */     getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
/*     */ 
/* 109 */     String response = "";
/*     */     try
/*     */     {
/* 112 */       int statusCode = httpClient.executeMethod(getMethod);
/*     */ 
/* 114 */       if (statusCode != 200) {
/* 115 */         System.err.println("Method failed: " + getMethod.getStatusLine());
/*     */       }
/*     */ 
/* 120 */       Header[] headers = getMethod.getResponseHeaders();
/* 121 */       for (Header h : headers) {
/* 122 */         System.out.println(h.getName() + "------------ " + h.getValue());
/*     */       }
/*     */ 
/* 125 */       byte[] responseBody = getMethod.getResponseBody();
/* 126 */       response = new String(responseBody, charset);
/* 127 */       System.out.println("----------response:" + response);
/*     */     }
/*     */     catch (HttpException e)
/*     */     {
/* 134 */       System.out.println("Please check your provided http address!");
/* 135 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/* 138 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/* 141 */       getMethod.releaseConnection();
/*     */     }
/* 143 */     return response;
/*     */   }
/*     */ 
/*     */   public static String getDoPostResponseDataByURL(String url, Map<String, String> params, String charset, boolean pretty)
/*     */   {
/* 158 */     StringBuffer response = new StringBuffer();
/*     */ 
/* 160 */     HttpClient client = new HttpClient();
/* 161 */     HttpMethod method = new PostMethod(url);
/*     */ 
/* 164 */     if (params != null) {
/* 165 */       HttpMethodParams p = new HttpMethodParams();
/* 166 */       for (Map.Entry entry : params.entrySet()) {
/* 167 */         p.setParameter((String)entry.getKey(), entry.getValue());
/*     */       }
/* 169 */       method.setParams(p);
/*     */     }
/*     */     try {
/* 172 */       client.executeMethod(method);
/* 173 */       if (method.getStatusCode() == 200)
/*     */       {
/* 175 */         BufferedReader reader = new BufferedReader(
/* 176 */           new InputStreamReader(method.getResponseBodyAsStream(), 
/* 177 */           charset));
/*     */         String line;
/* 179 */         while ((line = reader.readLine()) != null)
/*     */         {
///*     */           String line;
/* 180 */           if (pretty)
/* 181 */             response.append(line).append(System.getProperty("line.separator"));
/*     */           else
/* 183 */             response.append(line);
/*     */         }
/* 185 */         reader.close();
/*     */       }
/*     */     } catch (IOException e) {
/* 188 */       System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
/* 189 */       e.printStackTrace();
/*     */     } finally {
/* 191 */       method.releaseConnection();
/*     */     }
/* 193 */     System.out.println("--------------------" + response.toString());
/* 194 */     return response.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.HttpClientUtil
 * JD-Core Version:    0.6.2
 */