/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoErrorreportDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoErrorreport;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boErrorreportDao")
/*    */ public class BoErrorreportDaoImpl extends CommonsDaoImpl<BoErrorreport, Serializable>
/*    */   implements BoErrorreportDaoIface<BoErrorreport, Serializable>
/*    */ {
/*    */   public BoErrorreportDaoImpl()
/*    */   {
/* 22 */     super(BoErrorreport.class);
/*    */   }
/*    */ 
/*    */   public List<BoErrorreport> getAllList(Page page)
/*    */   {
/* 28 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoErrorreport.class);
/* 29 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoErrorreportDaoImpl
 * JD-Core Version:    0.6.2
 */