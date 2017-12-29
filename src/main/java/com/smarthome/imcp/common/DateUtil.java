/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class DateUtil
/*    */ {
/*    */   private static int getMondayPlus()
/*    */   {
/* 13 */     Calendar cd = Calendar.getInstance();
/*    */ 
/* 15 */     int dayOfWeek = cd.get(7);
/* 16 */     if (dayOfWeek == 1) {
/* 17 */       return -6;
/*    */     }
/* 19 */     return 2 - dayOfWeek;
/*    */   }
/*    */ 
/*    */   public static String getPreviousMonday()
/*    */   {
/* 25 */     int weeks = -1;
/* 26 */     int mondayPlus = getMondayPlus();
/* 27 */     GregorianCalendar currentDate = new GregorianCalendar();
/* 28 */     currentDate.add(5, mondayPlus + 7 * weeks);
/* 29 */     Date monday = currentDate.getTime();
/* 30 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 31 */     String preMonday = df.format(monday);
/* 32 */     return preMonday;
/*    */   }
/*    */ 
/*    */   public static String getSunday()
/*    */   {
/* 37 */     int weeks = -1;
/* 38 */     int mondayPlus = getMondayPlus();
/* 39 */     GregorianCalendar currentDate = new GregorianCalendar();
/* 40 */     currentDate.add(5, mondayPlus + 7 * weeks + 6);
/* 41 */     Date monday = currentDate.getTime();
/* 42 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 43 */     String preMonday = df.format(monday);
/* 44 */     return preMonday;
/*    */   }
/*    */ 
/*    */   public static String getCurrentMonday()
/*    */   {
/* 49 */     int mondayPlus = getMondayPlus();
/* 50 */     GregorianCalendar currentDate = new GregorianCalendar();
/* 51 */     currentDate.add(5, mondayPlus);
/* 52 */     Date monday = currentDate.getTime();
/* 53 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 54 */     String preMonday = df.format(monday);
/* 55 */     return preMonday;
/*    */   }
/*    */ 
/*    */   public String getNextMonday()
/*    */   {
/* 60 */     int weeks = 1;
/* 61 */     int mondayPlus = getMondayPlus();
/* 62 */     GregorianCalendar currentDate = new GregorianCalendar();
/* 63 */     currentDate.add(5, mondayPlus + 7 * weeks);
/* 64 */     Date monday = currentDate.getTime();
/* 65 */     DateFormat df = DateFormat.getDateInstance();
/* 66 */     String preMonday = df.format(monday);
/* 67 */     return preMonday;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.DateUtil
 * JD-Core Version:    0.6.2
 */