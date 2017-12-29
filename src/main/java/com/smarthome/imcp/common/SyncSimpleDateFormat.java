/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ public class SyncSimpleDateFormat
/*     */ {
/*  16 */   private static Map mapSimDateFormat = new ConcurrentHashMap();
/*     */ 
/*     */   public static String format(String strFormat, Date date)
/*     */   {
/*  29 */     DateFormat objDateFormat = getFormatObject(strFormat);
/*  30 */     synchronized (SyncSimpleDateFormat.class) {
/*  31 */       return objDateFormat.format(date);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, int intParsePosition) throws Exception
/*     */   {
/*  37 */     return parse(strFormat, text, false, 
/*  38 */       intParsePosition);
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, boolean bLenient, int intParsePosition) throws Exception
/*     */   {
/*     */     try {
/*  44 */       DateFormat objDateFormat = getFormatObject(strFormat);
/*  45 */       synchronized (SyncSimpleDateFormat.class) {
/*  46 */         objDateFormat.setLenient(bLenient);
/*  47 */         ParsePosition pos = new ParsePosition(intParsePosition);
/*  48 */         return objDateFormat.parse(text, pos);
/*     */       }
/*     */     } catch (Exception e) {
/*  51 */       e.printStackTrace();
/*  52 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, boolean bLenient)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/*  70 */       DateFormat objDateFormat = getFormatObject(strFormat);
/*  71 */       synchronized (SyncSimpleDateFormat.class) {
/*  72 */         objDateFormat.setLenient(bLenient);
/*  73 */         return objDateFormat.parse(text);
/*     */       }
/*     */     } catch (ParseException pe) {
/*  76 */       throw pe;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text)
/*     */     throws Exception
/*     */   {
/*  89 */     return parse(strFormat, text, false);
/*     */   }
/*     */ 
/*     */   private static DateFormat getFormatObject(String strFormat)
/*     */   {
/* 100 */     DateFormat objDateFormat = (DateFormat)mapSimDateFormat.get(strFormat);
/* 101 */     if (objDateFormat == null)
/*     */     {
/* 103 */       objDateFormat = new SimpleDateFormat(strFormat);
/* 104 */       mapSimDateFormat.put(strFormat, objDateFormat);
/*     */     }
/* 106 */     return objDateFormat;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.SyncSimpleDateFormat
 * JD-Core Version:    0.6.2
 */