package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoUserDevices;

import java.io.Serializable;
import java.util.List;

public abstract interface BoUserDevicesDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List<BoUserDevices> find();

	
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUserDevicesDaoIface
 * JD-Core Version:    0.6.2
 */