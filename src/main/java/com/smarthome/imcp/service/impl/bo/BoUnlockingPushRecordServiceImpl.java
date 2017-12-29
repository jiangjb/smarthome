/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUnlockingPushRecordDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUnlockingPushRecordServiceIface;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUnlockingPushRecordService")
/*     */ public class BoUnlockingPushRecordServiceImpl extends AbstractBasicService<BoUnlockingPushRecord, Serializable>
/*     */   implements BoUnlockingPushRecordServiceIface<BoUnlockingPushRecord, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUnlockingPushRecordDaoIface<BoUnlockingPushRecord, Serializable> boUnlockingPushRecordDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoUnlockingPushRecord model)
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUnlockingPushRecord save(BoUnlockingPushRecord model)
/*     */   {
/*  55 */     if (chkSaveValid(model)) {
/*  56 */       model.setUnlockingPushRecordId(UuidUtil.get32UUID());
/*  57 */       this.boUnlockingPushRecordDao.save(model);
/*     */     }
/*  59 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUnlockingPushRecord model)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUnlockingPushRecord update(BoUnlockingPushRecord model)
/*     */   {
/*  70 */     if (chkUpdateValid(model)) {
/*  71 */       this.boUnlockingPushRecordDao.update(model);
/*     */     }
/*  73 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoUnlockingPushRecord model)
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public BoUnlockingPushRecord delete(BoUnlockingPushRecord model)
/*     */   {
/*  85 */     if (chkUpdateValid(model)) {
/*  86 */       this.boUnlockingPushRecordDao.delete(model);
/*     */     }
/*  88 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  93 */     if ((id == null) || ("".equals(id)))
/*  94 */       return;
/*  95 */     if (chkDeleteValid(id))
/*  96 */       this.boUnlockingPushRecordDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 102 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 103 */     while (st.hasMoreElements()) {
/* 104 */       String id = st.nextToken();
/* 105 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void insertPush(String userCode, String lockAddress, String methodsStatus, String reportDate, String reportTimestamp)
/*     */   {
/* 113 */     Object[] values = { userCode, lockAddress, methodsStatus, reportDate, reportTimestamp, UuidUtil.get32UUID() };
/* 114 */     StringBuffer hql = new StringBuffer();
/* 115 */     hql.append("insert into kinconybackstage.bo_unlocking_push_record (USER_CODE, LOCK_ADDRESS, METHODS_STATUS, REPORT_DATE, REPORT_TIMESTAMP, UNLOCKING_PUSH_RECORD_ID) values (?, ?, ?, ?, ?, ?)");
/* 116 */     this.boUnlockingPushRecordDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public List<BoUnlockingPushRecord> getRecord(String userCode, Page page)
/*     */   {
/* 122 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUnlockingPushRecord.class);
/* 123 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 124 */     criteria.addOrder(Order.desc("reportTimestamp"));
/* 125 */     return this.boUnlockingPushRecordDao.findByCriteria(criteria, page);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUnlockingPushRecordServiceImpl
 * JD-Core Version:    0.6.2
 */