/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoInfraredButtonsDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoInfraredButtons;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoInfraredButtonsServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boInfraredButtonsService")
/*     */ public class BoInfraredButtonsServiceImpl extends AbstractBasicService<BoInfraredButtons, Serializable>
/*     */   implements BoInfraredButtonsServiceIface<BoInfraredButtons, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoInfraredButtonsDaoIface<BoInfraredButtons, Serializable> boInfraredButtonsDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoInfraredButtons model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons save(BoInfraredButtons model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.boInfraredButtonsDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoInfraredButtons model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons update(BoInfraredButtons model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.boInfraredButtonsDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoInfraredButtons model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons delete(BoInfraredButtons model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.boInfraredButtonsDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.boInfraredButtonsDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoInfraredButtons findBydeviceAddress(String userCode, String deviceCode, String deviceAddress)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.createAlias("boDevice", "boDevice");
/* 110 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 111 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 112 */     criteria.addOrder(Order.desc("infraredButtonsValuess"));
/* 113 */     List list = this.boInfraredButtonsDao.findByCriteria(criteria);
/* 114 */     if ((list == null) || (list.isEmpty())) {
/* 115 */       return null;
/*     */     }
/* 117 */     return (BoInfraredButtons)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredButtons> getListBy(String userCode, String deviceCode, String deviceAddress)
/*     */   {
/* 123 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 124 */     criteria.createAlias("boUsers", "boUsers");
/* 125 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 126 */     criteria.createAlias("boDevice", "boDevice");
/* 127 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 128 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 129 */     criteria.addOrder(Order.asc("infraredButtonsValuess"));
/* 130 */     return this.boInfraredButtonsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons findBydeviceAddress(String userCode, String deviceAddress, Integer infraredButtonsValuess)
/*     */   {
/* 137 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 138 */     criteria.createAlias("boUsers", "boUsers");
/* 139 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 140 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 141 */     criteria.add(Restrictions.eq("infraredButtonsValuess", infraredButtonsValuess));
/* 142 */     List list = this.boInfraredButtonsDao.findByCriteria(criteria);
/* 143 */     if ((list == null) || (list.isEmpty())) {
/* 144 */       return null;
/*     */     }
/* 146 */     return (BoInfraredButtons)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredButtons> getListBys(String userCode, String deviceCode)
/*     */   {
/* 152 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 153 */     criteria.createAlias("boUsers", "boUsers");
/* 154 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 155 */     criteria.createAlias("boDevice", "boDevice");
/* 156 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 157 */     return this.boInfraredButtonsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredButtons> getListBy(String userCode, String deviceAddress)
/*     */   {
/* 163 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 164 */     criteria.createAlias("boUsers", "boUsers");
/* 165 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 166 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 167 */     return this.boInfraredButtonsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons findBydeviceAddress(String userCode, String deviceCode, String deviceAddress, Integer infraredButtonsValuess)
/*     */   {
/* 174 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 175 */     criteria.createAlias("boUsers", "boUsers");
/* 176 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 177 */     criteria.createAlias("boDevice", "boDevice");
/* 178 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 179 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 180 */     criteria.add(Restrictions.eq("infraredButtonsValuess", infraredButtonsValuess));
/* 181 */     List list = this.boInfraredButtonsDao.findByCriteria(criteria);
/* 182 */     if ((list == null) || (list.isEmpty())) {
/* 183 */       return null;
/*     */     }
/* 185 */     return (BoInfraredButtons)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredButtons> getListBys(String userCode, String deviceAddress, Integer infraredButtonsValuess)
/*     */   {
/* 192 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 193 */     criteria.createAlias("boUsers", "boUsers");
/* 194 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 195 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 196 */     criteria.add(Restrictions.eq("infraredButtonsValuess", infraredButtonsValuess));
/* 197 */     return this.boInfraredButtonsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoInfraredButtons> getListBy(String userCode, String deviceCode, String deviceAddress, int s, int s1)
/*     */   {
/* 204 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 205 */     criteria.createAlias("boUsers", "boUsers");
/* 206 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 207 */     criteria.createAlias("boDevice", "boDevice");
/* 208 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 209 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 210 */     criteria.add(Restrictions.between("infraredButtonsValuess", Integer.valueOf(s), Integer.valueOf(s1)));
/* 211 */     return this.boInfraredButtonsDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public void deleteButton(int between, int and)
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoInfraredButtons findBydeviceAddress(String userCode, String deviceAddress)
/*     */   {
/* 224 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoInfraredButtons.class);
/* 225 */     criteria.createAlias("boUsers", "boUsers");
/* 226 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 227 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 228 */     criteria.addOrder(Order.desc("infraredButtonsValuess"));
/* 229 */     List list = this.boInfraredButtonsDao.findByCriteria(criteria);
/* 230 */     if ((list == null) || (list.isEmpty())) {
/* 231 */       return null;
/*     */     }
/* 233 */     return (BoInfraredButtons)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoInfraredButtonsServiceImpl
 * JD-Core Version:    0.6.2
 */