package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoIosVersion;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoIosVersionServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoIosVersion findAndroidVersionById(Integer paramInteger);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoIosVersion delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoIosVersionServiceIface
 * JD-Core Version:    0.6.2
 */