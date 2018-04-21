package com.smarthome.imcp.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.imcp.util.EmailUtils;
import com.smarthome.imcp.util.SpringTaskController;

import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Properties;

/**
 * 邮件工具类(发送验证码)
 */
public class MailUtil {
    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     */
	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);//4-20
    public static void send_mail(String to,String text) throws MessagingException {
    	//取出config-properties中的配置
    	Properties prop = new Properties();
    	InputStream in = in();
    	try {
			prop.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	String fromEmail=prop.getProperty("fromEmail");
    	String emailName=prop.getProperty("emailName");
    	String emailPassword=prop.getProperty("emailPassword");
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        //发送端口
        properties.setProperty("mail.smtp.port", "25");//25
        properties.setProperty("mail.smtp.auth", "true");
        //超时设置4-19
        properties.setProperty("mail.smtp.timeout", "80000");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication(emailName,emailPassword);
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(fromEmail));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //设置主题
        message.setSubject("验证码邮件");
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(text,"text/html;charset=utf-8");
        //发送一封邮件
        Transport.send(message);
        logger.info("text邮件发送成功");
    }
    public static void send_Htmlmail(String to,String text) throws MessagingException {//使用SSL协议发送邮件，465端口
    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    	final String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";
    	//取出config-properties中的配置
    	Properties prop = new Properties();
    	InputStream in = in();
    	try {
			prop.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	String fromEmail=prop.getProperty("fromEmail");
    	String emailName=prop.getProperty("emailName");
    	String emailPassword=prop.getProperty("emailPassword");
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        //发送邮件服务器
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        //发送端口
        properties.setProperty("mail.smtp.port", "465");//25
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        //超时设置4-19
        properties.setProperty("mail.smtp.timeout", "80000");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication(emailName,emailPassword);
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(fromEmail));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //设置主题
        message.setSubject("验证码邮件");
        //设置邮件正文  第二个参数是邮件发送的类型
//        message.setContent(text,"text/html;charset=utf-8");
        
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(text, "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // 将MiniMultipart对象设置为邮件内容
        message.setContent(mainPart);
        //发送一封邮件
        Transport.send(message);
        logger.info("html邮件发送成功");
    }
    public static InputStream in()
    {
    	return SpringTaskController.class.getResourceAsStream("/config.properties");
    }
    
    public static void main(String[] args) {
        try {
        	String centent= "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
        			"1768101847@qq.com" + " </td>" + "<tr " + 3 + "><td>" + "验证码为:" + "123" + ",请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
//            MailUtil.send_mail("1768101847@qq.com", "sjigkljl");
        	MailUtil.send_Htmlmail("1768101847@qq.com", centent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}