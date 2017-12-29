/*    */ package com.smarthome.dock.server.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SensorUtil
/*    */ {
/*    */   public static boolean getIndexFromArr(String val, String week)
/*    */   {
/*  9 */     boolean isR = false;
/* 10 */     String[] arrayStr = new String[0];
/* 11 */     arrayStr = week.split(",");
/* 12 */     for (int i = 0; 
/* 13 */       i < arrayStr.length; i++) {
/* 14 */       if (val.equals(arrayStr[i])) {
/* 15 */         isR = true;
/* 16 */         break;
/*    */       }
/*    */     }
/* 19 */     return isR;
/*    */   }
/*    */ 
/*    */   public static String getWeekOfDate(Date dt)
/*    */   {
/* 24 */     String[] weekDays = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
/* 25 */     Calendar cal = Calendar.getInstance();
/* 26 */     cal.setTime(dt);
/* 27 */     int w = cal.get(7) - 2;
/* 28 */     if (w < 0)
/* 29 */       w = 0;
/* 30 */     return weekDays[w];
/*    */   }
/*    */   public static void main(String[] args) {
/* 33 */     String weekOfDate = getWeekOfDate(new Date());
/* 34 */     boolean indexFromArr = getIndexFromArr(weekOfDate, "星期一,星期二,星期三,星期四,星期五,星期六,星期日,");
/* 35 */     if (indexFromArr)
/* 36 */       System.err.println("1");
/*    */     else
/* 38 */       System.err.println("2");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.util.SensorUtil
 * JD-Core Version:    0.6.2
 */