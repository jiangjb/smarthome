/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoSimplifyDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoSimplify;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boSimplifyDao")
/*    */ public class BoSimplifyDaoImpl extends CommonsDaoImpl<BoSimplify, Serializable>
/*    */   implements BoSimplifyDaoIface<BoSimplify, Serializable>
/*    */ {
/*    */   public BoSimplifyDaoImpl()
/*    */   {
/* 21 */     super(BoSimplify.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoSimplifyDaoImpl
 * JD-Core Version:    0.6.2
 */