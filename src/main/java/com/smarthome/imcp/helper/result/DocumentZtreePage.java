/*    */ package com.smarthome.imcp.helper.result;
/*    */ 
/*    */ public class DocumentZtreePage
/*    */ {
/*    */   private String ztreeAction;
/*    */   private String customizeAction;
/*    */   private String unfiyAction;
/*    */   private String url;
/*    */   private String rel;
/*    */ 
/*    */   public DocumentZtreePage(String ztreeAction, String url, String rel)
/*    */   {
/* 12 */     this.ztreeAction = ztreeAction;
/* 13 */     this.url = url;
/* 14 */     this.rel = rel;
/*    */   }
/*    */ 
/*    */   public DocumentZtreePage(String customizeAction, String unfiyAction, String url, String rel) {
/* 18 */     this.customizeAction = customizeAction;
/* 19 */     this.unfiyAction = unfiyAction;
/* 20 */     this.url = url;
/* 21 */     this.rel = rel;
/*    */   }
/*    */ 
/*    */   public String getZtreeAction() {
/* 25 */     return this.ztreeAction;
/*    */   }
/*    */ 
/*    */   public void setZtreeAction(String ztreeAction) {
/* 29 */     this.ztreeAction = ztreeAction;
/*    */   }
/*    */ 
/*    */   public String getCustomizeAction() {
/* 33 */     return this.customizeAction;
/*    */   }
/*    */   public void setCustomizeAction(String customizeAction) {
/* 36 */     this.customizeAction = customizeAction;
/*    */   }
/*    */   public String getUnfiyAction() {
/* 39 */     return this.unfiyAction;
/*    */   }
/*    */   public void setUnfiyAction(String unfiyAction) {
/* 42 */     this.unfiyAction = unfiyAction;
/*    */   }
/*    */   public String getUrl() {
/* 45 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 48 */     this.url = url;
/*    */   }
/*    */   public String getRel() {
/* 51 */     return this.rel;
/*    */   }
/*    */   public void setRel(String rel) {
/* 54 */     this.rel = rel;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.result.DocumentZtreePage
 * JD-Core Version:    0.6.2
 */