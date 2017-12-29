/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoControlEnclosureDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoControlEnclosure;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoControlEnclosureServiceIface;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boControlEnclosureService")
/*     */ public class BoControlEnclosureServiceImpl extends AbstractBasicService<BoControlEnclosure, Serializable>
/*     */   implements BoControlEnclosureServiceIface<BoControlEnclosure, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoControlEnclosureDaoIface<BoControlEnclosure, Serializable> boControlEnclosureDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoControlEnclosure model)
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   public BoControlEnclosure save(BoControlEnclosure model)
/*     */   {
/*  51 */     if (chkSaveValid(model))
/*     */     {
/*  53 */       this.boControlEnclosureDao.save(model);
/*     */     }
/*  55 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoControlEnclosure model)
/*     */   {
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   public BoControlEnclosure update(BoControlEnclosure model)
/*     */   {
/*  66 */     if (chkUpdateValid(model)) {
/*  67 */       this.boControlEnclosureDao.update(model);
/*     */     }
/*  69 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoControlEnclosure model)
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   public BoControlEnclosure delete(BoControlEnclosure model)
/*     */   {
/*  81 */     if (chkUpdateValid(model)) {
/*  82 */       this.boControlEnclosureDao.delete(model);
/*     */     }
/*  84 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  89 */     if ((id == null) || ("".equals(id)))
/*  90 */       return;
/*  91 */     if (chkDeleteValid(id))
/*  92 */       this.boControlEnclosureDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  98 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  99 */     while (st.hasMoreElements()) {
/* 100 */       String id = st.nextToken();
/* 101 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoControlEnclosure> controlEnclosure(String deviceCode, String userCode, Integer id)
/*     */   {
/* 109 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoControlEnclosure.class);
/* 110 */     criteria.createAlias("boDevice", "boDevice");
/* 111 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 112 */     criteria.createAlias("boUsers", "boUsers");
/* 113 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 114 */     criteria.createAlias("boHostDevice", "boHostDevice");
/* 115 */     criteria.add(Restrictions.eq("boHostDevice.id", id));
/* 116 */     return this.boControlEnclosureDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoControlEnclosure> controlEnclosure(String deviceCode, String userCode, String deviceAddress)
/*     */   {
/* 123 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoControlEnclosure.class);
/* 124 */     criteria.createAlias("boDevice", "boDevice");
/* 125 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 126 */     criteria.createAlias("boUsers", "boUsers");
/* 127 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 128 */     criteria.createAlias("boHostDevice", "boHostDevice");
/* 129 */     criteria.add(Restrictions.eq("boHostDevice.deviceAddress", deviceAddress));
/* 130 */     return this.boControlEnclosureDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoControlEnclosure> controlEnclosure(String deviceCode, String userCode)
/*     */   {
/* 137 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoControlEnclosure.class);
/* 138 */     criteria.createAlias("boDevice", "boDevice");
/* 139 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 140 */     criteria.createAlias("boUsers", "boUsers");
/* 141 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 142 */     return this.boControlEnclosureDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoControlEnclosure controlEnclosures(String deviceCode, String userCode)
/*     */   {
/* 149 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoControlEnclosure.class);
/* 150 */     criteria.createAlias("boDevice", "boDevice");
/* 151 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 152 */     criteria.createAlias("boUsers", "boUsers");
/* 153 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 155 */     List list = this.boControlEnclosureDao.findByCriteria(criteria);
/* 156 */     System.out.println(list);
/* 157 */     if ((list == null) || (list.isEmpty())) {
/* 158 */       return null;
/*     */     }
/* 160 */     return (BoControlEnclosure)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoControlEnclosure controlEnclosures(String deviceCode, String userCode, String deviceAddress)
/*     */   {
/* 167 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoControlEnclosure.class);
/* 168 */     criteria.createAlias("boDevice", "boDevice");
/* 169 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 170 */     criteria.createAlias("boUsers", "boUsers");
/* 171 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 172 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 174 */     List list = this.boControlEnclosureDao.findByCriteria(criteria);
/* 175 */     System.out.println(list);
/* 176 */     if ((list == null) || (list.isEmpty())) {
/* 177 */       return null;
/*     */     }
/* 179 */     return (BoControlEnclosure)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoControlEnclosureServiceImpl
 * JD-Core Version:    0.6.2
 */