/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.model.system.SysRoleMenus;
/*    */ import com.smarthome.imcp.dao.system.SysRoleMenusDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysRoleMenusDao")
/*    */ public class SysRoleMenusDaoImpl extends CommonsDaoImpl<SysRoleMenus, Serializable>
/*    */   implements SysRoleMenusDaoIface<SysRoleMenus, Serializable>
/*    */ {
/*    */   public SysRoleMenusDaoImpl()
/*    */   {
/* 20 */     super(SysRoleMenus.class);
/*    */   }
/*    */ 
/*    */   public List<SysRoleMenus> getListByMenuRoleCode(String menuRoleCode)
/*    */   {
/* 25 */     DetachedCriteria criteria = 
/* 26 */       DetachedCriteria.forClass(SysRoleMenus.class);
/* 27 */     criteria.add(Restrictions.eq("sysMenuRole.menuRoleCode", menuRoleCode));
/* 28 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public void deleteAllByMenuRoleCode(String menuRoleCode)
/*    */   {
/* 33 */     StringBuffer hql = new StringBuffer();
/* 34 */     hql.append("delete from SysRoleMenus sysRoleMenus");
/* 35 */     hql.append(" where sysRoleMenus.sysMenuRole.menuRoleCode = ?");
/* 36 */     bulkUpdate(hql.toString(), menuRoleCode);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysRoleMenusDaoImpl
 * JD-Core Version:    0.6.2
 */