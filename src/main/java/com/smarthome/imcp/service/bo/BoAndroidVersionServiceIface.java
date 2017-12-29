package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoAndroidVersion;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoAndroidVersionServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoAndroidVersion findAndroidVersionById(Integer paramInteger);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoAndroidVersion delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAndroidVersionServiceIface
 * JD-Core Version:    0.6.2
 */