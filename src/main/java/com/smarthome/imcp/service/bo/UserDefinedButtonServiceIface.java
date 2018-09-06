package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.UserDefinedButton;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface UserDefinedButtonServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<UserDefinedButton> getListById(int id);

   
}

