/*    */ package com.smarthome.imcp.helper.result;
/*    */ 
/*    */ public class DocumentPage
/*    */ {
/*    */   private String url;
/*    */   private String rel;
/*    */   private String sieadType;
/*    */   private boolean toParent;
/*    */ 
/*    */   public DocumentPage()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DocumentPage(String url, String rel, String sieadType, boolean toParent)
/*    */   {
/* 16 */     this.url = url;
/* 17 */     this.rel = rel;
/* 18 */     this.sieadType = sieadType;
/* 19 */     this.toParent = toParent;
/*    */   }
/*    */   public String getUrl() {
/* 22 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 25 */     this.url = url;
/*    */   }
/*    */   public String getRel() {
/* 28 */     return this.rel;
/*    */   }
/*    */   public void setRel(String rel) {
/* 31 */     this.rel = rel;
/*    */   }
/*    */   public String getSieadType() {
/* 34 */     return this.sieadType;
/*    */   }
/*    */   public void setSieadType(String sieadType) {
/* 37 */     this.sieadType = sieadType;
/*    */   }
/*    */   public boolean isToParent() {
/* 40 */     return this.toParent;
/*    */   }
/*    */   public void setToParent(boolean toParent) {
/* 43 */     this.toParent = toParent;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.result.DocumentPage
 * JD-Core Version:    0.6.2
 */