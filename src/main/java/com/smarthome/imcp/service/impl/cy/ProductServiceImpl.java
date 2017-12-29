/*     */ package com.smarthome.imcp.service.impl.cy;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaProduct;
/*     */ import com.smarthome.imcp.dao.cy.ProductDaoIface;
/*     */ import com.smarthome.imcp.dao.model.cy.Product;
/*     */ import com.smarthome.imcp.dao.vo.system.FileVo;
/*     */ import com.smarthome.imcp.service.cy.ProductServiceIface;
/*     */ import com.smarthome.imcp.service.system.FileServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("productService")
/*     */ public class ProductServiceImpl
/*     */   implements ProductServiceIface
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ProductDaoIface<Product, Serializable> productDao;
/*     */ 
/*     */   @Autowired
/*     */   private FileServiceIface<Object, Serializable> fileService;
/*     */ 
/*     */   public List<Product> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  37 */     SearchCriteriaProduct searchCriteriaOProduct = (SearchCriteriaProduct)searchCriteria;
/*  38 */     return this.productDao.getList(searchCriteriaOProduct, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(Product model)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public Product save(Product model)
/*     */   {
/*  48 */     if (!chkSaveValid(model)) {
/*  49 */       return model;
/*     */     }
/*     */ 
/*  52 */     saveFile(model);
/*  53 */     this.productDao.save(model);
/*     */ 
/*  55 */     return model;
/*     */   }
/*     */ 
/*     */   private String saveFile(Product model) {
/*  59 */     String img = model.getImg();
/*  60 */     if (StringUtils.isNotEmpty(img)) {
/*  61 */       FileVo fileVo = this.fileService.copyTempFileToDir(img, "uploads/images", ContextUtil.getSessionId());
/*  62 */       model.setImg(fileVo.getPathName());
/*     */     }
/*  64 */     this.fileService.deleteTempDirectory(ContextUtil.getSessionId());
/*  65 */     return img;
/*     */   }
/*     */ 
/*     */   public Product findByKey(Serializable id)
/*     */   {
/*  70 */     Product model = (Product)this.productDao.findById(id);
/*  71 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(Product model)
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   public Product update(Product model)
/*     */   {
/*  81 */     if (chkUpdateValid(model)) {
/*  82 */       this.productDao.update(model);
/*     */     }
/*  84 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  94 */     if (chkDeleteValid(id))
/*  95 */       this.productDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 101 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 102 */     while (st.hasMoreElements()) {
/* 103 */       String id = st.nextToken();
/* 104 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.ProductServiceImpl
 * JD-Core Version:    0.6.2
 */