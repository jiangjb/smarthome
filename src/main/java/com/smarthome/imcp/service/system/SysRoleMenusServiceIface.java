package com.smarthome.imcp.service.system;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface SysRoleMenusServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<T> getListByMenuRoleCode(String paramString);

  public abstract void deleteAllByMenuRoleCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.SysRoleMenusServiceIface
 * JD-Core Version:    0.6.2
 */