package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface RolePermissionServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	public abstract List<Integer> findListByRids(List<Integer> roleIds);

	
   
}

