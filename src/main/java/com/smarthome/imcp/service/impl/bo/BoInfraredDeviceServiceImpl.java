/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoInfraredDeviceDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoInfraredDevice;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoInfraredDeviceServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boInfraredDeviceService")
/*     */ public class BoInfraredDeviceServiceImpl extends AbstractBasicService<BoInfraredDevice, Serializable>
/*     */   implements BoInfraredDeviceServiceIface<BoInfraredDevice, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoInfraredDeviceDaoIface<BoInfraredDevice, Serializable> boInfraredDeviceDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoInfraredDevice model)
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredDevice save(BoInfraredDevice model)
/*     */   {
/*  45 */     if (chkSaveValid(model))
/*     */     {
/*  47 */       this.boInfraredDeviceDao.save(model);
/*     */     }
/*  49 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoInfraredDevice model)
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredDevice update(BoInfraredDevice model)
/*     */   {
/*  60 */     if (chkUpdateValid(model)) {
/*  61 */       this.boInfraredDeviceDao.update(model);
/*     */     }
/*  63 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoInfraredDevice model)
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public BoInfraredDevice delete(BoInfraredDevice model)
/*     */   {
/*  75 */     if (chkUpdateValid(model)) {
/*  76 */       this.boInfraredDeviceDao.delete(model);
/*     */     }
/*  78 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  83 */     if ((id == null) || ("".equals(id)))
/*  84 */       return;
/*  85 */     if (chkDeleteValid(id))
/*  86 */       this.boInfraredDeviceDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  92 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  93 */     while (st.hasMoreElements()) {
/*  94 */       String id = st.nextToken();
/*  95 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoInfraredDevice find(String deviceAddress)
/*     */   {
/* 102 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredDevice.class);
/* 103 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 104 */     List list = this.boInfraredDeviceDao.findByCriteria(criteria);
/* 105 */     if ((list == null) || (list.isEmpty())) {
/* 106 */       return null;
/*     */     }
/* 108 */     return (BoInfraredDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredDevice> getListBy(String deviceCode, Boolean fid)
/*     */   {
/* 114 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredDevice.class);
/* 115 */     criteria.createAlias("boDevice", "boDevice");
/* 116 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 117 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/*     */ 
/* 119 */     return this.boInfraredDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredDevice> getListBy(String userCode)
/*     */   {
/* 125 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredDevice.class);
/* 126 */     criteria.createAlias("boUsers", "boUsers");
/* 127 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 129 */     return this.boInfraredDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoInfraredDeviceServiceImpl
 * JD-Core Version:    0.6.2
 */