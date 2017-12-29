/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class ImgTextMessage extends Message
/*    */ {
/*    */   private String content;
/*    */   private String title;
/*    */   private String imageUri;
/*    */   private String extra;
/*    */ 
/*    */   public ImgTextMessage(String content, String title, String imageUri)
/*    */   {
/* 14 */     this.type = "RC:ImgTextMsg";
/* 15 */     this.content = content;
/* 16 */     this.title = title;
/* 17 */     this.imageUri = imageUri;
/*    */   }
/*    */ 
/*    */   public ImgTextMessage(String content, String title, String imageUri, String extra)
/*    */   {
/* 22 */     this(content, title, imageUri);
/* 23 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 27 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 31 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String getTitle() {
/* 35 */     return this.title;
/*    */   }
/*    */ 
/*    */   public void setTitle(String title) {
/* 39 */     this.title = title;
/*    */   }
/*    */ 
/*    */   public String getImageUri() {
/* 43 */     return this.imageUri;
/*    */   }
/*    */ 
/*    */   public void setImageUri(String imageUri) {
/* 47 */     this.imageUri = imageUri;
/*    */   }
/*    */ 
/*    */   public String getExtra() {
/* 51 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 55 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 60 */     return GsonUtil.toJson(this, ImgTextMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.ImgTextMessage
 * JD-Core Version:    0.6.2
 */