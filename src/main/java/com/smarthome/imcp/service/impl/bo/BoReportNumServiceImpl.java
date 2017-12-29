/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoReportNumDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaNum;
/*     */ import com.smarthome.imcp.dao.model.bo.BoReportNum;
/*     */ import com.smarthome.imcp.service.bo.BoReportNumServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boReportNumService")
/*     */ public class BoReportNumServiceImpl
/*     */   implements BoReportNumServiceIface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoReportNumDaoIface<BoReportNum, Serializable> boReportNumDao;
/*     */ 
/*     */   public BoReportNum doFindByDeviceCode(String deviceCode, int frameType, int deviceType)
/*     */   {
/*  30 */     BoReportNum boReportNum = this.boReportNumDao.findByDeviceCode(deviceCode, frameType, deviceType);
/*  31 */     if (boReportNum == null) {
/*  32 */       boReportNum = new BoReportNum();
/*  33 */       boReportNum.setDeviceCode(deviceCode);
/*  34 */       boReportNum.setDeviceType(Integer.valueOf(deviceType));
/*  35 */       boReportNum.setFrameType(Integer.valueOf(frameType));
/*  36 */       boReportNum.setReportNum(Integer.valueOf(0));
/*  37 */       boReportNum.setUpdateTime(new Date());
/*     */ 
/*  39 */       this.boReportNumDao.save(boReportNum);
/*     */     }
/*     */ 
/*  42 */     return boReportNum;
/*     */   }
/*     */ 
/*     */   public int doPlusNum(String deviceCode, int frameType, int deviceType) {
/*  46 */     BoReportNum boReportNum = doFindByDeviceCode(deviceCode, frameType, deviceType);
/*  47 */     return this.boReportNumDao.update(1, deviceCode, frameType, deviceType);
/*     */   }
/*     */ 
/*     */   public void doInitNum(String deviceCode, int frameType, int deviceType) {
/*  51 */     BoReportNum boReportNum = doFindByDeviceCode(deviceCode, frameType, deviceType);
/*  52 */     boReportNum.setReportNum(Integer.valueOf(0));
/*  53 */     update(boReportNum);
/*     */   }
/*     */ 
/*     */   public List<BoReportNum> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  58 */     SearchCriteriaNum searchCriteriartNum = (SearchCriteriaNum)searchCriteria;
/*     */ 
/*  60 */     return this.boReportNumDao.getList(searchCriteriartNum, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoReportNum model)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public BoReportNum save(BoReportNum model)
/*     */   {
/*  70 */     if (chkSaveValid(model)) {
/*  71 */       this.boReportNumDao.save(model);
/*     */     }
/*  73 */     return model;
/*     */   }
/*     */ 
/*     */   public BoReportNum findByKey(Serializable id)
/*     */   {
/*  78 */     BoReportNum model = (BoReportNum)this.boReportNumDao.findById(id);
/*  79 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoReportNum model)
/*     */   {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   public BoReportNum update(BoReportNum model)
/*     */   {
/*  89 */     if (chkUpdateValid(model)) {
/*  90 */       this.boReportNumDao.update(model);
/*     */     }
/*  92 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 102 */     if (chkDeleteValid(id))
/* 103 */       this.boReportNumDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 109 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 110 */     while (st.hasMoreElements()) {
/* 111 */       String id = st.nextToken();
/* 112 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoReportNumServiceImpl
 * JD-Core Version:    0.6.2
 */