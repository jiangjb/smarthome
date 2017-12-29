/*     */ package com.smarthome.imcp.service.impl.cy;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.BoProcessHelper;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecord;
/*     */ import com.smarthome.imcp.dao.cy.TradeRecordDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.dao.model.cy.BoCrystal;
/*     */ import com.smarthome.imcp.dao.model.cy.BoPercent;
/*     */ import com.smarthome.imcp.dao.model.cy.CrystalRecharge;
/*     */ import com.smarthome.imcp.dao.model.cy.TradeRecord;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.cy.BoCrystalServiceIface;
/*     */ import com.smarthome.imcp.service.cy.BoPercentServiceIface;
/*     */ import com.smarthome.imcp.service.cy.CrystalRechargeServiceIface;
/*     */ import com.smarthome.imcp.service.cy.TradeRecordServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("tradeRecordService")
/*     */ public class TradeRecordServiceImpl
/*     */   implements TradeRecordServiceIface
/*     */ {
/*  41 */   private static final Logger logger = Logger.getLogger(TradeRecordServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private TradeRecordDaoIface<TradeRecord, Serializable> tradeRecordDao;
/*     */ 
/*     */   @Autowired
/*     */   private CrystalRechargeServiceIface crystalRechargeService;
/*     */ 
/*     */   @Autowired
/*     */   private BoPercentServiceIface boPercentService;
/*     */ 
/*     */   @Autowired
/*     */   private BoCrystalServiceIface boCrystalService;
/*     */ 
/*     */   @Autowired
/*     */   private BoProcessHelper boProcessHelper;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceServiceIface<BoUserDevice, Serializable> boUserDeviceService;
/*     */ 
/*  58 */   public TradeRecord findByOrderNo(String orderNo) { return this.tradeRecordDao.findByOrderNo(orderNo); }
/*     */ 
/*     */ 
/*     */   public List<TradeRecord> getListByUserId(int userId, Page page)
/*     */   {
/*  63 */     return this.tradeRecordDao.getListByUserId(userId, page);
/*     */   }
/*     */ 
/*     */   public List<TradeRecord> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  68 */     SearchCriteriaRecord searchCriteriaRecord = (SearchCriteriaRecord)searchCriteria;
/*  69 */     return this.tradeRecordDao.getList(searchCriteriaRecord, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(TradeRecord model)
/*     */   {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   public TradeRecord save(TradeRecord model)
/*     */   {
/*  79 */     if (!chkSaveValid(model)) {
/*  80 */       return model;
/*     */     }
/*     */ 
/*  83 */     this.tradeRecordDao.save(model);
/*  84 */     saveRecharge(model);
/*     */ 
/*  86 */     return model;
/*     */   }
/*     */ 
/*     */   private void saveRecharge(TradeRecord record)
/*     */   {
/*  94 */     if (record.getStatus().intValue() == 1) {
/*  95 */       BoPercent percent = this.boPercentService.find(record.getTradeMoney().doubleValue());
/*     */ 
/* 103 */       int crystal = percent.getCrystals().intValue();
/* 104 */       CrystalRecharge recharge = new CrystalRecharge();
/* 105 */       recharge.setCrystal(Integer.valueOf(crystal));
/* 106 */       recharge.setTradeMoney(record.getTradeMoney());
/* 107 */       recharge.setTradeNo(record.getTradeNo());
/* 108 */       recharge.setTradId(record.getId());
/* 109 */       recharge.setBoUser(new BoUser(record.getUserId()));
/*     */ 
/* 111 */       this.crystalRechargeService.save(recharge);
/*     */ 
/* 113 */       BoCrystal boCrystal = this.boCrystalService.findByUserId(record.getUserId().intValue());
/* 114 */       if (boCrystal == null) {
/* 115 */         boCrystal = new BoCrystal();
/* 116 */         boCrystal.setTotal(Integer.valueOf(crystal));
/* 117 */         boCrystal.setBalance(Integer.valueOf(crystal));
/* 118 */         boCrystal.setFreeze(Integer.valueOf(0));
/* 119 */         boCrystal.setBoUser(new BoUser(record.getUserId()));
/*     */ 
/* 121 */         this.boCrystalService.save(boCrystal);
/*     */       } else {
/* 123 */         this.boCrystalService.update(crystal, crystal, 0, record.getUserId().intValue());
/*     */       }
/*     */ 
/* 126 */       if (boCrystal.getBalance().intValue() > 0)
/* 127 */         enableDevices(record.getUserId().intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   public TradeRecord findByKey(Serializable id)
/*     */   {
/* 134 */     TradeRecord model = (TradeRecord)this.tradeRecordDao.findById(id);
/* 135 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(TradeRecord model)
/*     */   {
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   public TradeRecord update(TradeRecord model)
/*     */   {
/* 145 */     if (chkUpdateValid(model)) {
/* 146 */       this.tradeRecordDao.update(model);
/* 147 */       saveRecharge(model);
/*     */     }
/* 149 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 159 */     if (chkDeleteValid(id))
/* 160 */       this.tradeRecordDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 166 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 167 */     while (st.hasMoreElements()) {
/* 168 */       String id = st.nextToken();
/* 169 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void enableDevices(int userId) {
/* 174 */     List<BoUserDevice> list = this.boUserDeviceService.getListByUserId(userId);
/* 175 */     for (BoUserDevice device : list) {
/* 176 */       String deviceCode = device.getDeviceCode();
/* 177 */       String devId = deviceCode;
/* 178 */       char devType = 'ɀ';
/* 179 */       byte frameType = 2;
/* 180 */       byte[] frameBody = new byte[1];
/* 181 */       frameBody[0] = 1;
/*     */ 
/* 183 */       logger.info("Trying to enable device#" + deviceCode);
/* 184 */       this.boProcessHelper.processSendDData(devId, devType, frameType, frameBody);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.TradeRecordServiceImpl
 * JD-Core Version:    0.6.2
 */