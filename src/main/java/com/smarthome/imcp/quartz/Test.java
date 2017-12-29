/*    */ package com.smarthome.imcp.quartz;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static Boolean sdS()
/*    */   {
/* 11 */     String batFilePath = "cmd /c d:/workshop/set/startup_setdb.bat";
/*    */     try
/*    */     {
/* 14 */       Process process = Runtime.getRuntime().exec(batFilePath);
/* 15 */       InputStream in = process.getInputStream();
/* 16 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*    */ 
/* 18 */       String str = null;
/* 19 */       while ((str = br.readLine()) != null)
/* 20 */         System.out.println(str);
/*    */       try
/*    */       {
/* 23 */         process.waitFor();
/* 24 */         br.close();
/* 25 */         in.close();
/*    */       }
/*    */       catch (IOException e) {
/* 28 */         return Boolean.valueOf(false);
/*    */       }
/*    */       catch (InterruptedException e) {
/* 31 */         return Boolean.valueOf(false);
/*    */       }
/*    */     } catch (IOException e) {
/* 34 */       return Boolean.valueOf(false);
/*    */     }
/* 36 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.quartz.Test
 * JD-Core Version:    0.6.2
 */