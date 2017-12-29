package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoUserDevices;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserDevicesServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUserDevices> getListByDeviceCode(String paramString);

  public abstract List<BoUserDevices> getListByDeviceCodes(String paramString);

  public abstract BoUserDevices findBy(String paramString1, String paramString2);

  public abstract BoUserDevices findByUserDeviceCodes(String paramString1, String paramString2);

  public abstract BoUserDevices findBy(String paramString);

  public abstract List<BoUserDevices> getBy(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoUserDevices delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUserDevicesServiceIface
 * JD-Core Version:    0.6.2
 */