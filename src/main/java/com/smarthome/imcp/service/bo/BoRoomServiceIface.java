package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoRoom;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoRoomServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoRoom> get(Integer paramInteger);

  public abstract BoRoom findByCode(String paramString);

  public abstract BoRoom findByUserCode(String paramString);

  public abstract BoRoom findByRommCode(String paramString);

  public abstract BoRoom findByRommName(String paramString);

  public abstract List<BoRoom> getAllListByUserCode();

  public abstract List<BoRoom> getAllListByUserCode(String paramString);

  public abstract List<BoRoom> getAllListByRommCode(String paramString);

  public abstract List<BoRoom> get(String paramString);

  public abstract List<BoRoom> getAllListByFloorCode(String paramString);

  public abstract List<BoRoom> getUserCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoRoom delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoRoomServiceIface
 * JD-Core Version:    0.6.2
 */