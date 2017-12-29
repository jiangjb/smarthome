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
/*     */ public class HttpClientUtils
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
/*  49 */     client.setTimeout(3000);
/*     */ 
/*  51 */     if ((url == null) || (!url.startsWith("http"))) {
/*  52 */       throw new Exception("请求地址格式不对");
/*     */     }
/*     */ 
/*  56 */     if (charset != null)
/*  57 */       method1.addRequestHeader("Content-Type", 
/*  58 */         "application/x-www-form-urlencoded; charset=" + charset);
/*     */     else {
/*  60 */       method1.addRequestHeader("Content-Type", 
/*  61 */         "application/x-www-form-urlencoded; charset=utf-8");
/*     */     }
/*  63 */     int statusCode = client.executeMethod(method1);
/*     */ 
/*  65 */     if (statusCode != 200) {
/*  66 */       System.out.println("Method failed: " + method1.getStatusLine());
/*     */     }
/*     */ 
/*  69 */     byte[] responseBody = method1.getResponseBodyAsString().getBytes(method1.getResponseCharSet());
/*     */ 
/*  71 */     String response = new String(responseBody, "utf-8");
/*  72 */     System.out.println("------------------response:" + response);
/*     */ 
/*  74 */     method1.releaseConnection();
/*  75 */     return response;
/*     */   }
/*     */ 
/*     */   public static String getDoGetURL2(String url, String charset)
/*     */     throws Exception
/*     */   {
/*  99 */     HttpClient httpClient = new HttpClient();
/*     */ 
/* 101 */     httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
/*     */ 
/* 104 */     GetMethod getMethod = new GetMethod(url);
/*     */ 
/* 106 */     getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(5000));
/*     */ 
/* 108 */     getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
/*     */ 
/* 110 */     String response = "";
/*     */     try
/*     */     {
/* 113 */       int statusCode = httpClient.executeMethod(getMethod);
/*     */ 
/* 115 */       if (statusCode != 200) {
/* 116 */         System.err.println("Method failed: " + getMethod.getStatusLine());
/*     */       }
/*     */ 
/* 121 */       Header[] headers = getMethod.getResponseHeaders();
/* 122 */       for (Header h : headers) {
/* 123 */         System.out.println(h.getName() + "------------ " + h.getValue());
/*     */       }
/*     */ 
/* 126 */       byte[] responseBody = getMethod.getResponseBody();
/* 127 */       response = new String(responseBody, charset);
/* 128 */       System.out.println("----------response:" + response);
/*     */     }
/*     */     catch (HttpException e)
/*     */     {
/* 135 */       System.out.println("Please check your provided http address!");
/* 136 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/* 139 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/* 142 */       getMethod.releaseConnection();
/*     */     }
/* 144 */     return response;
/*     */   }
/*     */ 
/*     */   public static String getDoPostResponseDataByURL(String url, Map<String, String> params, String charset, boolean pretty)
/*     */   {
/* 159 */     StringBuffer response = new StringBuffer();
/*     */ 
/* 161 */     HttpClient client = new HttpClient();
/* 162 */     HttpMethod method = new PostMethod(url);
/*     */ 
/* 165 */     if (params != null) {
/* 166 */       HttpMethodParams p = new HttpMethodParams();
/* 167 */       for (Map.Entry entry : params.entrySet()) {
/* 168 */         p.setParameter((String)entry.getKey(), entry.getValue());
/*     */       }
/* 170 */       method.setParams(p);
/*     */     }
/*     */     try {
/* 173 */       client.executeMethod(method);
/* 174 */       if (method.getStatusCode() == 200)
/*     */       {
/* 176 */         BufferedReader reader = new BufferedReader(
/* 177 */           new InputStreamReader(method.getResponseBodyAsStream(), 
/* 178 */           charset));
/*     */         String line;
/* 180 */         while ((line = reader.readLine()) != null)
/*     */         {
///*     */           String line;
/* 181 */           if (pretty)
/* 182 */             response.append(line).append(System.getProperty("line.separator"));
/*     */           else
/* 184 */             response.append(line);
/*     */         }
/* 186 */         reader.close();
/*     */       }
/*     */     } catch (IOException e) {
/* 189 */       System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
/* 190 */       e.printStackTrace();
/*     */     } finally {
/* 192 */       method.releaseConnection();
/*     */     }
/* 194 */     System.out.println("--------------------" + response.toString());
/* 195 */     return response.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.HttpClientUtils
 * JD-Core Version:    0.6.2
 */