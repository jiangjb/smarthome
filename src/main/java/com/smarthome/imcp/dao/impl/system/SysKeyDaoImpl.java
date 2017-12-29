/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaKey;
/*    */ import com.smarthome.imcp.dao.model.system.SysKey;
/*    */ import com.smarthome.imcp.dao.system.SysKeyDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysKeyDao")
/*    */ public class SysKeyDaoImpl extends CommonsDaoImpl<SysKey, Serializable>
/*    */   implements SysKeyDaoIface<SysKey, Serializable>
/*    */ {
/*    */   public SysKeyDaoImpl()
/*    */   {
/* 24 */     super(SysKey.class);
/*    */   }
/*    */ 
/*    */   public List<SysKey> getList(SearchCriteriaKey searchCriteriaKey, Page page)
/*    */   {
/* 29 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysKey.class);
/* 30 */     if ((!GlobalMethod.isNullorEmpty(searchCriteriaKey)) && 
/* 31 */       (StringUtils.isNotEmpty(searchCriteriaKey.getKeyCode()))) {
/* 32 */       criteria.add(Restrictions.eq("keyCode", searchCriteriaKey.getKeyCode()));
/*    */     }
/*    */ 
/* 35 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 36 */       if (page.isAsc())
/* 37 */         criteria.addOrder(Order.asc("keyId"));
/*    */       else {
/* 39 */         criteria.addOrder(Order.asc("keyId"));
/*    */       }
/*    */     }
/* 42 */     else if (page.isAsc())
/* 43 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 45 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 48 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysKeyDaoImpl
 * JD-Core Version:    0.6.2
 */