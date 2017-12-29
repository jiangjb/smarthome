/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoIconLibraryDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoIconLibrary;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boIconLibraryDao")
/*    */ public class BoIconLibraryDaoImpl extends CommonsDaoImpl<BoIconLibrary, Serializable>
/*    */   implements BoIconLibraryDaoIface<BoIconLibrary, Serializable>
/*    */ {
/*    */   public BoIconLibraryDaoImpl()
/*    */   {
/* 22 */     super(BoIconLibrary.class);
/*    */   }
/*    */ 
/*    */   public List<BoIconLibrary> getList()
/*    */   {
/* 29 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIconLibrary.class);
/* 30 */     criteria.add(Restrictions.eq("classify", Integer.valueOf(1)));
/* 31 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoIconLibrary> getList1()
/*    */   {
/* 38 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIconLibrary.class);
/* 39 */     criteria.add(Restrictions.eq("classify", Integer.valueOf(2)));
/* 40 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoIconLibrary> getList2()
/*    */   {
/* 47 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIconLibrary.class);
/* 48 */     criteria.add(Restrictions.eq("classify", Integer.valueOf(3)));
/* 49 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoIconLibrary> getList3()
/*    */   {
/* 56 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoIconLibrary.class);
/* 57 */     criteria.add(Restrictions.eq("classify", Integer.valueOf(4)));
/* 58 */     return findByCriteria(criteria);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoIconLibraryDaoImpl
 * JD-Core Version:    0.6.2
 */