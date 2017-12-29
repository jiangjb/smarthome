/*    */ package com.smarthome.imcp.service.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.dao.cy.CrystalRateDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.CrystalRate;
/*    */ import com.smarthome.imcp.service.cy.CrystalRateServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("crystalRateService")
/*    */ public class CrystalRateServiceImpl
/*    */   implements CrystalRateServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CrystalRateDaoIface<CrystalRate, Serializable> crystalRateDao;
/*    */ 
/*    */   public List<CrystalRate> getList()
/*    */   {
/* 21 */     return this.crystalRateDao.getList();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.CrystalRateServiceImpl
 * JD-Core Version:    0.6.2
 */