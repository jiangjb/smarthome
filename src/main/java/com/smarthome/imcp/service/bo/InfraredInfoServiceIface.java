package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.InfraredInfo;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface InfraredInfoServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<InfraredInfo> getBy(String string, String modelId);


   
}

