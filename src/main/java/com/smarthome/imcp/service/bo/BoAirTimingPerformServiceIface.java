package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoAirTimingPerform;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAirTimingPerformServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoAirTimingPerform> getAllByUser(String paramString1, String paramString2);

  public abstract BoAirTimingPerform findAirTimingPerform(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoAirTimingPerform delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAirTimingPerformServiceIface
 * JD-Core Version:    0.6.2
 */