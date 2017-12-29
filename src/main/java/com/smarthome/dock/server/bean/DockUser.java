/*    */ package com.smarthome.dock.server.bean;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DockUser
/*    */ {
/*    */   private String userId;
/*    */   private Date registerTime;
/*    */   private Date keepAliveTime;
/*    */   private String ip;
/*    */   private int port;
/*    */   private Date logoutTime;
/*    */   private int status;
/*    */ 
/*    */   public String getUserId()
/*    */   {
/* 19 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(String userId) {
/* 23 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public Date getRegisterTime() {
/* 27 */     return this.registerTime;
/*    */   }
/*    */ 
/*    */   public void setRegisterTime(Date registerTime) {
/* 31 */     this.registerTime = registerTime;
/*    */   }
/*    */ 
/*    */   public Date getKeepAliveTime()
/*    */   {
/* 36 */     return this.keepAliveTime;
/*    */   }
/*    */ 
/*    */   public void setKeepAliveTime(Date keepAliveTime) {
/* 40 */     this.keepAliveTime = keepAliveTime;
/*    */   }
/*    */ 
/*    */   public Date getLogoutTime() {
/* 44 */     return this.logoutTime;
/*    */   }
/*    */ 
/*    */   public void setLogoutTime(Date logoutTime) {
/* 48 */     this.logoutTime = logoutTime;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 52 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public void setIp(String ip) {
/* 56 */     this.ip = ip;
/*    */   }
/*    */ 
/*    */   public int getStatus() {
/* 60 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(int status) {
/* 64 */     this.status = status;
/*    */   }
/*    */ 
/*    */   public int getPort() {
/* 68 */     return this.port;
/*    */   }
/*    */ 
/*    */   public void setPort(int port) {
/* 72 */     this.port = port;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.bean.DockUser
 * JD-Core Version:    0.6.2
 */