/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoDeviceStateDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDeviceState;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDeviceStateDao")
/*    */ public class BoDeviceStateDaoImpl extends CommonsDaoImpl<BoDeviceState, Serializable>
/*    */   implements BoDeviceStateDaoIface<BoDeviceState, Serializable>
/*    */ {
/*    */   public BoDeviceStateDaoImpl()
/*    */   {
/* 22 */     super(BoDeviceState.class);
/*    */   }
/*    */ 
/*    */   public List<BoDeviceState> getByuserCode(String userCode)
/*    */   {
/* 28 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/*    */ 
/* 30 */     criteria.createAlias("boUsers", "boUsers");
/* 31 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*    */ 
/* 34 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoDeviceState> getBydeviceAddress(String deviceAddress)
/*    */   {
/* 40 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/* 41 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 42 */     return findByCriteria(criteria);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoDeviceStateDaoImpl
 * JD-Core Version:    0.6.2
 */