/*    */ package com.smarthome.imcp.service.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaTradeCrystal;
/*    */ import com.smarthome.imcp.dao.cy.CrystalTradeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalTrade;
/*    */ import com.smarthome.imcp.service.cy.CrystalTradeServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalTradeService")
/*    */ public class CrystalTradeServiceImpl
/*    */   implements CrystalTradeServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CrystalTradeDaoIface<CrystalTrade, Serializable> crystalTradeDao;
/*    */ 
/*    */   public List<CrystalTrade> getList(Integer userId, Integer deviceId, Integer day, Page page)
/*    */   {
/* 30 */     return this.crystalTradeDao.getList(userId, deviceId, day, page);
/*    */   }
/*    */ 
/*    */   public List<CrystalTrade> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 35 */     SearchCriteriaTradeCrystal searchCriteriaTrade = (SearchCriteriaTradeCrystal)searchCriteria;
/* 36 */     return this.crystalTradeDao.getList(searchCriteriaTrade, page);
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(CrystalTrade model)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public CrystalTrade save(CrystalTrade model)
/*    */   {
/* 46 */     if (chkSaveValid(model)) {
/* 47 */       this.crystalTradeDao.save(model);
/*    */     }
/* 49 */     return model;
/*    */   }
/*    */ 
/*    */   public CrystalTrade findByKey(Serializable id)
/*    */   {
/* 54 */     CrystalTrade model = (CrystalTrade)this.crystalTradeDao.findById(id);
/* 55 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(CrystalTrade model)
/*    */   {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   public CrystalTrade update(CrystalTrade model)
/*    */   {
/* 65 */     if (chkUpdateValid(model)) {
/* 66 */       this.crystalTradeDao.update(model);
/*    */     }
/* 68 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(String id)
/*    */   {
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String id)
/*    */   {
/* 78 */     if (chkDeleteValid(id))
/* 79 */       this.crystalTradeDao.deleteByKey(id);
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String ids)
/*    */   {
/* 85 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 86 */     while (st.hasMoreElements()) {
/* 87 */       String id = st.nextToken();
/* 88 */       deleteByKey(id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.CrystalTradeServiceImpl
 * JD-Core Version:    0.6.2
 */