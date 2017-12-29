/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoUserDevicesDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevices;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUserDevicesServiceIface;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("BoUserDevicesService")
/*     */ public class BoUserDevicesServiceImpl extends AbstractBasicService<BoUserDevices, Serializable>
/*     */   implements BoUserDevicesServiceIface<BoUserDevices, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDevicesDaoIface<BoUserDevices, Serializable> BoUserDevicesDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoUserDevices model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserDevices save(BoUserDevices model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.BoUserDevicesDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUserDevices model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserDevices update(BoUserDevices model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.BoUserDevicesDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoUserDevices model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoUserDevices delete(BoUserDevices model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.BoUserDevicesDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.BoUserDevicesDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoUserDevices findBy(String userCode, String deviceCode)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.createAlias("boDevice", "boDevice");
/* 110 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/* 113 */     List list = this.BoUserDevicesDao.findByCriteria(criteria);
/* 114 */     System.out.println(list);
/* 115 */     if ((list == null) || (list.isEmpty())) {
/* 116 */       return null;
/*     */     }
/* 118 */     return (BoUserDevices)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevices> getListByDeviceCode(String deviceCode)
/*     */   {
/* 124 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 125 */     criteria.createAlias("boDevice", "boDevice");
/* 126 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 127 */     return this.BoUserDevicesDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoUserDevices findBy(String userCode)
/*     */   {
/* 133 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 134 */     criteria.createAlias("boUsers", "boUsers");
/* 135 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 139 */     List list = this.BoUserDevicesDao.findByCriteria(criteria);
/* 140 */     System.out.println(list);
/* 141 */     if ((list == null) || (list.isEmpty())) {
/* 142 */       return null;
/*     */     }
/* 144 */     return (BoUserDevices)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevices> getListByDeviceCodes(String userCode)
/*     */   {
/* 150 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 151 */     criteria.createAlias("boUsers", "boUsers");
/* 152 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 153 */     return this.BoUserDevicesDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevices> getBy(String userCode)
/*     */   {
/* 159 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 160 */     criteria.createAlias("boUsers", "boUsers");
/* 161 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 162 */     return this.BoUserDevicesDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoUserDevices findByUserDeviceCodes(String deviceCode, String nickName)
/*     */   {
/* 168 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
/* 169 */     criteria.createAlias("boDevice", "boDevice");
/* 170 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 171 */     criteria.add(Restrictions.eq("nickName", nickName));
/*     */ 
/* 173 */     List list = this.BoUserDevicesDao.findByCriteria(criteria);
/* 174 */     System.out.println(list);
/* 175 */     if ((list == null) || (list.isEmpty())) {
/* 176 */       return null;
/*     */     }
/* 178 */     return (BoUserDevices)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUserDevicesServiceImpl
 * JD-Core Version:    0.6.2
 */