/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoModelDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoModel;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoModelServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boModelService")
/*     */ public class BoModelServiceImpl extends AbstractBasicService<BoModel, Serializable>
/*     */   implements BoModelServiceIface<BoModel, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoModelDaoIface<BoModel, Serializable> boModelDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoModel model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoModel save(BoModel model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boModelDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoModel model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoModel update(BoModel model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boModelDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoModel model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoModel delete(BoModel model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boModelDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boModelDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoModel find(String userCode, String modelId)
/*     */   {
/* 105 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 106 */     criteria.createAlias("boUsers", "boUsers");
/* 107 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 108 */     criteria.add(Restrictions.eq("modelId", modelId));
/* 109 */     List list = this.boModelDao.findByCriteria(criteria);
/* 110 */     if ((list == null) || (list.isEmpty())) {
/* 111 */       return null;
/*     */     }
/* 113 */     return (BoModel)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoModel> getListBy(String userCode)
/*     */   {
/* 119 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 120 */     criteria.createAlias("boUsers", "boUsers");
/* 121 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 124 */     return this.boModelDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoModel> getBy(String userCode, String modelId)
/*     */   {
/* 130 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 131 */     criteria.createAlias("boUsers", "boUsers");
/* 132 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 133 */     criteria.add(Restrictions.eq("modelId", modelId));
/*     */ 
/* 135 */     return this.boModelDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoModel> getBy()
/*     */   {
/* 141 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 142 */     return this.boModelDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoModel> getBys(String week, String time)
/*     */   {
/* 148 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/*     */ 
/* 150 */     criteria.add(Restrictions.eq("week", week));
/* 151 */     criteria.add(Restrictions.eq("time", time));
/* 152 */     return this.boModelDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoModel finds(String week, String time)
/*     */   {
/* 158 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/*     */ 
/* 160 */     criteria.add(Restrictions.eq("week", week));
/* 161 */     criteria.add(Restrictions.eq("time", time));
/* 162 */     List list = this.boModelDao.findByCriteria(criteria);
/* 163 */     if ((list == null) || (list.isEmpty())) {
/* 164 */       return null;
/*     */     }
/* 166 */     return (BoModel)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoModel findby(String userCode, String name)
/*     */   {
/* 172 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 173 */     criteria.createAlias("boUsers", "boUsers");
/* 174 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 175 */     criteria.add(Restrictions.eq("name", name));
/* 176 */     List list = this.boModelDao.findByCriteria(criteria);
/* 177 */     if ((list == null) || (list.isEmpty())) {
/* 178 */       return null;
/*     */     }
/* 180 */     return (BoModel)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoModel> getListBys(String userCode)
/*     */   {
/* 186 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModel.class);
/* 187 */     criteria.createAlias("boUsers", "boUsers");
/* 188 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 189 */     criteria.add(Restrictions.eq("flag", Boolean.valueOf(true)));
/*     */ 
/* 191 */     return this.boModelDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoModelServiceImpl
 * JD-Core Version:    0.6.2
 */