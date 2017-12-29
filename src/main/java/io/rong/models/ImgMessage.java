/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class ImgMessage extends Message
/*    */ {
/*    */   private String content;
/*    */   private String imageUri;
/*    */   private String extra;
/*    */ 
/*    */   public String getExtra()
/*    */   {
/* 13 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 17 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public ImgMessage(String content, String imageUri) {
/* 21 */     this.type = "RC:ImgMsg";
/* 22 */     this.content = content;
/* 23 */     this.imageUri = imageUri;
/*    */   }
/*    */ 
/*    */   public ImgMessage(String content, String imageUri, String extra) {
/* 27 */     this(content, imageUri);
/* 28 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 32 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 36 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String getImageKey() {
/* 40 */     return this.imageUri;
/*    */   }
/*    */ 
/*    */   public void setImageKey(String imageUri) {
/* 44 */     this.imageUri = imageUri;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 49 */     return GsonUtil.toJson(this, ImgMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.ImgMessage
 * JD-Core Version:    0.6.2
 */