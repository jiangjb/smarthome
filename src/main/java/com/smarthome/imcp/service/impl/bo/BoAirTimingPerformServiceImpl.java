/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoAirTimingPerformDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAirTimingPerform;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAirTimingPerformServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAirTimingPerformService")
/*     */ public class BoAirTimingPerformServiceImpl extends AbstractBasicService<BoAirTimingPerform, Serializable>
/*     */   implements BoAirTimingPerformServiceIface<BoAirTimingPerform, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAirTimingPerformDaoIface<BoAirTimingPerform, Serializable> boAirTimingPerformDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoAirTimingPerform model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAirTimingPerform save(BoAirTimingPerform model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.boAirTimingPerformDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAirTimingPerform model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAirTimingPerform update(BoAirTimingPerform model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.boAirTimingPerformDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoAirTimingPerform model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoAirTimingPerform delete(BoAirTimingPerform model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.boAirTimingPerformDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.boAirTimingPerformDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  96 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  97 */     while (st.hasMoreElements()) {
/*  98 */       String id = st.nextToken();
/*  99 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoAirTimingPerform> getAllByUser(String userCode, String deviceAddress)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirTimingPerform.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.add(Restrictions.eq("deviceaddress", deviceAddress));
/* 110 */     return this.boAirTimingPerformDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoAirTimingPerform findAirTimingPerform(String userCode, String contrl, String time, String days)
/*     */   {
/* 117 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirTimingPerform.class);
/* 118 */     criteria.createAlias("boUsers", "boUsers");
/* 119 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 120 */     criteria.add(Restrictions.eq("contrl", contrl));
/* 121 */     criteria.add(Restrictions.eq("time", time));
/* 122 */     criteria.add(Restrictions.eq("days", days));
/*     */ 
/* 124 */     List list = this.boAirTimingPerformDao.findByCriteria(criteria);
/* 125 */     if ((list == null) || (list.isEmpty())) {
/* 126 */       return null;
/*     */     }
/* 128 */     return (BoAirTimingPerform)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAirTimingPerformServiceImpl
 * JD-Core Version:    0.6.2
 */