/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoResendVerificationDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoResendVerification;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoResendVerificationServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boResendVerificationService")
/*     */ public class BoResendVerificationServiceImpl extends AbstractBasicService<BoResendVerification, Serializable>
/*     */   implements BoResendVerificationServiceIface<BoResendVerification, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoResendVerificationDaoIface<BoResendVerification, Serializable> boResendVerificationDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoResendVerification model)
/*     */   {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   public BoResendVerification save(BoResendVerification model)
/*     */   {
/*  47 */     if (chkSaveValid(model))
/*     */     {
/*  49 */       this.boResendVerificationDao.save(model);
/*     */     }
/*  51 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoResendVerification model)
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public BoResendVerification update(BoResendVerification model)
/*     */   {
/*  62 */     if (chkUpdateValid(model)) {
/*  63 */       this.boResendVerificationDao.update(model);
/*     */     }
/*  65 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoResendVerification model)
/*     */   {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   public BoResendVerification delete(BoResendVerification model)
/*     */   {
/*  77 */     if (chkUpdateValid(model)) {
/*  78 */       this.boResendVerificationDao.delete(model);
/*     */     }
/*  80 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  85 */     if ((id == null) || ("".equals(id)))
/*  86 */       return;
/*  87 */     if (chkDeleteValid(id))
/*  88 */       this.boResendVerificationDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  94 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  95 */     while (st.hasMoreElements()) {
/*  96 */       String id = st.nextToken();
/*  97 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoResendVerification find(String deviceCode, String deviceAddress, String command)
/*     */   {
/* 105 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoResendVerification.class);
/* 106 */     criteria.createAlias("boDevice", "boDevice");
/* 107 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 108 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 109 */     criteria.add(Restrictions.eq("command", command));
///* 110 */     this.boResendVerificationDao.flush();1-26
/*     */ 
/* 112 */     List list = this.boResendVerificationDao.findByCriteria(criteria);
/* 113 */     if ((list == null) || (list.isEmpty())) {
/* 114 */       return null;
/*     */     }
/* 116 */     return (BoResendVerification)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoResendVerification> getAll()
/*     */   {
/* 122 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoResendVerification.class);
/*     */ 
/* 124 */     return this.boResendVerificationDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoResendVerificationServiceImpl
 * JD-Core Version:    0.6.2
 */