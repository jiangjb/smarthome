/*     */ package com.smarthome.imcp.service.impl.cy;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPercent;
/*     */ import com.smarthome.imcp.dao.cy.BoPercentDaoIface;
/*     */ import com.smarthome.imcp.dao.model.cy.BoPercent;
/*     */ import com.smarthome.imcp.service.cy.BoPercentServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boPercentService")
/*     */ public class BoPercentServiceImpl
/*     */   implements BoPercentServiceIface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoPercentDaoIface<BoPercent, Serializable> boPercentDao;
/*     */ 
/*     */   public List<BoPercent> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  32 */     SearchCriteriaPercent searchCriteriaPercent = (SearchCriteriaPercent)searchCriteria;
/*  33 */     return this.boPercentDao.getList(searchCriteriaPercent, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoPercent model)
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   public BoPercent save(BoPercent model)
/*     */   {
/*  43 */     if (chkSaveValid(model)) {
/*  44 */       this.boPercentDao.save(model);
/*     */     }
/*  46 */     return model;
/*     */   }
/*     */ 
/*     */   public BoPercent find()
/*     */   {
/*  51 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoPercent.class);
/*  52 */     List list = this.boPercentDao.findByCriteria(criteria);
/*  53 */     if ((list != null) && (!list.isEmpty())) {
/*  54 */       return (BoPercent)list.get(0);
/*     */     }
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   public BoPercent find(double money)
/*     */   {
/*  61 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoPercent.class);
/*  62 */     criteria.add(Restrictions.eq("money", Double.valueOf(money)));
/*     */ 
/*  64 */     List list = this.boPercentDao.findByCriteria(criteria);
/*  65 */     if ((list != null) && (!list.isEmpty())) {
/*  66 */       return (BoPercent)list.get(0);
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   public BoPercent findByKey(Serializable id)
/*     */   {
/*  73 */     BoPercent model = (BoPercent)this.boPercentDao.findById(id);
/*  74 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoPercent model)
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   public BoPercent update(BoPercent model)
/*     */   {
/*  84 */     if (chkUpdateValid(model)) {
/*  85 */       this.boPercentDao.update(model);
/*     */     }
/*  87 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  97 */     if (chkDeleteValid(id))
/*  98 */       this.boPercentDao.deleteByKey(Integer.valueOf(Integer.parseInt(id)));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 104 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 105 */     while (st.hasMoreElements()) {
/* 106 */       String id = st.nextToken();
/* 107 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoPercent> getAllList()
/*     */   {
/* 113 */     return this.boPercentDao.getAllList();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.BoPercentServiceImpl
 * JD-Core Version:    0.6.2
 */