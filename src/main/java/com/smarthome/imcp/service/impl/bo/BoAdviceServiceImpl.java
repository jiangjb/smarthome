/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoAdviceDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAdvice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAdvice;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAdviceServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAdviceService")
/*     */ public class BoAdviceServiceImpl extends AbstractBasicService<BoAdvice, Serializable>
/*     */   implements BoAdviceServiceIface<BoAdvice, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAdviceDaoIface<BoAdvice, Serializable> boAdviceDao;
/*     */ 
/*     */   public List<BoAdvice> getListByUserCode(String userCode, Page page)
/*     */   {
/*  37 */     return this.boAdviceDao.getListByUserCode(userCode, page);
/*     */   }
/*     */ 
/*     */   public List<BoAdvice> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  42 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/*  43 */     String userType = currentUser.getUserType();
/*     */ 
/*  45 */     SearchCriteriaAdvice searchCriteriaAdvice = (SearchCriteriaAdvice)searchCriteria;
/*  46 */     if (searchCriteriaAdvice == null) searchCriteriaAdvice = new SearchCriteriaAdvice();
/*     */ 
/*  48 */     if (("IDSSUP".equals(userType)) || ("IDSNOR".equals(userType))) {
/*  49 */       searchCriteriaAdvice.setFactoryId(currentUser.getFactoryId());
/*     */     }
/*     */ 
/*  52 */     if (chkCriteriaValid(searchCriteria)) {
/*  53 */       return this.boAdviceDao.getList(searchCriteriaAdvice, page);
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoAdvice model)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAdvice save(BoAdvice model)
/*     */   {
/*  65 */     if (chkSaveValid(model)) {
/*  66 */       this.boAdviceDao.save(model);
/*     */     }
/*  68 */     return model;
/*     */   }
/*     */ 
/*     */   public BoAdvice findByKey(Serializable id)
/*     */   {
/*  73 */     BoAdvice model = (BoAdvice)this.boAdviceDao.findById(id);
/*  74 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAdvice model)
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAdvice update(BoAdvice model)
/*     */   {
/*  84 */     if (chkUpdateValid(model)) {
/*  85 */       this.boAdviceDao.update(model);
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
/*  97 */     if ((id == null) || ("".equals(id)))
/*  98 */       return;
/*  99 */     if (chkDeleteValid(id))
/* 100 */       this.boAdviceDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 106 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 107 */     while (st.hasMoreElements()) {
/* 108 */       String id = st.nextToken();
/* 109 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAdviceServiceImpl
 * JD-Core Version:    0.6.2
 */