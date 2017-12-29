/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoRepairDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaRepair;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoRepair;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoRepairServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
/*     */ import com.smarthome.imcp.service.impl.push.PushService;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boRepairService")
/*     */ public class BoRepairServiceImpl extends AbstractBasicService<BoRepair, Serializable>
/*     */   implements BoRepairServiceIface<BoRepair, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoRepairDaoIface<BoRepair, Serializable> boRepairDao;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceServiceIface<BoUserDevice, Serializable> boUserDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   public boolean isExistUndo(String userCode, String deviceCode)
/*     */   {
/*  49 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRepair.class);
/*     */ 
/*  51 */     criteria.createAlias("boUser", "boUser");
/*  52 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/*     */ 
/*  54 */     criteria.createAlias("boDevice", "boDevice");
/*  55 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/*  57 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  59 */     criteria.addOrder(Order.asc("repairId"));
/*  60 */     List list = this.boRepairDao.findByCriteria(criteria);
/*     */ 
/*  62 */     if ((list == null) || (list.isEmpty())) {
/*  63 */       return false;
/*     */     }
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public List<BoRepair> getListByUserCode(String userCode, Page page)
/*     */   {
/*  70 */     return this.boRepairDao.getListByUserCode(userCode, page);
/*     */   }
/*     */ 
/*     */   public List<BoRepair> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  75 */     SearchCriteriaRepair searchCriteriaRepair = (SearchCriteriaRepair)searchCriteria;
/*  76 */     if (chkCriteriaValid(searchCriteria)) {
/*  77 */       return this.boRepairDao.getList(searchCriteriaRepair, page);
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoRepair model)
/*     */   {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRepair save(BoRepair model)
/*     */   {
/*  89 */     if (!chkSaveValid(model)) {
/*  90 */       return model;
/*     */     }
/*  92 */     this.boRepairDao.save(model);
/*  93 */     return model;
/*     */   }
/*     */ 
/*     */   public BoRepair findByKey(Serializable id)
/*     */   {
/*  98 */     BoRepair model = (BoRepair)this.boRepairDao.findById(id);
/*  99 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoRepair model)
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRepair update(BoRepair model)
/*     */   {
/* 109 */     if (!chkUpdateValid(model)) {
/* 110 */       return model;
/*     */     }
/*     */ 
/* 113 */     String title = "友情提示";
/*     */ 
/* 115 */     BoDevice boDevice = (BoDevice)this.boDeviceService.findByKey(model.getBoDevice().getDeviceId());
/*     */ 
/* 117 */     if (boDevice == null) {
/* 118 */       throw new BusinessException("设备不存在！");
/*     */     }
/*     */ 
/* 121 */     BoUser boUser = (BoUser)this.boUserService.findByKey(model.getBoUser().getUserId());
/* 122 */     String CID = boUser.getCID();
/*     */ 
/* 124 */     BoUserDevice ud = this.boUserDeviceService.findByUserDeviceCode(boUser.getUserCode(), boDevice.getDeviceCode());
/*     */ 
/* 126 */     if (ud == null) {
/* 127 */       throw new BusinessException("设备未绑定用户！");
/*     */     }
/*     */ 
/* 130 */     StringBuffer text = new StringBuffer();
/* 131 */     text.append("尊敬的客户，你的");
/* 132 */     text.append(ud.getNickName());
/* 133 */     text.append("报修，我公司将于");
/* 134 */     text.append(GlobalMethod.formatDate(model.getOrderDate(), "yyyy-MM-dd HH:mm:ss"));
/* 135 */     text.append("派工作人员进行维修，如有不更和其他事宜，请联系");
/* 136 */     text.append(model.getWorkTel());
/*     */ 
/* 138 */     Integer type = ud.getBoUser().getPhoneType();
/* 139 */     PushService pushService = new PushService();
/* 140 */     if (model.getBoUser().getSource().intValue() == 336) {
/* 141 */       pushService.setAppId("egB7MW2o0L9It60nlxt098");
/* 142 */       pushService.setAppkey("iIgM1MTmnY8F1YDF8f9x38");
/* 143 */       pushService.setMaster("KhY2GOaBPq6Xupb7FFzBM4");
/*     */     }
/*     */ 
/* 146 */     if ((type == null) || (type.intValue() == 0))
/* 147 */       pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */     else {
/* 149 */       pushService.apnPush(CID, title, text.toString(), text.toString());
/*     */     }
/*     */ 
/* 152 */     model.setRepairStatus("1");
/* 153 */     this.boRepairDao.update(model);
/*     */ 
/* 155 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 165 */     if ((id == null) || ("".equals(id)))
/* 166 */       return;
/* 167 */     if (chkDeleteValid(id))
/* 168 */       this.boRepairDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 174 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 175 */     while (st.hasMoreElements()) {
/* 176 */       String id = st.nextToken();
/* 177 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoRepairServiceImpl
 * JD-Core Version:    0.6.2
 */