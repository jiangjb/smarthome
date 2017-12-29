/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoControlEnclosureDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoControlEnclosure;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boControlEnclosureDao")
/*    */ public class BoControlEnclosureDaoImpl extends CommonsDaoImpl<BoControlEnclosure, Serializable>
/*    */   implements BoControlEnclosureDaoIface<BoControlEnclosure, Serializable>
/*    */ {
/*    */   public BoControlEnclosureDaoImpl()
/*    */   {
/* 20 */     super(BoControlEnclosure.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoControlEnclosureDaoImpl
 * JD-Core Version:    0.6.2
 */