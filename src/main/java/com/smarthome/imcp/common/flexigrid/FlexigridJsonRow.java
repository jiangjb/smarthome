/*    */ package com.smarthome.imcp.common.flexigrid;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FlexigridJsonRow<T>
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private T cell;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 13 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 17 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 21 */     this.id = String.valueOf(id);
/*    */   }
/*    */ 
/*    */   public T getCell() {
/* 25 */     return this.cell;
/*    */   }
/*    */ 
/*    */   public void setCell(T cell) {
/* 29 */     this.cell = cell;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.flexigrid.FlexigridJsonRow
 * JD-Core Version:    0.6.2
 */