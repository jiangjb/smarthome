/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoDevicePlanDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDevicePlan;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDevicePlanDao")
/*    */ public class BoDevicePlanDaoImpl extends CommonsDaoImpl<BoDevicePlan, Serializable>
/*    */   implements BoDevicePlanDaoIface<BoDevicePlan, Serializable>
/*    */ {
/*    */   public BoDevicePlanDaoImpl()
/*    */   {
/* 21 */     super(BoDevicePlan.class);
/*    */   }
/*    */ 
/*    */   public List<BoDevicePlan> getCurrentPlanByDevice(String deviceCode)
/*    */   {
/* 26 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevicePlan.class);
/*    */ 
/* 28 */     criteria.createAlias("device", "device");
/* 29 */     criteria.add(Restrictions.eq("device.deviceCode", deviceCode));
/* 30 */     criteria.add(Restrictions.ge("endDate", new Date()));
/*    */ 
/* 32 */     List list = findByCriteria(criteria);
/* 33 */     return list;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoDevicePlanDaoImpl
 * JD-Core Version:    0.6.2
 */