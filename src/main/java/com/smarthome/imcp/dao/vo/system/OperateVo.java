/*    */ package com.smarthome.imcp.dao.vo.system;
/*    */ 
/*    */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*    */ import com.smarthome.imcp.dao.model.system.SysParam;
/*    */ 
/*    */ public class OperateVo
/*    */ {
/*    */   private String name;
/*    */   private int index;
/*    */   private char value;
/*    */   private SysParam opreateParam;
/*    */ 
/*    */   public OperateVo(String name, int index, char value)
/*    */   {
/* 15 */     this.name = name;
/* 16 */     this.index = index;
/* 17 */     this.value = value;
/* 18 */     this.opreateParam = SysParamCacheManager.getInstance().getSysParam(
/* 19 */       "FLAG_YESNO", String.valueOf(value));
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public int getIndex() {
/* 31 */     return this.index;
/*    */   }
/*    */ 
/*    */   public void setIndex(int index) {
/* 35 */     this.index = index;
/*    */   }
/*    */ 
/*    */   public char getValue() {
/* 39 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(char value) {
/* 43 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public SysParam getOpreateParam() {
/* 47 */     return this.opreateParam;
/*    */   }
/*    */ 
/*    */   public void setOpreateParam(SysParam opreateParam) {
/* 51 */     this.opreateParam = opreateParam;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.system.OperateVo
 * JD-Core Version:    0.6.2
 */