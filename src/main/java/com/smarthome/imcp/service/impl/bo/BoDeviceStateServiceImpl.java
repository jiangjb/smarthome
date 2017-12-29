/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoDeviceStateDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDeviceState;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceStateServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boDeviceStateService")
/*     */ public class BoDeviceStateServiceImpl extends AbstractBasicService<BoDeviceState, Serializable>
/*     */   implements BoDeviceStateServiceIface<BoDeviceState, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceStateDaoIface<BoDeviceState, Serializable> boDeviceStateDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoDeviceState model)
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDeviceState save(BoDeviceState model)
/*     */   {
/*  45 */     if (chkSaveValid(model))
/*     */     {
/*  47 */       this.boDeviceStateDao.save(model);
/*     */     }
/*  49 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoDeviceState model)
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDeviceState update(BoDeviceState model)
/*     */   {
/*  60 */     if (chkUpdateValid(model)) {
/*  61 */       this.boDeviceStateDao.update(model);
/*     */     }
/*  63 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoDeviceState model)
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public BoDeviceState delete(BoDeviceState model)
/*     */   {
/*  75 */     if (chkUpdateValid(model)) {
/*  76 */       this.boDeviceStateDao.delete(model);
/*     */     }
/*  78 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  83 */     if ((id == null) || ("".equals(id)))
/*  84 */       return;
/*  85 */     if (chkDeleteValid(id))
/*  86 */       this.boDeviceStateDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoDeviceState findBy(String deviceAddress)
/*     */   {
/* 102 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/*     */ 
/* 104 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 106 */     List list = this.boDeviceStateDao.findByCriteria(criteria);
/* 107 */     if ((list == null) || (list.isEmpty())) {
/* 108 */       return null;
/*     */     }
/* 110 */     return (BoDeviceState)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoDeviceState findBy(String userCode, String deviceAddress)
/*     */   {
/* 116 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/* 117 */     criteria.createAlias("boUsers", "boUsers");
/* 118 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 119 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 121 */     List list = this.boDeviceStateDao.findByCriteria(criteria);
/* 122 */     if ((list == null) || (list.isEmpty())) {
/* 123 */       return null;
/*     */     }
/* 125 */     return (BoDeviceState)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoDeviceState> getByuserCode(String userCode)
/*     */   {
/* 131 */     return this.boDeviceStateDao.getByuserCode(userCode);
/*     */   }
/*     */ 
/*     */   public List<BoDeviceState> getBydeviceAddress(String deviceAddress)
/*     */   {
/* 137 */     return this.boDeviceStateDao.getBydeviceAddress(deviceAddress);
/*     */   }
/*     */ 
/*     */   public List<BoDeviceState> getListBy(String deviceCode)
/*     */   {
/* 143 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/* 144 */     criteria.createAlias("boDevice", "boDevice");
/* 145 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/* 147 */     return this.boDeviceStateDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoDeviceState> getListBy(String userCode, String deviceCode)
/*     */   {
/* 153 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/* 154 */     criteria.createAlias("boDevice", "boDevice");
/* 155 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 156 */     criteria.createAlias("boUsers", "boUsers");
/* 157 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 158 */     return this.boDeviceStateDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoDeviceState> getBydeviceAddress(String userCode, String deviceAddress)
/*     */   {
/* 164 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDeviceState.class);
/* 165 */     criteria.createAlias("boUsers", "boUsers");
/* 166 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 167 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 168 */     return this.boDeviceStateDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoDeviceStateServiceImpl
 * JD-Core Version:    0.6.2
 */