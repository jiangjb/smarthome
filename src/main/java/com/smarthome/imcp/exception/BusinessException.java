/*    */ package com.smarthome.imcp.exception;
/*    */ 
/*    */ public class BusinessException extends RuntimeException
/*    */ {
/*    */   public BusinessException(String msg)
/*    */   {
/*  7 */     super(msg);
/*    */   }
/*    */ 
/*    */   public BusinessException(String msg, Throwable cause) {
/* 11 */     super(msg, cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.exception.BusinessException
 * JD-Core Version:    0.6.2
 */