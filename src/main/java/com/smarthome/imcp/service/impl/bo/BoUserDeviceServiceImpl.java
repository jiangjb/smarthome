/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUserDeviceDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUserDeviceService")
/*     */ public class BoUserDeviceServiceImpl extends AbstractBasicService<BoUserDevice, Serializable>
/*     */   implements BoUserDeviceServiceIface<BoUserDevice, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceDaoIface<BoUserDevice, Serializable> boUserDeviceDao;
/*     */ 
/*     */   public BoUserDevice findByUserDeviceCode(String userCode, String deviceCode)
/*     */   {
/*  37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/*  38 */     criteria.createAlias("boDevice", "boDevice");
/*  39 */     criteria.createAlias("boUser", "boUser");
/*  40 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/*  41 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*  42 */     List list = this.boUserDeviceDao.findByCriteria(criteria);
/*     */ 
/*  44 */     if ((list == null) || (list.isEmpty())) {
/*  45 */       return null;
/*     */     }
/*  47 */     return (BoUserDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevice> getListByDeviceCode(String deviceCode)
/*     */   {
/*  52 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/*  53 */     criteria.createAlias("boDevice", "boDevice");
/*  54 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*  55 */     return this.boUserDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevice> getListByUserCode(String userCode, Page page)
/*     */   {
/*  60 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/*  61 */     criteria.createAlias("boUser", "boUser");
/*  62 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/*  63 */     return this.boUserDeviceDao.findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoUserDevice> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  68 */     SearchCriteriaUserDevice searchCriteriaerDevice = (SearchCriteriaUserDevice)searchCriteria;
/*  69 */     if (chkCriteriaValid(searchCriteria)) {
/*  70 */       return this.boUserDeviceDao.getList(searchCriteriaerDevice, page);
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoUserDevice model)
/*     */   {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserDevice save(BoUserDevice model)
/*     */   {
/*  82 */     if (chkSaveValid(model)) {
/*  83 */       this.boUserDeviceDao.save(model);
/*     */     }
/*  85 */     return model;
/*     */   }
/*     */ 
/*     */   public BoUserDevice findByKey(Serializable id)
/*     */   {
/*  90 */     BoUserDevice model = (BoUserDevice)this.boUserDeviceDao.findById(id);
/*  91 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUserDevice model)
/*     */   {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUserDevice update(BoUserDevice model)
/*     */   {
/* 101 */     if (chkUpdateValid(model)) {
/* 102 */       this.boUserDeviceDao.update(model);
/*     */     }
/* 104 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 114 */     if ((id == null) || ("".equals(id)))
/* 115 */       return;
/* 116 */     if (chkDeleteValid(id))
/* 117 */       this.boUserDeviceDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 123 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 124 */     while (st.hasMoreElements()) {
/* 125 */       String id = st.nextToken();
/* 126 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoUserDevice> getListByUserId(int userId)
/*     */   {
/* 132 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/* 133 */     criteria.createAlias("boUser", "boUser");
/* 134 */     criteria.add(Restrictions.eq("boUser.userId", Integer.valueOf(userId)));
/* 135 */     return this.boUserDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoUserDevice getMasterBinding(String deviceCode)
/*     */   {
/* 140 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevice.class);
/* 141 */     criteria.createAlias("boDevice", "boDevice");
/* 142 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 143 */     criteria.add(Restrictions.eq("boDevice.mntDelete", "N"));
/* 144 */     criteria.add(Restrictions.eq("bindingType", Character.valueOf('M')));
/* 145 */     List list = this.boUserDeviceDao.findByCriteria(criteria);
/*     */ 
/* 147 */     if ((list != null) && (list.size() > 0)) {
/* 148 */       return (BoUserDevice)list.get(0);
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer countByDeviceId(int deviceId)
/*     */   {
/* 156 */     return this.boUserDeviceDao.getCount("from BoUserDevice d where d.boDevice.id = ?", Integer.valueOf(deviceId));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUserDeviceServiceImpl
 * JD-Core Version:    0.6.2
 */