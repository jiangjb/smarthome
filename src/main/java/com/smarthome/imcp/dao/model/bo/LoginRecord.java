/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ public class LoginRecord
/*    */ {
/*    */   private String ip;
/*    */   private long time;
/*    */   private String username;
/*    */ 
/*    */   public LoginRecord(String ip, long time, String username)
/*    */   {
/*  9 */     this.ip = ip;
/* 10 */     this.time = time;
/* 11 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 15 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public long getTime() {
/* 19 */     return this.time;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 23 */     return this.username;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 28 */     return "LoginRecord{ip='" + 
/* 29 */       this.ip + '\'' + 
/* 30 */       ", time=" + this.time + 
/* 31 */       ", username='" + this.username + '\'' + 
/* 32 */       '}';
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.LoginRecord
 * JD-Core Version:    0.6.2
 */