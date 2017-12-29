package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface SysUpdateInfoServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<T> getTheFirstThreeList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysUpdateInfoServiceIface
 * JD-Core Version:    0.6.2
 */