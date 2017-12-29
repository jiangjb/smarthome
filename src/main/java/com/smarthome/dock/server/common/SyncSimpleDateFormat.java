/*     */ package com.smarthome.dock.server.common;
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
/*  15 */   private static Map mapSimDateFormat = new ConcurrentHashMap();
/*     */ 
/*     */   public static String format(String strFormat, Date date)
/*     */   {
/*  28 */     DateFormat objDateFormat = getFormatObject(strFormat);
/*  29 */     synchronized (SyncSimpleDateFormat.class) {
/*  30 */       return objDateFormat.format(date);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, int intParsePosition) throws Exception
/*     */   {
/*  36 */     return parse(strFormat, text, false, intParsePosition);
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, boolean bLenient, int intParsePosition) throws Exception
/*     */   {
/*     */     try {
/*  42 */       DateFormat objDateFormat = getFormatObject(strFormat);
/*  43 */       synchronized (SyncSimpleDateFormat.class) {
/*  44 */         objDateFormat.setLenient(bLenient);
/*  45 */         ParsePosition pos = new ParsePosition(intParsePosition);
/*  46 */         return objDateFormat.parse(text, pos);
/*     */       }
/*     */     } catch (Exception e) {
/*  49 */       e.printStackTrace();
/*  50 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text, boolean bLenient)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/*  64 */       DateFormat objDateFormat = getFormatObject(strFormat);
/*  65 */       synchronized (SyncSimpleDateFormat.class) {
/*  66 */         objDateFormat.setLenient(bLenient);
/*  67 */         return objDateFormat.parse(text);
/*     */       }
/*     */     } catch (ParseException pe) {
/*  70 */       throw pe;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Date parse(String strFormat, String text)
/*     */     throws Exception
/*     */   {
/*  82 */     return parse(strFormat, text, false);
/*     */   }
/*     */ 
/*     */   private static DateFormat getFormatObject(String strFormat)
/*     */   {
/*  94 */     DateFormat objDateFormat = (DateFormat)mapSimDateFormat.get(strFormat);
/*  95 */     if (objDateFormat == null)
/*     */     {
/*  97 */       objDateFormat = new SimpleDateFormat(strFormat);
/*  98 */       mapSimDateFormat.put(strFormat, objDateFormat);
/*     */     }
/* 100 */     return objDateFormat;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.SyncSimpleDateFormat
 * JD-Core Version:    0.6.2
 */