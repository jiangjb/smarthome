/*    */ package com.smarthome.imcp.controller;

import java.util.List;

/*    */ 
/*    */ public class RequestJson
/*    */ {
/*  6 */   private Integer page = Integer.valueOf(0);
/*  7 */   private Integer total = Integer.valueOf(0);
/*  8 */   private Integer totalPages = Integer.valueOf(0);
/*  9 */   private boolean success = true;
/* 10 */   private String message = "";
/*    */   private Object data;
		   private String accountOperationType;//3-29 用于存放区分管理员和一般用户的标识
		   private List modelIds;//4-9 用于存放向Android|IOS传递的情景模式 的modelId
		   
		   
		   public List getModelIds() {
			   return modelIds;
		   }
		   public void setModelIds(List modelIdList) {
			   this.modelIds = modelIdList;
		   }
		   public String getAccountOperationType() {
			   return accountOperationType;
		   }
		   public void setAccountOperationType(String accountOperationType) {
			   this.accountOperationType = accountOperationType;
		   }
/*    */ 
/*    */   public Integer getPage()
/*    */   {
/* 15 */     return this.page;
/*    */   }
/*    */ 
/*    */   public void setPage(Integer page) {
/* 19 */     this.page = page;
/*    */   }
/*    */ 
/*    */   public Integer getTotal() {
/* 23 */     return this.total;
/*    */   }
/*    */ 
/*    */   public void setTotal(Integer total) {
/* 27 */     this.total = total;
/*    */   }
/*    */ 
/*    */   public boolean isSuccess() {
/* 31 */     return this.success;
/*    */   }
/*    */ 
/*    */   public void setSuccess(boolean success) {
/* 35 */     this.success = success;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 39 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 43 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public Object getData() {
/* 47 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(Object data) {
/* 51 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public Integer getTotalPages() {
/* 55 */     return this.totalPages;
/*    */   }
/*    */ 
/*    */   public void setTotalPages(Integer totalPages) {
/* 59 */     this.totalPages = totalPages;
/*    */   }
/*    */ 
/*    */   public RequestJson(Integer page, Integer total, Integer totalPages, boolean success, String message, Object data)
/*    */   {
/* 65 */     this.page = page;
/* 66 */     this.total = total;
/* 67 */     this.totalPages = totalPages;
/* 68 */     this.success = success;
/* 69 */     this.message = message;
/* 70 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public RequestJson(boolean success, String message, Object data)
/*    */   {
/* 75 */     this.success = success;
/* 76 */     this.message = message;
/* 77 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public RequestJson()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.RequestJson
 * JD-Core Version:    0.6.2
 */