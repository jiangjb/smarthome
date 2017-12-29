/*    */ package com.smarthome.dock.server.helper;
/*    */ 
/*    */ import com.smarthome.dock.server.bean.DockUser;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class UserManager
/*    */ {
/*    */   private HashMap<String, DockUser> userMap;
/*    */ 
/*    */   public UserManager()
/*    */   {
/* 19 */     this.userMap = new HashMap();
/*    */   }
/*    */ 
/*    */   public synchronized void addUser(String userId, Date registerDate, String ip, int port) {
/* 23 */     DockUser user = new DockUser();
/* 24 */     user.setUserId(userId);
/* 25 */     user.setRegisterTime(registerDate);
/* 26 */     user.setKeepAliveTime(registerDate);
/* 27 */     user.setIp(ip);
/* 28 */     user.setPort(port);
/* 29 */     user.setStatus(1);
/*    */ 
/* 31 */     this.userMap.put(userId, user);
/*    */   }
/*    */ 
/*    */   public synchronized void logoutUser(String userId, Date logoutTime, String logoutIp) {
/* 35 */     DockUser user = (DockUser)this.userMap.get(userId);
/* 36 */     if (user != null) {
/* 37 */       user.setIp(logoutIp);
/* 38 */       user.setLogoutTime(logoutTime);
/* 39 */       user.setStatus(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   public synchronized void keepAliveUser(String userId, Date keepAliveTime, String ip, int port) {
/* 44 */     DockUser user = (DockUser)this.userMap.get(userId);
/* 45 */     if (user == null) {
/* 46 */       user = new DockUser();
/* 47 */       this.userMap.put(userId, user);
/*    */     }
/* 49 */     user.setKeepAliveTime(keepAliveTime);
/* 50 */     user.setIp(ip);
/* 51 */     user.setPort(port);
/* 52 */     user.setStatus(1);
/*    */   }
/*    */ 
/*    */   public synchronized DockUser getUser(String userId) {
/* 56 */     return (DockUser)this.userMap.get(userId);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.UserManager
 * JD-Core Version:    0.6.2
 */