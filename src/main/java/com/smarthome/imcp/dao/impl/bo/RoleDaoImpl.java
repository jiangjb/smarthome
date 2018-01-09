/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.RoleDaoIface;
		 import com.smarthome.shiro.entity.Role;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("roleDao")
/*    */ public class RoleDaoImpl extends CommonsDaoImpl<Role, Serializable>
/*    */   implements RoleDaoIface<Role, Serializable>
/*    */ {
/*    */   public RoleDaoImpl()
/*    */   {
/* 20 */     super(Role.class);
/*    */   }


		}

