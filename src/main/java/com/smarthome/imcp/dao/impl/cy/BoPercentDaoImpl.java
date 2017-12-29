/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPercent;
/*    */ import com.smarthome.imcp.dao.cy.BoPercentDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.BoPercent;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boPercentDao")
/*    */ public class BoPercentDaoImpl extends CommonsDaoImpl<BoPercent, Serializable>
/*    */   implements BoPercentDaoIface<BoPercent, Serializable>
/*    */ {
/*    */   public BoPercentDaoImpl()
/*    */   {
/* 27 */     super(BoPercent.class);
/*    */   }
/*    */ 
/*    */   public List<BoPercent> getList(SearchCriteriaPercent searchCriteriapercent, Page page)
/*    */   {
/* 33 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoPercent.class);
/* 34 */     GlobalMethod.isNullorEmpty(searchCriteriapercent);
/*    */ 
/* 36 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<BoPercent> getAllList()
/*    */   {
/* 41 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoPercent.class);
/* 42 */     return findByCriteria(criteria);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.BoPercentDaoImpl
 * JD-Core Version:    0.6.2
 */