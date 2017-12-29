/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoChannelDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoChannel;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoChannelServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boChannelService")
/*     */ public class BoChannelServiceImpl extends AbstractBasicService<BoChannel, Serializable>
/*     */   implements BoChannelServiceIface<BoChannel, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoChannelDaoIface<BoChannel, Serializable> boChannelDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoChannel model)
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   public BoChannel save(BoChannel model)
/*     */   {
/*  48 */     if (chkSaveValid(model))
/*     */     {
/*  50 */       this.boChannelDao.save(model);
/*     */     }
/*  52 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoChannel model)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public BoChannel update(BoChannel model)
/*     */   {
/*  63 */     if (chkUpdateValid(model)) {
/*  64 */       this.boChannelDao.update(model);
/*     */     }
/*  66 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoChannel model)
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public BoChannel delete(BoChannel model)
/*     */   {
/*  78 */     if (chkUpdateValid(model)) {
/*  79 */       this.boChannelDao.delete(model);
/*     */     }
/*  81 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  86 */     if ((id == null) || ("".equals(id)))
/*  87 */       return;
/*  88 */     if (chkDeleteValid(id))
/*  89 */       this.boChannelDao.deleteByKey(Integer.valueOf(id));
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
/*     */   public BoChannel findBy(String userCode, String deviceCode, String deviceOrHost)
/*     */   {
/* 106 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoChannel.class);
/* 107 */     criteria.createAlias("boUsers", "boUsers");
/* 108 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 109 */     criteria.createAlias("boDevice", "boDevice");
/* 110 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 111 */     criteria.add(Restrictions.eq("deviceOrHost", deviceOrHost));
/*     */ 
/* 113 */     List list = this.boChannelDao.findByCriteria(criteria);
/*     */ 
/* 115 */     if ((list == null) || (list.isEmpty())) {
/* 116 */       return null;
/*     */     }
/* 118 */     return (BoChannel)list.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoChannelServiceImpl
 * JD-Core Version:    0.6.2
 */