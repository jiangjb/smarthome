/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SysKey
/*    */   implements Serializable
/*    */ {
/*    */   private Integer keyId;
/*    */   private String keyCode;
/*    */   private Integer keyValue;
/*    */   private String keyPrefix;
/*    */   private Short keyWidth;
/*    */ 
/*    */   public SysKey()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysKey(String keyCode, Integer keyValue, String keyPrefix, Short keyWidth)
/*    */   {
/* 27 */     this.keyCode = keyCode;
/* 28 */     this.keyValue = keyValue;
/* 29 */     this.keyPrefix = keyPrefix;
/* 30 */     this.keyWidth = keyWidth;
/*    */   }
/*    */ 
/*    */   public Integer getKeyId()
/*    */   {
/* 36 */     return this.keyId;
/*    */   }
/*    */ 
/*    */   public void setKeyId(Integer keyId) {
/* 40 */     this.keyId = keyId;
/*    */   }
/*    */ 
/*    */   public String getKeyCode() {
/* 44 */     return this.keyCode;
/*    */   }
/*    */ 
/*    */   public void setKeyCode(String keyCode) {
/* 48 */     this.keyCode = keyCode;
/*    */   }
/*    */ 
/*    */   public Integer getKeyValue() {
/* 52 */     return this.keyValue;
/*    */   }
/*    */ 
/*    */   public void setKeyValue(Integer keyValue) {
/* 56 */     this.keyValue = keyValue;
/*    */   }
/*    */ 
/*    */   public String getKeyPrefix() {
/* 60 */     return this.keyPrefix;
/*    */   }
/*    */ 
/*    */   public void setKeyPrefix(String keyPrefix) {
/* 64 */     this.keyPrefix = keyPrefix;
/*    */   }
/*    */ 
/*    */   public Short getKeyWidth() {
/* 68 */     return this.keyWidth;
/*    */   }
/*    */ 
/*    */   public void setKeyWidth(Short keyWidth) {
/* 72 */     this.keyWidth = keyWidth;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysKey
 * JD-Core Version:    0.6.2
 */