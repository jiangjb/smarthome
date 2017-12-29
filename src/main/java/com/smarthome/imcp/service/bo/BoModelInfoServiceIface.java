package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoModelInfo;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoModelInfoServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoModelInfo> getBy(String paramString1, String paramString2);

  public abstract BoModelInfo find(String paramString1, String paramString2);

  public abstract List<BoModelInfo> getBys(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoModelInfo delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoModelInfoServiceIface
 * JD-Core Version:    0.6.2
 */