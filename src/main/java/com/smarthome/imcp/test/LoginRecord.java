/*     */ package com.smarthome.imcp.test;
/*     */ 
/*     */ class LoginRecord
/*     */ {
/*     */   private String ip;
/*     */   private long time;
/*     */   private String username;
/*     */ 
/*     */   public LoginRecord(String ip, long time, String username)
/*     */   {
/* 104 */     this.ip = ip;
/* 105 */     this.time = time;
/* 106 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/* 110 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public long getTime() {
/* 114 */     return this.time;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 118 */     return this.username;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 123 */     return "LoginRecord{ip='" + 
/* 124 */       this.ip + '\'' + 
/* 125 */       ", time=" + this.time + 
/* 126 */       ", username='" + this.username + '\'' + 
/* 127 */       '}';
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.test.LoginRecord
 * JD-Core Version:    0.6.2
 */