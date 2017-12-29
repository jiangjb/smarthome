package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoDeviceState;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceStateServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoDeviceState findBy(String paramString);

  public abstract BoDeviceState findBy(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract List<BoDeviceState> getByuserCode(String paramString);

  public abstract List<BoDeviceState> getBydeviceAddress(String paramString);

  public abstract List<BoDeviceState> getBydeviceAddress(String paramString1, String paramString2);

  public abstract List<BoDeviceState> getListBy(String paramString);

  public abstract List<BoDeviceState> getListBy(String paramString1, String paramString2);

  public abstract BoDeviceState delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoDeviceStateServiceIface
 * JD-Core Version:    0.6.2
 */