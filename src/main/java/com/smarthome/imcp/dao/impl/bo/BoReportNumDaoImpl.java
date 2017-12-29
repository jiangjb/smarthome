/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoReportNumDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaNum;
/*    */ import com.smarthome.imcp.dao.model.bo.BoReportNum;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boReportNumDao")
/*    */ public class BoReportNumDaoImpl extends CommonsDaoImpl<BoReportNum, Serializable>
/*    */   implements BoReportNumDaoIface<BoReportNum, Serializable>
/*    */ {
/*    */   public BoReportNumDaoImpl()
/*    */   {
/* 26 */     super(BoReportNum.class);
/*    */   }
/*    */ 
/*    */   public BoReportNum findByDeviceCode(String deviceCode, int frameType, int deviceType) {
/* 30 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoReportNum.class);
/* 31 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/* 32 */     criteria.add(Restrictions.eq("frameType", Integer.valueOf(frameType)));
/* 33 */     criteria.add(Restrictions.eq("deviceType", Integer.valueOf(deviceType)));
/*    */ 
/* 35 */     List list = findByCriteria(criteria);
/* 36 */     if ((list != null) && (list.size() > 0)) {
/* 37 */       return (BoReportNum)list.get(0);
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   public int update(int num, String deviceCode, int frameType, int deviceType) {
/* 43 */     StringBuffer hql = new StringBuffer();
/* 44 */     hql.append("update BoReportNum c set c.reportNum=c.reportNum+?");
/* 45 */     hql.append(" where c.deviceCode = ?");
/* 46 */     hql.append(" and c.frameType = ?");
/* 47 */     hql.append(" and c.deviceType = ?");
/*    */ 
/* 49 */     Object[] values = { Integer.valueOf(num), deviceCode, Integer.valueOf(frameType), Integer.valueOf(deviceType) };
/*    */ 
/* 51 */     int count = bulkUpdate(hql.toString(), values);
/* 52 */     return count;
/*    */   }
/*    */ 
/*    */   public List<BoReportNum> getList(SearchCriteriaNum searchCriteriartNum, Page page)
/*    */   {
/* 58 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoReportNum.class);
/* 59 */     GlobalMethod.isNullorEmpty(searchCriteriartNum);
/*    */ 
/* 61 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 62 */       if (page.isAsc())
/* 63 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 65 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 68 */     else if (page.isAsc())
/* 69 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 71 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 74 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoReportNumDaoImpl
 * JD-Core Version:    0.6.2
 */