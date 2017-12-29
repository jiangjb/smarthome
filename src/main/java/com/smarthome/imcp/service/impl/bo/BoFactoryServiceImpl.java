/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.bo.BoFactoryDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFactory;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFactory;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoFactoryServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFactoryService")
/*    */ public class BoFactoryServiceImpl extends AbstractBasicService<BoFactory, Serializable>
/*    */   implements BoFactoryServiceIface<BoFactory, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoFactoryDaoIface<BoFactory, Serializable> boFactoryDao;
/*    */ 
/*    */   public List<BoFactory> getAllList()
/*    */   {
/* 31 */     return this.boFactoryDao.getAllList();
/*    */   }
/*    */ 
/*    */   public List<BoFactory> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 36 */     SearchCriteriaFactory searchCriteriaFactory = (SearchCriteriaFactory)searchCriteria;
/* 37 */     if (chkCriteriaValid(searchCriteria)) {
/* 38 */       return this.boFactoryDao.getList(searchCriteriaFactory, page);
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(BoFactory model)
/*    */   {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFactory save(BoFactory model)
/*    */   {
/* 50 */     if (chkSaveValid(model)) {
/* 51 */       this.boFactoryDao.save(model);
/*    */     }
/* 53 */     return model;
/*    */   }
/*    */ 
/*    */   public BoFactory findByKey(Serializable id)
/*    */   {
/* 58 */     BoFactory model = (BoFactory)this.boFactoryDao.findById(id);
/* 59 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(BoFactory model)
/*    */   {
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFactory update(BoFactory model)
/*    */   {
/* 69 */     if (chkUpdateValid(model)) {
/* 70 */       this.boFactoryDao.update(model);
/*    */     }
/* 72 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(String id)
/*    */   {
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String id)
/*    */   {
/* 82 */     if ((id == null) || ("".equals(id)))
/* 83 */       return;
/* 84 */     if (chkDeleteValid(id))
/* 85 */       this.boFactoryDao.deleteLogicByKey(Integer.valueOf(id));
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String ids)
/*    */   {
/* 91 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 92 */     while (st.hasMoreElements()) {
/* 93 */       String id = st.nextToken();
/* 94 */       deleteByKey(id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoFactoryServiceImpl
 * JD-Core Version:    0.6.2
 */