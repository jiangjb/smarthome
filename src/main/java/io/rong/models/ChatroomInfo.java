/*    */ package io.rong.models;
/*    */ 
/*    */ public class ChatroomInfo
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */ 
/*    */   public ChatroomInfo(String id, String name)
/*    */   {
/*  9 */     this.name = name;
/* 10 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 14 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 18 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getId() {
/* 22 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 26 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 31 */     return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", new Object[] { this.id, this.name });
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.ChatroomInfo
 * JD-Core Version:    0.6.2
 */