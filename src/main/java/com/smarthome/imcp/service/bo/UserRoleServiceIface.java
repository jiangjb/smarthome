package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface UserRoleServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	public abstract List<Integer> findListByUid(int userID);
   
}

