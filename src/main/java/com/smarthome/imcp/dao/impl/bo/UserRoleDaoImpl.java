/*    */ package com.smarthome.imcp.dao.impl.bo;

/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.UserRoleDaoIface;
		 import com.smarthome.shiro.entity.User_Role;
/*    */ import java.io.Serializable;
/*    */ import org.springframework.stereotype.Service;

/*    */ @Service("userRoleDao")
/*    */ public class UserRoleDaoImpl extends CommonsDaoImpl<User_Role, Serializable>
/*    */   implements UserRoleDaoIface<User_Role, Serializable>
/*    */ {
/*    */   public UserRoleDaoImpl()
/*    */   {
/* 20 */     super(User_Role.class);
/*    */   }


		}

