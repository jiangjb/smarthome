/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import javax.mail.Authenticator;
/*    */ import javax.mail.PasswordAuthentication;
/*    */ 
/*    */ class AjavaAuthenticator extends Authenticator
/*    */ {
/*    */   private String user;
/*    */   private String pwd;
/*    */ 
/*    */   public AjavaAuthenticator(String user, String pwd)
/*    */   {
/* 81 */     this.user = user;
/* 82 */     this.pwd = pwd;
/*    */   }
/*    */ 
/*    */   protected PasswordAuthentication getPasswordAuthentication()
/*    */   {
/* 87 */     return new PasswordAuthentication(this.user, this.pwd);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.AjavaAuthenticator
 * JD-Core Version:    0.6.2
 */