package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoFloor;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFloorDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoFloor> getAllListByUserCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoFloorDaoIface
 * JD-Core Version:    0.6.2
 */