/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class VoiceMessage extends Message
/*    */ {
/*    */   private String content;
/*    */   private Long duration;
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
/*    */   public VoiceMessage(String content, Long duration) {
/* 21 */     this.type = "RC:VcMsg";
/* 22 */     this.content = content;
/* 23 */     this.duration = duration;
/*    */   }
/*    */ 
/*    */   public VoiceMessage(String content, Long duration, String extra) {
/* 27 */     this(content, duration);
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
/*    */   public Long getDuration() {
/* 40 */     return this.duration;
/*    */   }
/*    */ 
/*    */   public void setDuration(Long duration) {
/* 44 */     this.duration = duration;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 48 */     return GsonUtil.toJson(this, VoiceMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.VoiceMessage
 * JD-Core Version:    0.6.2
 */