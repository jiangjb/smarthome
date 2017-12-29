/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoLockPasswordManageDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boLockPasswordManageDao")
/*    */ public class BoLockPasswordManageDaoImpl extends CommonsDaoImpl<BoLockPasswordManage, Serializable>
/*    */   implements BoLockPasswordManageDaoIface<BoLockPasswordManage, Serializable>
/*    */ {
/*    */   public BoLockPasswordManageDaoImpl()
/*    */   {
/* 20 */     super(BoLockPasswordManage.class);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoLockPasswordManageDaoImpl
 * JD-Core Version:    0.6.2
 */