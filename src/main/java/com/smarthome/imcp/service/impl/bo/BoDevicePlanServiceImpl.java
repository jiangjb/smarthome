/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.bo.BoDevicePlanDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDevicePlan;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoDevicePlanServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDevicePlanService")
/*    */ public class BoDevicePlanServiceImpl extends AbstractBasicService<BoDevicePlan, Serializable>
/*    */   implements BoDevicePlanServiceIface<BoDevicePlan, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoDevicePlanDaoIface<BoDevicePlan, Serializable> boDevicePlanDao;
/*    */ 
/*    */   public BoDevicePlan getInitPlan(String deviceCode)
/*    */   {
/* 25 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevicePlan.class);
/* 26 */     criteria.createAlias("device", "device");
/* 27 */     criteria.add(Restrictions.eq("device.deviceCode", deviceCode));
/* 28 */     criteria.add(Restrictions.eq("initPlan", Boolean.valueOf(true)));
/*    */ 
/* 30 */     List list = this.boDevicePlanDao.findByCriteria(criteria);
/*    */ 
/* 32 */     if ((list != null) && (list.size() > 0)) {
/* 33 */       return (BoDevicePlan)list.get(0);
/*    */     }
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   public BoDevicePlan update(BoDevicePlan model)
/*    */   {
/* 41 */     if (chkUpdateValid(model)) {
/* 42 */       this.boDevicePlanDao.update(model);
/*    */     }
/* 44 */     return model;
/*    */   }
/*    */ 
/*    */   public List<BoDevicePlan> getCurrentPlanByDevice(String deviceCode)
/*    */   {
/* 49 */     List devicePlan = this.boDevicePlanDao.getCurrentPlanByDevice(deviceCode);
/* 50 */     return devicePlan;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoDevicePlanServiceImpl
 * JD-Core Version:    0.6.2
 */