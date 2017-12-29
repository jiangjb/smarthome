/*    */ package io.verification.service.impl;
/*    */ 
/*    */ import io.verification.HttpSender;
/*    */ import io.verification.service.SMSVerificationServiceIface;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("smsVerificationService")
/*    */ public class SMSVerificationServiceImpl
/*    */   implements SMSVerificationServiceIface
/*    */ {
/*    */   public String sendVerificationCode(String mobile, String content)
/*    */     throws Exception
/*    */   {
/* 13 */     String url = "http://222.73.117.158/msg/";
/* 14 */     String account = "Jkdz888";
/* 15 */     String pswd = "Hificat882";
/*    */ 
/* 18 */     boolean needstatus = false;
/* 19 */     String product = null;
/* 20 */     String extno = null;
/*    */ 
/* 22 */     String returnString = HttpSender.batchSend(url, account, pswd, mobile, content, needstatus, product, extno);
/* 23 */     return returnString;
/*    */   }
/*    */ 
/*    */   public String generateVerificationCode()
/*    */   {
/* 35 */     String vcode = "";
/* 36 */     for (int i = 0; i < 6; i++) {
/* 37 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*    */     }
/* 39 */     return vcode;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.verification.service.impl.SMSVerificationServiceImpl
 * JD-Core Version:    0.6.2
 */