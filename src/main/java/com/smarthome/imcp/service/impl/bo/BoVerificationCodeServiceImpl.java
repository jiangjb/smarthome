/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.bo.BoVerificationCodeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoVerificationCode;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoVerificationCodeServiceIface;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boVerificationCodeService")
/*    */ public class BoVerificationCodeServiceImpl extends AbstractBasicService<BoVerificationCode, Serializable>
/*    */   implements BoVerificationCodeServiceIface<BoVerificationCode, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoVerificationCodeDaoIface<BoVerificationCode, Serializable> boVerificationDao;
/*    */ 
/*    */   public BoVerificationCode findByKey(Serializable id)
/*    */   {
/* 23 */     return (BoVerificationCode)this.boVerificationDao.findById(id);
/*    */   }
/*    */ 
/*    */   public BoVerificationCode saveOrUpdate(BoVerificationCode model)
/*    */   {
/* 28 */     this.boVerificationDao.saveOrUpdate(model);
/* 29 */     return model;
/*    */   }
/*    */ 
/*    */   public BoVerificationCode findByKey(String userPhone)
/*    */   {
/* 34 */     return (BoVerificationCode)this.boVerificationDao.findById(userPhone);
/*    */   }
/*    */ 
/*    */   public void executeVerificationRecycle(int timeout)
/*    */   {
/* 39 */     this.boVerificationDao.executeVerificationCodeRecycle(timeout);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoVerificationCodeServiceImpl
 * JD-Core Version:    0.6.2
 */