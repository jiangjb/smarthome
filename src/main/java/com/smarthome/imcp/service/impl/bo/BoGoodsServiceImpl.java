/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoGoodsDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaGoods;
/*     */ import com.smarthome.imcp.dao.model.bo.BoGoods;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoGoodsServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boGoodsService")
/*     */ public class BoGoodsServiceImpl extends AbstractBasicService<BoGoods, Serializable>
/*     */   implements BoGoodsServiceIface<BoGoods, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoGoodsDaoIface<BoGoods, Serializable> boGoodsDao;
/*     */ 
/*     */   public List<BoGoods> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  39 */     SearchCriteriaGoods searchCriteriaFree = (SearchCriteriaGoods)searchCriteria;
/*  40 */     if (chkCriteriaValid(searchCriteria)) {
/*  41 */       return this.boGoodsDao.getList(searchCriteriaFree, page);
/*     */     }
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoGoods> getList(SearchCriteria searchCriteria)
/*     */   {
/*  49 */     SearchCriteriaGoods searchCriteriaFree = (SearchCriteriaGoods)searchCriteria;
/*  50 */     if (chkCriteriaValid(searchCriteria)) {
/*  51 */       return this.boGoodsDao.getList(searchCriteriaFree);
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   public BoGoods findByKey(Serializable id)
/*     */   {
/*  58 */     BoGoods model = (BoGoods)this.boGoodsDao.findById(id);
/*  59 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoGoods model)
/*     */   {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   public BoGoods save(BoGoods model)
/*     */   {
/*  71 */     if (chkSaveValid(model))
/*     */     {
/*  73 */       this.boGoodsDao.save(model);
/*     */     }
/*  75 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoGoods model)
/*     */   {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   public BoGoods update(BoGoods model)
/*     */   {
/*  86 */     if (chkUpdateValid(model)) {
/*  87 */       this.boGoodsDao.update(model);
/*     */     }
/*  89 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoGoods model)
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public BoGoods delete(BoGoods model)
/*     */   {
/* 101 */     if (chkUpdateValid(model)) {
/* 102 */       this.boGoodsDao.delete(model);
/*     */     }
/* 104 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 109 */     if ((id == null) || ("".equals(id)))
/* 110 */       return;
/* 111 */     if (chkDeleteValid(id))
/* 112 */       this.boGoodsDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 118 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 119 */     while (st.hasMoreElements()) {
/* 120 */       String id = st.nextToken();
/* 121 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoGoods> getList()
/*     */   {
/* 128 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoGoods.class);
/*     */ 
/* 130 */     return this.boGoodsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoGoods findById(Integer id)
/*     */   {
/* 136 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoGoods.class);
/*     */ 
/* 138 */     criteria.add(Restrictions.eq("id", id));
/*     */ 
/* 140 */     List list = this.boGoodsDao.findByCriteria(criteria);
/*     */ 
/* 142 */     if ((list == null) || (list.isEmpty())) {
/* 143 */       return null;
/*     */     }
/* 145 */     return (BoGoods)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoGoods> getListbyId(Integer id)
/*     */   {
/* 152 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoGoods.class);
/* 153 */     criteria.add(Restrictions.eq("id", id));
/* 154 */     return this.boGoodsDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoGoodsServiceImpl
 * JD-Core Version:    0.6.2
 */