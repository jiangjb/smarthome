/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoReportDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaReport;
/*    */ import com.smarthome.imcp.dao.model.bo.BoReport;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boReportDao")
/*    */ public class BoReportDaoImpl extends CommonsDaoImpl<BoReport, Serializable>
/*    */   implements BoReportDaoIface<BoReport, Serializable>
/*    */ {
/*    */   public BoReportDaoImpl()
/*    */   {
/* 26 */     super(BoReport.class);
/*    */   }
/*    */ 
/*    */   public List<BoReport> getList(SearchCriteriaReport searchCriteriareport, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoReport.class);
/* 33 */     if (!GlobalMethod.isNullorEmpty(searchCriteriareport)) {
/* 34 */       criteria.add(Restrictions.eq("frameType", searchCriteriareport.getFrameType()));
/* 35 */       criteria.add(Restrictions.eq("deviceType", searchCriteriareport.getType()));
/*    */ 
/* 37 */       if (StringUtils.isNotEmpty(searchCriteriareport.getDeviceCode())) {
/* 38 */         criteria.add(Restrictions.like("deviceCode", searchCriteriareport.getDeviceCode(), MatchMode.END));
/*    */       }
/*    */ 
/* 41 */       if (searchCriteriareport.getTimeStart() != null) {
/* 42 */         criteria.add(Restrictions.ge("time", searchCriteriareport.getTimeStart()));
/*    */       }
/*    */ 
/* 45 */       if (searchCriteriareport.getTimeEnd() != null) {
/* 46 */         criteria.add(Restrictions.le("time", searchCriteriareport.getTimeEnd()));
/*    */       }
/*    */     }
/* 49 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 50 */       if (page.isAsc())
/* 51 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 53 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 56 */     else if (page.isAsc())
/* 57 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 59 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 62 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoReportDaoImpl
 * JD-Core Version:    0.6.2
 */