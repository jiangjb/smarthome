/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.MiniBlackDaoIface;
		 import com.smarthome.imcp.dao.model.bo.MiniBlack;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("miniBlackDao")
/*    */ public class MiniBlackDaoImpl extends CommonsDaoImpl<MiniBlack, Serializable>
/*    */   implements MiniBlackDaoIface<MiniBlack, Serializable>
/*    */ {
/*    */   public MiniBlackDaoImpl()
/*    */   {
/* 20 */     super(MiniBlack.class);
/*    */   }
		private static Logger logger = LoggerFactory.getLogger(MiniBlackDaoImpl.class);
	}

