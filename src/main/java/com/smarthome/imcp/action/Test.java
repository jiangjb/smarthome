/*    */ package com.smarthome.imcp.action;
/*    */ 
/*    */ import com.smarthome.imcp.common.Md5;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 15 */     String sendGet = sendGet("http://114.55.89.143:8080/smarthome.IMCPlatform/doorlock/gainParams.action?lockAddress=96", "ifw5QxqP0SYOLX1odo6crg==", "0N6V4L867E67RVZH", "1487814243", "U0000000049,13575854467");
/* 16 */     System.err.println(sendGet);
/*    */   }
/*    */ 
/*    */   public static String sendGet(String url, String access_token, String nonce, String timestamp, String userCode) {
/* 20 */     String result = "";
/* 21 */     BufferedReader in = null;
/*    */     try {
/* 23 */       String str = "";
/* 24 */       Md5 md5 = new Md5();
/* 25 */       str = str + "access_token=";
/* 26 */       str = str + access_token;
/* 27 */       System.err.println("access_Token " + access_token);
/* 28 */       str = str + "&nonce=";
/* 29 */       str = str + nonce;
/* 30 */       System.err.println("nonce " + nonce);
/* 31 */       str = str + "&timestamp=";
/* 32 */       str = str + timestamp;
/* 33 */       System.err.println("timestamp" + timestamp);
/* 34 */       str = str + "&userCode=";
/* 35 */       str = str + userCode;
/* 36 */       System.err.println("userCode " + userCode);
/* 37 */       str = str + "12345";
/* 38 */       String sign = md5.getMD5ofStr(str).toLowerCase();
/* 39 */       String urlNameString = url;
/* 40 */       URL realUrl = new URL(urlNameString);
/*    */ 
/* 42 */       URLConnection connection = realUrl.openConnection();
/*    */ 
/* 44 */       connection.setRequestProperty("access_token", access_token);
/* 45 */       connection.setRequestProperty("nonce", nonce);
/* 46 */       connection.setRequestProperty("timestamp", timestamp);
/* 47 */       connection.setRequestProperty("userCode", userCode);
/* 48 */       connection.setRequestProperty("sign", sign);
/*    */ 
/* 52 */       connection.connect();
/*    */ 
/* 54 */       Map map = connection.getHeaderFields();
			   List<String> keys=(List<String>) map.keySet();
/*    */ 
/* 56 */       for (String key : keys) {
/* 57 */         System.out.println(key + "--->" + map.get(key));
/*    */       }
/*    */ 
/* 60 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*    */       String line;
/* 62 */       while ((line = in.readLine()) != null)
/*    */       {
///*    */         String line;
/* 63 */         result = result + line;
/*    */       }
/* 65 */       System.err.println(result);
/*    */     } catch (Exception e) {
/* 67 */       System.out.println("发送GET请求出现异常！" + e);
/* 68 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 73 */         if (in != null)
/* 74 */           in.close();
/*    */       }
/*    */       catch (Exception e2) {
/* 77 */         e2.printStackTrace();
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 73 */         if (in != null)
/* 74 */           in.close();
/*    */       }
/*    */       catch (Exception e2) {
/* 77 */         e2.printStackTrace();
/*    */       }
/*    */     }
/* 80 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.Test
 * JD-Core Version:    0.6.2
 */