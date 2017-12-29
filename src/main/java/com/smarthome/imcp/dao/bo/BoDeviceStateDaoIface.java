package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoDeviceState;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceStateDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoDeviceState> getBydeviceAddress(String paramString);

  public abstract List<BoDeviceState> getByuserCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoDeviceStateDaoIface
 * JD-Core Version:    0.6.2
 */