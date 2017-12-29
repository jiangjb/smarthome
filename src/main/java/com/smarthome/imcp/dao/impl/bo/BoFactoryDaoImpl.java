/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoFactoryDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFactory;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFactory;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFactoryDao")
/*    */ public class BoFactoryDaoImpl extends CommonsDaoImpl<BoFactory, Serializable>
/*    */   implements BoFactoryDaoIface<BoFactory, Serializable>
/*    */ {
/*    */   public BoFactoryDaoImpl()
/*    */   {
/* 26 */     super(BoFactory.class);
/*    */   }
/*    */ 
/*    */   public List<BoFactory> getAllList() {
/* 30 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFactory.class);
/* 31 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoFactory> getList(SearchCriteriaFactory searchCriteriafactory, Page page)
/*    */   {
/* 37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFactory.class);
/* 38 */     if (!GlobalMethod.isNullorEmpty(searchCriteriafactory)) {
/* 39 */       if (StringUtils.isNotEmpty(searchCriteriafactory.getFactoryName())) {
/* 40 */         criteria.add(Restrictions.like("factoryName", searchCriteriafactory.getFactoryName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 43 */       if (StringUtils.isNotEmpty(searchCriteriafactory.getFactoryPhone())) {
/* 44 */         criteria.add(Restrictions.like("factoryPhone", searchCriteriafactory.getFactoryPhone(), MatchMode.END));
/*    */       }
/*    */     }
/* 47 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 48 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 49 */       if (page.isAsc())
/* 50 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("factoryId"));
/*    */       else {
/* 52 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("factoryId"));
/*    */       }
/*    */     }
/* 55 */     else if (page.isAsc())
/* 56 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 58 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 61 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoFactoryDaoImpl
 * JD-Core Version:    0.6.2
 */