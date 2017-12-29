/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecord;
/*    */ import com.smarthome.imcp.dao.cy.TradeRecordDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.TradeRecord;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("tradeRecordDao")
/*    */ public class TradeRecordDaoImpl extends CommonsDaoImpl<TradeRecord, Serializable>
/*    */   implements TradeRecordDaoIface<TradeRecord, Serializable>
/*    */ {
/*    */   public TradeRecordDaoImpl()
/*    */   {
/* 26 */     super(TradeRecord.class);
/*    */   }
/*    */ 
/*    */   public TradeRecord findByOrderNo(String orderNo)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(TradeRecord.class);
/* 32 */     criteria.add(Restrictions.eq("orderNo", orderNo));
/* 33 */     List list = findByCriteria(criteria);
/* 34 */     if ((list == null) || (list.size() <= 0)) {
/* 35 */       return null;
/*    */     }
/* 37 */     return (TradeRecord)list.get(0);
/*    */   }
/*    */ 
/*    */   public List<TradeRecord> getListByUserId(int userId, Page page)
/*    */   {
/* 42 */     DetachedCriteria criteria = DetachedCriteria.forClass(TradeRecord.class);
/* 43 */     criteria.add(Restrictions.eq("userId", Integer.valueOf(userId)));
/*    */ 
/* 45 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 46 */       if (page.isAsc())
/* 47 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 49 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 52 */     else if (page.isAsc())
/* 53 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 55 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 58 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<TradeRecord> getList(SearchCriteriaRecord searchCriteriarecord, Page page)
/*    */   {
/* 64 */     DetachedCriteria criteria = DetachedCriteria.forClass(TradeRecord.class);
/* 65 */     GlobalMethod.isNullorEmpty(searchCriteriarecord);
/*    */ 
/* 67 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 68 */       if (page.isAsc())
/* 69 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 71 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 74 */     else if (page.isAsc())
/* 75 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 77 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 80 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.TradeRecordDaoImpl
 * JD-Core Version:    0.6.2
 */