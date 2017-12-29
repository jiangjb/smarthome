/*    */ package com.smarthome.imcp.action.xing;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 17 */     String s = "15105873889";
/* 18 */     String replaceAll = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
/* 19 */     System.err.println(replaceAll);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.Test
 * JD-Core Version:    0.6.2
 */