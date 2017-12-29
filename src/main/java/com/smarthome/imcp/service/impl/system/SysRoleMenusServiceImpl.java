/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysRoleMenus;
/*    */ import com.smarthome.imcp.dao.system.SysRoleMenusDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.system.SysRoleMenusServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysRoleMenusService")
/*    */ public class SysRoleMenusServiceImpl extends AbstractBasicService<SysRoleMenus, Serializable>
/*    */   implements SysRoleMenusServiceIface<SysRoleMenus, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysRoleMenusDaoIface<SysRoleMenus, Serializable> sysRoleMenusDao;
/*    */ 
/*    */   public List<SysRoleMenus> getListByMenuRoleCode(String menuRoleCode)
/*    */   {
/* 24 */     return this.sysRoleMenusDao.getListByMenuRoleCode(menuRoleCode);
/*    */   }
/*    */ 
/*    */   public SysRoleMenus save(SysRoleMenus sysRoleMenus)
/*    */   {
/* 29 */     if (chkSaveValid(sysRoleMenus)) {
/* 30 */       this.sysRoleMenusDao.save(sysRoleMenus);
/*    */     }
/* 32 */     return sysRoleMenus;
/*    */   }
/*    */ 
/*    */   public void deleteAllByMenuRoleCode(String menuRoleCode)
/*    */   {
/* 37 */     this.sysRoleMenusDao.deleteAllByMenuRoleCode(menuRoleCode);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysRoleMenusServiceImpl
 * JD-Core Version:    0.6.2
 */