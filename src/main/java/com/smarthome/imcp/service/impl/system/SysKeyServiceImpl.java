/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaKey;
/*     */ import com.smarthome.imcp.dao.model.system.SysKey;
/*     */ import com.smarthome.imcp.dao.system.SysKeyDaoIface;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.system.SysKeyServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.CollectionUtils;
/*     */ 
/*     */ @Service("sysKeyService")
/*     */ public class SysKeyServiceImpl extends AbstractBasicService<SysKey, Serializable>
/*     */   implements SysKeyServiceIface<SysKey, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysKeyDaoIface<SysKey, Serializable> sysKeyDao;
/*     */ 
/*     */   public synchronized String getPrimaryKey(String keyCode)
/*     */   {
/*  33 */     String key = "";
/*  34 */     SysKey sysKey = findByCode(keyCode);
/*  35 */     key = sysKey.getKeyPrefix() + GlobalMethod.addLeadingZero(sysKey.getKeyValue().intValue(), sysKey.getKeyWidth().shortValue());
/*  36 */     sysKey.setKeyValue(Integer.valueOf(sysKey.getKeyValue().intValue() + 1));
/*  37 */     update(sysKey);
/*  38 */     return key;
/*     */   }
/*     */ 
/*     */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public List<SysKey> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  48 */     SearchCriteriaKey searchCriteriaKey = (SearchCriteriaKey)searchCriteria;
/*  49 */     if (chkCriteriaValid(searchCriteriaKey)) {
/*  50 */       return this.sysKeyDao.getList(searchCriteriaKey, page);
/*     */     }
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(SysKey model)
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public SysKey save(SysKey model)
/*     */   {
/*  62 */     if (chkSaveValid(model)) {
/*  63 */       this.sysKeyDao.save(model);
/*     */     }
/*  65 */     return model;
/*     */   }
/*     */ 
/*     */   public SysKey findByKey(Serializable id)
/*     */   {
/*  70 */     return (SysKey)this.sysKeyDao.findById(id);
/*     */   }
/*     */ 
/*     */   private SysKey findByCode(String keyCode) {
/*  74 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysKey.class);
/*  75 */     criteria.add(Restrictions.eq("keyCode", keyCode));
/*  76 */     List list = this.sysKeyDao.findByCriteria(criteria);
/*  77 */     if (CollectionUtils.isEmpty(list)) {
/*  78 */       return null;
/*     */     }
/*  80 */     return (SysKey)list.get(0);
/*     */   }
/*     */ 
/*     */   public SysKey update(SysKey model)
/*     */   {
/*  85 */     if (chkUpdateValid(model)) {
/*  86 */       this.sysKeyDao.update(model);
/*     */     }
/*  88 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  99 */     if (chkDeleteValid(id))
/* 100 */       this.sysKeyDao.deleteLogicByKey(Integer.valueOf(Integer.parseInt(id)));
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
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysKeyServiceImpl
 * JD-Core Version:    0.6.2
 */