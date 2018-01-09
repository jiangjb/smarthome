package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import com.smarthome.shiro.entity.Permission;
import java.io.Serializable;
import java.util.List;


public abstract interface PermissionServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	public abstract List<Permission> getListByPid(List<Integer> permissionIds);

}

