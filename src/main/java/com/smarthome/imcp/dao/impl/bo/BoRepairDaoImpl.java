/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoRepairDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaRepair;
/*    */ import com.smarthome.imcp.dao.model.bo.BoRepair;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boRepairDao")
/*    */ public class BoRepairDaoImpl extends CommonsDaoImpl<BoRepair, Serializable>
/*    */   implements BoRepairDaoIface<BoRepair, Serializable>
/*    */ {
/*    */   public BoRepairDaoImpl()
/*    */   {
/* 27 */     super(BoRepair.class);
/*    */   }
/*    */ 
/*    */   public List<BoRepair> getListByUserCode(String userCode, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRepair.class);
/*    */ 
/* 34 */     criteria.createAlias("boUser", "boUser");
/* 35 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/* 36 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 37 */     criteria.addOrder(Order.asc("repairId"));
/* 38 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<BoRepair> getList(SearchCriteriaRepair searchCriteriarepair, Page page)
/*    */   {
/* 44 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRepair.class);
/* 45 */     if (!GlobalMethod.isNullorEmpty(searchCriteriarepair))
/*    */     {
/* 47 */       if (StringUtils.isNotEmpty(searchCriteriarepair.getUserName())) {
/* 48 */         criteria.add(Restrictions.like("boUser.userName", searchCriteriarepair.getUserName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 51 */       if (StringUtils.isNotEmpty(searchCriteriarepair.getDeviceCode())) {
/* 52 */         criteria.add(Restrictions.like("boDevice.deviceCode", searchCriteriarepair.getDeviceCode(), MatchMode.END));
/*    */       }
/*    */ 
/* 55 */       if (searchCriteriarepair.getTimeStart() != null) {
/* 56 */         criteria.add(Restrictions.ge("repairDate", searchCriteriarepair.getTimeStart()));
/*    */       }
/*    */ 
/* 59 */       if (searchCriteriarepair.getTimeEnd() != null) {
/* 60 */         criteria.add(Restrictions.le("repairDate", searchCriteriarepair.getTimeEnd()));
/*    */       }
/*    */     }
/* 63 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 64 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 65 */       if (page.isAsc())
/* 66 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("repairId"));
/*    */       else {
/* 68 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("repairId"));
/*    */       }
/*    */     }
/* 71 */     else if (page.isAsc())
/* 72 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 74 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 77 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoRepairDaoImpl
 * JD-Core Version:    0.6.2
 */