/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoLockVerdictDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boLockVerdictDao")
/*    */ public class BoLockVerdictDaoImpl extends CommonsDaoImpl<BoLockVerdict, Serializable>
/*    */   implements BoLockVerdictDaoIface<BoLockVerdict, Serializable>
/*    */ {
/*    */   public BoLockVerdictDaoImpl()
/*    */   {
/* 24 */     super(BoLockVerdict.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoLockVerdictDaoImpl
 * JD-Core Version:    0.6.2
 */