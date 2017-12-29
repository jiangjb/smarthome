/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.commons.httpclient.HttpClient;
/*     */ import org.apache.commons.httpclient.HttpException;
/*     */ import org.apache.commons.httpclient.SimpleHttpConnectionManager;
/*     */ import org.apache.commons.httpclient.methods.GetMethod;
/*     */ import org.apache.commons.httpclient.methods.PostMethod;
/*     */ import org.apache.commons.httpclient.params.HttpClientParams;
/*     */ 
/*     */ public class HttpRequestUtil
/*     */ {
/*     */   public static String postRequest(String url, Map<String, String> params)
/*     */   {
/*  22 */     HttpClient httpClient = new HttpClient();
/*     */ 
/*  25 */     PostMethod postMethod = new PostMethod(url);
/*     */ 
/*  28 */     postMethod.setRequestHeader("Connection", "close");
/*     */ 
/*  31 */     for (Map.Entry entry : params.entrySet()) {
/*  32 */       postMethod.addParameter((String)entry.getKey(), (String)entry.getValue());
/*     */     }
/*     */ 
/*  36 */     httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
/*     */ 
/*  39 */     String result = null;
/*     */     try
/*     */     {
/*  42 */       httpClient.executeMethod(postMethod);
/*     */ 
/*  45 */       result = postMethod.getResponseBodyAsString();
/*     */     }
/*     */     catch (HttpException e) {
/*  48 */       System.out.println("请检查输入的URL!");
/*  49 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/*  52 */       System.out.println("发生网络异常!");
/*  53 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*  56 */       postMethod.releaseConnection();
/*     */ 
/*  59 */       if (httpClient != null) {
/*  60 */         ((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
/*  61 */         httpClient = null;
/*     */       }
/*     */     }
/*  64 */     return result;
/*     */   }
/*     */ 
/*     */   public static String getRequest(String url, String Phones)
/*     */   {
/*  73 */     HttpClient client = new HttpClient();
/*     */ 
/*  83 */     GetMethod method = new GetMethod(url);
/*     */ 
/*  86 */     String result = null;
/*     */     try
/*     */     {
/*  89 */       client.executeMethod(method);
/*     */ 
/*  92 */       result = method.getResponseBodyAsString();
/*     */     }
/*     */     catch (HttpException e) {
/*  95 */       System.out.println("请检查输入的URL!");
/*  96 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/*  99 */       System.out.println("发生网络异常!");
/* 100 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/* 103 */       method.releaseConnection();
/*     */ 
/* 106 */       if (client != null) {
/* 107 */         ((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
/* 108 */         client = null;
/*     */       }
/*     */     }
/* 111 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.HttpRequestUtil
 * JD-Core Version:    0.6.2
 */