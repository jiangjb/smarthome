/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.RolePermissionDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.RolePermissionServiceIface;
		 import com.smarthome.shiro.entity.Role_Permission;
/*    */ import java.io.Serializable;
		 import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("RolePermissionService")
/*    */ public class RolePermissionServiceImpl extends AbstractBasicService<Role_Permission, Serializable>
/*    */   implements RolePermissionServiceIface<Role_Permission, Serializable>
/*    */ {
			@Autowired
	        private RolePermissionDaoIface<Role_Permission, Serializable> rolePermissionDao;

			@Override
			public List<Integer> findListByRids(List<Integer> roleIds) {
				List<Integer> list=new ArrayList<Integer>();
				DetachedCriteria criteria = DetachedCriteria.forClass(Role_Permission.class);
				for(int i=0;i<roleIds.size();i++) {
					int roleId=roleIds.get(i);
//					System.out.println("roleId:"+roleId);  //测试无误  root 对应 1
					criteria.add(Restrictions.eq("role_id", roleId));
					List<Role_Permission> list01 = this.rolePermissionDao.findByCriteria(criteria);
					System.out.println("list01:"+list01);//null
					if(list01 != null) {
						for(int j=0;j<list01.size();j++) {
							int permissionId=list01.get(j).getPermission_id();
							System.out.println("permissionId:"+permissionId);
							list.add(permissionId);
						}	
					}
				} 
			    //测试
				for(int i=0;i<list.size();i++) {
					System.out.println("第"+i+"个角色名称是："+list.get(i));
				}
			    if ((list == null) || (list.isEmpty())) {
			       return null;
			    }
				return list;
			}
      	
		}
