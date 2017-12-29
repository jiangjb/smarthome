package com.smarthome.imcp.service.system;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface SysKeyServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract String getPrimaryKey(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.SysKeyServiceIface
 * JD-Core Version:    0.6.2
 */