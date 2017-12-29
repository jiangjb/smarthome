/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaTradeCrystal;
/*    */ import com.smarthome.imcp.dao.cy.CrystalTradeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalTrade;
/*    */ import java.io.Serializable;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalTradeDao")
/*    */ public class CrystalTradeDaoImpl extends CommonsDaoImpl<CrystalTrade, Serializable>
/*    */   implements CrystalTradeDaoIface<CrystalTrade, Serializable>
/*    */ {
/*    */   public CrystalTradeDaoImpl()
/*    */   {
/* 28 */     super(CrystalTrade.class);
/*    */   }
/*    */ 
/*    */   public List<CrystalTrade> getList(Integer userId, Integer deviceId, Integer day, Page page) {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(CrystalTrade.class);
/*    */ 
/* 34 */     Date today = new Date();
/* 35 */     Calendar c = Calendar.getInstance();
/* 36 */     c.setTime(today);
/* 37 */     c.add(5, day.intValue());
/*    */ 
/* 39 */     criteria.add(Restrictions.ge("boUser.userId", userId));
/* 40 */     criteria.add(Restrictions.ge("boDevice.deviceId", deviceId));
/* 41 */     criteria.add(Restrictions.le("tradeTime", today));
/* 42 */     criteria.add(Restrictions.ge("tradeTime", c.getTime()));
/*    */ 
/* 44 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<CrystalTrade> getList(SearchCriteriaTradeCrystal searchCriteriatrade, Page page)
/*    */   {
/* 50 */     DetachedCriteria criteria = DetachedCriteria.forClass(CrystalTrade.class);
/* 51 */     GlobalMethod.isNullorEmpty(searchCriteriatrade);
/*    */ 
/* 53 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 54 */       if (page.isAsc())
/* 55 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 57 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 60 */     else if (page.isAsc())
/* 61 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 63 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 66 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.CrystalTradeDaoImpl
 * JD-Core Version:    0.6.2
 */