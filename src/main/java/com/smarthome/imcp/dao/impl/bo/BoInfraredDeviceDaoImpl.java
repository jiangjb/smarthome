/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoInfraredDeviceDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoInfraredDevice;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boInfraredDeviceDao")
/*    */ public class BoInfraredDeviceDaoImpl extends CommonsDaoImpl<BoInfraredDevice, Serializable>
/*    */   implements BoInfraredDeviceDaoIface<BoInfraredDevice, Serializable>
/*    */ {
/*    */   public BoInfraredDeviceDaoImpl()
/*    */   {
/* 18 */     super(BoInfraredDevice.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoInfraredDeviceDaoImpl
 * JD-Core Version:    0.6.2
 */