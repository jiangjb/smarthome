/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoAlarmDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAlarm;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAlarm;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAlarmServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAlarmService")
/*     */ public class BoAlarmServiceImpl extends AbstractBasicService<BoAlarm, Serializable>
/*     */   implements BoAlarmServiceIface<BoAlarm, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAlarmDaoIface<BoAlarm, Serializable> boAlarmDao;
/*     */ 
/*     */   public List<BoAlarm> getListByDeviceCode(String deviceCode, int deviceType, int type, Page page)
/*     */   {
/*  36 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAlarm.class);
/*  37 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*  38 */     criteria.add(Restrictions.eq("deviceType", Integer.valueOf(deviceType)));
/*  39 */     criteria.add(Restrictions.eq("frameType", Integer.valueOf(type)));
/*     */ 
/*  41 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  42 */       if (page.isAsc())
/*  43 */         criteria.addOrder(Order.asc("id"));
/*     */       else {
/*  45 */         criteria.addOrder(Order.desc("id"));
/*     */       }
/*     */     }
/*  48 */     else if (page.isAsc())
/*  49 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  51 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  55 */     return this.boAlarmDao.findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoAlarm> getListByDeviceCode(String deviceCode, Integer status, Page page)
/*     */   {
/*  60 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAlarm.class);
/*  61 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*     */ 
/*  63 */     if ((status != null) && (status.intValue() > 0)) {
/*  64 */       criteria.add(Restrictions.eq("status", status));
/*     */     }
/*     */ 
/*  67 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  68 */       if (page.isAsc())
/*  69 */         criteria.addOrder(Order.asc("id"));
/*     */       else {
/*  71 */         criteria.addOrder(Order.desc("id"));
/*     */       }
/*     */     }
/*  74 */     else if (page.isAsc())
/*  75 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  77 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  81 */     return this.boAlarmDao.findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoAlarm> getListByDeviceCode336(String deviceCode, Integer type, Page page)
/*     */   {
/*  86 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAlarm.class);
/*  87 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*     */ 
/*  89 */     if (type.intValue() == 0)
/*  90 */       criteria.add(Restrictions.in("status", new Integer[] { Integer.valueOf(2), Integer.valueOf(3) }));
/*     */     else {
/*  92 */       criteria.add(Restrictions.not(Restrictions.in("status", new Integer[] { Integer.valueOf(2), Integer.valueOf(3) })));
/*     */     }
/*     */ 
/*  95 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  96 */       if (page.isAsc())
/*  97 */         criteria.addOrder(Order.asc("id"));
/*     */       else {
/*  99 */         criteria.addOrder(Order.desc("id"));
/*     */       }
/*     */     }
/* 102 */     else if (page.isAsc())
/* 103 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/* 105 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/* 109 */     return this.boAlarmDao.findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoAlarm> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/* 114 */     SearchCriteriaAlarm searchCriteriaAlarm = (SearchCriteriaAlarm)searchCriteria;
/* 115 */     if (chkCriteriaValid(searchCriteria)) {
/* 116 */       return this.boAlarmDao.getList(searchCriteriaAlarm, page);
/*     */     }
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoAlarm model)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAlarm save(BoAlarm model)
/*     */   {
/* 128 */     if (chkSaveValid(model)) {
/* 129 */       this.boAlarmDao.save(model);
/*     */     }
/* 131 */     return model;
/*     */   }
/*     */ 
/*     */   public BoAlarm findByKey(Serializable id)
/*     */   {
/* 136 */     BoAlarm model = (BoAlarm)this.boAlarmDao.findById(id);
/* 137 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAlarm model)
/*     */   {
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAlarm update(BoAlarm model)
/*     */   {
/* 147 */     if (chkUpdateValid(model)) {
/* 148 */       this.boAlarmDao.update(model);
/*     */     }
/* 150 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 160 */     if (chkDeleteValid(id))
/* 161 */       this.boAlarmDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 167 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 168 */     while (st.hasMoreElements()) {
/* 169 */       String id = st.nextToken();
/* 170 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAlarmServiceImpl
 * JD-Core Version:    0.6.2
 */