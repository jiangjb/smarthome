package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.system.SysRoleMenus;
import java.io.Serializable;
import java.util.List;

public abstract interface SysRoleMenusDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysRoleMenus> getListByMenuRoleCode(String paramString);

  public abstract void deleteAllByMenuRoleCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysRoleMenusDaoIface
 * JD-Core Version:    0.6.2
 */