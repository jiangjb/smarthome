/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*    */ 
/*    */ public class UserUtil
/*    */ {
/*    */   public static BoUsers save(String userPhone, String userPwd, String userEmail)
/*    */   {
/*  8 */     BoUsers user = new BoUsers();
/*  9 */     user.setUserPhone(userPhone);
/* 10 */     user.setUserCode(UuidUtil.get32UUID());
/* 11 */     user.setUserPwd(userPwd);
/* 12 */     user.setUserEmail(userEmail);
/* 13 */     user.setAccessToken("");
/* 14 */     user.setRefreshToken("");
/* 15 */     user.setAccessTokenTime("");
/* 16 */     user.setRefreshTokenTime("");
/* 17 */     user.setFluoriteAccessToken("");
/* 18 */     user.setFluoriteAccessTokenCreateTime("");
/* 19 */     user.setFluoriteAccessTokenExpireTime("");
/* 20 */     user.setCid("");
/* 21 */     user.setPhoneType(Integer.valueOf(0));
/* 22 */     user.setFluoriteAccessTokenExpireTime("");
/* 23 */     user.setUserAddr("");//无用字段 用作消息推送的标识之一
/* 24 */     user.setVersionType("0");
/* 25 */     user.setHeadPic("uploads/headpic/head.jpg");
/* 26 */     user.setUserName("");
/* 27 */     user.setSecurityTotalSwitch("0");
/* 28 */     user.setVersion(Integer.valueOf(0));
/* 29 */     user.setUserSex("");
/* 30 */     user.setUserAge("");//无用字段 用作消息推送的标识之一
/* 31 */     user.setCity("");
/* 32 */     user.setAuthorizationUserCode("");
/* 33 */     user.setLogoAccountType("M");
/* 34 */     user.setAccountOperationType("1");
/* 35 */     user.setSignature("");
/* 36 */     user.setIsFirst(Boolean.valueOf(false));
/* 37 */     user.setWhetherSetPwd(Boolean.valueOf(true));
/* 38 */     return user;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.UserUtil
 * JD-Core Version:    0.6.2
 */