/*    */ package com.smarthome.imcp.controller;
/*    */ 
/*    */ public class ResultJson
/*    */ {
/*  4 */   private String callbackType = "";
/*  5 */   private String forwardUrl = "";
/*  6 */   private String statusCode = "200";
/*  7 */   private String message = "";
/*  8 */   private String navTabId = "";
/*  9 */   private String flexId = "";
/*    */ 
/*    */   public ResultJson()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ResultJson(String message) {
/* 16 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public ResultJson(String message, String statusCode, String menuCode) {
/* 20 */     this.message = message;
/* 21 */     this.statusCode = statusCode;
/* 22 */     this.navTabId = menuCode;
/* 23 */     this.callbackType = "closeCurrent";
/*    */   }
/*    */ 
/*    */   public ResultJson(String message, String statusCode, String menuCode, String callbackType) {
/* 27 */     this.message = message;
/* 28 */     this.statusCode = statusCode;
/* 29 */     this.navTabId = menuCode;
/* 30 */     this.callbackType = callbackType;
/*    */   }
/*    */ 
/*    */   public ResultJson(String message, String statusCode) {
/* 34 */     this.message = message;
/* 35 */     this.statusCode = statusCode;
/*    */   }
/*    */ 
/*    */   public String getCallbackType() {
/* 39 */     return this.callbackType;
/*    */   }
/*    */   public void setCallbackType(String callbackType) {
/* 42 */     this.callbackType = callbackType;
/*    */   }
/*    */   public String getForwardUrl() {
/* 45 */     return this.forwardUrl;
/*    */   }
/*    */   public void setForwardUrl(String forwardUrl) {
/* 48 */     this.forwardUrl = forwardUrl;
/*    */   }
/*    */   public String getStatusCode() {
/* 51 */     return this.statusCode;
/*    */   }
/*    */   public void setStatusCode(String statusCode) {
/* 54 */     this.statusCode = statusCode;
/*    */   }
/*    */   public String getMessage() {
/* 57 */     return this.message;
/*    */   }
/*    */   public void setMessage(String message) {
/* 60 */     this.message = message;
/*    */   }
/*    */   public String getNavTabId() {
/* 63 */     return this.navTabId;
/*    */   }
/*    */   public void setNavTabId(String navTabId) {
/* 66 */     this.navTabId = navTabId;
/*    */   }
/*    */   public String getFlexId() {
/* 69 */     return this.flexId;
/*    */   }
/*    */   public void setFlexId(String flexId) {
/* 72 */     this.flexId = flexId;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.ResultJson
 * JD-Core Version:    0.6.2
 */