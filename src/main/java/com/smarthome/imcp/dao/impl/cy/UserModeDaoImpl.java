/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaUserMode;
/*    */ import com.smarthome.imcp.dao.cy.UserModeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.UserMode;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("userModeDao")
/*    */ public class UserModeDaoImpl extends CommonsDaoImpl<UserMode, Serializable>
/*    */   implements UserModeDaoIface<UserMode, Serializable>
/*    */ {
/*    */   public UserModeDaoImpl()
/*    */   {
/* 26 */     super(UserMode.class);
/*    */   }
/*    */ 
/*    */   public List<UserMode> getListByStatus(int status)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(UserMode.class);
/* 32 */     criteria.add(Restrictions.ge("status", Integer.valueOf(status)));
/* 33 */     List list = findByCriteria(criteria);
/* 34 */     return list;
/*    */   }
/*    */ 
/*    */   public UserMode findByKey(Integer userId, Integer deviceId)
/*    */   {
/* 39 */     DetachedCriteria criteria = DetachedCriteria.forClass(UserMode.class);
/* 40 */     criteria.add(Restrictions.ge("boUser.userId", userId));
/* 41 */     criteria.add(Restrictions.ge("boDevice.deviceId", deviceId));
/*    */ 
/* 43 */     List list = findByCriteria(criteria);
/* 44 */     if ((list != null) && (!list.isEmpty())) {
/* 45 */       return (UserMode)list.get(0);
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   public List<UserMode> getList(SearchCriteriaUserMode searchCriteriamode, Page page)
/*    */   {
/* 53 */     DetachedCriteria criteria = DetachedCriteria.forClass(UserMode.class);
/* 54 */     GlobalMethod.isNullorEmpty(searchCriteriamode);
/*    */ 
/* 56 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 57 */       if (page.isAsc())
/* 58 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 60 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 63 */     else if (page.isAsc())
/* 64 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 66 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 69 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.UserModeDaoImpl
 * JD-Core Version:    0.6.2
 */