/*    */ package com.smarthome.imcp.common.flexigrid;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FlexigridJson<T>
/*    */   implements Serializable
/*    */ {
/*    */   private Integer page;
/*    */   private Integer total;
/*    */   private List<FlexigridJsonRow<T>> rows;
/*    */ 
/*    */   public Integer getPage()
/*    */   {
/* 21 */     return this.page;
/*    */   }
/*    */ 
/*    */   public void setPage(Integer page)
/*    */   {
/* 29 */     this.page = page;
/*    */   }
/*    */ 
/*    */   public Integer getTotal()
/*    */   {
/* 36 */     return this.total;
/*    */   }
/*    */ 
/*    */   public void setTotal(Integer total)
/*    */   {
/* 44 */     this.total = total;
/*    */   }
/*    */ 
/*    */   public List<FlexigridJsonRow<T>> getRows() {
/* 48 */     return this.rows;
/*    */   }
/*    */ 
/*    */   public void setRows(List<FlexigridJsonRow<T>> rows) {
/* 52 */     this.rows = rows;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.flexigrid.FlexigridJson
 * JD-Core Version:    0.6.2
 */