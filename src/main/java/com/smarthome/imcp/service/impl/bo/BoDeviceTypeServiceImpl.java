/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoDeviceTypeDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDeviceType;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDeviceType;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceTypeServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boDeviceTypeService")
/*     */ public class BoDeviceTypeServiceImpl extends AbstractBasicService<BoDeviceType, Serializable>
/*     */   implements BoDeviceTypeServiceIface<BoDeviceType, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceTypeDaoIface<BoDeviceType, Serializable> boDeviceTypeDao;
/*     */ 
/*     */   public List<BoDeviceType> getList(SearchCriteria searchCriteria)
/*     */   {
/*  31 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/*  32 */     String userType = currentUser.getUserType();
/*     */ 
/*  34 */     SearchCriteriaDeviceType searchCriteriaceType = (SearchCriteriaDeviceType)searchCriteria;
/*  35 */     if (searchCriteriaceType == null) searchCriteriaceType = new SearchCriteriaDeviceType();
/*     */ 
/*  37 */     if (("IDSSUP".equals(userType)) || ("IDSNOR".equals(userType))) {
/*  38 */       searchCriteriaceType.setFactoryId(currentUser.getFactoryId());
/*     */     }
/*     */ 
/*  41 */     if (chkCriteriaValid(searchCriteria)) {
/*  42 */       return this.boDeviceTypeDao.getList(searchCriteriaceType);
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoDeviceType> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  49 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/*  50 */     String userType = currentUser.getUserType();
/*     */ 
/*  52 */     SearchCriteriaDeviceType searchCriteriaceType = (SearchCriteriaDeviceType)searchCriteria;
/*  53 */     if (searchCriteriaceType == null) searchCriteriaceType = new SearchCriteriaDeviceType();
/*     */ 
/*  55 */     if (("IDSSUP".equals(userType)) || ("IDSNOR".equals(userType))) {
/*  56 */       searchCriteriaceType.setFactoryId(currentUser.getFactoryId());
/*     */     }
/*     */ 
/*  59 */     if (chkCriteriaValid(searchCriteria)) {
/*  60 */       return this.boDeviceTypeDao.getList(searchCriteriaceType, page);
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoDeviceType model)
/*     */   {
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDeviceType save(BoDeviceType model)
/*     */   {
/*  72 */     if (chkSaveValid(model)) {
/*  73 */       this.boDeviceTypeDao.save(model);
/*     */     }
/*  75 */     return model;
/*     */   }
/*     */ 
/*     */   public BoDeviceType findByKey(Serializable id)
/*     */   {
/*  80 */     BoDeviceType model = (BoDeviceType)this.boDeviceTypeDao.findById(id);
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoDeviceType model)
/*     */   {
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDeviceType update(BoDeviceType model)
/*     */   {
/*  91 */     if (chkUpdateValid(model)) {
/*  92 */       this.boDeviceTypeDao.update(model);
/*     */     }
/*  94 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 104 */     if ((id == null) || ("".equals(id)))
/* 105 */       return;
/* 106 */     if (chkDeleteValid(id))
/* 107 */       this.boDeviceTypeDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 113 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 114 */     while (st.hasMoreElements()) {
/* 115 */       String id = st.nextToken();
/* 116 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoDeviceTypeServiceImpl
 * JD-Core Version:    0.6.2
 */