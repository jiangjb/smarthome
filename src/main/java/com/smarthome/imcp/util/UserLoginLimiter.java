/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.bo.LoginRecord;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class UserLoginLimiter
/*    */ {
/*    */   private int maxLoginTimes;
/*    */   private int thresholdInMillisecond;
/*    */   private LinkedList<LoginRecord> loginRecordList;
/*    */ 
/*    */   public boolean check(LoginRecord curLR)
/*    */   {
/* 24 */     if ((this.maxLoginTimes <= 0) || (this.thresholdInMillisecond <= 0)) return true;
/*    */ 
/* 27 */     if (this.loginRecordList.size() < this.maxLoginTimes) {
/* 28 */       this.loginRecordList.addLast(curLR);
/* 29 */       return true;
/*    */     }
/*    */ 
/* 33 */     LoginRecord firstLR = (LoginRecord)this.loginRecordList.getFirst();
/* 34 */     while ((this.loginRecordList.size() > 0) && (firstLR.getTime() + this.thresholdInMillisecond < curLR.getTime())) {
/* 35 */       this.loginRecordList.removeFirst();
/* 36 */       if (this.loginRecordList.size() <= 0) break;
/* 37 */       firstLR = (LoginRecord)this.loginRecordList.getFirst();
/*    */     }
/*    */ 
/* 44 */     if (this.loginRecordList.size() < this.maxLoginTimes) {
/* 45 */       this.loginRecordList.addLast(curLR);
/* 46 */       return true;
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */   private UserLoginLimiter() {
/*    */   }
/*    */ 
/*    */   public UserLoginLimiter(int thresholdInMillisecond, int maxLoginTimes) {
/* 54 */     this.thresholdInMillisecond = thresholdInMillisecond;
/* 55 */     this.maxLoginTimes = maxLoginTimes;
/* 56 */     this.loginRecordList = new LinkedList();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.UserLoginLimiter
 * JD-Core Version:    0.6.2
 */