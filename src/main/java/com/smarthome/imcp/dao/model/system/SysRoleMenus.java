/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.struts2.json.annotations.JSON;
/*    */ 
/*    */ public class SysRoleMenus
/*    */   implements Serializable
/*    */ {
/*    */   private Integer roleMenuId;
/*    */   private SysMenu sysMenu;
/*    */   private SysMenuRole sysMenuRole;
/*    */ 
/*    */   public SysRoleMenus()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysRoleMenus(SysMenu sysMenu, SysMenuRole sysMenuRole)
/*    */   {
/* 28 */     this.sysMenu = sysMenu;
/* 29 */     this.sysMenuRole = sysMenuRole;
/*    */   }
/*    */ 
/*    */   public Integer getRoleMenuId()
/*    */   {
/* 35 */     return this.roleMenuId;
/*    */   }
/*    */ 
/*    */   public void setRoleMenuId(Integer roleMenuId) {
/* 39 */     this.roleMenuId = roleMenuId;
/*    */   }
/*    */ 
/*    */   @JSON(serialize=false)
/*    */   public SysMenu getSysMenu() {
/* 44 */     return this.sysMenu;
/*    */   }
/*    */ 
/*    */   public void setSysMenu(SysMenu sysMenu) {
/* 48 */     this.sysMenu = sysMenu;
/*    */   }
/*    */ 
/*    */   @JSON(serialize=false)
/*    */   public SysMenuRole getSysMenuRole() {
/* 53 */     return this.sysMenuRole;
/*    */   }
/*    */ 
/*    */   public void setSysMenuRole(SysMenuRole sysMenuRole) {
/* 57 */     this.sysMenuRole = sysMenuRole;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysRoleMenus
 * JD-Core Version:    0.6.2
 */