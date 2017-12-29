/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUserDeviceDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserDevice;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUserDeviceDao")
/*    */ public class BoUserDeviceDaoImpl extends CommonsDaoImpl<BoUserDevice, Serializable>
/*    */   implements BoUserDeviceDaoIface<BoUserDevice, Serializable>
/*    */ {
/*    */   public BoUserDeviceDaoImpl()
/*    */   {
/* 26 */     super(BoUserDevice.class);
/*    */   }
/*    */ 
/*    */   public List<BoUserDevice> getList(SearchCriteriaUserDevice searchCriteriaerDevice, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/* 33 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaerDevice))
/*    */     {
/* 35 */       criteria.createAlias("boUser", "boUser");
/* 36 */       criteria.createAlias("boDevice", "boDevice");
/*    */ 
/* 38 */       if (StringUtils.isNotEmpty(searchCriteriaerDevice.getUserName())) {
/* 39 */         criteria.add(Restrictions.like("boUser.userName", searchCriteriaerDevice.getUserName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 42 */       if (StringUtils.isNotEmpty(searchCriteriaerDevice.getFactoryName())) {
/* 43 */         criteria.add(Restrictions.like("boDevice.boFactory.factoryName", searchCriteriaerDevice.getFactoryName(), MatchMode.ANYWHERE));
/*    */       }
/*    */ 
/* 46 */       if (searchCriteriaerDevice.getDeviceDate() != null) {
/* 47 */         criteria.add(Restrictions.eq("boDevice.deviceDate", searchCriteriaerDevice.getDeviceDate()));
/*    */       }
/*    */     }
/* 50 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 51 */       if (page.isAsc())
/* 52 */         criteria.addOrder(Order.asc("userDeviceId"));
/*    */       else {
/* 54 */         criteria.addOrder(Order.desc("userDeviceId"));
/*    */       }
/*    */     }
/* 57 */     else if (page.isAsc())
/* 58 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 60 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 63 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUserDeviceDaoImpl
 * JD-Core Version:    0.6.2
 */