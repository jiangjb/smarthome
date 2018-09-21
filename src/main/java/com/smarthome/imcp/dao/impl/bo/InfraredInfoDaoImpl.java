/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.InfraredInfoDaoIface;
		 import com.smarthome.imcp.dao.model.bo.InfraredInfo;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("infraredInfoDao")
/*    */ public class InfraredInfoDaoImpl extends CommonsDaoImpl<InfraredInfo, Serializable>
/*    */   implements InfraredInfoDaoIface<InfraredInfo, Serializable>
/*    */ {
/*    */   public InfraredInfoDaoImpl()
/*    */   {
/* 20 */     super(InfraredInfo.class);
/*    */   }


		}

