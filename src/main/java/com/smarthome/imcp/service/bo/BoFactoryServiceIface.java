package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoFactory;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFactoryServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoFactory> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoFactoryServiceIface
 * JD-Core Version:    0.6.2
 */