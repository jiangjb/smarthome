/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.bo.BoErrorreportDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoErrorreport;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoErrorreportServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boErrorreportService")
/*    */ public class BoErrorreportServiceImpl extends AbstractBasicService<BoErrorreport, Serializable>
/*    */   implements BoErrorreportServiceIface<BoErrorreport, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoErrorreportDaoIface<BoErrorreport, Serializable> boErrorreportDao;
/*    */ 
/*    */   public List<BoErrorreport> getAllList(Page page)
/*    */   {
/* 27 */     return this.boErrorreportDao.getAllList(page);
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(BoErrorreport model)
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   public BoErrorreport delete(BoErrorreport model)
/*    */   {
/* 39 */     if (chkUpdateValid(model)) {
/* 40 */       this.boErrorreportDao.delete(model);
/*    */     }
/* 42 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(BoErrorreport model)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public BoErrorreport save(BoErrorreport model)
/*    */   {
/* 55 */     if (chkSaveValid(model))
/*    */     {
/* 57 */       this.boErrorreportDao.save(model);
/*    */     }
/* 59 */     return model;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoErrorreportServiceImpl
 * JD-Core Version:    0.6.2
 */