package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import java.io.Serializable;
import java.util.List;

public abstract interface DatDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List<String> findByModelid(String modelid);

	
}

