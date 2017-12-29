/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoAndroidVersionDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAndroidVersion;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boAndroidVersionDao")
/*    */ public class BoAndroidVersionDaoImpl extends CommonsDaoImpl<BoAndroidVersion, Serializable>
/*    */   implements BoAndroidVersionDaoIface<BoAndroidVersion, Serializable>
/*    */ {
/*    */   public BoAndroidVersionDaoImpl()
/*    */   {
/* 20 */     super(BoAndroidVersion.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoAndroidVersionDaoImpl
 * JD-Core Version:    0.6.2
 */