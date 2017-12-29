/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoIosVersionDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoIosVersion;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boIosVersionDao")
/*    */ public class BoIosVersionDaoImpl extends CommonsDaoImpl<BoIosVersion, Serializable>
/*    */   implements BoIosVersionDaoIface<BoIosVersion, Serializable>
/*    */ {
/*    */   public BoIosVersionDaoImpl()
/*    */   {
/* 24 */     super(BoIosVersion.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoIosVersionDaoImpl
 * JD-Core Version:    0.6.2
 */