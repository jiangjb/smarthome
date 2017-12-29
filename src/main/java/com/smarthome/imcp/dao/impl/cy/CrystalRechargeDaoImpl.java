/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecharge;
/*    */ import com.smarthome.imcp.dao.cy.CrystalRechargeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalRecharge;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalRechargeDao")
/*    */ public class CrystalRechargeDaoImpl extends CommonsDaoImpl<CrystalRecharge, Serializable>
/*    */   implements CrystalRechargeDaoIface<CrystalRecharge, Serializable>
/*    */ {
/*    */   public CrystalRechargeDaoImpl()
/*    */   {
/* 26 */     super(CrystalRecharge.class);
/*    */   }
/*    */ 
/*    */   public CrystalRecharge findByOrderNo(String orderNo)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(CrystalRecharge.class);
/* 32 */     criteria.add(Restrictions.eq("orderNo", orderNo));
/* 33 */     List list = findByCriteria(criteria);
/* 34 */     if ((list == null) || (list.size() <= 0)) {
/* 35 */       return null;
/*    */     }
/* 37 */     return (CrystalRecharge)list.get(0);
/*    */   }
/*    */ 
/*    */   public List<CrystalRecharge> getList(SearchCriteriaRecharge searchCriteriarecharge, Page page)
/*    */   {
/* 43 */     DetachedCriteria criteria = DetachedCriteria.forClass(CrystalRecharge.class);
/* 44 */     GlobalMethod.isNullorEmpty(searchCriteriarecharge);
/*    */ 
/* 46 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 47 */       if (page.isAsc())
/* 48 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 50 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 53 */     else if (page.isAsc())
/* 54 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 56 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 59 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.CrystalRechargeDaoImpl
 * JD-Core Version:    0.6.2
 */