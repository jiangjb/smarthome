/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoShoppingCartDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoShoppingCart;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoShoppingCartServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boShoppingCartService")
/*     */ public class BoShoppingCartServiceImpl extends AbstractBasicService<BoShoppingCart, Serializable>
/*     */   implements BoShoppingCartServiceIface<BoShoppingCart, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoShoppingCartDaoIface<BoShoppingCart, Serializable> boShoppingCartDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoShoppingCart model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoShoppingCart save(BoShoppingCart model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boShoppingCartDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoShoppingCart model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoShoppingCart update(BoShoppingCart model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boShoppingCartDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoShoppingCart model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoShoppingCart delete(BoShoppingCart model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boShoppingCartDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boShoppingCartDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public List<BoShoppingCart> getListByUserCodes(String userCode)
/*     */   {
/* 105 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoShoppingCart.class);
/* 106 */     criteria.createAlias("boUsers", "boUsers");
/* 107 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 108 */     return this.boShoppingCartDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoShoppingCart findById(Integer id, String userPhone)
/*     */   {
/* 114 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoShoppingCart.class);
/* 115 */     criteria.createAlias("boUsers", "boUsers");
/* 116 */     criteria.add(Restrictions.eq("boUsers.userPhone", userPhone));
/* 117 */     criteria.add(Restrictions.eq("id", id));
/* 118 */     List list = this.boShoppingCartDao.findByCriteria(criteria);
/* 119 */     if ((list == null) || (list.isEmpty())) {
/* 120 */       return null;
/*     */     }
/* 122 */     return (BoShoppingCart)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoShoppingCart> getListByUserCode(String userPhone)
/*     */   {
/* 128 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoShoppingCart.class);
/* 129 */     criteria.createAlias("boUsers", "boUsers");
/* 130 */     criteria.add(Restrictions.eq("boUsers.userPhone", userPhone));
/* 131 */     return this.boShoppingCartDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoShoppingCartServiceImpl
 * JD-Core Version:    0.6.2
 */