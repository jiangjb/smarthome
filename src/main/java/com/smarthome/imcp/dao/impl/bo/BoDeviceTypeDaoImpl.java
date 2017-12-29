/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoDeviceTypeDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDeviceType;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDeviceType;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDeviceTypeDao")
/*    */ public class BoDeviceTypeDaoImpl extends CommonsDaoImpl<BoDeviceType, Serializable>
/*    */   implements BoDeviceTypeDaoIface<BoDeviceType, Serializable>
/*    */ {
/*    */   public BoDeviceTypeDaoImpl()
/*    */   {
/* 26 */     super(BoDeviceType.class);
/*    */   }
/*    */ 
/*    */   public List<BoDeviceType> getList(SearchCriteriaDeviceType searchCriteriaDeviceType)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceType.class);
/* 33 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaDeviceType)) {
/* 34 */       if ((searchCriteriaDeviceType.getFactoryId() != null) && (searchCriteriaDeviceType.getFactoryId().intValue() > 0)) {
/* 35 */         criteria.add(Restrictions.eq("boFactory.factoryId", searchCriteriaDeviceType.getFactoryId()));
/*    */       }
/*    */ 
/* 38 */       if (StringUtils.isNotEmpty(searchCriteriaDeviceType.getFactoryName())) {
/* 39 */         criteria.add(Restrictions.like("boFactory.factoryName", searchCriteriaDeviceType.getFactoryName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 42 */       if (StringUtils.isNotEmpty(searchCriteriaDeviceType.getTypeName())) {
/* 43 */         criteria.add(Restrictions.like("typeName", searchCriteriaDeviceType.getTypeName(), MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 46 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 47 */     criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("typeId"));
/* 48 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoDeviceType> getList(SearchCriteriaDeviceType searchCriteriaDeviceType, Page page)
/*    */   {
/* 54 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceType.class);
/* 55 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaDeviceType)) {
/* 56 */       criteria.createAlias("boFactory", "boFactory");
/*    */ 
/* 58 */       if ((searchCriteriaDeviceType.getFactoryId() != null) && (searchCriteriaDeviceType.getFactoryId().intValue() > 0)) {
/* 59 */         criteria.add(Restrictions.eq("boFactory.factoryId", searchCriteriaDeviceType.getFactoryId()));
/*    */       }
/*    */ 
/* 62 */       if (StringUtils.isNotEmpty(searchCriteriaDeviceType.getFactoryName())) {
/* 63 */         criteria.add(Restrictions.like("boFactory.factoryName", searchCriteriaDeviceType.getFactoryName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 66 */       if (StringUtils.isNotEmpty(searchCriteriaDeviceType.getTypeName())) {
/* 67 */         criteria.add(Restrictions.like("typeName", searchCriteriaDeviceType.getTypeName(), MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 70 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 71 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 72 */       if (page.isAsc())
/* 73 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("typeId"));
/*    */       else {
/* 75 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("typeId"));
/*    */       }
/*    */     }
/* 78 */     else if (page.isAsc())
/* 79 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 81 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 84 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoDeviceTypeDaoImpl
 * JD-Core Version:    0.6.2
 */