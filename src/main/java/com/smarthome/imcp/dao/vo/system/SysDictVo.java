/*    */ package com.smarthome.imcp.dao.vo.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysDict;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SysDictVo
/*    */ {
/*    */   private String dictCode;
/*    */   private List<SysDict> sysDictList;
/*    */ 
/*    */   public String getDictCode()
/*    */   {
/* 13 */     return this.dictCode;
/*    */   }
/*    */ 
/*    */   public void setDictCode(String dictCode) {
/* 17 */     this.dictCode = dictCode;
/*    */   }
/*    */ 
/*    */   public List<SysDict> getSysDictList() {
/* 21 */     return this.sysDictList;
/*    */   }
/*    */ 
/*    */   public void setSysDictList(List<SysDict> sysDictList) {
/* 25 */     this.sysDictList = sysDictList;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.system.SysDictVo
 * JD-Core Version:    0.6.2
 */