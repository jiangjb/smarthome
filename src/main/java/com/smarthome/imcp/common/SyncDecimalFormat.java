/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.ParseException;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SyncDecimalFormat
/*    */ {
/*  9 */   private static Map mapDecimalFormat = new SyncMap();
/*    */ 
/*    */   public static String format(String strFormat, Object object)
/*    */   {
/* 21 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 22 */     synchronized (SyncDecimalFormat.class) {
/* 23 */       return objDecimalFormat.format(object);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, Number number) {
/* 28 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 29 */     synchronized (SyncDecimalFormat.class) {
/* 30 */       return objDecimalFormat.format(number);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, long lNumber) {
/* 35 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 36 */     synchronized (SyncDecimalFormat.class) {
/* 37 */       return objDecimalFormat.format(lNumber);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String format(String strFormat, double dblNumber) {
/* 42 */     DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 43 */     synchronized (SyncDecimalFormat.class) {
/* 44 */       return objDecimalFormat.format(dblNumber);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Number parse(String strFormat, String text)
/*    */     throws Exception
/*    */   {
/*    */     try
/*    */     {
/* 58 */       DecimalFormat objDecimalFormat = getFormatObject(strFormat);
/* 59 */       synchronized (SyncDecimalFormat.class) {
/* 60 */         return objDecimalFormat.parse(text);
/*    */       }
/*    */     } catch (ParseException pe) {
/* 63 */       throw pe;
/*    */     }
/*    */   }
/*    */ 
/*    */   private static DecimalFormat getFormatObject(String strFormat)
/*    */   {
/* 74 */     DecimalFormat objDecimalFormat = (DecimalFormat)mapDecimalFormat.get(strFormat);
/* 75 */     if (objDecimalFormat == null)
/*    */     {
/* 77 */       objDecimalFormat = new DecimalFormat(strFormat);
/* 78 */       mapDecimalFormat.put(strFormat, objDecimalFormat);
/*    */     }
/* 80 */     return objDecimalFormat;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.SyncDecimalFormat
 * JD-Core Version:    0.6.2
 */