/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoInfraredPartDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoInfraredPartServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boInfraredPartService")
/*     */ public class BoInfraredPartServiceImpl extends AbstractBasicService<BoInfraredPart, Serializable>
/*     */   implements BoInfraredPartServiceIface<BoInfraredPart, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoInfraredPartDaoIface<BoInfraredPart, Serializable> boInfraredPartDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoInfraredPart model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredPart save(BoInfraredPart model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boInfraredPartDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoInfraredPart model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredPart update(BoInfraredPart model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boInfraredPartDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoInfraredPart model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoInfraredPart delete(BoInfraredPart model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boInfraredPartDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boInfraredPartDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public List<BoInfraredPart> find(String deviceAddress)
/*     */   {
/* 105 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredPart.class);
/* 106 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 107 */     List<BoInfraredPart> list = this.boInfraredPartDao.findByCriteria(criteria);
/* 108 */     if ((list == null) || (list.isEmpty())) {
/* 109 */       return null;
/*     */     }
/* 111 */     return list;
/*     */   }
/*     */
			@Override
			public List<BoInfraredPart> getAllInfraredPart() {
				return this.boInfraredPartDao.getAllInfraredPart();
			}
			@Override
			public String saveExcel(int choiceTo, String filepath) {
				String result="fail";
				try {
					result=this.boInfraredPartDao.saveExcel(choiceTo, filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoInfraredPartServiceImpl
 * JD-Core Version:    0.6.2
 */