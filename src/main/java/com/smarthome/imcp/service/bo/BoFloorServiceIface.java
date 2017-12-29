package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoFloor;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFloorServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoFloor> gets(String paramString);

  public abstract List<BoFloor> get(Integer paramInteger);

  public abstract List<BoFloor> get(String paramString);

  public abstract List<BoFloor> getUserCode(String paramString);

  public abstract List<BoFloor> getAllListByUserCode(String paramString);

  public abstract BoFloor findByFloorName(String paramString1, String paramString2);

  public abstract BoFloor findByFloorCode(String paramString);

  public abstract BoFloor findByUserCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoFloor delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoFloorServiceIface
 * JD-Core Version:    0.6.2
 */