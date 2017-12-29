/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SysDictCode
/*    */   implements Serializable
/*    */ {
/*    */   private String dictCode;
/*    */   private String dictCodeName;
/*    */   private String dictCodeRmk;
/*    */ 
/*    */   public SysDictCode()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysDictCode(String dictCode)
/*    */   {
/* 26 */     this.dictCode = dictCode;
/*    */   }
/*    */ 
/*    */   public SysDictCode(String dictCode, String dictCodeName)
/*    */   {
/* 31 */     this.dictCode = dictCode;
/* 32 */     this.dictCodeName = dictCodeName;
/*    */   }
/*    */ 
/*    */   public String getDictCode()
/*    */   {
/* 38 */     return this.dictCode;
/*    */   }
/*    */ 
/*    */   public void setDictCode(String dictCode) {
/* 42 */     this.dictCode = dictCode;
/*    */   }
/*    */ 
/*    */   public String getDictCodeName() {
/* 46 */     return this.dictCodeName;
/*    */   }
/*    */ 
/*    */   public void setDictCodeName(String dictCodeName) {
/* 50 */     this.dictCodeName = dictCodeName;
/*    */   }
/*    */ 
/*    */   public String getDictCodeRmk() {
/* 54 */     return this.dictCodeRmk;
/*    */   }
/*    */ 
/*    */   public void setDictCodeRmk(String dictCodeRmk) {
/* 58 */     this.dictCodeRmk = dictCodeRmk;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysDictCode
 * JD-Core Version:    0.6.2
 */