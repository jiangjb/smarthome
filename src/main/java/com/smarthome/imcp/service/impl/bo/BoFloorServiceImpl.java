/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoFloorDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoFloor;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoFloorServiceIface;
/*     */ import com.smarthome.imcp.util.SendMsgUtil;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boFloorService")
/*     */ public class BoFloorServiceImpl extends AbstractBasicService<BoFloor, Serializable>
/*     */   implements BoFloorServiceIface<BoFloor, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoFloorDaoIface<BoFloor, Serializable> boFloorDao;
/*     */ 
/*     */   public BoFloor findByFloorCode(String floorCode)
/*     */   {
/*  43 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/*  44 */     criteria.add(Restrictions.eq("floorCode", floorCode));
/*  45 */     List list = this.boFloorDao.findByCriteria(criteria);
/*  46 */     if ((list == null) || (list.isEmpty())) {
/*  47 */       return null;
/*     */     }
/*  49 */     return (BoFloor)list.get(0);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoFloor model)
/*     */   {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public BoFloor save(BoFloor model)
/*     */   {
/*  59 */     SendMsgUtil s = new SendMsgUtil();
/*     */ 
/*  61 */     if (chkSaveValid(model)) {
/*  62 */       model.setFloorCode(UuidUtil.get32UUID());
/*  63 */       this.boFloorDao.save(model);
/*     */     }
/*  65 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoFloor model)
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */   public BoFloor update(BoFloor model)
/*     */   {
/*  75 */     if (chkUpdateValid(model)) {
/*  76 */       this.boFloorDao.update(model);
/*     */     }
/*  78 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoFloor model)
/*     */   {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   public BoFloor delete(BoFloor model)
/*     */   {
/*  90 */     if (chkUpdateValid(model)) {
/*  91 */       this.boFloorDao.delete(model);
/*     */     }
/*  93 */     return model;
/*     */   }
/*     */ 
/*     */   public BoFloor findByFloorName(String userCode, String floorName)
/*     */   {
/*  99 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/* 100 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 101 */     criteria.add(Restrictions.eq("floorName", floorName));
/* 102 */     List list = this.boFloorDao.findByCriteria(criteria);
/* 103 */     if ((list == null) || (list.isEmpty())) {
/* 104 */       return null;
/*     */     }
/* 106 */     return (BoFloor)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoFloor> get(Integer floorId)
/*     */   {
/* 112 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/*     */ 
/* 114 */     criteria.add(Restrictions.eq("floorId", floorId));
/*     */ 
/* 116 */     return this.boFloorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoFloor> get(String floorCode)
/*     */   {
/* 122 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/* 123 */     criteria.add(Restrictions.eq("floorCode", floorCode));
/*     */ 
/* 126 */     return this.boFloorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoFloor> gets(String floorName)
/*     */   {
/* 132 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/*     */ 
/* 134 */     criteria.add(Restrictions.eq("floorName", floorName));
/*     */ 
/* 136 */     return this.boFloorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoFloor> getAllListByUserCode(String userCode)
/*     */   {
/* 142 */     return this.boFloorDao.getAllListByUserCode(userCode);
/*     */   }
/*     */ 
/*     */   public BoFloor findByUserCode(String userCode)
/*     */   {
/* 148 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/* 149 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 150 */     List list = this.boFloorDao.findByCriteria(criteria);
/* 151 */     if ((list == null) || (list.isEmpty())) {
/* 152 */       return null;
/*     */     }
/* 154 */     return (BoFloor)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoFloor> getUserCode(String userCode)
/*     */   {
/* 160 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFloor.class);
/* 161 */     criteria.add(Restrictions.eq("userCode", userCode));
/*     */ 
/* 164 */     return this.boFloorDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoFloorServiceImpl
 * JD-Core Version:    0.6.2
 */