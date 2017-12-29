/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPurchaseRequest;
/*    */ import com.smarthome.imcp.dao.cy.PurchaseRequestDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.PurchaseRequest;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("purchaseRequestDao")
/*    */ public class PurchaseRequestDaoImpl extends CommonsDaoImpl<PurchaseRequest, Serializable>
/*    */   implements PurchaseRequestDaoIface<PurchaseRequest, Serializable>
/*    */ {
/*    */   public PurchaseRequestDaoImpl()
/*    */   {
/* 21 */     super(PurchaseRequest.class);
/*    */   }
/*    */ 
/*    */   public List<PurchaseRequest> getList(SearchCriteriaPurchaseRequest searchCriteriaPurchaseRequest, Page page)
/*    */   {
/* 27 */     DetachedCriteria criteria = DetachedCriteria.forClass(PurchaseRequest.class);
/* 28 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaPurchaseRequest)) {
/* 29 */       criteria.add(Restrictions.eq("completed", Boolean.valueOf(searchCriteriaPurchaseRequest.getCompleted())));
/*    */     }
/* 31 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.PurchaseRequestDaoImpl
 * JD-Core Version:    0.6.2
 */