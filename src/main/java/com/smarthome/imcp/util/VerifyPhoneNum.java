/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class VerifyPhoneNum
/*    */ {
/*    */   public static boolean checkMobileNumber(String mobileNumber)
/*    */   {
/* 10 */     boolean flag = false;
/*    */     try {
/* 12 */       Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,1-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
/* 13 */       Matcher matcher = regex.matcher(mobileNumber);
/* 14 */       flag = matcher.matches();
/*    */     } catch (Exception e) {
/* 16 */       flag = false;
/*    */     }
/* 18 */     return flag;
/*    */   }
/*    */   public static void main(String[] args) {
/* 21 */     boolean checkMobileNumber = checkMobileNumber("1212121");
/* 22 */     System.out.println(checkMobileNumber);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.VerifyPhoneNum
 * JD-Core Version:    0.6.2
 */