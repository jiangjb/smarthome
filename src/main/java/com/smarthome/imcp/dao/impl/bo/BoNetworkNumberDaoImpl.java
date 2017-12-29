/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoNetworkNumberDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoNetworkNumber;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boNetworkNumberDao")
/*    */ public class BoNetworkNumberDaoImpl extends CommonsDaoImpl<BoNetworkNumber, Serializable>
/*    */   implements BoNetworkNumberDaoIface<BoNetworkNumber, Serializable>
/*    */ {
/*    */   public BoNetworkNumberDaoImpl()
/*    */   {
/* 20 */     super(BoNetworkNumber.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoNetworkNumberDaoImpl
 * JD-Core Version:    0.6.2
 */