/*    */ package com.smarthome.dock.server.helper;
/*    */ 
/*    */ import com.smarthome.dock.server.bean.DockUserStatus;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class DockUserStatusHelper
/*    */ {
/*  8 */   private Properties properites = new Properties();
/*    */ 
/*    */   public void setDockUserLogin(String dockUser, String loginTime) {
/* 11 */     this.properites.setProperty(dockUser + "_id", dockUser);
/* 12 */     this.properites.setProperty(dockUser + "_status", "登陆");
/* 13 */     this.properites.setProperty(dockUser + "_logintime", loginTime);
/*    */   }
/*    */ 
/*    */   public void setDockUserLogout(String dockUser, String logoutTime) {
/* 17 */     this.properites.setProperty(dockUser + "_id", dockUser);
/* 18 */     this.properites.setProperty(dockUser + "_status", "退出");
/* 19 */     this.properites.setProperty(dockUser + "_logouttime", logoutTime);
/*    */   }
/*    */ 
/*    */   public DockUserStatus getDockUserStatus(String dockUser) {
/* 23 */     DockUserStatus dockUserStatus = new DockUserStatus();
/* 24 */     dockUserStatus.setDockUser(dockUser);
/* 25 */     dockUserStatus.setStatus(this.properites.getProperty(dockUser + "_status", "未连接"));
/* 26 */     if ("登陆".equals(dockUserStatus.getStatus()))
/* 27 */       dockUserStatus.setStatusTime(this.properites.getProperty(dockUser + "_logintime", ""));
/*    */     else {
/* 29 */       dockUserStatus.setStatusTime(this.properites.getProperty(dockUser + "_logouttime", ""));
/*    */     }
/*    */ 
/* 32 */     return dockUserStatus;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.DockUserStatusHelper
 * JD-Core Version:    0.6.2
 */