/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoFeedBackDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFeedBack;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFeedBackDao")
/*    */ public class BoFeedBackDaoImpl extends CommonsDaoImpl<BoFeedBack, Serializable>
/*    */   implements BoFeedBackDaoIface<BoFeedBack, Serializable>
/*    */ {
/*    */   public BoFeedBackDaoImpl()
/*    */   {
/* 17 */     super(BoFeedBack.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoFeedBackDaoImpl
 * JD-Core Version:    0.6.2
 */