/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class CustomTxtMessage extends Message
/*    */ {
/*    */   private String content;
/*    */ 
/*    */   public CustomTxtMessage(String content)
/*    */   {
/* 11 */     this.type = "KM:TxtMsg";
/* 12 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 16 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 20 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 25 */     return GsonUtil.toJson(this, CustomTxtMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.CustomTxtMessage
 * JD-Core Version:    0.6.2
 */