/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.cy.PlanDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.Plan;
/*    */ import java.io.Serializable;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("planDao")
/*    */ public class PlanDaoImpl extends CommonsDaoImpl<Plan, Serializable>
/*    */   implements PlanDaoIface<Plan, Serializable>
/*    */ {
/*    */   public PlanDaoImpl()
/*    */   {
/* 27 */     super(Plan.class);
/*    */   }
/*    */ 
/*    */   public List<Plan> getList(Page page)
/*    */   {
/* 33 */     DetachedCriteria criteria = DetachedCriteria.forClass(Plan.class);
/* 34 */     criteria.add(Restrictions.eq("deleted", Character.valueOf('N')));
/*    */     List list;
///*    */     List list;
/* 38 */     if (page == null)
/* 39 */       list = findByCriteria(criteria);
/*    */     else {
/* 41 */       list = findByCriteria(criteria, page);
/*    */     }
/*    */ 
/* 44 */     return list;
/*    */   }
/*    */ 
/*    */   public List<String> deductDailyFee()
/*    */   {
/* 49 */     List rawList = findByNSQL("{call sp_ChargeDailyFee()}");
/* 50 */     List deviceList = new ArrayList();
/* 51 */     for (Iterator localIterator = rawList.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
/* 52 */       deviceList.add(obj.toString());
/*    */     }
/*    */ 
/* 55 */     return deviceList;
/*    */   }
/*    */ 
/*    */   public Plan getCurrentPlanByDevice(String deviceCode)
/*    */   {
/* 60 */     Query query = getCurrentSession().getNamedQuery("getCurrentPlanByDevice");
/* 61 */     query.setParameter(0, deviceCode);
/* 62 */     List planList = query.list();
/*    */ 
/* 64 */     if (planList.size() > 0) {
/* 65 */       return (Plan)planList.get(0);
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */   public void bindPlan(String deviceCode, String planCode)
/*    */   {
/* 73 */     bindPlan(deviceCode, planCode, new Date());
/*    */   }
/*    */ 
/*    */   public void bindPlan(String deviceCode, String planCode, Date startDate)
/*    */   {
/* 78 */     Query query = getCurrentSession().getNamedQuery("bindPlan");
/* 79 */     query.setParameter(0, deviceCode);
/* 80 */     query.setParameter(1, planCode);
/*    */ 
/* 82 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 83 */     query.setParameter(2, format.format(startDate));
/* 84 */     query.executeUpdate();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.PlanDaoImpl
 * JD-Core Version:    0.6.2
 */