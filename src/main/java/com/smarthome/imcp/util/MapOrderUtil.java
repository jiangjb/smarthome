/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class MapOrderUtil
/*    */ {
/*    */   public static int getInt(String str)
/*    */   {
/*  9 */     int i = 0;
/*    */     try {
/* 11 */       Pattern p = Pattern.compile("^\\d+");
/* 12 */       Matcher m = p.matcher(str);
/* 13 */       if (m.find())
/* 14 */         i = Integer.valueOf(m.group()).intValue();
/*    */     }
/*    */     catch (NumberFormatException e) {
/* 17 */       e.printStackTrace();
/*    */     }
/* 19 */     return i;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.MapOrderUtil
 * JD-Core Version:    0.6.2
 */