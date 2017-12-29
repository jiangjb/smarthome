/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoIconLibraryDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoIconLibrary;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoIconLibraryServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boIconLibraryService")
/*     */ public class BoIconLibraryServiceImpl extends AbstractBasicService<BoIconLibrary, Serializable>
/*     */   implements BoIconLibraryServiceIface<BoIconLibrary, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoIconLibraryDaoIface<BoIconLibrary, Serializable> boIconLibraryDao;
/*     */ 
/*     */   public BoIconLibrary findByURL(String url)
/*     */   {
/*  40 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIconLibrary.class);
/*  41 */     criteria.add(Restrictions.eq("url", url));
/*  42 */     List list = this.boIconLibraryDao.findByCriteria(criteria);
/*  43 */     if ((list == null) || (list.isEmpty())) {
/*  44 */       return null;
/*     */     }
/*  46 */     return (BoIconLibrary)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoIconLibrary> getList()
/*     */   {
/*  52 */     return this.boIconLibraryDao.getList();
/*     */   }
/*     */ 
/*     */   public List<BoIconLibrary> getList1()
/*     */   {
/*  58 */     return this.boIconLibraryDao.getList1();
/*     */   }
/*     */ 
/*     */   public List<BoIconLibrary> getList2()
/*     */   {
/*  64 */     return this.boIconLibraryDao.getList2();
/*     */   }
/*     */ 
/*     */   public List<BoIconLibrary> getList3()
/*     */   {
/*  70 */     return this.boIconLibraryDao.getList3();
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoIconLibrary model)
/*     */   {
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */   public BoIconLibrary save(BoIconLibrary model)
/*     */   {
/*  82 */     if (chkSaveValid(model))
/*     */     {
/*  84 */       this.boIconLibraryDao.save(model);
/*     */     }
/*  86 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoIconLibrary model)
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   public BoIconLibrary update(BoIconLibrary model)
/*     */   {
/*  97 */     if (chkUpdateValid(model)) {
/*  98 */       this.boIconLibraryDao.update(model);
/*     */     }
/* 100 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoIconLibrary model)
/*     */   {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public BoIconLibrary delete(BoIconLibrary model)
/*     */   {
/* 112 */     if (chkUpdateValid(model)) {
/* 113 */       this.boIconLibraryDao.delete(model);
/*     */     }
/* 115 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 120 */     if ((id == null) || ("".equals(id)))
/* 121 */       return;
/* 122 */     if (chkDeleteValid(id))
/* 123 */       this.boIconLibraryDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 129 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 130 */     while (st.hasMoreElements()) {
/* 131 */       String id = st.nextToken();
/* 132 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoIconLibraryServiceImpl
 * JD-Core Version:    0.6.2
 */