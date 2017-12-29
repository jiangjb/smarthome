/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoDevicehostinfoDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDevicehostinfo;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDevicehostinfoDao")
/*    */ public class BoDevicehostinfoDaoImpl extends CommonsDaoImpl<BoDevicehostinfo, Serializable>
/*    */   implements BoDevicehostinfoDaoIface<BoDevicehostinfo, Serializable>
/*    */ {
/*    */   public BoDevicehostinfoDaoImpl()
/*    */   {
/* 21 */     super(BoDevicehostinfo.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoDevicehostinfoDaoImpl
 * JD-Core Version:    0.6.2
 */