package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface DatServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List findByModelid(String modelid);

}

