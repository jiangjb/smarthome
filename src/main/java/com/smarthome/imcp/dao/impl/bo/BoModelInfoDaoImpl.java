/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoModelInfoDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoModelInfo;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boModelInfoDao")
/*    */ public class BoModelInfoDaoImpl extends CommonsDaoImpl<BoModelInfo, Serializable>
/*    */   implements BoModelInfoDaoIface<BoModelInfo, Serializable>
/*    */ {
/*    */   public BoModelInfoDaoImpl()
/*    */   {
/* 18 */     super(BoModelInfo.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoModelInfoDaoImpl
 * JD-Core Version:    0.6.2
 */