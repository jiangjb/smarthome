/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.RoleDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.RoleServiceIface;
		 import com.smarthome.shiro.entity.Role;
/*    */ import java.io.Serializable;
import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("RoleService")
/*    */ public class RoleServiceImpl extends AbstractBasicService<Role, Serializable>
/*    */   implements RoleServiceIface<Role, Serializable>
/*    */ {
			@Autowired
	        private RoleDaoIface<Role, Serializable> roleDao;
	        
			@Override
			public List<Role> getListByRID(List<Integer> roleIds) {
				List<Role> list=new ArrayList<Role>();
				DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
				for(int i=0;i<roleIds.size();i++) {
					int roleId=roleIds.get(i);
					criteria.add(Restrictions.eq("role_id", roleId));
					List<Role> list01 = this.roleDao.findByCriteria(criteria);
					for(int j=0;j<list01.size();j++) {
						Role role=list01.get(j);
						list.add(role);
					}
				}
				 
			    //测试
				for(int i=0;i<list.size();i++) {
					System.out.println("第"+i+"个角色名称是："+list.get(i).getRole_name());
				}
			    if ((list == null) || (list.isEmpty())) {
			       return null;
			    }
				return list;
			}
		}
