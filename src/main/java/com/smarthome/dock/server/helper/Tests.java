/*    */ package com.smarthome.dock.server.helper;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Tests
/*    */ {
/*    */   public static String getWeekOfDate(Date dt)
/*    */   {
/* 10 */     String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
/* 11 */     Calendar cal = Calendar.getInstance();
/* 12 */     cal.setTime(dt);
/* 13 */     int w = cal.get(7) - 1;
/* 14 */     if (w < 0)
/* 15 */       w = 0;
/* 16 */     return weekDays[w];
/*    */   }
/*    */ 
/*    */   public static boolean isInDate(Date date, String strDateBegin, String strDateEnd, String s)
/*    */   {
/* 21 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 22 */     String strDate = sdf.format(date);
/*    */ 
/* 24 */     int strDateH = Integer.parseInt(strDate.substring(11, 13));
/*    */ 
/* 27 */     int strDateBeginH = Integer.parseInt(strDateBegin);
/*    */ 
/* 30 */     int strDateEndH = Integer.parseInt(strDateEnd);
/*    */ 
/* 33 */     int ds = Integer.parseInt(s);
/* 34 */     if (ds == 1) {
/* 35 */       if ((strDateH >= strDateBeginH) && (strDateH <= strDateEndH))
/*    */       {
/* 37 */         return true;
/*    */       }
/* 39 */       return false;
/*    */     }
/*    */ 
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 50 */     boolean inDate = isInDate(new Date(), "10", "4", "1");
/* 51 */     String weekOfDate = getWeekOfDate(new Date());
/* 52 */     if (weekOfDate.equals("星期二")) {
/* 53 */       if (inDate)
/* 54 */         System.err.println("1");
/*    */       else
/* 56 */         System.err.println("2");
/*    */     }
/*    */     else
/* 59 */       System.err.println("星期不对");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.Tests
 * JD-Core Version:    0.6.2
 */