/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUserDaoIface;
/*     */ import com.smarthome.imcp.dao.bo.BoUserGroupDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserGroup;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserGroup;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUserGroupServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUserGroupService")
/*     */ public class BoUserGroupServiceImpl extends AbstractBasicService<BoUserGroup, Serializable>
/*     */   implements BoUserGroupServiceIface<BoUserGroup, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUserGroupDaoIface<BoUserGroup, Serializable> boUserGroupDao;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDaoIface<BoUser, Serializable> boUserDao;
/*     */ 
/*     */   public List<BoUserGroup> getAllList()
/*     */   {
/*  33 */     return this.boUserGroupDao.getAllList();
/*     */   }
/*     */ 
/*     */   public List<BoUserGroup> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  38 */     SearchCriteriaUserGroup searchCriteriaerGroup = (SearchCriteriaUserGroup)searchCriteria;
/*  39 */     if (chkCriteriaValid(searchCriteria)) {
/*  40 */       return this.boUserGroupDao.getList(searchCriteriaerGroup, page);
/*     */     }
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoUserGroup model)
/*     */   {
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserGroup save(BoUserGroup model, String userIds)
/*     */   {
/*  52 */     save(model);
/*     */ 
/*  55 */     StringTokenizer st = new StringTokenizer(userIds, ",");
/*  56 */     while (st.hasMoreElements()) {
/*  57 */       String id = st.nextToken();
/*  58 */       if (!StringUtils.isEmpty(id))
/*     */       {
/*  62 */         BoUser user = (BoUser)this.boUserDao.findById(id);
/*  63 */         user.setBoUserGroup(model);
/*  64 */         this.boUserDao.update(user);
/*     */       }
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public BoUserGroup save(BoUserGroup model)
/*     */   {
/*  72 */     if (chkSaveValid(model)) {
/*  73 */       this.boUserGroupDao.save(model);
/*     */     }
/*  75 */     return model;
/*     */   }
/*     */ 
/*     */   public BoUserGroup findByKey(Serializable id)
/*     */   {
/*  80 */     BoUserGroup model = (BoUserGroup)this.boUserGroupDao.findById(id);
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUserGroup model)
/*     */   {
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserGroup update(BoUserGroup model, String userIds)
/*     */   {
/*  91 */     model = update(model);
/*     */ 
/*  93 */     StringTokenizer st = new StringTokenizer(userIds, ",");
/*  94 */     while (st.hasMoreElements()) {
/*  95 */       String id = st.nextToken();
/*  96 */       if (!StringUtils.isEmpty(id))
/*     */       {
/* 100 */         BoUser user = (BoUser)this.boUserDao.findById(Integer.valueOf(id));
/* 101 */         user.setBoUserGroup(model);
/* 102 */         this.boUserDao.update(user);
/*     */       }
/*     */     }
/* 105 */     return model;
/*     */   }
/*     */ 
/*     */   public BoUserGroup update(BoUserGroup model)
/*     */   {
/* 110 */     if (chkUpdateValid(model)) {
/* 111 */       this.boUserGroupDao.update(model);
/*     */     }
/* 113 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 123 */     if (chkDeleteValid(id))
/* 124 */       this.boUserGroupDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 130 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 131 */     while (st.hasMoreElements()) {
/* 132 */       String id = st.nextToken();
/* 133 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUserGroupServiceImpl
 * JD-Core Version:    0.6.2
 */