/*    */ package com.smarthome.dock.server.common;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.ParseException;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SyncDecimalFormat
/*    */ {
/* 24 */   private static Map mapDecimalFormat = new SyncMap();
/*    */ 
/*    */   public static String format(String strFormat, Object object)
/*    */   {
/* 36 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 37 */     synchronized (SyncDecimalFormat.class) {
/* 38 */       return objDecimalFormat.format(object);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, Number number) {
/* 43 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 44 */     synchronized (SyncDecimalFormat.class) {
/* 45 */       return objDecimalFormat.format(number);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, long lNumber) {
/* 50 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 51 */     synchronized (SyncDecimalFormat.class) {
/* 52 */       return objDecimalFormat.format(lNumber);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, double dblNumber) {
/* 57 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 58 */     synchronized (SyncDecimalFormat.class) {
/* 59 */       return objDecimalFormat.format(dblNumber);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Number parse(String strFormat, String text)
/*    */     throws Exception
/*    */   {
/*    */     try
/*    */     {
/* 73 */       DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 74 */       synchronized (SyncDecimalFormat.class) {
/* 75 */         return objDecimalFormat.parse(text);
/*    */       }
/*    */     } catch (ParseException pe) {
/* 78 */       throw pe;
/*    */     }
/*    */   }
/*    */ 
/*    */   private static DecimalFormat getFormatObject(String strFormat)
/*    */   {
/* 89 */     DecimalFormat objDecimalFormat = (DecimalFormat)mapDecimalFormat.get(strFormat);
/* 90 */     if (objDecimalFormat == null)
/*    */     {
/* 92 */       objDecimalFormat = new DecimalFormat(strFormat);
/* 93 */       mapDecimalFormat.put(strFormat, objDecimalFormat);
/*    */     }
/* 95 */     return objDecimalFormat;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.SyncDecimalFormat
 * JD-Core Version:    0.6.2
 */