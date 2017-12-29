/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.bo.BoDevicehostinfoDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoDevicehostinfo;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoDevicehostinfoServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boDevicehostinfoService")
/*    */ public class BoDevicehostinfoServiceImpl extends AbstractBasicService<BoDevicehostinfo, Serializable>
/*    */   implements BoDevicehostinfoServiceIface<BoDevicehostinfo, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoDevicehostinfoDaoIface<BoDevicehostinfo, Serializable> boDevicehostinfoDao;
/*    */ 
/*    */   public BoDevicehostinfo findByCode(String deviceCode)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevicehostinfo.class);
/* 33 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/* 34 */     List list = this.boDevicehostinfoDao.findByCriteria(criteria);
/* 35 */     if ((list == null) || (list.isEmpty())) {
/* 36 */       return null;
/*    */     }
/* 38 */     return (BoDevicehostinfo)list.get(0);
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(BoDevicehostinfo model)
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   public BoDevicehostinfo save(BoDevicehostinfo model)
/*    */   {
/* 50 */     if (chkSaveValid(model))
/*    */     {
/* 52 */       this.boDevicehostinfoDao.save(model);
/*    */     }
/* 54 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(BoDevicehostinfo model)
/*    */   {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   public BoDevicehostinfo update(BoDevicehostinfo model)
/*    */   {
/* 65 */     if (chkUpdateValid(model)) {
/* 66 */       this.boDevicehostinfoDao.update(model);
/*    */     }
/* 68 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(BoDevicehostinfo model)
/*    */   {
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   public BoDevicehostinfo delete(BoDevicehostinfo model)
/*    */   {
/* 80 */     if (chkUpdateValid(model)) {
/* 81 */       this.boDevicehostinfoDao.delete(model);
/*    */     }
/* 83 */     return model;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoDevicehostinfoServiceImpl
 * JD-Core Version:    0.6.2
 */