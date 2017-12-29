package com.smarthome.imcp.service.system;

import com.smarthome.imcp.dao.model.system.SysMessage;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface SysMessageServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<SysMessage> getFirstList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.SysMessageServiceIface
 * JD-Core Version:    0.6.2
 */