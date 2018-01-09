/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.RolePermissionDaoIface;
		 import com.smarthome.shiro.entity.Role_Permission;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("rolePermissionDao")
/*    */ public class RolePermissionDaoImpl extends CommonsDaoImpl<Role_Permission, Serializable>
/*    */   implements RolePermissionDaoIface<Role_Permission, Serializable>
/*    */ {
/*    */   public RolePermissionDaoImpl()
/*    */   {
/* 20 */     super(Role_Permission.class);
/*    */   }


		}

