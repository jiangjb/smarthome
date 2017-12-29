/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.cy.DailyDeduction;
/*    */ import com.smarthome.imcp.dao.system.SysBatchDaoIface;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysBatchDao")
/*    */ public class SysBatchDaoImpl
/*    */   implements SysBatchDaoIface
/*    */ {
/*    */   protected SessionFactory sessionFactory;
/*    */ 
/*    */   public SessionFactory getCurrentSessionFactory()
/*    */   {
/* 21 */     return this.sessionFactory;
/*    */   }
/*    */ 
/*    */   @Resource(name="sessionFactory")
/*    */   public void setSessionFactory(SessionFactory sessionFactory)
/*    */   {
/* 27 */     this.sessionFactory = sessionFactory;
/*    */   }
/*    */ 
/*    */   public Session getCurrentSession()
/*    */   {
/* 32 */     return this.sessionFactory.getCurrentSession();
/*    */   }
/*    */ 
/*    */   public List<DailyDeduction> getDevicesDailyDeduction(int deviceTypeId, int userId)
/*    */   {
/* 37 */     Query query = getCurrentSession().getNamedQuery("getDevicePlanDeduction");
/* 38 */     query.setParameter(0, Integer.valueOf(deviceTypeId));
/* 39 */     query.setParameter(1, Integer.valueOf(userId));
/*    */ 
/* 41 */     return query.list();
/*    */   }
/*    */ 
/*    */   public void executePlanRefill()
/*    */   {
/* 46 */     Query query = getCurrentSession().createSQLQuery("{call sp_executePlanRefill()}");
/* 47 */     query.executeUpdate();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysBatchDaoImpl
 * JD-Core Version:    0.6.2
 */