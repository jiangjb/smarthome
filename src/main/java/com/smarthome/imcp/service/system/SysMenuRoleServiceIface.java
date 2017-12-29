package com.smarthome.imcp.service.system;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface SysMenuRoleServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract void save(T paramT, String paramString);

  public abstract void update(T paramT, String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.SysMenuRoleServiceIface
 * JD-Core Version:    0.6.2
 */