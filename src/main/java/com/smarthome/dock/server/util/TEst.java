/*    */ package com.smarthome.dock.server.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class TEst
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  6 */     char c = '\n';
/*    */ 
/*  8 */     int i = 34;
/*    */ 
/* 16 */     System.out.println(c);
/*    */   }
/*    */ 
/*    */   public static byte getLow(int i)
/*    */   {
/* 25 */     return (byte)(i & 0xFF);
/*    */   }
/*    */ 
/*    */   public static byte getHigh(int i) {
/* 29 */     i >>= 8;
/* 30 */     return (byte)(i & 0xFF);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.util.TEst
 * JD-Core Version:    0.6.2
 */