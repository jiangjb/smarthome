/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenuRole;
/*    */ import com.smarthome.imcp.dao.model.system.SysMenuRole;
/*    */ import com.smarthome.imcp.dao.system.SysMenuRoleDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysMenuRoleDao")
/*    */ public class SysMenuRoleDaoImpl extends CommonsDaoImpl<SysMenuRole, Serializable>
/*    */   implements SysMenuRoleDaoIface<SysMenuRole, Serializable>
/*    */ {
/*    */   public SysMenuRoleDaoImpl()
/*    */   {
/* 28 */     super(SysMenuRole.class);
/*    */   }
/*    */ 
/*    */   private void authorizeCriteria(DetachedCriteria criteria, String userType)
/*    */   {
/* 37 */     StringBuffer sql = new StringBuffer();
/* 38 */     if ("NOR".equals(userType)) {
/* 39 */       sql.append("MENU_ROLE_CODE <> 'SUP'");
/* 40 */       criteria.add(Restrictions.sqlRestriction(sql.toString()));
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<SysMenuRole> getList(SearchCriteriaMenuRole searchCriteriaMenuRole, Page page)
/*    */   {
/* 47 */     DetachedCriteria criteria = 
/* 48 */       DetachedCriteria.forClass(SysMenuRole.class);
/* 49 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaMenuRole))
/*    */     {
/* 51 */       if (StringUtils.isNotEmpty(searchCriteriaMenuRole.getMenuRoleName())) {
/* 52 */         criteria.add(Restrictions.like("menuRoleName", 
/* 53 */           searchCriteriaMenuRole.getMenuRoleName(), 
/* 54 */           MatchMode.ANYWHERE));
/*    */       }
/* 56 */       if (StringUtils.isNotEmpty(searchCriteriaMenuRole.getUserType())) {
/* 57 */         authorizeCriteria(criteria, 
/* 58 */           searchCriteriaMenuRole.getUserType());
/*    */       }
/*    */     }
/* 61 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 62 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 63 */       if (page.isAsc())
/* 64 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(
/* 65 */           Order.asc("menuRoleCode"));
/*    */       else {
/* 67 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(
/* 68 */           Order.asc("menuRoleCode"));
/*    */       }
/*    */     }
/* 71 */     else if (page.isAsc())
/* 72 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 74 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 77 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public boolean isExistsByMenuRoleName(String menuRoleName)
/*    */   {
/* 82 */     StringBuffer hql = new StringBuffer();
/* 83 */     hql.append("select 1 from SysMenuRole sysMenuRole");
/* 84 */     hql.append(" where sysMenuRole.menuRoleName = ?");
/* 85 */     List list = findByHQL(hql.toString(), menuRoleName);
/* 86 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 87 */       return false;
/*    */     }
/* 89 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysMenuRoleDaoImpl
 * JD-Core Version:    0.6.2
 */