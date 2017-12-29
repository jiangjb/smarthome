/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoInfraredButtonsDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoInfraredButtons;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boInfraredButtonsDao")
/*    */ public class BoInfraredButtonsDaoImpl extends CommonsDaoImpl<BoInfraredButtons, Serializable>
/*    */   implements BoInfraredButtonsDaoIface<BoInfraredButtons, Serializable>
/*    */ {
/*    */   public BoInfraredButtonsDaoImpl()
/*    */   {
/* 18 */     super(BoInfraredButtons.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoInfraredButtonsDaoImpl
 * JD-Core Version:    0.6.2
 */