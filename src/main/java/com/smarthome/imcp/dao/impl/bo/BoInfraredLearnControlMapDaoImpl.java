/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoInfraredLearnControlMapDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boInfraredLearnControlMapDao")
/*    */ public class BoInfraredLearnControlMapDaoImpl extends CommonsDaoImpl<BoInfraredLearnControlMap, Serializable>
/*    */   implements BoInfraredLearnControlMapDaoIface<BoInfraredLearnControlMap, Serializable>
/*    */ {
/*    */   public BoInfraredLearnControlMapDaoImpl()
/*    */   {
/* 20 */     super(BoInfraredLearnControlMap.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoInfraredLearnControlMapDaoImpl
 * JD-Core Version:    0.6.2
 */