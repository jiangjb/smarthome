package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoInfraredPart;

import java.io.Serializable;
import java.util.List;

public abstract interface BoInfraredPartDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List<BoInfraredPart> getAllInfraredPart();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoInfraredPartDaoIface
 * JD-Core Version:    0.6.2
 */