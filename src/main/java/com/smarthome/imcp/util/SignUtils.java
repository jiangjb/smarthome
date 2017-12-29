/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.smarthome.imcp.common.Md5;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.struts2.ServletActionContext;
/*    */ 
/*    */ public class SignUtils
/*    */ {
/*    */   public static Boolean isRal(HttpServletRequest request)
/*    */   {
/* 12 */     request = ServletActionContext.getRequest();
/* 13 */     Md5 md5 = new Md5();
/* 14 */     boolean isRel = true;
/* 15 */     StringBuffer signStr = new StringBuffer();
/* 16 */     signStr.append("access_token=");
/* 17 */     signStr.append(request.getHeader("access_token"));
/* 18 */     signStr.append("&nonce=");
/* 19 */     signStr.append(request.getHeader("nonce"));
/* 20 */     signStr.append("&timestamp=");
/* 21 */     signStr.append(request.getHeader("timestamp"));
/* 22 */     signStr.append("&userCode=");
/* 23 */     signStr.append(request.getHeader("userCode"));
/* 24 */     signStr.append("12345");
/* 25 */     String service_sign = md5.getMD5ofStr(signStr.toString()).toLowerCase();
/* 26 */     System.err.println("客户端  " + request.getHeader("sign"));
/* 27 */     System.err.println("服务端  " + service_sign);
/* 28 */     if (service_sign.equals(request.getHeader("sign"))) {
/* 29 */       return Boolean.valueOf(isRel);
/*    */     }
/* 31 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.SignUtils
 * JD-Core Version:    0.6.2
 */