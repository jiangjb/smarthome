/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoHostDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoHost;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boHostDao")
/*    */ public class BoHostDaoImpl extends CommonsDaoImpl<BoHost, Serializable>
/*    */   implements BoHostDaoIface<BoHost, Serializable>
/*    */ {
/*    */   public BoHostDaoImpl()
/*    */   {
/* 21 */     super(BoHost.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoHostDaoImpl
 * JD-Core Version:    0.6.2
 */