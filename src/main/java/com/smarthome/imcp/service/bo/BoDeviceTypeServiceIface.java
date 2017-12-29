package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceTypeServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<T> getList(SearchCriteria paramSearchCriteria);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoDeviceTypeServiceIface
 * JD-Core Version:    0.6.2
 */