/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.Date;
/*    */ import java.util.Properties;
/*    */ import javax.mail.Authenticator;
/*    */ import javax.mail.Message;
/*    */ import javax.mail.Message.RecipientType;
/*    */ import javax.mail.Session;
/*    */ import javax.mail.Transport;
/*    */ import javax.mail.internet.InternetAddress;
/*    */ import javax.mail.internet.MimeMessage;
/*    */ 
/*    */ public class EmailUtils
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 28 */     Properties prop = new Properties();
/* 29 */     InputStream in = in();
/* 30 */     prop.load(in);
/* 31 */     String title = "服务器预警提示 - ";
/* 32 */     String coneet = "<iframe src=\"https://www.baidu.com/s?ie=UTF-8&wd=aaa\"></iframe>";
/*    */ 
/* 34 */     sendMail(prop.getProperty("fromEmail"), prop.getProperty("toEmail"), prop.getProperty("emailName"), prop.getProperty("emailPassword"), "测试", coneet);
/*    */   }
/*    */ 
/*    */   public static InputStream in()
/*    */   {
/* 41 */     return SpringTaskController.class.getResourceAsStream("/config.properties");
/*    */   }
/*    */ 
/*    */   public static void sendMail(String fromEmail, String toEmail, String emailName, String emailPassword, String title, String centent)
/*    */     throws Exception
/*    */   {
/* 55 */     Properties properties = new Properties();
/* 56 */     properties.setProperty("mail.transport.protocol", "smtp");
/* 57 */     properties.put("mail.smtp.host", "smtp.126.com");
/* 58 */     properties.setProperty("mail.smtp.auth", "true");
/* 59 */     Authenticator auth = new AjavaAuthenticator(emailName, 
/* 60 */       emailPassword);
/* 61 */     Session session = Session.getDefaultInstance(properties, auth);
/* 62 */     Message message = new MimeMessage(session);
/* 63 */     message.setFrom(new InternetAddress(fromEmail));
/* 64 */     message.setRecipient(Message.RecipientType.TO, new InternetAddress(
/* 65 */       toEmail));
/*    */     
/* 67 */     message.setContent(centent, "text/html;charset=utf-8");
/* 68 */     message.setSubject(title);
/* 69 */     message.setSentDate(new Date());
/* 70 */     Transport.send(message);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.EmailUtils
 * JD-Core Version:    0.6.2
 */