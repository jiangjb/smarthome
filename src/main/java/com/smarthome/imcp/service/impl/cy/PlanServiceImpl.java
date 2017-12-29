/*     */ package com.smarthome.imcp.service.impl.cy;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoDevicePlanDaoIface;
/*     */ import com.smarthome.imcp.dao.cy.PlanDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevicePlan;
/*     */ import com.smarthome.imcp.dao.model.cy.Plan;
/*     */ import com.smarthome.imcp.dao.vo.system.FileVo;
/*     */ import com.smarthome.imcp.service.cy.PlanServiceIface;
/*     */ import com.smarthome.imcp.service.system.FileServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("planService")
/*     */ public class PlanServiceImpl
/*     */   implements PlanServiceIface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private PlanDaoIface<Plan, Serializable> planDao;
/*     */ 
/*     */   @Autowired
/*     */   private BoDevicePlanDaoIface<BoDevicePlan, Serializable> boDevicePlanDao;
/*     */ 
/*     */   @Autowired
/*     */   private FileServiceIface<Object, Serializable> fileService;
/*     */ 
/*     */   public List<Plan> getList(Page page)
/*     */   {
/*  44 */     return this.planDao.getList(page);
/*     */   }
/*     */ 
/*     */   public Plan save(Plan plan)
/*     */   {
/*  49 */     saveFile(plan);
/*  50 */     this.planDao.save(plan);
/*     */ 
/*  52 */     return plan;
/*     */   }
/*     */ 
/*     */   private String saveFile(Plan model) {
/*  56 */     String img = model.getPicUrl();
/*  57 */     if (StringUtils.isNotEmpty(img)) {
/*  58 */       FileVo fileVo = this.fileService.copyTempFileToDir(img, "uploads/images", ContextUtil.getSessionId());
/*  59 */       model.setPicUrl(fileVo.getPathName());
/*     */     }
/*  61 */     this.fileService.deleteTempDirectory(ContextUtil.getSessionId());
/*  62 */     return img;
/*     */   }
/*     */ 
/*     */   public List<String> chargeDailyFee()
/*     */   {
/*  67 */     return this.planDao.deductDailyFee();
/*     */   }
/*     */ 
/*     */   public void bindPlan(String deviceCode, String planCode)
/*     */   {
/*  72 */     BoDevicePlan currentPlan = getDefaultPlan(deviceCode);
/*     */ 
/*  74 */     if (currentPlan != null) {
/*  75 */       Plan plan = findByCode(planCode);
/*  76 */       currentPlan.setPlan(plan);
/*     */ 
/*  78 */       Date today = new Date();
/*  79 */       Calendar cld = Calendar.getInstance();
/*  80 */       cld.setTime(today);
/*  81 */       cld.add(2, plan.getDuration());
/*  82 */       Date endDate = cld.getTime();
/*     */ 
/*  84 */       currentPlan.setStartDate(getShortDate(today));
/*  85 */       currentPlan.setEndDate(getShortDate(endDate));
/*     */ 
/*  87 */       this.boDevicePlanDao.update(currentPlan);
/*     */     } else {
/*  89 */       this.planDao.bindPlan(deviceCode, planCode);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Plan findByKey(Serializable id)
/*     */   {
/*  97 */     return (Plan)this.planDao.findById(id);
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 107 */     if (chkDeleteValid(id))
/* 108 */       this.planDao.deleteLogicByKey("deleted", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 114 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 115 */     while (st.hasMoreElements()) {
/* 116 */       String id = st.nextToken();
/* 117 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(Plan model)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   public Plan update(Plan model)
/*     */   {
/* 128 */     if (chkUpdateValid(model)) {
/* 129 */       this.planDao.update(model);
/*     */     }
/* 131 */     return model;
/*     */   }
/*     */ 
/*     */   public Plan findByCode(String planCode)
/*     */   {
/* 136 */     DetachedCriteria criteria = DetachedCriteria.forClass(Plan.class);
/* 137 */     criteria.add(Restrictions.eq("code", planCode));
/*     */ 
/* 139 */     List plans = this.planDao.findByCriteria(criteria);
/* 140 */     if ((plans != null) && (plans.size() > 0)) {
/* 141 */       return (Plan)plans.get(0);
/*     */     }
/*     */ 
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   private Date getShortDate(Date date) {
/* 148 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 150 */       String dateStr = format.format(date);
/* 151 */       return format.parse(dateStr); } catch (ParseException ex) {
/*     */     }
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */   private BoDevicePlan getDefaultPlan(String deviceCode)
/*     */   {
/* 158 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevicePlan.class);
/* 159 */     criteria.createAlias("device", "device");
/* 160 */     criteria.createAlias("plan", "plan");
/* 161 */     criteria.add(Restrictions.eq("device.deviceCode", deviceCode));
/* 162 */     criteria.add(Restrictions.eq("plan.id", Integer.valueOf(1999)));
/* 163 */     List devicePlans = this.boDevicePlanDao.findByCriteria(criteria);
/*     */ 
/* 165 */     if ((devicePlans != null) && (devicePlans.size() > 0)) {
/* 166 */       return (BoDevicePlan)devicePlans.get(0);
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */   public List<Plan> getAllList()
/*     */   {
/* 174 */     return this.planDao.getList(null);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.PlanServiceImpl
 * JD-Core Version:    0.6.2
 */