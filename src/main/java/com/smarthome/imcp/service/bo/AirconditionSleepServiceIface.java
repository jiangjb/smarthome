package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.AirconditionSleep;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface AirconditionSleepServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<AirconditionSleep> findByrcid(int id);

	void delete(AirconditionSleep as);

   
}

