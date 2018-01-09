/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.UserRoleDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.UserRoleServiceIface;
		 import com.smarthome.shiro.entity.User_Role;
/*    */ import java.io.Serializable;
		 import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("UserRoleService")
/*    */ public class UserRoleServiceImpl extends AbstractBasicService<User_Role, Serializable>
/*    */   implements UserRoleServiceIface<User_Role, Serializable>
/*    */ {
			@Autowired
	        private UserRoleDaoIface<User_Role, Serializable> userRoleDao;

			@Override
			public List<Integer> findListByUid(int userID) {
				DetachedCriteria criteria = DetachedCriteria.forClass(User_Role.class);
				criteria.add(Restrictions.eq("userId", userID));
				List<User_Role> listRoles = this.userRoleDao.findByCriteria(criteria);
				List<Integer> list=new ArrayList<Integer>();
				if ((listRoles == null) || (listRoles.isEmpty())) {
					return null;
				}else {
					for(int i=0;i<listRoles.size();i++) {
						int roleId=listRoles.get(i).getRole_id();
						list.add(roleId);
					}
				}
				//测试roleId
				for(int i=0;i<list.size();i++) {
					System.out.println("第"+i+"个是："+list.get(i));
				}
				return list;
			}
   	
		}
