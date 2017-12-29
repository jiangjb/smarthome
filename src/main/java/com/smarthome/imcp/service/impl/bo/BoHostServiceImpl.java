/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoHostDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoHost;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoHostServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boHostService")
/*     */ public class BoHostServiceImpl extends AbstractBasicService<BoHost, Serializable>
/*     */   implements BoHostServiceIface<BoHost, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoHostDaoIface<BoHost, Serializable> boHostDao;
/*     */ 
/*     */   public List<BoHost> getListByUserCode(String userCode)
/*     */   {
/*  34 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHost.class);
/*  35 */     criteria.createAlias("boUsers", "boUsers");
/*  36 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  37 */     return this.boHostDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoHost findByHostCode(String userCode, String hostCode)
/*     */   {
/*  43 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHost.class);
/*  44 */     criteria.createAlias("boUsers", "boUsers");
/*  45 */     criteria.add(Restrictions.eq("hostCode", hostCode));
/*  46 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  47 */     List list = this.boHostDao.findByCriteria(criteria);
/*     */ 
/*  49 */     if ((list == null) || (list.isEmpty())) {
/*  50 */       return null;
/*     */     }
/*  52 */     return (BoHost)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHost> getListByHostCode(String hostCode)
/*     */   {
/*  59 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHost.class);
/*  60 */     criteria.add(Restrictions.eq("hostCode", hostCode));
/*  61 */     return this.boHostDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoHost model)
/*     */   {
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */   public BoHost save(BoHost model)
/*     */   {
/*  73 */     if (chkSaveValid(model))
/*     */     {
/*  75 */       this.boHostDao.save(model);
/*     */     }
/*  77 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoHost model)
/*     */   {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   public BoHost update(BoHost model)
/*     */   {
/*  88 */     if (chkUpdateValid(model)) {
/*  89 */       this.boHostDao.update(model);
/*     */     }
/*  91 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoHost model)
/*     */   {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public BoHost delete(BoHost model)
/*     */   {
/* 103 */     if (chkUpdateValid(model)) {
/* 104 */       this.boHostDao.delete(model);
/*     */     }
/* 106 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 111 */     if ((id == null) || ("".equals(id)))
/* 112 */       return;
/* 113 */     if (chkDeleteValid(id))
/* 114 */       this.boHostDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 120 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 121 */     while (st.hasMoreElements()) {
/* 122 */       String id = st.nextToken();
/* 123 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoHostServiceImpl
 * JD-Core Version:    0.6.2
 */