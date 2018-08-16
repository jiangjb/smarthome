/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.InfraredTimerDaoIface;
		 import com.smarthome.imcp.dao.model.bo.InfraredTimer;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("infraredTimerDao")
/*    */ public class InfraredTimerDaoImpl extends CommonsDaoImpl<InfraredTimer, Serializable>
/*    */   implements InfraredTimerDaoIface<InfraredTimer, Serializable>
/*    */ {
/*    */   public InfraredTimerDaoImpl()
/*    */   {
/* 20 */     super(InfraredTimer.class);
/*    */   }


		}

