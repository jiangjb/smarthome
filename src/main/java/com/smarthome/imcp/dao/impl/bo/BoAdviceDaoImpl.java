/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAdviceDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAdvice;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAdvice;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAdviceDao")
/*    */ public class BoAdviceDaoImpl extends CommonsDaoImpl<BoAdvice, Serializable>
/*    */   implements BoAdviceDaoIface<BoAdvice, Serializable>
/*    */ {
/*    */   public BoAdviceDaoImpl()
/*    */   {
/* 27 */     super(BoAdvice.class);
/*    */   }
/*    */ 
/*    */   public List<BoAdvice> getListByUserCode(String userCode, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAdvice.class);
/*    */ 
/* 34 */     criteria.createAlias("boUser", "boUser");
/* 35 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/*    */ 
/* 37 */     criteria.addOrder(Order.asc("adviceId"));
/* 38 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<BoAdvice> getList(SearchCriteriaAdvice searchCriteriaadvice, Page page)
/*    */   {
/* 44 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAdvice.class);
/* 45 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaadvice))
/*    */     {
/* 52 */       if (StringUtils.isNotEmpty(searchCriteriaadvice.getUserName())) {
/* 53 */         criteria.add(Restrictions.like("boUser.userName", searchCriteriaadvice.getUserName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 56 */       if (searchCriteriaadvice.getTimeStart() != null) {
/* 57 */         criteria.add(Restrictions.ge("adviceDate", searchCriteriaadvice.getTimeStart()));
/*    */       }
/*    */ 
/* 60 */       if (searchCriteriaadvice.getTimeEnd() != null) {
/* 61 */         criteria.add(Restrictions.le("adviceDate", searchCriteriaadvice.getTimeEnd()));
/*    */       }
/*    */     }
/* 64 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 65 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 66 */       if (page.isAsc())
/* 67 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("adviceId"));
/*    */       else {
/* 69 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("adviceId"));
/*    */       }
/*    */     }
/* 72 */     else if (page.isAsc())
/* 73 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 75 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 78 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAdviceDaoImpl
 * JD-Core Version:    0.6.2
 */