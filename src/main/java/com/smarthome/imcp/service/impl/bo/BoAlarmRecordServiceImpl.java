/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoAlarmRecordDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAlarmRecord;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoAlarmRecordServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boAlarmRecordService")
/*     */ public class BoAlarmRecordServiceImpl extends AbstractBasicService<BoAlarmRecord, Serializable>
/*     */   implements BoAlarmRecordServiceIface<BoAlarmRecord, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoAlarmRecordDaoIface<BoAlarmRecord, Serializable> boAlarmRecordDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoAlarmRecord model)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAlarmRecord save(BoAlarmRecord model)
/*     */   {
/*  50 */     if (chkSaveValid(model))
/*     */     {
/*  52 */       this.boAlarmRecordDao.save(model);
/*     */     }
/*  54 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoAlarmRecord model)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public BoAlarmRecord update(BoAlarmRecord model)
/*     */   {
/*  65 */     if (chkUpdateValid(model)) {
/*  66 */       this.boAlarmRecordDao.update(model);
/*     */     }
/*  68 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoAlarmRecord model)
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   public BoAlarmRecord delete(BoAlarmRecord model)
/*     */   {
/*  80 */     if (chkUpdateValid(model)) {
/*  81 */       this.boAlarmRecordDao.delete(model);
/*     */     }
/*  83 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  88 */     if ((id == null) || ("".equals(id)))
/*  89 */       return;
/*  90 */     if (chkDeleteValid(id))
/*  91 */       this.boAlarmRecordDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  97 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  98 */     while (st.hasMoreElements()) {
/*  99 */       String id = st.nextToken();
/* 100 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoAlarmRecord> getAlarmRecordByUserCode(String userCode, Page page)
/*     */   {
/* 107 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoAlarmRecord.class);
/* 108 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 109 */     criteria.addOrder(Order.desc("id"));
/*     */ 
/* 114 */     return this.boAlarmRecordDao.findByCriteria(criteria, page);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoAlarmRecordServiceImpl
 * JD-Core Version:    0.6.2
 */