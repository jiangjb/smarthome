/*    */ package io.rong.models;
/*    */ 
/*    */ public enum FormatType
/*    */ {
/*  5 */   json("json", 0), xml("xml", 1);
/*    */ 
/*    */   private String name;
/*    */   private int index;
/*    */ 
/* 11 */   public int getIndex() { return this.index; }
/*    */ 
/*    */   private FormatType(String name, int index)
/*    */   {
/* 15 */     this.name = name;
/* 16 */     this.index = index;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 21 */     return this.name;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.FormatType
 * JD-Core Version:    0.6.2
 */