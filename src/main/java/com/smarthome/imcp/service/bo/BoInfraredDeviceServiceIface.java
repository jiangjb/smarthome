package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoInfraredDevice;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoInfraredDeviceServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoInfraredDevice> getListBy(String paramString, Boolean paramBoolean);

  public abstract List<BoInfraredDevice> getListBy(String paramString);

  public abstract BoInfraredDevice find(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoInfraredDevice delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoInfraredDeviceServiceIface
 * JD-Core Version:    0.6.2
 */