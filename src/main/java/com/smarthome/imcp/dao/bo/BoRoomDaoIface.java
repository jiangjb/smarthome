package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoRoom;
import java.io.Serializable;
import java.util.List;

public abstract interface BoRoomDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoRoom> getAllListByUserCode(String paramString);

  public abstract List<BoRoom> getAllListByRommCode(String paramString);

  public abstract List<BoRoom> getAllListByFloorCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoRoomDaoIface
 * JD-Core Version:    0.6.2
 */