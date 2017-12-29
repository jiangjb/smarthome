/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoRepairsDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoRepairs;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoRepairsServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boRepairsService")
/*     */ public class BoRepairsServiceImpl extends AbstractBasicService<BoRepairs, Serializable>
/*     */   implements BoRepairsServiceIface<BoRepairs, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoRepairsDaoIface<BoRepairs, Serializable> boRepairsDao;
/*     */ 
/*     */   public boolean isExistUndo(String userCode)
/*     */   {
/*  50 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRepairs.class);
/*     */ 
/*  52 */     criteria.createAlias("boUsers", "boUsers");
/*  53 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/*  60 */     criteria.addOrder(Order.asc("repairId"));
/*  61 */     List list = this.boRepairsDao.findByCriteria(criteria);
/*     */ 
/*  63 */     if ((list == null) || (list.isEmpty())) {
/*  64 */       return false;
/*     */     }
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */   public List<BoRepairs> getListByUserCode(String userCode, Page page)
/*     */   {
/*  71 */     return this.boRepairsDao.getListByUserCode(userCode, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoRepairs model)
/*     */   {
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRepairs save(BoRepairs model)
/*     */   {
/*  90 */     if (!chkSaveValid(model)) {
/*  91 */       return model;
/*     */     }
/*  93 */     this.boRepairsDao.save(model);
/*  94 */     return model;
/*     */   }
/*     */ 
/*     */   public BoRepairs findByKey(Serializable id)
/*     */   {
/*  99 */     BoRepairs model = (BoRepairs)this.boRepairsDao.findById(id);
/* 100 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoRepairs model)
/*     */   {
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRepairs update(BoRepairs model)
/*     */   {
/* 110 */     if (!chkUpdateValid(model)) {
/* 111 */       return model;
/*     */     }
/*     */ 
/* 114 */     this.boRepairsDao.update(model);
/*     */ 
/* 116 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 126 */     if ((id == null) || ("".equals(id)))
/* 127 */       return;
/* 128 */     if (chkDeleteValid(id))
/* 129 */       this.boRepairsDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 135 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 136 */     while (st.hasMoreElements()) {
/* 137 */       String id = st.nextToken();
/* 138 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoRepairsServiceImpl
 * JD-Core Version:    0.6.2
 */