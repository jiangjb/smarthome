/*    */ package com.smarthome.imcp.exception;
/*    */ 
/*    */ public class RelationExistException extends RuntimeException
/*    */ {
/*    */   private String[] field;
/*    */   private String[] value;
/*    */ 
/*    */   public RelationExistException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RelationExistException(String[] value)
/*    */   {
/* 16 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public RelationExistException(String msg) {
/* 20 */     super(msg);
/*    */   }
/*    */ 
/*    */   public RelationExistException(String msg, Throwable cause) {
/* 24 */     super(msg, cause);
/*    */   }
/*    */ 
/*    */   public String[] getField() {
/* 28 */     return this.field;
/*    */   }
/*    */ 
/*    */   public void setField(String[] field) {
/* 32 */     this.field = field;
/*    */   }
/*    */ 
/*    */   public String[] getValue() {
/* 36 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String[] value) {
/* 40 */     this.value = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.exception.RelationExistException
 * JD-Core Version:    0.6.2
 */