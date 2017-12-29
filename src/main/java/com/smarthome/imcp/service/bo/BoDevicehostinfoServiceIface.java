package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoDevicehostinfo;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoDevicehostinfoServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoDevicehostinfo findByCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoDevicehostinfo delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoDevicehostinfoServiceIface
 * JD-Core Version:    0.6.2
 */