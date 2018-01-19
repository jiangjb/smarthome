/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
import java.util.ArrayList;
/*    */ import java.util.Date;
		 import java.util.List;
/*    */ import java.util.Map;

/*    */ 
/*    */ public class SimulateHTTPRequestUtil
/*    */ {
/*    */   public String sendGet(String url, String userCode)
/*    */   {
/* 13 */     String result = "";
/* 14 */     BufferedReader in = null;
/*    */     try {
/* 16 */       String urlNameString = url;
/* 17 */       URL realUrl = new URL(urlNameString);
/*    */ 
/* 19 */       URLConnection connection = realUrl.openConnection();
/*    */ 
/* 22 */       connection.setRequestProperty("userCode", userCode);
/* 23 */       connection.setRequestProperty("timestamp", new Date().getTime()+"");
/*    */ 
/* 25 */       connection.connect();

/* 27 */       Map map = connection.getHeaderFields();
			   List<String> keys = new ArrayList<String>(map.keySet());
///*    */       List<String> keys=(List<String>) map.keySet();
///* 29 */       for (String key : keys) {
//			   List<String> keys=(List<String>)map.keySet();
			   for (String key : keys) {
/* 30 */         System.out.println(key + "--->" + map.get(key));
/*    */       }
/*    */ 
/* 33 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*    */       String line;
/* 35 */       while ((line = in.readLine()) != null)
/*    */       {
///*    */         String line;
/* 36 */         result = result + line;
/*    */       }
/* 38 */       System.err.println(result);
/*    */     } catch (Exception e) {
/* 40 */       System.out.println("发送GET请求出现异常！" + e);
/* 41 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 46 */         if (in != null)
/* 47 */           in.close();
/*    */       }
/*    */       catch (Exception e2) {
/* 50 */         e2.printStackTrace();
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 46 */         if (in != null)
/* 47 */           in.close();
/*    */       }
/*    */       catch (Exception e2) {
/* 50 */         e2.printStackTrace();
/*    */       }
/*    */     }
/* 53 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.SimulateHTTPRequestUtil
 * JD-Core Version:    0.6.2
 */