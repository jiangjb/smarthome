/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoLockPasswordManageDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boLockPasswordManageDaoIfaceService")
/*     */ public class BoLockPasswordManageServiceImpl extends AbstractBasicService<BoLockPasswordManage, Serializable>
/*     */   implements BoLockPasswordManageServiceIface<BoLockPasswordManage, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoLockPasswordManageDaoIface<BoLockPasswordManage, Serializable> boLockPasswordManageDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoLockPasswordManage model)
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage save(BoLockPasswordManage model)
/*     */   {
/*  51 */     if (chkSaveValid(model)) {
/*  52 */       model.setLockPasswordManageId(UuidUtil.get32UUID());
/*  53 */       this.boLockPasswordManageDao.save(model);
/*     */     }
/*  55 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoLockPasswordManage model)
/*     */   {
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage update(BoLockPasswordManage model)
/*     */   {
/*  66 */     if (chkUpdateValid(model)) {
/*  67 */       this.boLockPasswordManageDao.update(model);
/*     */     }
/*  69 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoLockPasswordManage model)
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage delete(BoLockPasswordManage model)
/*     */   {
/*  81 */     if (chkUpdateValid(model)) {
/*  82 */       this.boLockPasswordManageDao.delete(model);
/*     */     }
/*  84 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  89 */     if ((id == null) || ("".equals(id)))
/*  90 */       return;
/*  91 */     if (chkDeleteValid(id))
/*  92 */       this.boLockPasswordManageDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  98 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  99 */     while (st.hasMoreElements()) {
/* 100 */       String id = st.nextToken();
/* 101 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage findBylockAddress(String userCode, String lockAddress, Integer lockType)
/*     */   {
/* 109 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockPasswordManage.class);
/* 110 */     criteria.createAlias("boUsers", "boUsers");
/* 111 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 112 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 113 */     criteria.add(Restrictions.eq("lockType", lockType));
/* 114 */     List list = this.boLockPasswordManageDao.findByCriteria(criteria);
/* 115 */     if ((list == null) || (list.isEmpty())) {
/* 116 */       return null;
/*     */     }
/* 118 */     return (BoLockPasswordManage)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoLockPasswordManage> getLock(String userCode, String lockAddress, Integer lockType)
/*     */   {
/* 125 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockPasswordManage.class);
/* 126 */     criteria.createAlias("boUsers", "boUsers");
/* 127 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 128 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 129 */     criteria.add(Restrictions.eq("lockType", lockType));
/* 130 */     return this.boLockPasswordManageDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoLockPasswordManage> getLock(Integer lockType)
/*     */   {
/* 136 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockPasswordManage.class);
/* 137 */     criteria.add(Restrictions.eq("lockType", lockType));
/* 138 */     return this.boLockPasswordManageDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoLockPasswordManage find(String userCode, Integer lockType)
/*     */   {
/* 144 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockPasswordManage.class);
/* 145 */     criteria.createAlias("boUsers", "boUsers");
/* 146 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 147 */     criteria.add(Restrictions.eq("lockType", lockType));
/* 148 */     List list = this.boLockPasswordManageDao.findByCriteria(criteria);
/* 149 */     if ((list == null) || (list.isEmpty())) {
/* 150 */       return null;
/*     */     }
/* 152 */     return (BoLockPasswordManage)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoLockPasswordManage> get(String lockAddress)
/*     */   {
/* 158 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoLockPasswordManage.class);
/* 159 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 160 */     return this.boLockPasswordManageDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoLockPasswordManageServiceImpl
 * JD-Core Version:    0.6.2
 */