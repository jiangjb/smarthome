/*    */ package com.smarthome.imcp.service.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecharge;
/*    */ import com.smarthome.imcp.dao.cy.CrystalRechargeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalRecharge;
/*    */ import com.smarthome.imcp.service.cy.CrystalRechargeServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalRechargeService")
/*    */ public class CrystalRechargeServiceImpl
/*    */   implements CrystalRechargeServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CrystalRechargeDaoIface<CrystalRecharge, Serializable> crystalRechargeDao;
/*    */ 
/*    */   public CrystalRecharge findByOrderNo(String orderNo)
/*    */   {
/* 30 */     return this.crystalRechargeDao.findByOrderNo(orderNo);
/*    */   }
/*    */ 
/*    */   public List<CrystalRecharge> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 35 */     SearchCriteriaRecharge searchCriteriaRecharge = (SearchCriteriaRecharge)searchCriteria;
/* 36 */     return this.crystalRechargeDao.getList(searchCriteriaRecharge, page);
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(CrystalRecharge model)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public CrystalRecharge save(CrystalRecharge model)
/*    */   {
/* 46 */     if (chkSaveValid(model)) {
/* 47 */       this.crystalRechargeDao.save(model);
/*    */     }
/* 49 */     return model;
/*    */   }
/*    */ 
/*    */   public CrystalRecharge findByKey(Serializable id)
/*    */   {
/* 54 */     CrystalRecharge model = (CrystalRecharge)this.crystalRechargeDao.findById(id);
/* 55 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(CrystalRecharge model)
/*    */   {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   public CrystalRecharge update(CrystalRecharge model)
/*    */   {
/* 65 */     if (chkUpdateValid(model)) {
/* 66 */       this.crystalRechargeDao.update(model);
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
/* 79 */       this.crystalRechargeDao.deleteByKey(id);
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
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.CrystalRechargeServiceImpl
 * JD-Core Version:    0.6.2
 */