/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoModelDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoModel;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boModelDao")
/*    */ public class BoModelDaoImpl extends CommonsDaoImpl<BoModel, Serializable>
/*    */   implements BoModelDaoIface<BoModel, Serializable>
/*    */ {
/*    */   public BoModelDaoImpl()
/*    */   {
/* 18 */     super(BoModel.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoModelDaoImpl
 * JD-Core Version:    0.6.2
 */