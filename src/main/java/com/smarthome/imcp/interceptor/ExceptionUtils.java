/*    */ package com.smarthome.imcp.interceptor;
/*    */ 
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import com.smarthome.imcp.exception.DataExistException;
/*    */ import com.smarthome.imcp.exception.NotPermissionException;
/*    */ import com.smarthome.imcp.exception.RelationExistException;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.exception.ConstraintViolationException;
/*    */ import org.springframework.dao.DataIntegrityViolationException;
/*    */ import org.springframework.dao.InvalidDataAccessResourceUsageException;
/*    */ import org.springframework.dao.TypeMismatchDataAccessException;
/*    */ import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
/*    */ import org.springframework.orm.hibernate3.HibernateQueryException;
/*    */ 
/*    */ public final class ExceptionUtils
/*    */ {
/*    */   public static String returnExcetionMessage(Exception e, String actionName)
/*    */   {
/* 19 */     String actionNameUpper = actionName.toUpperCase();
/* 20 */     if ((e instanceof TypeMismatchDataAccessException))
/* 21 */       return "字段类型不匹配！";
/* 22 */     if ((e instanceof InvalidDataAccessResourceUsageException)) {
/* 23 */       if ((actionNameUpper.startsWith("SAVE")) || 
/* 24 */         (actionNameUpper.startsWith("UPDATE")) || 
/* 25 */         (actionNameUpper.startsWith("EDIT"))) {
/* 26 */         return "输入的字段长度过长或字段不存在！" + e.getMessage();
/*    */       }
/* 28 */       return "查询语句出错！" + e.getMessage();
/*    */     }
/* 30 */     if ((e instanceof DataIntegrityViolationException)) {
/* 31 */       if ((e.getCause() instanceof ConstraintViolationException)) {
/* 32 */         return "违反唯一约束条件！" + e.getMessage();
/*    */       }
/* 34 */       return "字段不允许为空或违反约束性条件！" + e.getMessage();
/*    */     }
/* 36 */     if ((e instanceof ConstraintViolationException))
/* 37 */       return "字段不允许为空或违反约束性条件！" + e.getMessage();
/* 38 */     if ((e instanceof HibernateOptimisticLockingFailureException))
/* 39 */       return "当前记录已经被修改或删除！" + e.getMessage();
/* 40 */     if ((e instanceof HibernateQueryException))
/* 41 */       return "查询语句出错！" + e.getMessage();
/* 42 */     if ((e instanceof DataExistException)) {
/* 43 */       if (StringUtils.isEmpty(e.getMessage())) {
/* 44 */         return "当前记录已经存在！" + e.getMessage();
/*    */       }
/* 46 */       return e.getMessage();
/*    */     }
/* 48 */     if ((e instanceof RelationExistException)) {
/* 49 */       if (StringUtils.isEmpty(e.getMessage())) {
/* 50 */         return "当前记录已经被使用不能删除！" + e.getMessage();
/*    */       }
/* 52 */       return e.getMessage();
/*    */     }
/* 54 */     if ((e instanceof NotPermissionException))
/* 55 */       return "无权限访问！" + e.getMessage();
/* 56 */     if ((e instanceof BusinessException)) {
/* 57 */       return e.getMessage();
/*    */     }
/* 59 */     e.printStackTrace();
/* 60 */     if (actionNameUpper.startsWith("SAVE"))
/* 61 */       return "保存失败！" + e.getMessage();
/* 62 */     if ((actionNameUpper.startsWith("UPDATE")) || 
/* 63 */       (actionNameUpper.startsWith("EDIT")))
/* 64 */       return "修改失败！" + e.getMessage();
/* 65 */     if (actionNameUpper.startsWith("DELETE"))
/* 66 */       return "删除失败！" + e.getMessage();
/* 67 */     if (actionNameUpper.startsWith("APPROVE")) {
/* 68 */       return "审批失败！" + e.getMessage();
/*    */     }
/* 70 */     return "操作失败！" + e.getMessage();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.interceptor.ExceptionUtils
 * JD-Core Version:    0.6.2
 */