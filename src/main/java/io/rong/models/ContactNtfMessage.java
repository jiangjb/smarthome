/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class ContactNtfMessage extends Message
/*    */ {
/*    */   private String operation;
/*    */   private String sourceUserId;
/*    */   private String targetUserId;
/*    */   private String extra;
/*    */   private String message;
/*    */ 
/*    */   public String getOperation()
/*    */   {
/* 15 */     return this.operation;
/*    */   }
/*    */ 
/*    */   public void setOperation(String operation) {
/* 19 */     this.operation = operation;
/*    */   }
/*    */ 
/*    */   public String getSourceUserId() {
/* 23 */     return this.sourceUserId;
/*    */   }
/*    */ 
/*    */   public void setSourceUserId(String sourceUserId) {
/* 27 */     this.sourceUserId = sourceUserId;
/*    */   }
/*    */ 
/*    */   public String getTargetUserId() {
/* 31 */     return this.targetUserId;
/*    */   }
/*    */ 
/*    */   public void setTargetUserId(String targetUserId) {
/* 35 */     this.targetUserId = targetUserId;
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
/*    */   public String getExtra() {
/* 47 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 51 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public ContactNtfMessage(String operation, String sourceUserId, String targetUserId, String message)
/*    */   {
/* 56 */     this.type = "RC:ContactNtf";
/* 57 */     this.operation = operation;
/* 58 */     this.sourceUserId = sourceUserId;
/* 59 */     this.targetUserId = targetUserId;
/* 60 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public ContactNtfMessage(String operation, String sourceUserId, String targetUserId, String message, String extra)
/*    */   {
/* 65 */     this(operation, sourceUserId, targetUserId, message);
/* 66 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 71 */     return GsonUtil.toJson(this, ContactNtfMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.ContactNtfMessage
 * JD-Core Version:    0.6.2
 */