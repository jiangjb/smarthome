/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MsgUtil
/*     */ {
/*  14 */   private static Logger logger = Logger.getLogger(MsgUtil.class);
/*     */ 
/*     */   private static String aiBoRuiSendMsg(String mobile, String content)
/*     */   {
/*  20 */     BufferedReader in = null;
/*     */     try {
/*  22 */       String encode = URLEncoder.encode(content, "utf-8");
/*  23 */       String account = "Jkdz888";
/*  24 */       String pwd = "Hificat882";
/*  25 */       String url = 
/*  26 */         String.format("http://222.73.117.158/msg/HttpBatchSendSM?account=%s&pswd=%s&mobile=%s&needstatus=1&msg=%s", new Object[] { 
/*  27 */         account, pwd, mobile, encode });
/*     */ 
/*  29 */       System.out.println(url);
/*     */ 
/*  31 */       URL realUrl = new URL(url);
/*  32 */       URLConnection connection = realUrl.openConnection();
/*  33 */       HttpURLConnection httpUrlConnection = (HttpURLConnection)connection;
/*  34 */       httpUrlConnection.setRequestProperty("accept", "*/*");
/*  35 */       httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
/*  36 */       httpUrlConnection.setRequestProperty("Accept-Charset", "UTF-8");
/*  37 */       httpUrlConnection.connect();
/*     */ 
/*  39 */       String code = new Integer(httpUrlConnection.getResponseCode())
/*  40 */         .toString();
/*  41 */       in = new BufferedReader(new InputStreamReader(
/*  42 */         httpUrlConnection.getInputStream()));
/*     */       String line;
/*  44 */       while ((line = in.readLine()) != null)
/*  45 */         line = line + line;
/*  46 */       logger.info("sendMsg Result Code > " + code);
/*  47 */       logger.info("sendMsg Result > " + line);
/*  48 */       return line;
/*     */     } catch (Exception e) {
/*  50 */       logger.error("sendMsg > " + e.getMessage());
/*  51 */       e.printStackTrace();
/*     */     }
/*  53 */     return "";
/*     */   }
/*     */ 
/*     */   private static String sendMsg(String mobile, String content)
/*     */   {
/*  62 */     BufferedReader in = null;
/*     */     try {
/*  64 */       String encode = URLEncoder.encode(content, "utf-8");
/*  65 */       String account = "Jkdz888";
/*  66 */       String pwd = "Hificat882";
/*  67 */       String url = 
/*  68 */         String.format("http://222.73.117.158/msg/HttpBatchSendSM?account=%s&pswd=%s&mobile=%s&needstatus=1&msg=%s", new Object[] { 
/*  69 */         account, pwd, mobile, encode });
/*     */ 
/*  71 */       System.out.println(url);
/*     */ 
/*  73 */       URL realUrl = new URL(url);
/*  74 */       URLConnection connection = realUrl.openConnection();
/*  75 */       HttpURLConnection httpUrlConnection = (HttpURLConnection)connection;
/*  76 */       httpUrlConnection.setRequestProperty("accept", "*/*");
/*  77 */       httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
/*  78 */       httpUrlConnection.setRequestProperty("Accept-Charset", "UTF-8");
/*  79 */       httpUrlConnection.connect();
/*     */ 
/*  81 */       String code = new Integer(httpUrlConnection.getResponseCode())
/*  82 */         .toString();
/*  83 */       in = new BufferedReader(new InputStreamReader(
/*  84 */         httpUrlConnection.getInputStream()));
/*     */       String line;
/*  86 */       while ((line = in.readLine()) != null)
/*  87 */         line = line + line;
/*  88 */       logger.info("sendMsg Result Code > " + code);
/*  89 */       logger.info("sendMsg Result > " + line);
/*  90 */       return line;
/*     */     } catch (Exception e) {
/*  92 */       logger.error("sendMsg > " + e.getMessage());
/*  93 */       e.printStackTrace();
/*     */     }
/*  95 */     return "";
/*     */   }
/*     */ 
/*     */   public static void sendVCode(String tel, String code)
/*     */   {
/* 102 */     sendMsg(tel, "尊敬的用户，您的验证码为" + code);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 106 */     sendVCode("13263915506", "12345");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.MsgUtil
 * JD-Core Version:    0.6.2
 */