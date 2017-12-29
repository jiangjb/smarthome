/*    */ package com.smarthome.imcp.service.impl.secur;
/*    */ 
/*    */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*    */ import com.smarthome.imcp.dao.model.system.SysMenuRole;
/*    */ import com.smarthome.imcp.dao.model.system.SysParam;
/*    */ import com.smarthome.imcp.dao.model.system.SysUser;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
/*    */ import com.smarthome.imcp.service.secur.SecurServiceIface;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("securService")
/*    */ public class SecurServiceImpl
/*    */   implements SecurServiceIface
/*    */ {
/*    */   public String doCheckUser(SysUser sysUser)
/*    */   {
/* 21 */     return null;
/*    */   }
/*    */ 
/*    */   public CurrentUser createCurrentUser(SysUser sysUser)
/*    */   {
/* 26 */     CurrentUser currentUser = new CurrentUser();
/* 27 */     String userType = sysUser.getUserType();
/* 28 */     String regionType = sysUser.getRegionType();
/*    */ 
/* 30 */     currentUser.setUserId(sysUser.getUserId());
/* 31 */     currentUser.setUserName(sysUser.getUserName());
/* 32 */     currentUser.setUserType(userType);
/* 33 */     currentUser.setUserTypeName(SysParamCacheManager.getInstance()
/* 34 */       .getSysParam("USER_TYPE", userType)
/* 35 */       .getParamName());
/*    */ 
/* 37 */     currentUser.setFactoryId(sysUser.getFactoryId());
/*    */ 
/* 39 */     currentUser.setProvinceId(sysUser.getProvinceId());
/* 40 */     currentUser.setCityId(sysUser.getCityId());
/* 41 */     currentUser.setCountyId(sysUser.getCountyId());
/* 42 */     currentUser.setTownId(sysUser.getTownId());
/* 43 */     currentUser.setVillageId(sysUser.getVillageId());
/*    */ 
/* 45 */     currentUser.setProvinceName(sysUser.getProvinceName());
/* 46 */     currentUser.setCityName(sysUser.getCityName());
/* 47 */     currentUser.setCountyName(sysUser.getCountyName());
/* 48 */     currentUser.setTownName(sysUser.getTownName());
/* 49 */     currentUser.setVillageName(sysUser.getVillageName());
/*    */ 
/* 51 */     currentUser.setOrganizeId(sysUser.getOrganizeId());
/* 52 */     currentUser.setOrganizeName(sysUser.getOrganizeName());
/*    */ 
/* 54 */     currentUser.setRegionType(regionType);
			 System.out.println("currentUser="+currentUser);//com.smarthome.imcp.secur.CurrentUser@4bb81210
/*    */ 
/* 56 */     if ("PRO".equals(regionType)) {
/* 57 */       currentUser.setRegionId(sysUser.getProvinceId());
/* 58 */       currentUser.setRegionName(sysUser.getProvinceName());
/* 59 */     } else if ("CIT".equals(regionType)) {
/* 60 */       currentUser.setRegionId(sysUser.getCityId());
/* 61 */       currentUser.setRegionName(sysUser.getCityName());
/* 62 */     } else if ("COU".equals(regionType)) {
/* 63 */       currentUser.setRegionId(sysUser.getCountyId());
/* 64 */       currentUser.setRegionName(sysUser.getCountyName());
/* 65 */     } else if ("TOW".equals(regionType)) {
/* 66 */       currentUser.setRegionId(sysUser.getTownId());
/* 67 */       currentUser.setRegionName(sysUser.getTownName());
/* 68 */     } else if ("VIL".equals(regionType)) {
/* 69 */       currentUser.setRegionId(sysUser.getVillageId());
/* 70 */       currentUser.setRegionName(sysUser.getVillageName());
/*    */     }
/*    */ 
/* 75 */     "NOR".equals(userType);
/*    */ 
/* 82 */     if ("admin".equals(sysUser.getLoginName())) {
/* 83 */       currentUser.setMenuRole("SUP");
/* 84 */       currentUser.setUserType("SUP");
/*    */     } else {
///* 86 */       currentUser.setMenuRole(sysUser.getSysMenuRole().getMenuRoleCode());//可以到这里啦，庆祝
	             System.out.println("空指针异常");
/*    */     }
/*    */ 
/* 89 */     return currentUser;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.secur.SecurServiceImpl
 * JD-Core Version:    0.6.2
 */