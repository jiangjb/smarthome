package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import com.smarthome.shiro.entity.Role;
import java.io.Serializable;
import java.util.List;


public abstract interface RoleServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	public abstract List<Role> getListByRID(List<Integer> roleIds);
  
}

