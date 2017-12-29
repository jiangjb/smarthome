/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoSensorDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoSensor;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boSensorDao")
/*    */ public class BoSensorDaoImpl extends CommonsDaoImpl<BoSensor, Serializable>
/*    */   implements BoSensorDaoIface<BoSensor, Serializable>
/*    */ {
/*    */   public BoSensorDaoImpl()
/*    */   {
/* 18 */     super(BoSensor.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoSensorDaoImpl
 * JD-Core Version:    0.6.2
 */