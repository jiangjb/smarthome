/*    */ package io.rong.models;
/*    */ 
/*    */ import io.rong.util.GsonUtil;
/*    */ 
/*    */ public class LBSMessage extends Message
/*    */ {
/*    */   private String content;
/*    */   private double latitude;
/*    */   private double longitude;
/*    */   private String poi;
/*    */   private String extra;
/*    */ 
/*    */   public LBSMessage(String content, double latitude, double longitude, String poi)
/*    */   {
/* 15 */     this.type = "RC:LBSMsg";
/* 16 */     this.content = content;
/* 17 */     this.latitude = latitude;
/* 18 */     this.longitude = longitude;
/* 19 */     this.poi = poi;
/*    */   }
/*    */ 
/*    */   public LBSMessage(String content, double latitude, double longitude, String poi, String extra)
/*    */   {
/* 25 */     this(content, latitude, longitude, poi);
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
/*    */   public double getLatitude() {
/* 38 */     return this.latitude;
/*    */   }
/*    */ 
/*    */   public void setLatitude(double latitude) {
/* 42 */     this.latitude = latitude;
/*    */   }
/*    */ 
/*    */   public double getLongitude() {
/* 46 */     return this.longitude;
/*    */   }
/*    */ 
/*    */   public void setLongitude(double longitude) {
/* 50 */     this.longitude = longitude;
/*    */   }
/*    */ 
/*    */   public String getPoi() {
/* 54 */     return this.poi;
/*    */   }
/*    */ 
/*    */   public void setPoi(String poi) {
/* 58 */     this.poi = poi;
/*    */   }
/*    */ 
/*    */   public String getExtra() {
/* 62 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 66 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 71 */     return GsonUtil.toJson(this, LBSMessage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.LBSMessage
 * JD-Core Version:    0.6.2
 */