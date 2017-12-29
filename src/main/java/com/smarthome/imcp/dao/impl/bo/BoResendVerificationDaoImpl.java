/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoResendVerificationDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoResendVerification;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boResendVerificationDao")
/*    */ public class BoResendVerificationDaoImpl extends CommonsDaoImpl<BoResendVerification, Serializable>
/*    */   implements BoResendVerificationDaoIface<BoResendVerification, Serializable>
/*    */ {
/*    */   public BoResendVerificationDaoImpl()
/*    */   {
/* 20 */     super(BoResendVerification.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoResendVerificationDaoImpl
 * JD-Core Version:    0.6.2
 */