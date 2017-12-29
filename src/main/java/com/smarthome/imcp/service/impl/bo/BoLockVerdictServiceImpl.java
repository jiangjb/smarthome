/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoLockVerdictDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boLockVerdictService")
/*     */ public class BoLockVerdictServiceImpl extends AbstractBasicService<BoLockVerdict, Serializable>
/*     */   implements BoLockVerdictServiceIface<BoLockVerdict, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoLockVerdictDaoIface<BoLockVerdict, Serializable> boLockVerdictDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoLockVerdict model)
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLockVerdict save(BoLockVerdict model)
/*     */   {
/*  55 */     if (chkSaveValid(model)) {
/*  56 */       model.setLockverdictId(UuidUtil.get32UUID());
/*  57 */       this.boLockVerdictDao.save(model);
/*     */     }
/*  59 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoLockVerdict model)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLockVerdict update(BoLockVerdict model)
/*     */   {
/*  70 */     if (chkUpdateValid(model)) {
/*  71 */       this.boLockVerdictDao.update(model);
/*     */     }
/*  73 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoLockVerdict model)
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public BoLockVerdict delete(BoLockVerdict model)
/*     */   {
/*  85 */     if (chkUpdateValid(model)) {
/*  86 */       this.boLockVerdictDao.delete(model);
/*     */     }
/*  88 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  93 */     if ((id == null) || ("".equals(id)))
/*  94 */       return;
/*  95 */     if (chkDeleteValid(id))
/*  96 */       this.boLockVerdictDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 102 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 103 */     while (st.hasMoreElements()) {
/* 104 */       String id = st.nextToken();
/* 105 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoLockVerdict findLock(String userCode, String lockAddress)
/*     */   {
/* 112 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockVerdict.class);
/* 113 */     criteria.createAlias("boUsers", "boUsers");
/* 114 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 115 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 116 */     List list = this.boLockVerdictDao.findByCriteria(criteria);
/* 117 */     if ((list == null) || (list.isEmpty())) {
/* 118 */       return null;
/*     */     }
/* 120 */     return (BoLockVerdict)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoLockVerdict findLock(String userCode, String deviceCode, String lockAddress)
/*     */   {
/* 127 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockVerdict.class);
/* 128 */     criteria.createAlias("boUsers", "boUsers");
/* 129 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 130 */     criteria.createAlias("boDevice", "boDevice");
/* 131 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 132 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 133 */     List list = this.boLockVerdictDao.findByCriteria(criteria);
/* 134 */     if ((list == null) || (list.isEmpty())) {
/* 135 */       return null;
/*     */     }
/* 137 */     return (BoLockVerdict)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoLockVerdict> getAll()
/*     */   {
/* 143 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockVerdict.class);
/* 144 */     criteria.add(Restrictions.eq("status", "5"));
/* 145 */     return this.boLockVerdictDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoLockVerdict findLock(String lockAddress)
/*     */   {
/* 151 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockVerdict.class);
/* 152 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 153 */     List list = this.boLockVerdictDao.findByCriteria(criteria);
/* 154 */     if ((list == null) || (list.isEmpty())) {
/* 155 */       return null;
/*     */     }
/* 157 */     return (BoLockVerdict)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoLockVerdict> getByDeviceCode(String deviceCode)
/*     */   {
/* 164 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockVerdict.class);
/* 165 */     criteria.createAlias("boDevice", "boDevice");
/* 166 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 167 */     return this.boLockVerdictDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoLockVerdictServiceImpl
 * JD-Core Version:    0.6.2
 */