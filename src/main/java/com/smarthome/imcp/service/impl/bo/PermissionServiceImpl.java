/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.PermissionDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.PermissionServiceIface;
		 import com.smarthome.shiro.entity.Permission;
/*    */ import java.io.Serializable;
		 import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("PermissionService")
/*    */ public class PermissionServiceImpl extends AbstractBasicService<Permission, Serializable>
/*    */   implements PermissionServiceIface<Permission, Serializable>
/*    */ {
			@Autowired
	        private PermissionDaoIface<Permission, Serializable> permissionDao;

			@Override
			public List<Permission> getListByPid(List<Integer> permissionIds) {
				List<Permission> list=new ArrayList<Permission>();
				DetachedCriteria criteria = DetachedCriteria.forClass(Permission.class);
				for(int i=0;i<permissionIds.size();i++) {
					int permissionId=permissionIds.get(i);
					criteria.add(Restrictions.eq("permission_id", permissionId));
					List<Permission> list01 = this.permissionDao.findByCriteria(criteria);
					for(int j=0;j<list01.size();j++) {
						Permission permission=list01.get(j);
						list.add(permission);
					}
				}
				 
			    //测试
				for(int i=0;i<list.size();i++) {
					System.out.println("第"+i+"个权限名称是："+list.get(i).getPermission_name());
				}
			    if ((list == null) || (list.isEmpty())) {
			       return null;
			    }
				return list;
			}
	        
			
		}
