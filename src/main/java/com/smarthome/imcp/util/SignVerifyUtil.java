/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.smarthome.imcp.common.Md5;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.struts2.ServletActionContext;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class SignVerifyUtil
/*    */ {
/*    */   public static final String SECTET = "12345";
/* 15 */   private static Logger logger = LoggerFactory.getLogger(SignVerifyUtil.class);
/*    */ 
/*    */   public static Boolean isRal(String timestamp, String nonce, String sign, String access_Token, String userCode, String interfaceName)
/*    */   {
/* 27 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 28 */     String ip = request.getRemoteAddr();
/* 29 */     String str = "";
/* 30 */     Md5 md5 = new Md5();
/* 31 */     boolean isRel = true;
/*    */ 
/* 33 */     str = str + "access_token=";
/* 34 */     str = str + access_Token;
/* 35 */     System.err.println("access_Token " + access_Token);
/* 36 */     str = str + "&nonce=";
/* 37 */     str = str + nonce;
/* 38 */     System.err.println("nonce " + nonce);
/* 39 */     str = str + "&timestamp=";
/* 40 */     str = str + timestamp;
/* 41 */     System.err.println("timestamp " + timestamp);
/* 42 */     str = str + "&userCode=";
/* 43 */     str = str + userCode;
/* 44 */     System.err.println("userCode " + userCode);
/* 45 */     str = str + "12345";
/* 46 */     String service_sign = md5.getMD5ofStr(str).toLowerCase();
/* 47 */     System.err.println("客户端" + sign);
/* 48 */     System.err.println("服务器" + service_sign);
/* 49 */     if (service_sign.equals(sign)) {
/* 50 */       return Boolean.valueOf(isRel);
/*    */     }
/* 52 */     logger.error("ip： " + ip + "   " + "接口昵称：" + interfaceName + " 验签验证不通过");
/* 53 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.SignVerifyUtil
 * JD-Core Version:    0.6.2
 */