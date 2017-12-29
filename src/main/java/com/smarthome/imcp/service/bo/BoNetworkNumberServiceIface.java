package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoNetworkNumber;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoNetworkNumberServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoNetworkNumber findBy(String paramString1, String paramString2, String paramString3);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoNetworkNumber delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoNetworkNumberServiceIface
 * JD-Core Version:    0.6.2
 */