/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUserDevicesDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUserDevices;
/*    */ import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("BoUserDevicesDao")
/*    */ public class BoUserDevicesDaoImpl extends CommonsDaoImpl<BoUserDevices, Serializable>
/*    */   implements BoUserDevicesDaoIface<BoUserDevices, Serializable>
/*    */ {
/*    */   public BoUserDevicesDaoImpl()
/*    */   {
/* 18 */     super(BoUserDevices.class);
/*    */   }

		  @Override
		  public List<BoUserDevices> find() {
			  DetachedCriteria criteria = DetachedCriteria.forClass(BoUserDevices.class);
			  return findByCriteria(criteria);
		  }

/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUserDevicesDaoImpl
 * JD-Core Version:    0.6.2
 */