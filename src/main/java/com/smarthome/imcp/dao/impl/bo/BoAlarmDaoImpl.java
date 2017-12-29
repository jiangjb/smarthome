/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAlarmDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAlarm;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAlarm;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAlarmDao")
/*    */ public class BoAlarmDaoImpl extends CommonsDaoImpl<BoAlarm, Serializable>
/*    */   implements BoAlarmDaoIface<BoAlarm, Serializable>
/*    */ {
/*    */   public BoAlarmDaoImpl()
/*    */   {
/* 26 */     super(BoAlarm.class);
/*    */   }
/*    */ 
/*    */   public List<BoAlarm> getList(SearchCriteriaAlarm searchCriteriaalarm, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAlarm.class);
/* 33 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaalarm)) {
/* 34 */       criteria.add(Restrictions.eq("deviceType", searchCriteriaalarm.getType()));
/*    */ 
/* 36 */       if (StringUtils.isNotEmpty(searchCriteriaalarm.getDeviceCode())) {
/* 37 */         criteria.add(Restrictions.like("deviceCode", searchCriteriaalarm.getDeviceCode(), MatchMode.END));
/*    */       }
/*    */ 
/* 40 */       if (searchCriteriaalarm.getTimeStart() != null) {
/* 41 */         criteria.add(Restrictions.ge("time", searchCriteriaalarm.getTimeStart()));
/*    */       }
/*    */ 
/* 44 */       if (searchCriteriaalarm.getTimeEnd() != null) {
/* 45 */         criteria.add(Restrictions.le("time", searchCriteriaalarm.getTimeEnd()));
/*    */       }
/*    */     }
/* 48 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 49 */       if (page.isAsc())
/* 50 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 52 */         criteria.addOrder(Order.desc("id"));
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
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAlarmDaoImpl
 * JD-Core Version:    0.6.2
 */