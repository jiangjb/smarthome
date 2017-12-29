/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAirTimingPerformDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAirTimingPerform;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAirTimingPerformDao")
/*    */ public class BoAirTimingPerformDaoImpl extends CommonsDaoImpl<BoAirTimingPerform, Serializable>
/*    */   implements BoAirTimingPerformDaoIface<BoAirTimingPerform, Serializable>
/*    */ {
/*    */   public BoAirTimingPerformDaoImpl()
/*    */   {
/* 20 */     super(BoAirTimingPerform.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAirTimingPerformDaoImpl
 * JD-Core Version:    0.6.2
 */