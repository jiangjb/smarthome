/*    */ package com.smarthome.imcp.test;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ class UserLoginLimiter
/*    */ {
/*    */   private int maxLoginTimes;
/*    */   private int thresholdInMillisecond;
/*    */   private LinkedList<LoginRecord> loginRecordList;
/*    */ 
/*    */   public boolean check(LoginRecord curLR)
/*    */   {
/* 58 */     if ((this.maxLoginTimes <= 0) || (this.thresholdInMillisecond <= 0)) return true;
/*    */ 
/* 61 */     if (this.loginRecordList.size() < this.maxLoginTimes) {
/* 62 */       this.loginRecordList.addLast(curLR);
/* 63 */       return true;
/*    */     }
/*    */ 
/* 67 */     LoginRecord firstLR = (LoginRecord)this.loginRecordList.getFirst();
/* 68 */     while ((this.loginRecordList.size() > 0) && (firstLR.getTime() + this.thresholdInMillisecond < curLR.getTime())) {
/* 69 */       this.loginRecordList.removeFirst();
/* 70 */       if (this.loginRecordList.size() <= 0) break;
/* 71 */       firstLR = (LoginRecord)this.loginRecordList.getFirst();
/*    */     }
/*    */ 
/* 78 */     if (this.loginRecordList.size() < this.maxLoginTimes) {
/* 79 */       this.loginRecordList.addLast(curLR);
/* 80 */       return true;
/*    */     }
/* 82 */     return false;
/*    */   }
/*    */   private UserLoginLimiter() {
/*    */   }
/*    */ 
/*    */   public UserLoginLimiter(int thresholdInMillisecond, int maxLoginTimes) {
/* 88 */     this.thresholdInMillisecond = thresholdInMillisecond;
/* 89 */     this.maxLoginTimes = maxLoginTimes;
/* 90 */     this.loginRecordList = new LinkedList();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.test.UserLoginLimiter
 * JD-Core Version:    0.6.2
 */