/*    */ package com.smarthome.imcp.helper.dispatch;
/*    */ 
/*    */ public class RedirectAction
/*    */ {
/*  4 */   private String namespace = "";
/*  5 */   private String redirectAction = "";
/*    */ 
/*    */   public RedirectAction()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RedirectAction(String redirectAction) {
/* 12 */     this.redirectAction = redirectAction;
/*    */   }
/*    */ 
/*    */   public RedirectAction(String redirectAction, String namespace) {
/* 16 */     this.redirectAction = redirectAction;
/* 17 */     this.namespace = namespace;
/*    */   }
/*    */ 
/*    */   public String getNamespace() {
/* 21 */     return this.namespace;
/*    */   }
/*    */   public void setNamespace(String namespace) {
/* 24 */     this.namespace = namespace;
/*    */   }
/*    */   public String getRedirectAction() {
/* 27 */     return this.redirectAction;
/*    */   }
/*    */   public void setRedirectAction(String redirectAction) {
/* 30 */     this.redirectAction = redirectAction;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.dispatch.RedirectAction
 * JD-Core Version:    0.6.2
 */