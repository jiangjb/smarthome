/*    */ package com.smarthome.imcp.service.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPurchaseRequest;
/*    */ import com.smarthome.imcp.dao.cy.PurchaseRequestDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.PurchaseRequest;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.cy.PurchaseRequestServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("purchaseRequestService")
/*    */ public class PurchaseRequestServiceImpl extends AbstractBasicService<PurchaseRequest, Serializable>
/*    */   implements PurchaseRequestServiceIface<PurchaseRequest, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private PurchaseRequestDaoIface<PurchaseRequest, Serializable> purchaseRequestDao;
/*    */ 
/*    */   public List<PurchaseRequest> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 27 */     SearchCriteriaPurchaseRequest searchCriteriaPurchaseRequest = (SearchCriteriaPurchaseRequest)searchCriteria;
/* 28 */     return this.purchaseRequestDao.getList(searchCriteriaPurchaseRequest, page);
/*    */   }
/*    */ 
/*    */   public PurchaseRequest save(PurchaseRequest model)
/*    */   {
/* 33 */     this.purchaseRequestDao.save(model);
/*    */ 
/* 35 */     return model;
/*    */   }
/*    */ 
/*    */   public PurchaseRequest findByKey(Serializable id)
/*    */   {
/* 40 */     return (PurchaseRequest)this.purchaseRequestDao.findById(id);
/*    */   }
/*    */ 
/*    */   public PurchaseRequest update(PurchaseRequest model)
/*    */   {
/* 45 */     this.purchaseRequestDao.update(model);
/* 46 */     return model;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.PurchaseRequestServiceImpl
 * JD-Core Version:    0.6.2
 */