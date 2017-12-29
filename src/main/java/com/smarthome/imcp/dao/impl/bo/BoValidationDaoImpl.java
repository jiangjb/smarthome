/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoValidationDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoValidation;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boValidationDao")
/*    */ public class BoValidationDaoImpl extends CommonsDaoImpl<BoValidation, Serializable>
/*    */   implements BoValidationDaoIface<BoValidation, Serializable>
/*    */ {
/*    */   public BoValidationDaoImpl()
/*    */   {
/* 18 */     super(BoValidation.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoValidationDaoImpl
 * JD-Core Version:    0.6.2
 */