/*     */ package com.smarthome.imcp.aspect;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.dao.model.system.SysOperate;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.BasicServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.aspectj.lang.JoinPoint;
/*     */ import org.aspectj.lang.Signature;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ public class LogAspect
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BasicServiceIface<SysOperate, Serializable> sysOperateService;
/*     */ 
/*     */   public void doSystemSuccessLog(JoinPoint point)
/*     */   {
/*  24 */     saveSystemLog(point, "成功");
/*     */   }
/*     */ 
/*     */   public void doSystemErrorLog(JoinPoint point, Exception exception) {
/*  28 */     saveSystemLog(point, "错误");
/*     */   }
/*     */ 
/*     */   private boolean saveSystemLog(JoinPoint point, String status)
/*     */   {
/*  33 */     String opearteType = "";
/*  34 */     String methodName = point.getSignature().getName();
/*  35 */     Class persistentClass = null;
/*     */     try {
/*  37 */       if (!GlobalMethod.isNullorEmpty(methodName))
/*     */       {
/*  39 */         Object model = null;
/*  40 */         if (point.getArgs().length >= 1) {
/*  41 */           model = point.getArgs()[0];
/*     */         }
/*  43 */         if (model != null) {
/*  44 */           persistentClass = model.getClass();
/*  45 */           LogConfig logConfig = null;
/*  46 */           logConfig = LogConfigManager.getLogConfig(persistentClass.getName());
/*  47 */           if (logConfig == null) {
/*  48 */             return false;
/*     */           }
/*  50 */           if (GlobalMethod.isNullorEmpty(opearteType)) {
/*  51 */             opearteType = getOpearteType(methodName);
/*     */           }
/*  53 */           CurrentUser currentUser = ContextUtil.getCurrentUser();
/*  54 */           if (!GlobalMethod.isNull(currentUser)) {
/*  55 */             SysOperate sysOperate = new SysOperate();
/*  56 */             sysOperate.setOperateStatus(status);
/*  57 */             sysOperate.setOperateIpTxt(ContextUtil.getIP());
/*  58 */             sysOperate.setOperateType(opearteType);
/*  59 */             sysOperate.setOperateContentTxt(getContent(logConfig, 
/*  60 */               model, currentUser, opearteType));
/*  61 */             sysOperate.setOperateModuleTxt(logConfig.getModel());
/*  62 */             sysOperate.setUserId(currentUser.getUserId()
/*  63 */               .toString());
/*  64 */             sysOperate.setOperateUserName(currentUser.getUserName());
/*  65 */             sysOperate.setOperateDate(GlobalMethod.formatDate(GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
/*  66 */             this.sysOperateService.save(sysOperate);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  72 */       return false;
/*     */     }
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   private String getOpearteType(String straMethodName)
/*     */   {
/*  84 */     String strOpearteType = "";
/*  85 */     String strMethodName = straMethodName.toLowerCase();
/*  86 */     if (strMethodName.startsWith("get"))
/*  87 */       strOpearteType = "查询";
/*  88 */     else if ((strMethodName.startsWith("save")) || 
/*  89 */       (strMethodName.startsWith("justsave")))
/*  90 */       strOpearteType = "保存";
/*  91 */     else if (strMethodName.startsWith("update"))
/*  92 */       strOpearteType = "修改";
/*  93 */     else if (strMethodName.startsWith("delete")) {
/*  94 */       strOpearteType = "删除";
/*     */     }
/*  96 */     return strOpearteType;
/*     */   }
/*     */ 
/*     */   private String getContent(LogConfig logConfig, Object obj, CurrentUser currentUser, String opearteType)
/*     */   {
/* 101 */     String strContent = logConfig.getContent();
/* 102 */     List listKey = logConfig.getListKey();
/* 103 */     strContent = strContent.replace("${userID}", "'" + currentUser.getUserId() + "'");
/* 104 */     strContent = strContent.replace("${userName}", "'" + currentUser.getUserName() + "'");
/* 105 */     strContent = strContent.replace("${action}", opearteType);
/* 106 */     for (int i = 0; i < listKey.size(); i++) {
/* 107 */       String key = (String)listKey.get(i);
/*     */       try {
/* 109 */         strContent = strContent.replace("${" + i + "}", "'" + 
/* 110 */           BeanUtils.getProperty(obj, key) + "'");
/*     */       } catch (Exception e) {
/* 112 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 115 */     return strContent;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.aspect.LogAspect
 * JD-Core Version:    0.6.2
 */