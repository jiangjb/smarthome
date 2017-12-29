/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.cy.DailyDeduction;
/*    */ import com.smarthome.imcp.dao.system.SysBatchDaoIface;
/*    */ import com.smarthome.imcp.service.system.SysBatchServiceIface;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysBatchService")
/*    */ public class SysBatchServiceImpl
/*    */   implements SysBatchServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysBatchDaoIface sysBatchDao;
/*    */ 
/*    */   public List<DailyDeduction> getDevicesDailyDeduction(int deviceTypeId, int userId)
/*    */   {
/* 20 */     return this.sysBatchDao.getDevicesDailyDeduction(deviceTypeId, userId);
/*    */   }
/*    */ 
/*    */   public void executePlanRefill()
/*    */   {
/* 25 */     this.sysBatchDao.executePlanRefill();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysBatchServiceImpl
 * JD-Core Version:    0.6.2
 */