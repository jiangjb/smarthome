package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import java.io.Serializable;
import java.util.List;

public abstract interface ModelDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List findAllType(int deviceId);

	List findByType(String label);

	String findTestCode(int id);

	String findByLabel(String label);

	String findModelidByLabel(String label);
	
}

