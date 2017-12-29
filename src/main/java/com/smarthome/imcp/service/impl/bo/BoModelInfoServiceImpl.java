/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoModelInfoDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoModelInfo;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoModelInfoServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boModelInfoService")
/*     */ public class BoModelInfoServiceImpl extends AbstractBasicService<BoModelInfo, Serializable>
/*     */   implements BoModelInfoServiceIface<BoModelInfo, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoModelInfoDaoIface<BoModelInfo, Serializable> boModelInfoDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoModelInfo model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoModelInfo save(BoModelInfo model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.boModelInfoDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoModelInfo model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoModelInfo update(BoModelInfo model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.boModelInfoDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoModelInfo model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoModelInfo delete(BoModelInfo model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.boModelInfoDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.boModelInfoDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public List<BoModelInfo> getBy(String userCode, String modelId)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModelInfo.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.createAlias("boModel", "boModel");
/* 110 */     criteria.add(Restrictions.eq("boModel.modelId", modelId));
/* 111 */     criteria.addOrder(Order.asc("id"));
/* 112 */     return this.boModelInfoDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoModelInfo> getBys(String userCode, String deviceCode)
/*     */   {
/* 118 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoModelInfo.class);
/* 119 */     criteria.createAlias("boUsers", "boUsers");
/* 120 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 121 */     criteria.createAlias("boDevice", "boDevice");
/* 122 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/* 124 */     return this.boModelInfoDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoModelInfo find(String userCode, String modelId)
/*     */   {
/* 130 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoModelInfoServiceImpl
 * JD-Core Version:    0.6.2
 */