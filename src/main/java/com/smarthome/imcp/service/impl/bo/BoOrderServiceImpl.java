/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoOrderDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoOrder;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoOrderServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boOrderService")
/*     */ public class BoOrderServiceImpl extends AbstractBasicService<BoOrder, Serializable>
/*     */   implements BoOrderServiceIface<BoOrder, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoOrderDaoIface<BoOrder, Serializable> boOrderDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoOrder model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoOrder save(BoOrder model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boOrderDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoOrder model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoOrder update(BoOrder model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boOrderDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoOrder model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoOrder delete(BoOrder model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boOrderDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boOrderDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public List<BoOrder> getByuserPhone(String userPhone)
/*     */   {
/* 105 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoOrder.class);
/* 106 */     criteria.createAlias("boUsers", "boUsers");
/* 107 */     criteria.add(Restrictions.eq("boUsers.userPhone", userPhone));
/* 108 */     return this.boOrderDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoOrder findByOrderId(String userPhone, Integer orderId)
/*     */   {
/* 114 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoOrder.class);
/* 115 */     criteria.createAlias("boUsers", "boUsers");
/* 116 */     criteria.add(Restrictions.eq("boUsers.userPhone", userPhone));
/* 117 */     criteria.add(Restrictions.eq("orderId", orderId));
/*     */ 
/* 121 */     List list = this.boOrderDao.findByCriteria(criteria);
/*     */ 
/* 123 */     if ((list == null) || (list.isEmpty())) {
/* 124 */       return null;
/*     */     }
/* 126 */     return (BoOrder)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoOrder> getByuserCode(String userCode)
/*     */   {
/* 132 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoOrder.class);
/* 133 */     criteria.createAlias("boUsers", "boUsers");
/* 134 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 135 */     return this.boOrderDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoOrderServiceImpl
 * JD-Core Version:    0.6.2
 */