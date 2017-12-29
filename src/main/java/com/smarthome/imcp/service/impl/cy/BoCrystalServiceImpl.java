/*     */ package com.smarthome.imcp.service.impl.cy;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUserDaoIface;
/*     */ import com.smarthome.imcp.dao.bo.BoUserDeviceDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaCrystal;
/*     */ import com.smarthome.imcp.dao.cy.BoCrystalDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.dao.model.cy.BoCrystal;
/*     */ import com.smarthome.imcp.service.cy.BoCrystalServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boCrystalService")
/*     */ public class BoCrystalServiceImpl
/*     */   implements BoCrystalServiceIface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoCrystalDaoIface<BoCrystal, Serializable> boCrystalDao;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDaoIface<BoUser, Serializable> boUserDao;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceDaoIface<BoUserDevice, Serializable> boUserDeviceDao;
/*     */ 
/*     */   public List<BoCrystal> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  44 */     SearchCriteriaCrystal searchCriteriaCrystal = (SearchCriteriaCrystal)searchCriteria;
/*  45 */     return this.boCrystalDao.getList(searchCriteriaCrystal, page);
/*     */   }
/*     */ 
/*     */   public BoCrystal save(BoCrystal model)
/*     */   {
/*  50 */     this.boCrystalDao.save(model);
/*  51 */     return model;
/*     */   }
/*     */ 
/*     */   public BoCrystal findByKey(Serializable id)
/*     */   {
/*  56 */     BoCrystal model = (BoCrystal)this.boCrystalDao.findById(Integer.valueOf((String) id));
/*  57 */     return model;
/*     */   }
/*     */ 
/*     */   public BoCrystal findByUserId(int userId)
/*     */   {
/*  62 */     return this.boCrystalDao.findByUserId(userId);
/*     */   }
/*     */ 
/*     */   public BoCrystal update(BoCrystal model)
/*     */   {
/*  67 */     model.setTotal(Integer.valueOf(model.getBalance().intValue() + model.getFreeze().intValue()));
/*  68 */     this.boCrystalDao.update(model);
/*  69 */     return model;
/*     */   }
/*     */ 
/*     */   public void update(int totalVar, int useVar, int nouseVar, int userId) {
/*  73 */     BoCrystal boCrystal = findByUserId(userId);
/*  74 */     if ((boCrystal == null) || (boCrystal.getId() == null) || (boCrystal.getBoUser() == null) || (boCrystal.getBoUser().getUserId() == null)) {
/*  75 */       boCrystal = new BoCrystal();
/*  76 */       boCrystal.setBoUser(new BoUser(Integer.valueOf(userId)));
/*  77 */       boCrystal.setTotal(Integer.valueOf(0));
/*  78 */       boCrystal.setBalance(Integer.valueOf(0));
/*  79 */       boCrystal.setFreeze(Integer.valueOf(0));
/*  80 */       this.boCrystalDao.save(boCrystal);
/*     */     }
/*  82 */     this.boCrystalDao.update(totalVar, useVar, nouseVar, userId);
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  87 */     this.boCrystalDao.deleteByKey(id);
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
/*     */   public boolean transferCrystal(int fromId, int toId, int amount)
/*     */   {
/* 101 */     return this.boCrystalDao.transferCrystal(fromId, toId, amount);
/*     */   }
/*     */ 
/*     */   public BoCrystal findByUserCode(String userCode)
/*     */   {
/* 106 */     return this.boCrystalDao.findByUserCode(userCode);
/*     */   }
/*     */ 
/*     */   public List<BoCrystal> findCrystalByDeviceType(int deviceTypeId)
/*     */   {
/* 111 */     return this.boCrystalDao.findByDeviceType(deviceTypeId);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.BoCrystalServiceImpl
 * JD-Core Version:    0.6.2
 */