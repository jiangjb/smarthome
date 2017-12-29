package com.smarthome.imcp.service.secur;

import com.smarthome.imcp.dao.model.system.SysUser;
import com.smarthome.imcp.secur.CurrentUser;

public abstract interface SecurServiceIface
{
  public abstract String doCheckUser(SysUser paramSysUser);

  public abstract CurrentUser createCurrentUser(SysUser paramSysUser);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.secur.SecurServiceIface
 * JD-Core Version:    0.6.2
 */