/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.PermissionDaoIface;
		 import com.smarthome.shiro.entity.Permission;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("permissionDao")
/*    */ public class PermissionDaoImpl extends CommonsDaoImpl<Permission, Serializable>
/*    */   implements PermissionDaoIface<Permission, Serializable>
/*    */ {
/*    */   public PermissionDaoImpl()
/*    */   {
/* 20 */     super(Permission.class);
/*    */   }


		}

