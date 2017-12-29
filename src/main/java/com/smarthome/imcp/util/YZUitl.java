/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import edu.emory.mathcs.backport.java.util.Arrays;
/*    */ import java.io.PrintStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.codec.digest.DigestUtils;
/*    */ 
/*    */ public class YZUitl
/*    */ {
/*    */   public static Map<String, Object> paramsInit(String method, Map<String, Object> paramsMap)
/*    */   {
/* 23 */     Map map = new HashMap();
/* 24 */     long time = System.currentTimeMillis() / 1000L;
/*    */ 
/* 27 */     String key = "98283ab61de44e48bc10ef414a0ca549";
/*    */ 
/* 29 */     String secret = "5db8748211745e1f4d73b42a382b454d";
/* 30 */     StringBuilder paramString = new StringBuilder();
/* 31 */     List paramList = new ArrayList();
/* 32 */     for (Iterator it = paramsMap.keySet().iterator(); it.hasNext(); ) {
/* 33 */       String key1 = (String)it.next();
/* 34 */       String param = key1 + ":" + paramsMap.get(key1);
/* 35 */       paramList.add(param);
/*    */     }
/* 37 */     String[] params = (String[])paramList.toArray(new String[paramList.size()]);
/* 38 */     Arrays.sort(params);
/*    */     String[] arrayOfString1;
/* 39 */     int str1 = (arrayOfString1 = params).length; 
             for (int param = 0; param < str1; param++) { 
            	 String param1 = arrayOfString1[param];
/* 40 */         paramString.append(param1).append(",");
/*    */     }
/* 42 */     paramString.append("method").append(":").append(method).append(",");
/* 43 */     paramString.append("time").append(":").append(time).append(",");
/* 44 */     paramString.append("secret").append(":").append(secret);
/* 45 */     System.out.println(paramString.toString().trim());
/*    */ 
/* 47 */     String sign = null;
/*    */     try {
/* 49 */       sign = DigestUtils.md5Hex(paramString.toString().trim().getBytes("UTF-8"));
/*    */     } catch (UnsupportedEncodingException e) {
/* 51 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 54 */     Map systemMap = new HashMap();
/* 55 */     systemMap.put("ver", "1.0");
/* 56 */     systemMap.put("sign", sign);
/* 57 */     systemMap.put("key", key);
/* 58 */     systemMap.put("time", Long.valueOf(time));
/*    */ 
/* 60 */     map.put("system", systemMap);
/* 61 */     map.put("method", method);
/* 62 */     map.put("params", paramsMap);
/* 63 */     map.put("id", "123456");
/* 64 */     return map;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.YZUitl
 * JD-Core Version:    0.6.2
 */