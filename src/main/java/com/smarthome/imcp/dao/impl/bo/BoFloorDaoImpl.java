/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoFloorDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFloor;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFloorDao")
/*    */ public class BoFloorDaoImpl extends CommonsDaoImpl<BoFloor, Serializable>
/*    */   implements BoFloorDaoIface<BoFloor, Serializable>
/*    */ {
/*    */   public BoFloorDaoImpl()
/*    */   {
/* 20 */     super(BoFloor.class);
/*    */   }
/*    */ 
/*    */   public List<BoFloor> getAllListByUserCode(String userCode)
/*    */   {
/* 26 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/* 27 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 28 */     return findByCriteria(criteria);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoFloorDaoImpl
 * JD-Core Version:    0.6.2
 */