/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoShoppingCartDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoShoppingCart;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boShoppingCartDao")
/*    */ public class BoShoppingCartDaoImpl extends CommonsDaoImpl<BoShoppingCart, Serializable>
/*    */   implements BoShoppingCartDaoIface<BoShoppingCart, Serializable>
/*    */ {
/*    */   public BoShoppingCartDaoImpl()
/*    */   {
/* 18 */     super(BoShoppingCart.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoShoppingCartDaoImpl
 * JD-Core Version:    0.6.2
 */