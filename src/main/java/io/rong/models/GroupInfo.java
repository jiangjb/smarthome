/*    */ package io.rong.models;
/*    */ 
/*    */ public class GroupInfo
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */ 
/*    */   public GroupInfo(String id, String name)
/*    */   {
/* 10 */     this.name = name;
/* 11 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 15 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 19 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 27 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 32 */     return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", new Object[] { this.id, this.name });
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.GroupInfo
 * JD-Core Version:    0.6.2
 */