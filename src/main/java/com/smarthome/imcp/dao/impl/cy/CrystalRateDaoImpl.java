/*    */ package com.smarthome.imcp.dao.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.cy.CrystalRateDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalRate;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalRateDao")
/*    */ public class CrystalRateDaoImpl extends CommonsDaoImpl<CrystalRate, Serializable>
/*    */   implements CrystalRateDaoIface<CrystalRate, Serializable>
/*    */ {
/*    */   public CrystalRateDaoImpl()
/*    */   {
/* 20 */     super(CrystalRate.class);
/*    */   }
/*    */ 
/*    */   public List<CrystalRate> getList()
/*    */   {
/* 25 */     Query query = getCurrentSession().getNamedQuery("GetCrystalRates");
/* 26 */     List list = query.list();
/*    */ 
/* 28 */     return list;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.CrystalRateDaoImpl
 * JD-Core Version:    0.6.2
 */