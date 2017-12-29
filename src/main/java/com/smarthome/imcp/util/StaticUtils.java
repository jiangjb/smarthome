/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StaticUtils
/*    */ {
/* 12 */   public static Map<String, String[]> dIdByName = new HashMap();
/*    */ 
/* 16 */   public static Map<String, String[]> drik = new HashMap();
/*    */ 
/* 20 */   public static Map<String, String[]> CURTAIN = new HashMap();
/* 21 */   public static Map<String, String[]> QUERYLIGHT = new HashMap();
/* 22 */   public static Map<String, String[]> VERIFICATIONCODE = new HashMap();
/*    */ 
/* 28 */   public static Map<String, String[]> AUTHORIZATIONSENDMSG = new HashMap();
/*    */ 
/* 33 */   public static Map<String, Integer> locks = new HashMap();
/*    */ 
/* 38 */   public static Map<String, Integer> status = new HashMap();
/*    */ 
/*    */   public static void lockNum(String lockAddress) {
/* 41 */     if (locks.get(lockAddress) == null)
/* 42 */       locks.put(lockAddress, Integer.valueOf(1));
/*    */     else
/* 44 */       locks.put(lockAddress, Integer.valueOf(((Integer)locks.get(lockAddress)).intValue() + 1));
/*    */   }
/*    */ 
/*    */   public static void Status(String roomCode)
/*    */   {
/* 50 */     if (status.get(roomCode) == null)
/* 51 */       status.put(roomCode, Integer.valueOf(1));
/*    */     else
/* 53 */       status.put(roomCode, Integer.valueOf(((Integer)status.get(roomCode)).intValue() + 1));
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.StaticUtils
 * JD-Core Version:    0.6.2
 */