/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoAirBindingPanelDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAirBindingPanelServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAirBindingPanelService")
/*     */ public class BoAirBindingPanelServiceImpl extends AbstractBasicService<BoAirBindingPanel, Serializable>
/*     */   implements BoAirBindingPanelServiceIface<BoAirBindingPanel, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAirBindingPanelDaoIface<BoAirBindingPanel, Serializable> boAirBindingPanelDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoAirBindingPanel model)
/*     */   {
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAirBindingPanel save(BoAirBindingPanel model)
/*     */   {
/*  49 */     if (chkSaveValid(model))
/*     */     {
/*  51 */       this.boAirBindingPanelDao.save(model);
/*     */     }
/*  53 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAirBindingPanel model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAirBindingPanel update(BoAirBindingPanel model)
/*     */   {
/*  64 */     if (chkUpdateValid(model)) {
/*  65 */       this.boAirBindingPanelDao.update(model);
/*     */     }
/*  67 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoAirBindingPanel model)
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public BoAirBindingPanel delete(BoAirBindingPanel model)
/*     */   {
/*  79 */     if (chkUpdateValid(model)) {
/*  80 */       this.boAirBindingPanelDao.delete(model);
/*     */     }
/*  82 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     if ((id == null) || ("".equals(id)))
/*  88 */       return;
/*  89 */     if (chkDeleteValid(id))
/*  90 */       this.boAirBindingPanelDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoAirBindingPanel findBydeviceAddress(String userCode, String panelAddress)
/*     */   {
/* 107 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirBindingPanel.class);
/* 108 */     criteria.createAlias("boUsers", "boUsers");
/* 109 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 110 */     criteria.add(Restrictions.eq("panelAddress", panelAddress));
/*     */ 
/* 112 */     List list = this.boAirBindingPanelDao.findByCriteria(criteria);
/* 113 */     if ((list == null) || (list.isEmpty())) {
/* 114 */       return null;
/*     */     }
/* 116 */     return (BoAirBindingPanel)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoAirBindingPanel> getPanelAll(String userCode, String deviceAddress)
/*     */   {
/* 122 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirBindingPanel.class);
/* 123 */     criteria.createAlias("boUsers", "boUsers");
/* 124 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 125 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 126 */     return this.boAirBindingPanelDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoAirBindingPanel findPanelBindingDeviceAddress(String userCode, String deviceAddress)
/*     */   {
/* 133 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirBindingPanel.class);
/* 134 */     criteria.createAlias("boUsers", "boUsers");
/* 135 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 136 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 138 */     List list = this.boAirBindingPanelDao.findByCriteria(criteria);
/* 139 */     if ((list == null) || (list.isEmpty())) {
/* 140 */       return null;
/*     */     }
/* 142 */     return (BoAirBindingPanel)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoAirBindingPanel> airBindingPanelList(String userCode, String deviceCode)
/*     */   {
/* 149 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAirBindingPanel.class);
/* 150 */     criteria.createAlias("boUsers", "boUsers");
/* 151 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 152 */     criteria.createAlias("boDevice", "boDevice");
/* 153 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 154 */     return this.boAirBindingPanelDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAirBindingPanelServiceImpl
 * JD-Core Version:    0.6.2
 */