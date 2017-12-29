/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenu;
/*    */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*    */ import com.smarthome.imcp.dao.system.SysMenuDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysMenuDao")
/*    */ public class SysMenuDaoImpl extends CommonsDaoImpl<SysMenu, Serializable>
/*    */   implements SysMenuDaoIface<SysMenu, Serializable>
/*    */ {
/*    */   public SysMenuDaoImpl()
/*    */   {
/* 26 */     super(SysMenu.class);
/*    */   }
/*    */ 
/*    */   public List<SysMenu> getList(SearchCriteriaMenu searchCriteriaMenu, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysMenu.class);
/* 33 */     if ((!GlobalMethod.isNullorEmpty(searchCriteriaMenu)) && 
/* 34 */       (StringUtils.isNotEmpty(searchCriteriaMenu.getMenuName()))) {
/* 35 */       criteria.add(Restrictions.like("menuName", 
/* 36 */         searchCriteriaMenu.getMenuName(), MatchMode.ANYWHERE));
/*    */     }
/*    */ 
/* 39 */     criteria.add(Restrictions.or(Restrictions.isNull("menuVisiable"), 
/* 40 */       Restrictions.ne("menuVisiable", "N")));
/* 41 */     criteria.addOrder(Order.asc("menuPcode"))
/* 42 */       .addOrder(Order.asc("mntPosition"))
/* 43 */       .addOrder(Order.asc("menuCode"));
/* 44 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public boolean isExistsByMenuCode(String menuCode)
/*    */   {
/* 49 */     StringBuffer hql = new StringBuffer();
/* 50 */     hql.append("select 1 from SysMenu sysMenu");
/* 51 */     hql.append(" where sysMenu.menuCode = ?");
/* 52 */     List list = findByHQL(hql.toString(), menuCode);
/* 53 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 54 */       return false;
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isExistsByMenuPcodeName(String menuPcode, String menuName)
/*    */   {
/* 61 */     Object[] values = new Object[0];
/* 62 */     StringBuffer hql = new StringBuffer();
/* 63 */     hql.append("select 1 from SysMenu sysMenu");
/* 64 */     hql.append(" where sysMenu.menuPcode = ?");
/* 65 */     if (StringUtils.isNotEmpty(menuName)) {
/* 66 */       hql.append(" and sysMenu.menuName = ?");
/* 67 */       values = new Object[] { menuPcode, menuName };
/*    */     } else {
/* 69 */       values = new Object[] { menuPcode };
/*    */     }
/* 71 */     List list = findByHQL(hql.toString(), values);
/* 72 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 73 */       return false;
/*    */     }
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isExistsByMenuCodePcodeName(String menuCode, String menuPcode, String menuName)
/*    */   {
/* 80 */     Object[] values = { menuPcode, menuName, menuCode };
/* 81 */     StringBuffer hql = new StringBuffer();
/* 82 */     hql.append("select menuCode from SysMenu sysMenu");
/* 83 */     hql.append(" where sysMenu.menuPcode = ?");
/* 84 */     hql.append(" and sysMenu.menuName = ?");
/* 85 */     hql.append(" and sysMenu.menuCode <> ?");
/* 86 */     List list = findByHQL(hql.toString(), values);
/* 87 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 88 */       return false;
/*    */     }
/* 90 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysMenuDaoImpl
 * JD-Core Version:    0.6.2
 */