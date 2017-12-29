/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAlarmRecordDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAlarmRecord;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAlarmRecordDao")
/*    */ public class BoAlarmRecordDaoImpl extends CommonsDaoImpl<BoAlarmRecord, Serializable>
/*    */   implements BoAlarmRecordDaoIface<BoAlarmRecord, Serializable>
/*    */ {
/*    */   public BoAlarmRecordDaoImpl()
/*    */   {
/* 20 */     super(BoAlarmRecord.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAlarmRecordDaoImpl
 * JD-Core Version:    0.6.2
 */