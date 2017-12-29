/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoOrderDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoOrder;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boOrderDao")
/*    */ public class BoOrderDaoImpl extends CommonsDaoImpl<BoOrder, Serializable>
/*    */   implements BoOrderDaoIface<BoOrder, Serializable>
/*    */ {
/*    */   public BoOrderDaoImpl()
/*    */   {
/* 18 */     super(BoOrder.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoOrderDaoImpl
 * JD-Core Version:    0.6.2
 */