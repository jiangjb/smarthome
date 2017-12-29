/*    */ package com.smarthome.imcp.controller;
/*    */ 
/*    */ public class ResponseBase
/*    */ {
/*    */   public int code;
/*    */   public String msg;
/*    */   public Object data;
/*    */ 
/*    */   public ResponseBase(int code, String msg)
/*    */   {
/*  6 */     this.code = code;
/*  7 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public ResponseBase(int code, String msg, Object data) {
/* 11 */     this.code = code;
/* 12 */     this.msg = msg;
/* 13 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public int getCode()
/*    */   {
/* 23 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(int code) {
/* 27 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 31 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 35 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public Object getData() {
/* 39 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(Object data) {
/* 43 */     this.data = data;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.ResponseBase
 * JD-Core Version:    0.6.2
 */