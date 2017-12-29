/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaProduct;
/*    */ import com.smarthome.imcp.dao.cy.ProductDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.Product;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("productDao")
/*    */ public class ProductDaoImpl extends CommonsDaoImpl<Product, Serializable>
/*    */   implements ProductDaoIface<Product, Serializable>
/*    */ {
/*    */   public ProductDaoImpl()
/*    */   {
/* 23 */     super(Product.class);
/*    */   }
/*    */ 
/*    */   public List<Product> getList(SearchCriteriaProduct searchCriteriaoProduct, Page page)
/*    */   {
/* 29 */     DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
/* 30 */     GlobalMethod.isNullorEmpty(searchCriteriaoProduct);
/*    */ 
/* 32 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 33 */       if (page.isAsc())
/* 34 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("id"));
/*    */       else {
/* 36 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 39 */     else if (page.isAsc())
/* 40 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 42 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 45 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.ProductDaoImpl
 * JD-Core Version:    0.6.2
 */