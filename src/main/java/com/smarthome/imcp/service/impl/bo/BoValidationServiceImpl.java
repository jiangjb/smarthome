/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoValidationDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoValidation;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoValidationServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boValidationService")
/*     */ public class BoValidationServiceImpl extends AbstractBasicService<BoValidation, Serializable>
/*     */   implements BoValidationServiceIface<BoValidation, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoValidationDaoIface<BoValidation, Serializable> boValidationDao;
/*     */ 
/*     */   public BoValidation findByUserPhone(String userPhone)
/*     */   {
/*  37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoValidation.class);
/*  38 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*  39 */     List list = this.boValidationDao.findByCriteria(criteria);
/*  40 */     if ((list == null) || (list.isEmpty())) {
/*  41 */       return null;
/*     */     }
/*  43 */     return (BoValidation)list.get(0);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoValidation model)
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public BoValidation save(BoValidation model)
/*     */   {
/*  55 */     if (chkSaveValid(model))
/*     */     {
/*  57 */       this.boValidationDao.save(model);
/*     */     }
/*  59 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoValidation model)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public BoValidation update(BoValidation model)
/*     */   {
/*  70 */     if (chkUpdateValid(model)) {
/*  71 */       this.boValidationDao.update(model);
/*     */     }
/*  73 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoValidation model)
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public BoValidation delete(BoValidation model)
/*     */   {
/*  85 */     if (chkUpdateValid(model)) {
/*  86 */       this.boValidationDao.delete(model);
/*     */     }
/*  88 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  93 */     if ((id == null) || ("".equals(id)))
/*  94 */       return;
/*  95 */     if (chkDeleteValid(id))
/*  96 */       this.boValidationDao.deleteByKey(Integer.valueOf(id));
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
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoValidationServiceImpl
 * JD-Core Version:    0.6.2
 */