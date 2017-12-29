/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUnlockingPushRecordDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUnlockingPushRecordDao")
/*    */ public class BoUnlockingPushRecordDaoImpl extends CommonsDaoImpl<BoUnlockingPushRecord, Serializable>
/*    */   implements BoUnlockingPushRecordDaoIface<BoUnlockingPushRecord, Serializable>
/*    */ {
/*    */   public BoUnlockingPushRecordDaoImpl()
/*    */   {
/* 24 */     super(BoUnlockingPushRecord.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUnlockingPushRecordDaoImpl
 * JD-Core Version:    0.6.2
 */