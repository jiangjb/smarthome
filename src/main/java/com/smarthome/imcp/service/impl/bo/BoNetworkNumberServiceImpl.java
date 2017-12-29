/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoNetworkNumberDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoNetworkNumber;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoNetworkNumberServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boNetworkNumberService")
/*     */ public class BoNetworkNumberServiceImpl extends AbstractBasicService<BoNetworkNumber, Serializable>
/*     */   implements BoNetworkNumberServiceIface<BoNetworkNumber, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoNetworkNumberDaoIface<BoNetworkNumber, Serializable> boNetworkNumberDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoNetworkNumber model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoNetworkNumber save(BoNetworkNumber model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boNetworkNumberDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoNetworkNumber model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoNetworkNumber update(BoNetworkNumber model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boNetworkNumberDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoNetworkNumber model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoNetworkNumber delete(BoNetworkNumber model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boNetworkNumberDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boNetworkNumberDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  95 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  96 */     while (st.hasMoreElements()) {
/*  97 */       String id = st.nextToken();
/*  98 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoNetworkNumber findBy(String userCode, String deviceCode, String deviceOrHost)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoNetworkNumber.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.createAlias("boDevice", "boDevice");
/* 110 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 111 */     criteria.add(Restrictions.eq("deviceOrHost", deviceOrHost));
/*     */ 
/* 113 */     List list = this.boNetworkNumberDao.findByCriteria(criteria);
/*     */ 
/* 115 */     if ((list == null) || (list.isEmpty())) {
/* 116 */       return null;
/*     */     }
/* 118 */     return (BoNetworkNumber)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoNetworkNumberServiceImpl
 * JD-Core Version:    0.6.2
 */