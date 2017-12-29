/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class SysMenuRole extends AbstractData
/*    */ {
/*    */   private String menuRoleCode;
/*    */   private String menuRoleName;
/*    */   private String menuRoleRmk;
/* 21 */   private Set sysRoleMenuses = new HashSet(0);
/*    */ 
/*    */   public SysMenuRole()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysMenuRole(String menuRoleCode)
/*    */   {
/* 31 */     this.menuRoleCode = menuRoleCode;
/*    */   }
/*    */ 
/*    */   public SysMenuRole(String menuRoleCode, String menuRoleName, String menuRoleRmk)
/*    */   {
/* 37 */     this.menuRoleCode = menuRoleCode;
/* 38 */     this.menuRoleName = menuRoleName;
/* 39 */     this.menuRoleRmk = menuRoleRmk;
/*    */   }
/*    */ 
/*    */   public String getMenuRoleCode()
/*    */   {
/* 45 */     return this.menuRoleCode;
/*    */   }
/*    */ 
/*    */   public void setMenuRoleCode(String menuRoleCode) {
/* 49 */     this.menuRoleCode = menuRoleCode;
/*    */   }
/*    */ 
/*    */   public String getMenuRoleName() {
/* 53 */     return this.menuRoleName;
/*    */   }
/*    */ 
/*    */   public void setMenuRoleName(String menuRoleName) {
/* 57 */     this.menuRoleName = menuRoleName;
/*    */   }
/*    */ 
/*    */   public String getMenuRoleRmk() {
/* 61 */     return this.menuRoleRmk;
/*    */   }
/*    */ 
/*    */   public void setMenuRoleRmk(String menuRoleRmk) {
/* 65 */     this.menuRoleRmk = menuRoleRmk;
/*    */   }
/*    */ 
/*    */   public Set getSysRoleMenuses()
/*    */   {
/* 70 */     return this.sysRoleMenuses;
/*    */   }
/*    */ 
/*    */   public void setSysRoleMenuses(Set sysRoleMenuses)
/*    */   {
/* 75 */     this.sysRoleMenuses = sysRoleMenuses;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysMenuRole
 * JD-Core Version:    0.6.2
 */