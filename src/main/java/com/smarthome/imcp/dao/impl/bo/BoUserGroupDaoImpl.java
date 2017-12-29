/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUserGroupDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserGroup;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUserGroup;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUserGroupDao")
/*    */ public class BoUserGroupDaoImpl extends CommonsDaoImpl<BoUserGroup, Serializable>
/*    */   implements BoUserGroupDaoIface<BoUserGroup, Serializable>
/*    */ {
/*    */   public BoUserGroupDaoImpl()
/*    */   {
/* 27 */     super(BoUserGroup.class);
/*    */   }
/*    */ 
/*    */   public List<BoUserGroup> getAllList()
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserGroup.class);
/* 33 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 34 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoUserGroup> getList(SearchCriteriaUserGroup searchCriteriaerGroup, Page page)
/*    */   {
/* 40 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserGroup.class);
/* 41 */     GlobalMethod.isNullorEmpty(searchCriteriaerGroup);
/*    */ 
/* 43 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 44 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 45 */       if (page.isAsc())
/* 46 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("groupId"));
/*    */       else {
/* 48 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("groupId"));
/*    */       }
/*    */     }
/* 51 */     else if (page.isAsc())
/* 52 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 54 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 57 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUserGroupDaoImpl
 * JD-Core Version:    0.6.2
 */