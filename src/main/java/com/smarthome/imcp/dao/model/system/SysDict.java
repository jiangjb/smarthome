/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ import org.apache.struts2.json.annotations.JSON;
/*    */ 
/*    */ public class SysDict extends AbstractData
/*    */ {
/*    */   private SysDictCode sysDictCode;
/*    */   private Integer dictId;
/*    */   private String dictValue;
/*    */   private String dictName;
/*    */   private String dictRmk;
/*    */ 
/*    */   public SysDict()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysDict(String dictCode, String dictValue, String dictName)
/*    */   {
/* 30 */     if (this.sysDictCode == null) {
/* 31 */       this.sysDictCode = new SysDictCode();
/* 32 */       this.sysDictCode.setDictCode(dictCode);
/*    */     }
/* 34 */     this.dictValue = dictValue;
/* 35 */     this.dictName = dictName;
/*    */   }
/*    */ 
/*    */   @JSON(serialize=false)
/*    */   public SysDictCode getSysDictCode()
/*    */   {
/* 42 */     return this.sysDictCode;
/*    */   }
/*    */ 
/*    */   public void setSysDictCode(SysDictCode sysDictCode) {
/* 46 */     this.sysDictCode = sysDictCode;
/*    */   }
/*    */ 
/*    */   public Integer getDictId() {
/* 50 */     return this.dictId;
/*    */   }
/*    */ 
/*    */   public void setDictId(Integer dictId) {
/* 54 */     this.dictId = dictId;
/*    */   }
/*    */ 
/*    */   public String getDictValue() {
/* 58 */     return this.dictValue;
/*    */   }
/*    */ 
/*    */   public void setDictValue(String dictValue) {
/* 62 */     this.dictValue = dictValue;
/*    */   }
/*    */ 
/*    */   public String getDictName() {
/* 66 */     return this.dictName;
/*    */   }
/*    */ 
/*    */   public void setDictName(String dictName) {
/* 70 */     this.dictName = dictName;
/*    */   }
/*    */ 
/*    */   public String getDictRmk() {
/* 74 */     return this.dictRmk;
/*    */   }
/*    */ 
/*    */   public void setDictRmk(String dictRmk) {
/* 78 */     this.dictRmk = dictRmk;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysDict
 * JD-Core Version:    0.6.2
 */