/*     */ package com.smarthome.imcp.service;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public abstract class AbstractBasicService<T, PK extends Serializable>
/*     */   implements BasicServiceIface<T, PK>
/*     */ {
/*     */   public List<T> getList(SearchCriteria searchCriteria)
/*     */   {
/*  22 */     return null;
/*     */   }
/*     */ 
/*     */   public List<T> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  35 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   public T findByKey(PK id)
/*     */   {
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(T model)
/*     */   {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   public T save(T model)
/*     */   {
/*  72 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(T model)
/*     */   {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   public T update(T model)
/*     */   {
/*  90 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String keys)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String key)
/*     */   {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String key)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void authFilterProvince(CurrentUser currentUser, SearchCriteria searchCriteria)
/*     */   {
/* 128 */     authFilterProvince(currentUser, searchCriteria, null);
/*     */   }
/*     */ 
/*     */   protected void authFilterProvince(CurrentUser currentUser, SearchCriteria searchCriteria, String field)
/*     */   {
/*     */     try
/*     */     {
/* 141 */       if (currentUser.getProvinceId() != null)
/* 142 */         if (StringUtils.isEmpty(field))
/* 143 */           BeanUtils.setProperty(searchCriteria, "provinceId", currentUser.getProvinceId());
/*     */         else
/* 145 */           BeanUtils.setProperty(searchCriteria, field, currentUser.getProvinceId());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 149 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void authFilterCity(CurrentUser currentUser, SearchCriteria searchCriteria)
/*     */   {
/* 161 */     authFilterCity(currentUser, searchCriteria, null);
/*     */   }
/*     */ 
/*     */   protected void authFilterCity(CurrentUser currentUser, SearchCriteria searchCriteria, String field)
/*     */   {
/*     */     try
/*     */     {
/* 174 */       if (currentUser.getCityId() != null)
/* 175 */         if (StringUtils.isEmpty(field))
/* 176 */           BeanUtils.setProperty(searchCriteria, "cityId", currentUser.getCityId());
/*     */         else
/* 178 */           BeanUtils.setProperty(searchCriteria, field, currentUser.getCityId());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 182 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void authFilterCounty(CurrentUser currentUser, SearchCriteria searchCriteria)
/*     */   {
/* 194 */     authFilterCounty(currentUser, searchCriteria, null);
/*     */   }
/*     */ 
/*     */   protected void authFilterCounty(CurrentUser currentUser, SearchCriteria searchCriteria, String field)
/*     */   {
/*     */     try
/*     */     {
/* 207 */       if (currentUser.getCountyId() != null)
/* 208 */         if (StringUtils.isEmpty(field))
/* 209 */           BeanUtils.setProperty(searchCriteria, "countyId", currentUser.getCountyId());
/*     */         else
/* 211 */           BeanUtils.setProperty(searchCriteria, field, currentUser.getCountyId());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 215 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void authFilterTown(CurrentUser currentUser, SearchCriteria searchCriteria)
/*     */   {
/* 227 */     authFilterTown(currentUser, searchCriteria, null);
/*     */   }
/*     */ 
/*     */   protected void authFilterTown(CurrentUser currentUser, SearchCriteria searchCriteria, String field)
/*     */   {
/*     */     try
/*     */     {
/* 240 */       if (currentUser.getTownId() != null)
/* 241 */         if (StringUtils.isEmpty(field))
/* 242 */           BeanUtils.setProperty(searchCriteria, "townId", currentUser.getTownId());
/*     */         else
/* 244 */           BeanUtils.setProperty(searchCriteria, field, currentUser.getTownId());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 248 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void authFilterVillage(CurrentUser currentUser, SearchCriteria searchCriteria)
/*     */   {
/* 260 */     authFilterVillage(currentUser, searchCriteria, null);
/*     */   }
/*     */ 
/*     */   protected void authFilterVillage(CurrentUser currentUser, SearchCriteria searchCriteria, String field)
/*     */   {
/*     */     try
/*     */     {
/* 273 */       if (currentUser.getVillageId() != null)
/* 274 */         if (StringUtils.isEmpty(field))
/* 275 */           BeanUtils.setProperty(searchCriteria, "villageId", currentUser.getVillageId());
/*     */         else
/* 277 */           BeanUtils.setProperty(searchCriteria, field, currentUser.getVillageId());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 281 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.AbstractBasicService
 * JD-Core Version:    0.6.2
 */