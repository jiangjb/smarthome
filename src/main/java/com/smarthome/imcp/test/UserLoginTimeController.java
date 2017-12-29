/*    */ package com.smarthome.imcp.test;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class UserLoginTimeController
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws InterruptedException
/*    */   {
/* 10 */     UserLoginLimiter limiter = new UserLoginLimiter(6000, 3);
/*    */ 
/* 12 */     Random r = new Random();
/* 13 */     int allowCount = 0;
/* 14 */     int denyCount = 0;
/* 15 */     LoginRecord loginRecord = new LoginRecord("192.168.1.171", System.currentTimeMillis(), "15105873889");
/*    */ 
/* 17 */     for (int i = 0; i < 10; i++) {
/* 18 */       Thread.sleep(1000L);
/*    */       try {
/* 20 */         Thread.sleep(r.nextInt(50));
/*    */       } catch (InterruptedException e) {
/* 22 */         e.printStackTrace();
/*    */       }
/*    */ 
/* 25 */       if (limiter.check(loginRecord)) {
/* 26 */         System.out.println(loginRecord + "允许登录！");
/* 27 */         allowCount++;
/*    */       } else {
/* 29 */         System.out.println(loginRecord + "禁止登录！");
/* 30 */         denyCount++;
/*    */       }
/*    */     }
/* 33 */     System.out.println("允许数量：" + allowCount);
/* 34 */     System.out.println("拒绝数量：" + denyCount);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.test.UserLoginTimeController
 * JD-Core Version:    0.6.2
 */