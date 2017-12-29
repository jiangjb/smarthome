/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.bo.BoMetadataDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.model.bo.BoMetadata;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoMetadataServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boMetadataService")
/*    */ public class BoMetadataServiceImpl extends AbstractBasicService<BoMetadata, Serializable>
/*    */   implements BoMetadataServiceIface<BoMetadata, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoMetadataDaoIface<BoMetadata, Serializable> boMetadataDao;
/*    */ 
/*    */   public List<BoMetadata> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 33 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoMetadata.class);
/* 34 */     GlobalMethod.isNullorEmpty(searchCriteria);
/*    */ 
/* 36 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 37 */       if (page.isAsc())
/* 38 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 40 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 43 */     else if (page.isAsc())
/* 44 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 46 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 49 */     return this.boMetadataDao.findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public BoMetadata update(BoMetadata model)
/*    */   {
/* 54 */     this.boMetadataDao.update(model);
/* 55 */     return model;
/*    */   }
/*    */ 
/*    */   public BoMetadata findByKey(Serializable id)
/*    */   {
/* 60 */     BoMetadata model = (BoMetadata)this.boMetadataDao.findById(id);
/* 61 */     return model;
/*    */   }
/*    */ 
/*    */   public BoMetadata getMetadataByKeyName(String key)
/*    */   {
/* 66 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoMetadata.class);
/* 67 */     criteria.add(Restrictions.eq("keyName", key));
/*    */ 
/* 69 */     List list = this.boMetadataDao.findByCriteria(criteria);
/* 70 */     if ((list != null) && (list.size() > 0)) {
/* 71 */       return (BoMetadata)list.get(0);
/*    */     }
/* 73 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoMetadataServiceImpl
 * JD-Core Version:    0.6.2
 */