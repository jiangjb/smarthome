package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface ModelServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List findAllType(int deviceId);

	List findByType(String label);

	String findTestCode(int id);

	String findByLabel(String label);

	String findModelidByLabel(String label);

}

