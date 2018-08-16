package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.InfraredTimer;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface InfraredTimerServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<InfraredTimer> getBy(String usereCode, String modelId);

	public abstract InfraredTimer delete(T paramT);
   
}

