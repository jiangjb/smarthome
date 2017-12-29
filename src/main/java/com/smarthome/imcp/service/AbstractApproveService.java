/*     */ package com.smarthome.imcp.service;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import com.smarthome.imcp.dao.model.AbstractDpyData;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractApproveService<T, PK extends Serializable>
/*     */   implements ApproveServiceIface<T, PK>
/*     */ {
/*     */   public List<T> getList(SearchCriteria searchCriteria)
/*     */   {
/*  21 */     return null;
/*     */   }
/*     */ 
/*     */   public List<T> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  34 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public T findByKey(PK id)
/*     */   {
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(T model)
/*     */   {
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public T save(T model)
/*     */   {
/*  71 */     return model;
/*     */   }
/*     */ 
/*     */   public T approve(T model) {
/*  75 */     return model;
/*     */   }
/*     */ 
/*     */   public T deploy(T model) {
/*  79 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(T model)
/*     */   {
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   public T update(T model)
/*     */   {
/*  97 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String keys)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String key)
/*     */   {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String key)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setCreator(AbstractData objaData)
/*     */   {
/* 128 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 129 */     if (currentUser != null) {
/* 130 */       objaData.setMntCreatorId(currentUser.getUserId());
/* 131 */       objaData.setMntCreatorName(currentUser.getUserName());
/*     */     }
/* 133 */     objaData.setMntCreatorDate(GlobalMethod.formatDate(
/* 134 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 135 */       "yyyy-MM-dd HH:mm:ss"));
/*     */   }
/*     */ 
/*     */   public void setUpdater(AbstractData objaData) {
/* 139 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 140 */     if (currentUser != null) {
/* 141 */       objaData.setMntUpdatedId(currentUser.getUserId());
/* 142 */       objaData.setMntUpdatedName(currentUser.getUserName());
/*     */     }
/* 144 */     objaData.setMntUpdatedDate(GlobalMethod.formatDate(
/* 145 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 146 */       "yyyy-MM-dd HH:mm:ss"));
/*     */   }
/*     */ 
/*     */   public void setApprover(AbstractData objaData) {
/* 150 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 151 */     if (currentUser != null) {
/* 152 */       objaData.setMntAutId(currentUser.getUserId());
/* 153 */       objaData.setMntAutName(currentUser.getUserName());
/*     */     }
/* 155 */     objaData.setMntAutDate(GlobalMethod.formatDate(
/* 156 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 157 */       "yyyy-MM-dd HH:mm:ss"));
/*     */   }
/*     */ 
/*     */   public void setDraft(AbstractDpyData objaData)
/*     */   {
/* 168 */     setCreator(objaData);
/* 169 */     objaData.setMntSttsF("I");
/* 170 */     objaData.setMntAutsttsF("I");
/*     */   }
/*     */ 
/*     */   public void setUnEdit(AbstractDpyData objaData)
/*     */   {
/* 179 */     setCreator(objaData);
/* 180 */     objaData.setMntSttsF("I");
/* 181 */     objaData.setMntAutsttsF("E");
/*     */   }
/*     */ 
/*     */   public void setUnApproved(AbstractDpyData objaData)
/*     */   {
/* 190 */     setUpdater(objaData);
/* 191 */     objaData.setMntAutsttsF("U");
/*     */   }
/*     */ 
/*     */   public void setApproved(AbstractDpyData objaData)
/*     */   {
/* 200 */     setApprover(objaData);
/* 201 */     objaData.setMntAutsttsF("A");
/*     */   }
/*     */ 
/*     */   public void setRejected(AbstractData objaData)
/*     */   {
/* 210 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 211 */     if (currentUser != null) {
/* 212 */       objaData.setMntAutId(currentUser.getUserId());
/* 213 */       objaData.setMntAutName(currentUser.getUserName());
/*     */     }
/* 215 */     objaData.setMntAutDate(GlobalMethod.formatDate(
/* 216 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 217 */       "yyyy-MM-dd HH:mm:ss"));
/* 218 */     objaData.setMntAutsttsF("R");
/*     */   }
/*     */ 
/*     */   public void setDeployed(AbstractDpyData objaData)
/*     */   {
/* 227 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 228 */     if (currentUser != null) {
/* 229 */       objaData.setMntDeployId(currentUser.getUserId());
/* 230 */       objaData.setMntDeployName(currentUser.getUserName());
/*     */     }
/* 232 */     objaData.setMntDeployDate(GlobalMethod.formatDate(
/* 233 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 234 */       "yyyy-MM-dd HH:mm:ss"));
/* 235 */     objaData.setMntAutsttsF("D");
/*     */   }
/*     */ 
/*     */   public void setPrevented(AbstractDpyData objaData)
/*     */   {
/* 244 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 245 */     if (currentUser != null) {
/* 246 */       objaData.setMntDeployId(currentUser.getUserId());
/* 247 */       objaData.setMntDeployName(currentUser.getUserName());
/*     */     }
/* 249 */     objaData.setMntDeployDate(GlobalMethod.formatDate(
/* 250 */       GlobalMethod.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"), 
/* 251 */       "yyyy-MM-dd HH:mm:ss"));
/* 252 */     objaData.setMntAutsttsF("R");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.AbstractApproveService
 * JD-Core Version:    0.6.2
 */