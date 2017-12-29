/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.action.xing.XingUserAction;
/*     */ import com.smarthome.imcp.dao.bo.BoSensorDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoSensor;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoSensorServiceIface;
/*     */ import com.smarthome.imcp.service.impl.push.PushService;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boSensorService")
/*     */ public class BoSensorServiceImpl extends AbstractBasicService<BoSensor, Serializable>
/*     */   implements BoSensorServiceIface<BoSensor, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoSensorDaoIface<BoSensor, Serializable> boSensorDao;
/*  39 */   private XingUserAction s = new XingUserAction();
/*     */ 
/*     */   public boolean chkSaveValid(BoSensor model)
/*     */   {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   public BoSensor save(BoSensor model)
/*     */   {
/*  53 */     if (chkSaveValid(model))
/*     */     {
/*  55 */       this.boSensorDao.save(model);
/*     */     }
/*  57 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoSensor model)
/*     */   {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   public BoSensor update(BoSensor model)
/*     */   {
/*  68 */     if (chkUpdateValid(model)) {
/*  69 */       this.boSensorDao.update(model);
/*     */     }
/*  71 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoSensor model)
/*     */   {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public BoSensor delete(BoSensor model)
/*     */   {
/*  83 */     if (chkUpdateValid(model)) {
/*  84 */       this.boSensorDao.delete(model);
/*     */     }
/*  86 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  91 */     if ((id == null) || ("".equals(id)))
/*  92 */       return;
/*  93 */     if (chkDeleteValid(id))
/*  94 */       this.boSensorDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 100 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 101 */     while (st.hasMoreElements()) {
/* 102 */       String id = st.nextToken();
/* 103 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoSensor> get(String deviceCode)
/*     */   {
/* 110 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 111 */     criteria.createAlias("boDevice", "boDevice");
/* 112 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 113 */     criteria.addOrder(Order.desc("deviceAddress"));
/*     */ 
/* 115 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoSensor> getListByUserCodes(String userCode, String patternType)
/*     */   {
/* 121 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 122 */     criteria.createAlias("boUsers", "boUsers");
/* 123 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 124 */     criteria.add(Restrictions.eq("type", patternType));
/* 125 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoSensor find(String deviceCode, String deviceAddress)
/*     */   {
/* 131 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 132 */     criteria.createAlias("boDevice", "boDevice");
/* 133 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 134 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 135 */     List list = this.boSensorDao.findByCriteria(criteria);
/*     */ 
/* 137 */     if ((list == null) || (list.isEmpty())) {
/* 138 */       return null;
/*     */     }
/* 140 */     return (BoSensor)list.get(0);
/*     */   }
/*     */ 
/*     */   public void Key()
/*     */   {
/* 146 */     PushService pushService = new PushService();
/* 147 */     pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/* 148 */     pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/* 149 */     pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/* 150 */     String CID = "90b6756124b0fb2fb9e3f25ec21d23bf8499407ee039ad843a43cdc01f0e6de0";
/* 151 */     String title = "";
/*     */ 
/* 153 */     StringBuffer text = new StringBuffer();
/*     */ 
/* 155 */     title = "友情提示";
/* 156 */     text.append("尊敬的客户您好");
/*     */ 
/* 158 */     text.append("掌上智家");
/*     */ 
/* 160 */     text.append(" 请务必留意。");
/* 161 */     System.err.println(text.toString());
/*     */ 
/* 163 */     Integer type = Integer.valueOf(1);
/*     */ 
/* 165 */     if ((type == null) || (type.intValue() == 0)) {
/* 166 */       pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */     }
/*     */     else {
/* 169 */       pushService.apnPush(CID, title, text.toString(), text.toString());
/* 170 */       System.err.println("<>");
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoSensor> get(String deviceCode, String deviceAddress)
/*     */   {
/* 178 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 179 */     criteria.createAlias("boDevice", "boDevice");
/* 180 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 181 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 183 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public int getcountSensor(String deviceCode)
/*     */   {
/* 190 */     String sql = "select COUNT(*)  from bo_sensor a inner join bo_device b on a.DEVICE_ID = b.DEVICE_ID";
/* 191 */     List list = this.boSensorDao.find(sql);
/* 192 */     if ((list != null) && (list.size() == 1))
/* 193 */       return Integer.valueOf(list.get(0).toString()).intValue();
/* 194 */     return 0;
/*     */   }
/*     */ 
/*     */   public List<BoSensor> getByModelId(String modelId)
/*     */   {
/* 200 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 201 */     criteria.createAlias("boModel", "boModel");
/* 202 */     criteria.add(Restrictions.eq("boModel.modelId", modelId));
/*     */ 
/* 204 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoSensor findBydeviceAddress(String userCode, String deviceCode, String deviceAddress, String patternType)
/*     */   {
/* 210 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 211 */     criteria.createAlias("boUsers", "boUsers");
/* 212 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 213 */     criteria.createAlias("boDevice", "boDevice");
/* 214 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 215 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 216 */     criteria.add(Restrictions.eq("type", patternType));
/*     */ 
/* 218 */     List list = this.boSensorDao.findByCriteria(criteria);
/*     */ 
/* 220 */     if ((list == null) || (list.isEmpty())) {
/* 221 */       return null;
/*     */     }
/* 223 */     return (BoSensor)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoSensor findBydeviceAddress(String userCode, String deviceAddress)
/*     */   {
/* 229 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 230 */     criteria.createAlias("boUsers", "boUsers");
/* 231 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 233 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 235 */     List list = this.boSensorDao.findByCriteria(criteria);
/*     */ 
/* 237 */     if ((list == null) || (list.isEmpty())) {
/* 238 */       return null;
/*     */     }
/* 240 */     return (BoSensor)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoSensor> gets(String userCode, String deviceCode)
/*     */   {
/* 246 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 247 */     criteria.createAlias("boUsers", "boUsers");
/* 248 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 249 */     criteria.createAlias("boDevice", "boDevice");
/* 250 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/* 253 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoSensor> getSecurity(String userCode, String patternType)
/*     */   {
/* 259 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoSensor.class);
/* 260 */     criteria.createAlias("boUsers", "boUsers");
/* 261 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 262 */     criteria.add(Restrictions.eq("type", patternType));
/*     */ 
/* 264 */     return this.boSensorDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoSensorServiceImpl
 * JD-Core Version:    0.6.2
 */