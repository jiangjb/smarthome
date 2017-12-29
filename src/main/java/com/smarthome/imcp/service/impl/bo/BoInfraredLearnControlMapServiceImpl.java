/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoInfraredLearnControlMapDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoInfraredLearnControlMapServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boInfraredLearnControlMapService")
/*     */ public class BoInfraredLearnControlMapServiceImpl extends AbstractBasicService<BoInfraredLearnControlMap, Serializable>
/*     */   implements BoInfraredLearnControlMapServiceIface<BoInfraredLearnControlMap, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoInfraredLearnControlMapDaoIface<BoInfraredLearnControlMap, Serializable> boInfraredLearnControlMapDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoInfraredLearnControlMap model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredLearnControlMap save(BoInfraredLearnControlMap model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.boInfraredLearnControlMapDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoInfraredLearnControlMap model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredLearnControlMap update(BoInfraredLearnControlMap model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.boInfraredLearnControlMapDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoInfraredLearnControlMap model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoInfraredLearnControlMap delete(BoInfraredLearnControlMap model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.boInfraredLearnControlMapDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.boInfraredLearnControlMapDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  96 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  97 */     while (st.hasMoreElements()) {
/*  98 */       String id = st.nextToken();
/*  99 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoInfraredLearnControlMap> getListBy(String userCode, String deviceCode, String deviceAddress)
/*     */   {
/* 107 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredLearnControlMap.class);
/* 108 */     criteria.createAlias("boUsers", "boUsers");
/* 109 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 110 */     criteria.createAlias("boDevice", "boDevice");
/* 111 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 112 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 114 */     return this.boInfraredLearnControlMapDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoInfraredLearnControlMap findBy(String userCode, String deviceAddress, String originalValue)
/*     */   {
/* 121 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredLearnControlMap.class);
/* 122 */     criteria.createAlias("boUsers", "boUsers");
/* 123 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 124 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 125 */     criteria.add(Restrictions.eq("originalValue", originalValue));
/* 126 */     List list = this.boInfraredLearnControlMapDao.findByCriteria(criteria);
/* 127 */     if ((list == null) || (list.isEmpty())) {
/* 128 */       return null;
/*     */     }
/* 130 */     return (BoInfraredLearnControlMap)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredLearnControlMap> getListBy(String userCode, String deviceCode, String deviceAddress, int s, int s1)
/*     */   {
/* 137 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredLearnControlMap.class);
/* 138 */     criteria.createAlias("boUsers", "boUsers");
/* 139 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 140 */     criteria.createAlias("boDevice", "boDevice");
/* 141 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 142 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 143 */     criteria.add(Restrictions.between("originalValue", Integer.valueOf(s), Integer.valueOf(s1)));
/* 144 */     return this.boInfraredLearnControlMapDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoInfraredLearnControlMap findBy(String userCode, String deviceCode, String deviceAddress, String originalValue)
/*     */   {
/* 151 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredLearnControlMap.class);
/* 152 */     criteria.createAlias("boUsers", "boUsers");
/* 153 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 154 */     criteria.createAlias("boDevice", "boDevice");
/* 155 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 156 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 157 */     criteria.add(Restrictions.eq("originalValue", originalValue));
/* 158 */     List list = this.boInfraredLearnControlMapDao.findByCriteria(criteria);
/* 159 */     if ((list == null) || (list.isEmpty())) {
/* 160 */       return null;
/*     */     }
/* 162 */     return (BoInfraredLearnControlMap)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredLearnControlMap> infraredLearnControlMapList(String userCode, String deviceCode)
/*     */   {
/* 169 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredLearnControlMap.class);
/* 170 */     criteria.createAlias("boUsers", "boUsers");
/* 171 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 172 */     criteria.createAlias("boDevice", "boDevice");
/* 173 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 174 */     return this.boInfraredLearnControlMapDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoInfraredLearnControlMapServiceImpl
 * JD-Core Version:    0.6.2
 */