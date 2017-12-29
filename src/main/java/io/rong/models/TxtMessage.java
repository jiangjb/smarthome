/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class TxtMessage extends Message
/*    */ {
/*    */   private String content;
/*    */   private String extra;
/*    */ 
/*    */   public String getExtra()
/*    */   {
/* 12 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 16 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public TxtMessage(String content) {
/* 20 */     this.type = "RC:TxtMsg";
/* 21 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public TxtMessage(String content, String extra) {
/* 25 */     this(content);
/* 26 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 30 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 34 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 39 */     return GsonUtil.toJson(this, TxtMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.TxtMessage
 * JD-Core Version:    0.6.2
 */