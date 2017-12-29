/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoAndroidVersionDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAndroidVersion;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAndroidVersionServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAndroidVersionService")
/*     */ public class BoAndroidVersionServiceImpl extends AbstractBasicService<BoAndroidVersion, Serializable>
/*     */   implements BoAndroidVersionServiceIface<BoAndroidVersion, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAndroidVersionDaoIface<BoAndroidVersion, Serializable> boAndroidVersionDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoAndroidVersion model)
/*     */   {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAndroidVersion save(BoAndroidVersion model)
/*     */   {
/*  47 */     if (chkSaveValid(model))
/*     */     {
/*  49 */       this.boAndroidVersionDao.save(model);
/*     */     }
/*  51 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAndroidVersion model)
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAndroidVersion update(BoAndroidVersion model)
/*     */   {
/*  62 */     if (chkUpdateValid(model)) {
/*  63 */       this.boAndroidVersionDao.update(model);
/*     */     }
/*  65 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoAndroidVersion model)
/*     */   {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   public BoAndroidVersion delete(BoAndroidVersion model)
/*     */   {
/*  77 */     if (chkUpdateValid(model)) {
/*  78 */       this.boAndroidVersionDao.delete(model);
/*     */     }
/*  80 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  85 */     if ((id == null) || ("".equals(id)))
/*  86 */       return;
/*  87 */     if (chkDeleteValid(id))
/*  88 */       this.boAndroidVersionDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoAndroidVersion findAndroidVersionById(Integer id)
/*     */   {
/* 104 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAndroidVersion.class);
/* 105 */     criteria.add(Restrictions.eq("id", id));
/* 106 */     List list = this.boAndroidVersionDao.findByCriteria(criteria);
/* 107 */     if ((list == null) || (list.isEmpty())) {
/* 108 */       return null;
/*     */     }
/* 110 */     return (BoAndroidVersion)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAndroidVersionServiceImpl
 * JD-Core Version:    0.6.2
 */