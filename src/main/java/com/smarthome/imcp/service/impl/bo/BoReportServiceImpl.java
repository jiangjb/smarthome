/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.BoProcessHelper;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoReportDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaReport;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoReport;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.dao.model.cy.BoPercent;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoReportNumServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoReportServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
/*     */ import com.smarthome.imcp.service.cy.BoCrystalServiceIface;
/*     */ import com.smarthome.imcp.service.cy.BoPercentServiceIface;
/*     */ import com.smarthome.imcp.service.cy.CrystalTradeServiceIface;
/*     */ import com.smarthome.imcp.service.impl.push.PushService;
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boReportService")
/*     */ public class BoReportServiceImpl extends AbstractBasicService<BoReport, Serializable>
/*     */   implements BoReportServiceIface<BoReport, Serializable>
/*     */ {
/*  45 */   private static Logger logger = LoggerFactory.getLogger(BoReportServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private BoReportDaoIface<BoReport, Serializable> boReportDao;
/*     */ 
/*     */   @Autowired
/*     */   private CrystalTradeServiceIface crystalTradeService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoPercentServiceIface boPercentService;
/*     */ 
/*     */   @Autowired
/*     */   private BoCrystalServiceIface boCrystalService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceServiceIface<BoUserDevice, Serializable> boUserDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoProcessHelper boProcessHelper;
/*     */ 
/*     */   @Autowired
/*     */   private BoReportNumServiceIface boReportNumService;
/*     */ 
/*  68 */   public List<BoReport> getListByDeviceCode(String deviceCode, int deviceType, int type, Page page) { DetachedCriteria criteria = DetachedCriteria.forClass(BoReport.class);
/*  69 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*  70 */     criteria.add(Restrictions.eq("deviceType", Integer.valueOf(deviceType)));
/*  71 */     criteria.add(Restrictions.eq("frameType", Integer.valueOf(type)));
/*     */ 
/*  73 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  74 */       if (page.isAsc())
/*  75 */         criteria.addOrder(Order.asc("id"));
/*     */       else {
/*  77 */         criteria.addOrder(Order.desc("id"));
/*     */       }
/*     */     }
/*  80 */     else if (page.isAsc())
/*  81 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  83 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  87 */     return this.boReportDao.findByCriteria(criteria, page); }
/*     */ 
/*     */   public BoReport findByDeviceCode(String deviceCode, int deviceType, int type)
/*     */   {
/*  91 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoReport.class);
/*  92 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*  93 */     criteria.add(Restrictions.eq("deviceType", Integer.valueOf(deviceType)));
/*  94 */     criteria.add(Restrictions.eq("frameType", Integer.valueOf(type)));
/*  95 */     Page page = new Page();
/*  96 */     page.setPageSize(1);
/*  97 */     page.setPageNum(1);
/*  98 */     criteria.addOrder(Order.desc("id"));
/*  99 */     List list = this.boReportDao.findByCriteria(criteria, page);
/* 100 */     if ((list == null) || (list.isEmpty())) {
/* 101 */       return null;
/*     */     }
/* 103 */     return (BoReport)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoReport> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/* 108 */     SearchCriteriaReport searchCriteriaReport = (SearchCriteriaReport)searchCriteria;
/* 109 */     if (chkCriteriaValid(searchCriteria)) {
/* 110 */       return this.boReportDao.getList(searchCriteriaReport, page);
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoReport model)
/*     */   {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   public BoReport save(BoReport model)
/*     */   {
/* 122 */     chkSaveValid(model);
/*     */ 
/* 124 */     if (model.getDeviceType().intValue() == 336) {
/* 125 */       Date addTime = model.getTime();
/* 126 */       deleteHDate(model.getDeviceCode(), model.getDeviceType().intValue(), addTime);
/* 127 */       this.boReportNumService.doPlusNum(model.getDeviceCode(), model.getFrameType().intValue(), model.getDeviceType().intValue());
/*     */     }
/*     */ 
/* 130 */     if ((model.getDeviceType().intValue() == 576) && (model.getFrameType().intValue() == 3)) {
/* 131 */       String deviceCode = model.getDeviceCode();
/*     */ 
/* 133 */       BoDevice device = this.boDeviceService.findByCode(deviceCode);
/*     */ 
/* 135 */       BoPercent percent = this.boPercentService.find();
/*     */ 
/* 137 */       int lastWater = device.getWater().intValue();
/* 138 */       int newWater = model.getTotalWater().intValue();
/* 139 */       int water = newWater - lastWater;
/*     */ 
/* 149 */       if (water > 0) {
/* 150 */         device.setWater(Integer.valueOf(newWater));
/* 151 */         this.boDeviceService.update(device);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 189 */     this.boReportDao.save(model);
/* 190 */     return model;
/*     */   }
/*     */ 
/*     */   private void sendPacket(String deviceCode, int i)
/*     */   {
/* 199 */     byte[] b = { (byte)i };
/* 200 */     this.boProcessHelper.processSendDData(deviceCode, 'ɀ', (byte)2, b);
/* 201 */     this.boProcessHelper.processSend0Packet(deviceCode, 'ɀ', (byte)16);
/*     */   }
/*     */ 
/*     */   private void sendCrystalLow(BoUserDevice ud)
/*     */   {
/* 209 */     PushService pushService = new PushService();
/*     */ 
/* 211 */     String CID = ud.getBoUser().getCID();
/* 212 */     String title = "";
/*     */ 
/* 214 */     StringBuffer text = new StringBuffer();
/* 215 */     title = "友情提示";
/* 216 */     text.append("尊敬的客户您好");
/* 217 */     text.append("你的水晶过低请及时充值。");
/*     */ 
/* 219 */     Integer type = ud.getBoUser().getPhoneType();
/*     */ 
/* 221 */     if ((type == null) || (type.intValue() == 0))
/* 222 */       pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */     else
/* 224 */       pushService.apnPush(CID, title, text.toString(), text.toString());
/*     */   }
/*     */ 
/*     */   private void deleteHDate(String deviceCode, int deviceType, Date dt)
/*     */   {
/* 235 */     Calendar cd = Calendar.getInstance();
/* 236 */     cd.setTime(dt);
/* 237 */     cd.set(cd.get(1), cd.get(2), cd.get(5), cd.get(11), 0);
/* 238 */     Date startDate = cd.getTime();
/* 239 */     cd.add(10, 1);
/* 240 */     Date endDate = cd.getTime();
/* 241 */     Object[] values = { deviceCode, Integer.valueOf(deviceType), startDate, endDate };
/* 242 */     StringBuffer hql = new StringBuffer();
/* 243 */     hql.append("delete from BoReport");
/* 244 */     hql.append(" where deviceCode = ?");
/* 245 */     hql.append(" and deviceType = ?");
/* 246 */     hql.append(" and time >= ?");
/* 247 */     hql.append(" and time < ?");
/* 248 */     this.boReportDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   private int getLastWater(String deviceCode) {
/* 252 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoReport.class);
/* 253 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/* 254 */     criteria.add(Restrictions.eq("deviceType", Integer.valueOf(576)));
/* 255 */     criteria.add(Restrictions.eq("frameType", Integer.valueOf(3)));
/* 256 */     criteria.addOrder(Order.desc("id"));
/* 257 */     List list = this.boReportDao.findByCriteria(criteria);
/* 258 */     if ((list != null) && (!list.isEmpty())) {
/* 259 */       return ((BoReport)list.get(0)).getTotalWater().intValue();
/*     */     }
/* 261 */     return 0;
/*     */   }
/*     */ 
/*     */   public BoReport findByKey(Serializable id)
/*     */   {
/* 266 */     BoReport model = (BoReport)this.boReportDao.findById(id);
/* 267 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoReport model)
/*     */   {
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   public BoReport update(BoReport model)
/*     */   {
/* 277 */     if (chkUpdateValid(model)) {
/* 278 */       this.boReportDao.update(model);
/*     */     }
/* 280 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 290 */     if (chkDeleteValid(id))
/* 291 */       this.boReportDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 297 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 298 */     while (st.hasMoreElements()) {
/* 299 */       String id = st.nextToken();
/* 300 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoReportServiceImpl
 * JD-Core Version:    0.6.2
 */