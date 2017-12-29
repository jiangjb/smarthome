package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoHost;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoHostServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoHost> getListByUserCode(String paramString);

  public abstract List<BoHost> getListByHostCode(String paramString);

  public abstract BoHost findByHostCode(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoHost delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoHostServiceIface
 * JD-Core Version:    0.6.2
 */