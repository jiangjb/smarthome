/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.RemoteControlDaoIface;
import com.smarthome.imcp.dao.model.bo.MiniBlack;
import com.smarthome.imcp.dao.model.bo.RemoteControl;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
		 import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("remoteControlDao")
/*    */ public class RemoteControlDaoImpl extends CommonsDaoImpl<RemoteControl, Serializable>
/*    */   implements RemoteControlDaoIface<RemoteControl, Serializable>
/*    */ {
/*    */   public RemoteControlDaoImpl()
/*    */   {
/* 20 */     super(RemoteControl.class);
/*    */   }
			private static Logger logger = LoggerFactory.getLogger(RemoteControlDaoImpl.class);
			
			
	}

