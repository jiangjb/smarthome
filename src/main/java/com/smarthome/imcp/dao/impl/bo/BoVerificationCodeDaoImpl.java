/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoVerificationCodeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoVerificationCode;
/*    */ import java.io.Serializable;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boVerificationDao")
/*    */ public class BoVerificationCodeDaoImpl extends CommonsDaoImpl<BoVerificationCode, Serializable>
/*    */   implements BoVerificationCodeDaoIface<BoVerificationCode, Serializable>
/*    */ {
/*    */   public BoVerificationCodeDaoImpl()
/*    */   {
/* 17 */     super(BoVerificationCode.class);
/*    */   }
/*    */ 
/*    */   public void executeVerificationCodeRecycle(int timeout)
/*    */   {
/* 22 */     Query query = getCurrentSession().createSQLQuery("{call sp_executeVerificationCodeRecycle(?)}");
/* 23 */     query.setParameter(0, Integer.valueOf(timeout));
/*    */ 
/* 25 */     query.executeUpdate();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoVerificationCodeDaoImpl
 * JD-Core Version:    0.6.2
 */