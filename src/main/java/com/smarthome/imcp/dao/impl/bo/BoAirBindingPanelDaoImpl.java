/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAirBindingPanelDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAirBindingPanelDao")
/*    */ public class BoAirBindingPanelDaoImpl extends CommonsDaoImpl<BoAirBindingPanel, Serializable>
/*    */   implements BoAirBindingPanelDaoIface<BoAirBindingPanel, Serializable>
/*    */ {
/*    */   public BoAirBindingPanelDaoImpl()
/*    */   {
/* 20 */     super(BoAirBindingPanel.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAirBindingPanelDaoImpl
 * JD-Core Version:    0.6.2
 */