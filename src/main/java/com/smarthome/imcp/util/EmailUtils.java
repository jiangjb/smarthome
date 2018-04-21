/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.InputStream;
import java.security.Security;
/*    */ import java.util.Date;
/*    */ import java.util.Properties;
/*    */ import javax.mail.Authenticator;
import javax.mail.BodyPart;
/*    */ import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
/*    */ import javax.mail.Message.RecipientType;
/*    */ import javax.mail.Session;
/*    */ import javax.mail.Transport;
/*    */ import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
/*    */ import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory;

/*    */ public class EmailUtils
/*    */ {
		   private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);//4-19
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 28 */     Properties prop = new Properties();
/* 29 */     InputStream in = in();
/* 30 */     prop.load(in);
			System.out.println(prop);
/* 31 */     String title = "智能屋";
/* 32 */     String centent= "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
		"1768101847@qq.com" + " </td>" + "<tr " + 3 + "><td>" + "验证码为:" + "123" + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 34 */     sendMail(prop.getProperty("fromEmail"), "1768101847@qq.com", prop.getProperty("emailName"), prop.getProperty("emailPassword"), title, centent);
/*    */   }
/*    */ 
/*    */   public static InputStream in()
/*    */   {
/* 41 */     return SpringTaskController.class.getResourceAsStream("/config.properties");
/*    */   }
/*    */ 
/*    */   public static void sendMail(String fromEmail, String toEmail, String emailName, String emailPassword, String title, String centent)
/*    */     throws Exception
/*    */   { //使用SSL协议 发送邮件，阿里云服务器禁了25号端口的使用
//			centent= "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
//	        		 toEmail + " </td>" + "<tr " + 3 + "><td>" + "验证码为:" + "123" + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
			 logger.info("fromEmail:"+fromEmail+",toEmail:"+toEmail+",emailName:"+emailName+",emailPassword:"+emailPassword+",title:"+title+",centent:"+centent);
			 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());//4-21
		     final String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";//4-21
/* 55 */     Properties properties = new Properties();
///* 56 */     properties.setProperty("mail.transport.protocol", "smtp");
//			 properties.put("mail.transport.protocol", "smtp");
			 properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);//4-21
			 properties.setProperty("mail.smtp.host", "smtp.163.com");
			 properties.setProperty("mail.smtp.port", "465");
			 properties.setProperty("mail.smtp.socketFactory.port", "465");//4-21
			 properties.setProperty("mail.smtp.auth", "true");
			//超时设置4-19
//			properties.put("mail.smtp.connectiontimeout", "100000");
			 properties.setProperty("mail.smtp.timeout", "80000");
			Session session = Session.getInstance(properties, new Authenticator() {
			    @Override
			    protected PasswordAuthentication getPasswordAuthentication() {
			        //两个参数分别是发送邮件的账户和密码
			        return new PasswordAuthentication(emailName,emailPassword);
			    }
			});
/* 62 */     Message message = new MimeMessage(session);
/* 63 */     message.setFrom(new InternetAddress(fromEmail));
/* 64 */     message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
/* 67 */     message.setContent(centent, "text/html;charset=utf-8");
/* 68 */     message.setSubject(title);
/* 69 */     message.setSentDate(new Date());
			//MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(centent, "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			message.setContent(mainPart);
			 // 发送邮件  
/* 70 */     Transport.send(message);
			 logger.info("发送成功");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.EmailUtils
 * JD-Core Version:    0.6.2
 */