/*    */ package com.smarthome.imcp.dao.vo.system;
/*    */ 
/*    */ public class MenuPageVo
/*    */ {
/*    */   private String resultCode;
/*    */   private String menuTree;
/*    */ 
/*    */   public MenuPageVo(String resultCode, String menuTree)
/*    */   {
/*  9 */     this.resultCode = resultCode;
/* 10 */     this.menuTree = menuTree;
/*    */   }
/*    */ 
/*    */   public String getResultCode() {
/* 14 */     return this.resultCode;
/*    */   }
/*    */ 
/*    */   public void setResultCode(String resultCode) {
/* 18 */     this.resultCode = resultCode;
/*    */   }
/*    */ 
/*    */   public String getMenuTree() {
/* 22 */     return this.menuTree;
/*    */   }
/*    */ 
/*    */   public void setMenuTree(String menuTree) {
/* 26 */     this.menuTree = menuTree;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.system.MenuPageVo
 * JD-Core Version:    0.6.2
 */