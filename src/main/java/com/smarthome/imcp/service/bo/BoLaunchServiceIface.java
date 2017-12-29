package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoLaunch;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoLaunchServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoLaunch> getListByGroupId(Integer paramInteger1, Integer paramInteger2);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoLaunchServiceIface
 * JD-Core Version:    0.6.2
 */