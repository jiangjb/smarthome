/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.bo.BoFeedBackDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFeedBack;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoFeedBackServiceIface;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFeedBackService")
/*    */ public class BoFeedBackServiceImpl extends AbstractBasicService<BoFeedBack, Serializable>
/*    */   implements BoFeedBackServiceIface<BoFeedBack, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoFeedBackDaoIface<BoFeedBack, Serializable> boFeedBackDao;
/*    */ 
/*    */   public boolean chkSaveValid(BoFeedBack model)
/*    */   {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFeedBack save(BoFeedBack model)
/*    */   {
/* 40 */     if (chkSaveValid(model))
/*    */     {
/* 42 */       this.boFeedBackDao.save(model);
/*    */     }
/* 44 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(BoFeedBack model)
/*    */   {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFeedBack update(BoFeedBack model)
/*    */   {
/* 55 */     if (chkUpdateValid(model)) {
/* 56 */       this.boFeedBackDao.update(model);
/*    */     }
/* 58 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(BoFeedBack model)
/*    */   {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   public BoFeedBack delete(BoFeedBack model)
/*    */   {
/* 70 */     if (chkUpdateValid(model)) {
/* 71 */       this.boFeedBackDao.delete(model);
/*    */     }
/* 73 */     return model;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoFeedBackServiceImpl
 * JD-Core Version:    0.6.2
 */