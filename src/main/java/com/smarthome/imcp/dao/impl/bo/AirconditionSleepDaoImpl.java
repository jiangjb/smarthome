/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.AirconditionSleepDaoIface;
		 import com.smarthome.imcp.dao.model.bo.AirconditionSleep;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("airconditionSleepDao")
/*    */ public class AirconditionSleepDaoImpl extends CommonsDaoImpl<AirconditionSleep, Serializable>
/*    */   implements AirconditionSleepDaoIface<AirconditionSleep, Serializable>
/*    */ {
/*    */   public AirconditionSleepDaoImpl()
/*    */   {
/* 20 */     super(AirconditionSleep.class);
/*    */   }


		}

