/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoRepairsDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoRepair;
/*    */ import com.smarthome.imcp.dao.model.bo.BoRepairs;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boRepairsDao")
/*    */ public class BoRepairsDaoImpl extends CommonsDaoImpl<BoRepairs, Serializable>
/*    */   implements BoRepairsDaoIface<BoRepairs, Serializable>
/*    */ {
/*    */   public BoRepairsDaoImpl()
/*    */   {
/* 29 */     super(BoRepair.class);
/*    */   }
/*    */ 
/*    */   public List<BoRepairs> getListByUserCode(String userCode, Page page)
/*    */   {
/* 34 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRepair.class);
/*    */ 
/* 36 */     criteria.createAlias("boUser", "boUser");
/* 37 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/* 38 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 39 */     criteria.addOrder(Order.asc("repairId"));
/* 40 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoRepairsDaoImpl
 * JD-Core Version:    0.6.2
 */