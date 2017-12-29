/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoLaunchDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaLaunch;
/*    */ import com.smarthome.imcp.dao.model.bo.BoLaunch;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boLaunchDao")
/*    */ public class BoLaunchDaoImpl extends CommonsDaoImpl<BoLaunch, Serializable>
/*    */   implements BoLaunchDaoIface<BoLaunch, Serializable>
/*    */ {
/*    */   public BoLaunchDaoImpl()
/*    */   {
/* 26 */     super(BoLaunch.class);
/*    */   }
/*    */ 
/*    */   public List<BoLaunch> getList(SearchCriteriaLaunch searchCriterialaunch) {
/* 30 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLaunch.class);
/* 31 */     if (!GlobalMethod.isNullorEmpty(searchCriterialaunch))
/*    */     {
/* 33 */       criteria.add(Restrictions.eq("type", searchCriterialaunch.getType()));
/*    */ 
/* 35 */       if (searchCriterialaunch.getGroupId() != null) {
/* 36 */         criteria.add(Restrictions.eq("boUserGroup.groupId", searchCriterialaunch.getGroupId()));
/*    */       }
/*    */     }
/* 39 */     criteria.addOrder(Order.asc("imageId"));
/* 40 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoLaunch> getList(SearchCriteriaLaunch searchCriterialaunch, Page page)
/*    */   {
/* 46 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLaunch.class);
/* 47 */     if (!GlobalMethod.isNullorEmpty(searchCriterialaunch)) {
/* 48 */       criteria.add(Restrictions.eq("type", searchCriterialaunch.getType()));
/*    */ 
/* 50 */       if (searchCriterialaunch.getGroupId() != null) {
/* 51 */         criteria.add(Restrictions.eq("boUserGroup.groupId", searchCriterialaunch.getGroupId()));
/*    */       }
/*    */     }
/* 54 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 55 */       if (page.isAsc())
/* 56 */         criteria.addOrder(Order.asc("imageId"));
/*    */       else {
/* 58 */         criteria.addOrder(Order.desc("imageId"));
/*    */       }
/*    */     }
/* 61 */     else if (page.isAsc())
/* 62 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 64 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 67 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoLaunchDaoImpl
 * JD-Core Version:    0.6.2
 */