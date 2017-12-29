/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoSimplifyDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoSimplify;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoSimplifyServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boSimplifyService")
/*     */ public class BoSimplifyServiceImpl extends AbstractBasicService<BoSimplify, Serializable>
/*     */   implements BoSimplifyServiceIface<BoSimplify, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoSimplifyDaoIface<BoSimplify, Serializable> boSimplifyDao;
/*     */ 
/*     */   public List<BoSimplify> getListByUserCode(String userCode, String deviceCode)
/*     */   {
/*  33 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSimplify.class);
/*  34 */     criteria.createAlias("boUsers", "boUsers");
/*  35 */     criteria.createAlias("boDevice", "boDevice");
/*  36 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  37 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*  38 */     return this.boSimplifyDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoSimplify> getListByUserCode(String userCode, String roomCode, Page page)
/*     */   {
/*  44 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSimplify.class);
/*  45 */     criteria.createAlias("boUsers", "boUsers");
/*  46 */     criteria.createAlias("boRoom", "boRoom");
/*  47 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  48 */     criteria.add(Restrictions.eq("boRoom.roomCode", roomCode));
/*  49 */     return this.boSimplifyDao.findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoSimplify> getListByUserCode(String userCode, Boolean fid)
/*     */   {
/*  55 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSimplify.class);
/*  56 */     criteria.createAlias("boUsers", "boUsers");
/*  57 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/*  58 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  59 */     return this.boSimplifyDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoSimplify findByBoSimplifyCode(String userCode, String deviceCode)
/*     */   {
/*  65 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSimplify.class);
/*  66 */     criteria.createAlias("boDevice", "boDevice");
/*  67 */     criteria.createAlias("boUsers", "boUsers");
/*  68 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*  69 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*  70 */     List list = this.boSimplifyDao.findByCriteria(criteria);
/*     */ 
/*  72 */     if ((list == null) || (list.isEmpty())) {
/*  73 */       return null;
/*     */     }
/*  75 */     return (BoSimplify)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoSimplify> getListByDeviceCode(String deviceCode)
/*     */   {
/*  81 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSimplify.class);
/*  82 */     criteria.createAlias("boDevice", "boDevice");
/*  83 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*  84 */     return this.boSimplifyDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoSimplify model)
/*     */   {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   public BoSimplify save(BoSimplify model)
/*     */   {
/* 127 */     if (chkSaveValid(model))
/*     */     {
/* 129 */       this.boSimplifyDao.save(model);
/*     */     }
/* 131 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoSimplify model)
/*     */   {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   public BoSimplify update(BoSimplify model)
/*     */   {
/* 142 */     if (chkUpdateValid(model)) {
/* 143 */       this.boSimplifyDao.update(model);
/*     */     }
/* 145 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoSimplify model)
/*     */   {
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   public BoSimplify delete(BoSimplify model)
/*     */   {
/* 157 */     if (chkUpdateValid(model)) {
/* 158 */       this.boSimplifyDao.delete(model);
/*     */     }
/* 160 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 165 */     if ((id == null) || ("".equals(id)))
/* 166 */       return;
/* 167 */     if (chkDeleteValid(id))
/* 168 */       this.boSimplifyDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 174 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 175 */     while (st.hasMoreElements()) {
/* 176 */       String id = st.nextToken();
/* 177 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoSimplifyServiceImpl
 * JD-Core Version:    0.6.2
 */