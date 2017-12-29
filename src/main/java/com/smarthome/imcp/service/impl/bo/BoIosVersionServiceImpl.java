/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoIosVersionDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoIosVersion;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoIosVersionServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boIosVersionService")
/*     */ public class BoIosVersionServiceImpl extends AbstractBasicService<BoIosVersion, Serializable>
/*     */   implements BoIosVersionServiceIface<BoIosVersion, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoIosVersionDaoIface<BoIosVersion, Serializable> boIosVersionDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoIosVersion model)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public BoIosVersion save(BoIosVersion model)
/*     */   {
/*  50 */     if (chkSaveValid(model))
/*     */     {
/*  52 */       this.boIosVersionDao.save(model);
/*     */     }
/*  54 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoIosVersion model)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public BoIosVersion update(BoIosVersion model)
/*     */   {
/*  65 */     if (chkUpdateValid(model)) {
/*  66 */       this.boIosVersionDao.update(model);
/*     */     }
/*  68 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoIosVersion model)
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   public BoIosVersion delete(BoIosVersion model)
/*     */   {
/*  80 */     if (chkUpdateValid(model)) {
/*  81 */       this.boIosVersionDao.delete(model);
/*     */     }
/*  83 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  88 */     if ((id == null) || ("".equals(id)))
/*  89 */       return;
/*  90 */     if (chkDeleteValid(id))
/*  91 */       this.boIosVersionDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  97 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  98 */     while (st.hasMoreElements()) {
/*  99 */       String id = st.nextToken();
/* 100 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoIosVersion findAndroidVersionById(Integer id)
/*     */   {
/* 107 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIosVersion.class);
/* 108 */     criteria.add(Restrictions.eq("id", id));
/* 109 */     List list = this.boIosVersionDao.findByCriteria(criteria);
/* 110 */     if ((list == null) || (list.isEmpty())) {
/* 111 */       return null;
/*     */     }
/* 113 */     return (BoIosVersion)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoIosVersionServiceImpl
 * JD-Core Version:    0.6.2
 */