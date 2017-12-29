/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UuidUtil
/*    */ {
/*    */   public static String get32UUID()
/*    */   {
/*  8 */     String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
/*  9 */     return uuid;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 13 */     System.out.println(get32UUID());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.UuidUtil
 * JD-Core Version:    0.6.2
 */